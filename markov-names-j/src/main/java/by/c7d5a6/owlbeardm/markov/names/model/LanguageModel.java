/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.c7d5a6.owlbeardm.markov.names.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author c7d5a6
 */
public class LanguageModel {

    private final DataModel dataModel;
    private LanguageModel prevLanguageModel;
    private final int n;
    private final BigInteger variants;

    public LanguageModel(DataModel dataModel, int n) {
        Objects.requireNonNull(dataModel, "DataModel should be not null");
        if (n < 0) {
            throw new RuntimeException("n should be >= 0");
        }

        if (n > 1) {
            this.prevLanguageModel = new LanguageModel(dataModel, n - 1);
        }
        this.dataModel = dataModel;
        this.n = n;
        this.variants = n == 0 ? BigInteger.ONE : BigInteger.valueOf(dataModel.getSymbols().size()).pow(n);
    }

    public BigDecimal getProbability(Character c, String context) {
        return getMLEProbability(c, context);
    }

    public BigDecimal getMLEProbability(Character c, String context) {
        final BigInteger count = dataModel.getCount(context + c);
        return new BigDecimal(count).divide(new BigDecimal(dataModel.getDataLength(n)), MathContext.DECIMAL128);
    }

    public int getN() {
        return n;
    }

    public List<Character> getSymbols() {
        return dataModel.getSymbols();
    }

}
