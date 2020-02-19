package com.template.flows;

import co.paralleluniverse.fibers.Suspendable;
import net.corda.core.flows.FinalityFlow;
import net.corda.core.flows.FlowException;
import net.corda.core.flows.FlowLogic;
import net.corda.core.transactions.SignedTransaction;
import net.corda.core.transactions.TransactionBuilder;

public class VerificaAssinaturaEFinalizaFlow extends FlowLogic<SignedTransaction> {
    private final TransactionBuilder transactionBuilder;

    public VerificaAssinaturaEFinalizaFlow(TransactionBuilder builder) {
        this. transactionBuilder = builder;
    }

    @Suspendable
    public SignedTransaction call() throws FlowException {
        transactionBuilder.verify(getServiceHub());
        SignedTransaction signedTransaction = getServiceHub().signInitialTransaction(transactionBuilder);
        return subFlow(new FinalityFlow(signedTransaction));

    }
}

