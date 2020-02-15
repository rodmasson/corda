package com.template.contracts;

import net.corda.core.contracts.CommandData;

public interface PropertyCommand extends CommandData {
    class Criar implements  PropertyCommand {}
    class Transferir implements PropertyCommand{}
    class AprovacaoBanco implements PropertyCommand{}

}
