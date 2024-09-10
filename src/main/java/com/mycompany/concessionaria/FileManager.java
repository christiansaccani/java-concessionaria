package com.mycompany.concessionaria;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileManager implements AutoCloseable {

    private BufferedWriter fileOutputAZ;
    private List<InputRecord> inputRecords1;
    private Map<String, String> descriptionMap = new HashMap<>();
    private String outputFileAZPath;

    public FileManager(String outputFilePath, String outputFileAZPath) {
        this.outputFileAZPath = outputFileAZPath;
    }

    public void setInputData1(List<InputRecord> data) {
        this.inputRecords1 = data;
    }

    public void setInputData2(List<InputRecord> data) {
        // Costruisci una mappa per le descrizioni per un accesso rapido
        this.descriptionMap = data.stream()
                                  .collect(Collectors.toMap(InputRecord::getKey, InputRecord::getDescrizione));
    }

    public void openFiles() throws IOException {
        if (outputFileAZPath != null) {
            fileOutputAZ = new BufferedWriter(new FileWriter(outputFileAZPath));
        }
    }

    @Override
    public void close() throws IOException {
        if (fileOutputAZ != null) {
            fileOutputAZ.close();
        }
    }

    public String getDescriptionForKey(String key) {
        return descriptionMap.get(key);
    }

    public void writeToOutputFileAZ(OutputRecord record) throws IOException {
        if (fileOutputAZ != null) {
            fileOutputAZ.write(record.toString());
            fileOutputAZ.newLine();
        }
    }
}
