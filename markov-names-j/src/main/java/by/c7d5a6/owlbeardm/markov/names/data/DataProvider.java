/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.c7d5a6.owlbeardm.markov.names.data;

import java.util.List;

/**
 *
 * @author c7d5a6
 */
public class DataProvider {

    static private DataProvider dataProvider;

    private List<String> data;

    static DataProvider getInstance() {
        if (dataProvider == null) {
            dataProvider = new DataProvider();
//            dataProvider.init(new FileDataLoader(file));
        }
        return dataProvider;
    }

    public void init(DataLoader loader) {
//        this.data = loader.loadData();
    }

}
