/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.c7d5a6.owlbeardm.markov.names.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author c7d5a6
 */
public class FileDataLoader extends DataLoader {

    private String fileName;

    public FileDataLoader(String file) {
        this.fileName = file;
    }

    @Override
    public List<String> loadData() throws DataLoaderException {
        List<String> result = new ArrayList<>();
        try ( Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(line -> result.add(line));
        } catch (IOException ex) {
            throw new DataLoaderException(ex);
        }
        return result;
    }

}
