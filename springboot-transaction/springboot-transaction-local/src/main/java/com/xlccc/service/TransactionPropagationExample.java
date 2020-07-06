package com.xlccc.service;

public interface TransactionPropagationExample {

    void truncated();

    void no_transaction_required_required();

    void no_transaction_exception_required_required();

    void no_transaction_required_required_exception();




    void transaction_exception_required_required();

}
