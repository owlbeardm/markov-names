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
public abstract class DataLoader {

    public abstract List<String> loadData() throws DataLoaderException;
}
