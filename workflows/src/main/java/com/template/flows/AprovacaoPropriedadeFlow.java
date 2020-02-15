package com.template.flows;

import co.paralleluniverse.fibers.Suspendable;
import com.google.common.collect.ImmutableList;
import com.template.states.PropriedadeState;
import jdk.nashorn.internal.ir.annotations.Immutable;
import net.corda.core.contracts.StateAndRef;
import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.flows.FlowException;
import net.corda.core.flows.FlowLogic;
import net.corda.core.identity.Party;
import net.corda.core.node.services.vault.QueryCriteria;
import net.corda.core.transactions.SignedTransaction;
import net.corda.core.utilities.ProgressTracker;

public class AprovacaoPropriedadeFlow extends FlowLogic<SignedTransaction> {
    private final ProgressTracker progressTracker = new ProgressTracker();
    private final UniqueIdentifier linearId;
    private final boolean  isAprovado;

    public AprovacaoPropriedadeFlow(UniqueIdentifier linearId, boolean isAprovado) {
        this.linearId = linearId;
        this.isAprovado = isAprovado;
    }

    @Suspendable
    public SignedTransaction call() throws FlowException {
        QueryCriteria queryCriteria = new QueryCriteria.LinearStateQueryCriteria(null, ImmutableList.of(linearId.getId()));
        StateAndRef<PropriedadeState> inputStateAndRef = getServiceHub().getVaultService().queryBy(PropriedadeState.class,queryCriteria).getStates().get(0);

        Party proprietario = inputStateAndRef.getState().getData().getProprietario();
        return null;
    }
}
