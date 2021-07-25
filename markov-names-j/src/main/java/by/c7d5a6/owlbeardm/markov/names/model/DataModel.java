/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.c7d5a6.owlbeardm.markov.names.model;

import by.c7d5a6.owlbeardm.markov.names.util.Constants;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author c7d5a6
 */
public class DataModel {

    private final List<Character> characters;
    private final List<String> data;
    private final Map<String, BigInteger> count;
    private final Integer dataLength;

    public DataModel(List<Character> characters, List<String> data) {
        this.characters = characters;
        this.characters.add(Constants.BORDER_SYMBOL);
        this.data = data;
        this.dataLength = data.stream().map((w) -> w.length()).reduce(0, (l, c) -> l + c);
        this.count = new HashMap<>();
    }

    public List<Character> getSymbols() {
        return characters;
    }

    public BigInteger getCount(String x) {
        if (x.length() <= 0) {
            return BigInteger.ZERO;
        }
        if (!count.containsKey(x)) {
            BigInteger c = BigInteger.ZERO;
            count.put(x, c);
            data.forEach(rightWord -> {

                String word = "";
                for (int i = 1; i < x.length(); i++) {
                    word += Constants.BORDER_SYMBOL;
                }
                word += rightWord + Constants.BORDER_SYMBOL;
                final int to = word.length() - x.length() + 1;
                for (int i = 0; i < to; i++) {
                    if (word.startsWith(x, i)) {
                        count.put(x, count.get(x).add(BigInteger.ONE));
                    }
                }
            });
        }
        return count.get(x);
    }

    public BigInteger getDataLength(int n) {
        return BigInteger.valueOf(dataLength + data.size() * n);
    }

    public DataModel add(DataModel dm) {
        final Set<Character> symbols = new LinkedHashSet<>();
        symbols.addAll(characters);
        symbols.addAll(dm.characters);
        List<String> list = new ArrayList<>(dm.data.size() + data.size());
        list.addAll(data);
        list.addAll(dm.data);
        return new DataModel(symbols.stream().collect(Collectors.toList()), list);
    }

}
