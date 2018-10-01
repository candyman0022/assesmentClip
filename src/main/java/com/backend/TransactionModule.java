package com.backend;

import com.backend.model.TransactionAdderFileSystem;
import com.backend.interfaces.Adder;
import com.backend.interfaces.Calculate;
import com.backend.interfaces.Lister;
import com.backend.interfaces.Searcher;
import com.backend.model.ListTransactionFileSystem;
import com.backend.model.LookUpTransactionFileSystem;
import com.backend.model.SumTransactionFileSystem;
import com.google.inject.AbstractModule;

public class TransactionModule extends AbstractModule {
    @Override
    protected void configure() {

        bind(Adder.class).to(TransactionAdderFileSystem.class);
        bind(Searcher.class).to(LookUpTransactionFileSystem.class);
        bind(Lister.class).to(ListTransactionFileSystem.class);
        bind(Calculate.class).to(SumTransactionFileSystem.class);
    }
}
