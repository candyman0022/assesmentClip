package com.backend;

import com.backend.interfaces.Adder;
import com.backend.interfaces.Calculate;
import com.backend.interfaces.Lister;
import com.backend.interfaces.Searcher;
import com.google.inject.Inject;

public class TransactionService {

    private final Adder adder;
    private final Searcher searcher;
    private final Lister lister;
    private final Calculate calculate;

    @Inject
    public TransactionService(Adder adder, Searcher searcher, Lister lister, Calculate calculate) {
        this.adder = adder;
        this.searcher = searcher;
        this.lister = lister;
        this.calculate = calculate;
    }

    Adder getAdder() {
        return adder;
    }

    Searcher getSearcher() {
        return searcher;
    }

    Lister getLister() {
        return lister;
    }

    Calculate getCalculate() {
        return calculate;
    }
}
