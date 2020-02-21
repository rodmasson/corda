package com.template.webserver;

import com.template.flows.InicializaPropriedadeFlow;
import com.template.flows.PropriedadeBean;
import com.template.states.PropriedadeState;
import net.corda.core.contracts.StateAndRef;
import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.identity.Party;
import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.node.services.Vault;
import net.corda.core.node.services.vault.QueryCriteria;
import net.corda.core.transactions.SignedTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Define your API endpoints here.
 */
@RestController
@RequestMapping("/") // The paths for HTTP requests are relative to this base path.
public class Controller {
    private final CordaRPCOps proxy;
    private final static Logger logger = LoggerFactory.getLogger(Controller.class);

    public Controller(NodeRPCConnection rpc) {
        this.proxy = rpc.proxy;
    }

    @GetMapping(value = "/templateendpoint", produces = "text/plain")
    private String templateendpoint() {return "Define an endpoint here."; }

    @GetMapping (value = "/buscarTodasPropriedades", produces = "application/json")
    public List<StateAndRef<PropriedadeState>> buscaTodasPropriedades(){
        return proxy.vaultQuery(PropriedadeState.class).getStates();
    }

    @GetMapping(value="/buscarPropriedadePorId", produces = "application/json")
    public List<StateAndRef<PropriedadeState>> buscarPropriedadePorID(@QueryParam("id") String idString){
        UniqueIdentifier linearId = UniqueIdentifier.Companion.fromString(idString);
        List<UniqueIdentifier> linearIds = new ArrayList<>();
        linearIds.add(linearId);

        QueryCriteria linearCriteriaAll = new QueryCriteria.LinearStateQueryCriteria(
                null,
                linearIds,
                Vault.StateStatus.ALL,
                null
        );

        return proxy.vaultQueryByCriteria(linearCriteriaAll,PropriedadeState.class).getStates();
    }

    @PostMapping(value = "/inicializaPropriedadeTransacao", consumes = "application/json", produces = "application/json")
    public Response inicializaPropriedadeTransacao(@RequestBody PropriedadeBean propriedadeBean) throws ExecutionException, InterruptedException {
        Party proprietario = proxy.partiesFromName(propriedadeBean
                .getProprietario(), false).iterator().next();
        try {
            final SignedTransaction signedTx = proxy.startFlowDynamic(InicializaPropriedadeFlow.class,
                    propriedadeBean.getPropriedadeId(),
                    propriedadeBean.getPropriedadeEndereco(),
                    propriedadeBean.getPropriedadepreco(),
                    propriedadeBean.getCompradorId(),
                    propriedadeBean.getVendedorId(),
                    propriedadeBean.getIsAprovacaoEngenheiro(),
                    propriedadeBean.getIsHipoteca(),
                    proprietario).getReturnValue().get();

            final String msg = String.format("Transacao com Sucesso!", signedTx.getId());
            return Response.status(Response.Status.CREATED).entity(msg).build();

        } catch (Throwable ex) {
            final String msg = ex.getMessage();
            return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
        }
    }
}