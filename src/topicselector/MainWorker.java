/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package topicselector;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author Rustem
 */
public class MainWorker {
    private String fromPath;
    private String toPath;
    private List<String> topics;
    private String[] names;
    private ReadWriter readWriter;
    private SecureRandom random;
    public MainWorker(String fromPath, String toPath) throws IOException, FileNotFoundException, InvalidFormatException, NotExcelFileException {
        //Initialize fields that are for specifing source and destination file pathes
        this.fromPath = fromPath;
        this.toPath = toPath;
        this.readWriter = new ReadWriter();
        this.random = new SecureRandom();
        work();
    }

    private void work() throws IOException, FileNotFoundException, NotExcelFileException, InvalidFormatException {
        initTopicsAndNames();
        this.readWriter.uploadToFile(this.toPath, prepareMatrixForOutput());
    }

    private void initTopicsAndNames() throws IOException {
        String[][] data = this.readWriter.readMatrixFromFile(this.fromPath);
        this.names = new String[data.length];
        this.topics = new ArrayList<>(data.length);
        for (int i = 0; i < this.names.length; i++) {
            this.names[i] = data[i][0];
            this.topics.add(data[i][1]);
        }
    }
    
    private String[][] prepareMatrixForOutput() {
        String[][] data = new String[this.names.length][2];
        for (int i = 0; i < data.length; i++) {
            data[i][0] = this.names[i];
            int index = nextIndex();
            data[i][1] = this.topics.get(index);
            this.topics.remove(index);
        }
        return data;
    }
    private int nextIndex(){
        return (this.random.nextInt(1_000_000_000) % this.topics.size());
    }
}
