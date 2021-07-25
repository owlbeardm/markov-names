package by.c7d5a6.owlbeardm.markov.names;

import by.c7d5a6.owlbeardm.markov.names.model.DataModel;
import by.c7d5a6.owlbeardm.markov.names.model.Generator;
import by.c7d5a6.owlbeardm.markov.names.model.LanguageModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author c7d5a6
 */
public class NameGenerator {

    public static void main(String[] args) {
        int n = 1;
        Character[] smbls = {'a', 'b', 'c'};
        String[] data = {"abac",
            "cababa",
            "babac",
            "bababaca",
            "abacab",
            "bacab",
            "babac",
            "cabab",
            "cabababa"};
        final List<Character> symbols = new LinkedHashSet<>(Arrays.asList(smbls)).stream().collect(Collectors.toList());
        final DataModel dataModel = new DataModel(symbols, Arrays.asList(data));
        final LanguageModel languageModel = new LanguageModel(dataModel, n);
        final Generator generator = new Generator(languageModel);
        for (int i = 0; i < 10; i++) {
            System.out.println(generator.generate());
        }
    }

}
