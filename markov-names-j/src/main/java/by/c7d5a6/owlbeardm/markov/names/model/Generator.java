/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.c7d5a6.owlbeardm.markov.names.model;

import by.c7d5a6.owlbeardm.markov.names.util.Constants;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author c7d5a6
 */
public class Generator {

    private final LanguageModel languageModel;

    public Generator(LanguageModel languageModel) {
        this.languageModel = languageModel;
    }

    public String generate() {
        final int n = languageModel.getN();
        final List<Character> symbols = languageModel.getSymbols();
        String word = "";
        for (int i = 1; i < n; i++) {
            word = word + Constants.BORDER_SYMBOL;
        }
        do {
            Map<Character, BigDecimal> map = new HashMap<>(symbols.size());
            String context = word.substring(word.length() - n + 1, word.length());
            symbols.forEach(s -> map.put(s, languageModel.getProbability(s, context)));
            final BigDecimal probSum = symbols.stream().map((s) -> map.get(s)).reduce(BigDecimal.ZERO, (l, c) -> l.add(c));
            if (probSum.compareTo(BigDecimal.ZERO) > 0) {
                symbols.forEach(s -> map.put(s, map.get(s).divide(probSum, MathContext.DECIMAL128)));
            }
            BigDecimal k = BigDecimal.ZERO;
            BigDecimal r = BigDecimal.valueOf(Math.random());
            boolean added = false;
            for (int i = 0; i < symbols.size(); i++) {

                k = k.add(map.get(symbols.get(i)));
                if (r.compareTo(k) <= 0) {
                    word = word + symbols.get(i);
                    added = true;
                    break;
                }
            }
            if (!added) {
                word = word + Constants.BORDER_SYMBOL;
            }
        } while (!Constants.BORDER_SYMBOL.equals(word.charAt(word.length() - 1)) || word.length() >= Constants.MAX_LENGTH);
        if (word.length() >= Constants.MAX_LENGTH) {
            System.out.println("[Generate] max length: " + word);
        }
        return word;

    }

}
