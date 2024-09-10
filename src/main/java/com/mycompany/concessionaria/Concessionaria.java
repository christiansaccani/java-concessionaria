package com.mycompany.concessionaria;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Concessionaria {

    public static void main(String[] args) {
        // Creazione dei dati di input
        List<InputRecord> inputRecords1 = List.of(
            new InputRecord("CAR001", "Toyota", "Corolla", ""),
            new InputRecord("CAR002", "Honda", "Civic", ""),
            new InputRecord("CAR003", "Ford", "Mustang", ""),
            new InputRecord("CAR004", "Chevrolet", "Malibu", ""),
            new InputRecord("CAR001", "Toyota", "Camry", ""),
            new InputRecord("CAR002", "Honda", "Accord", ""),
            new InputRecord("CAR003", "Ford", "F-150", ""),
            new InputRecord("CAR004", "Chevrolet", "Impala", ""),
            new InputRecord("CAR001", "Toyota", "RAV4", ""),
            new InputRecord("CAR002", "Honda", "Pilot", "")
        );

        List<InputRecord> inputRecords2 = List.of(
            new InputRecord("CAR001", "", "", "Utilitaria"),
            new InputRecord("CAR002", "", "", "Compatta"),
            new InputRecord("CAR003", "", "", "Sportiva"),
            new InputRecord("CAR004", "", "", "Sedan")
        );

        // Creazione della mappa per descrizioni
        Map<String, String> descriptionMap = new HashMap<>();
        for (InputRecord record : inputRecords2) {
            descriptionMap.put(record.getKey(), record.getDescrizione());
        }

        // Inizializza FileManager con il percorso per il file di output
        try (FileManager fileManager = new FileManager(null, "ListaAutoAZ.txt")) {
            fileManager.setInputData1(inputRecords1);
            fileManager.setInputData2(inputRecords2);
            fileManager.openFiles();

            List<OutputRecord> outputRecords = new ArrayList<>();

            // Processa i record dalla prima lista
            for (InputRecord record1 : inputRecords1) {
                String description = descriptionMap.get(record1.getKey());
                if (description != null) {
                    OutputRecord outputRecord = new OutputRecord(
                        record1.getMarca(), record1.getModello(), description);
                    outputRecords.add(outputRecord);
                    // Scrivi su file e stampa su console
                    fileManager.writeToOutputFileAZ(outputRecord);
                    System.out.println(outputRecord);
                }
            }

            // Ordinare i record e scrivere su ListaAutoAZ.txt
            outputRecords.sort(Comparator
                .comparing(OutputRecord::getDescrizione)
                .thenComparing(OutputRecord::getMarca)
                .thenComparing(OutputRecord::getModello));

            // Riapriamo il file per scrivere i dati ordinati
            fileManager.openFiles(); // Riapre il file per scrivere i dati ordinati
            for (OutputRecord record : outputRecords) {
                fileManager.writeToOutputFileAZ(record);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
