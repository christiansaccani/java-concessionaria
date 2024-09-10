import com.mycompany.esamereverse.EsameReverse;
import com.mycompany.esamereverse.FileManager;
import com.mycompany.esamereverse.InputRecord;
import com.mycompany.esamereverse.OutputRecord;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EsameReverseTest {

    @Test
    public void testEsameReverse() throws Exception {
        // Cattura l'output su console
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Setup FileManager e dati
        FileManager fileManager = new FileManager(null, "ListaAutoAZ.txt");

        InputRecord record1 = new InputRecord("CAR001", "Toyota", "Corolla", "");
        InputRecord record2 = new InputRecord("CAR002", "Honda", "Civic", "");
        InputRecord record3 = new InputRecord("CAR003", "Ford", "Mustang", "");
        InputRecord record4 = new InputRecord("CAR004", "Chevrolet", "Malibu", "");
        InputRecord record5 = new InputRecord("CAR001", "Toyota", "Camry", "");
        InputRecord record6 = new InputRecord("CAR002", "Honda", "Accord", "");
        InputRecord record7 = new InputRecord("CAR003", "Ford", "F-150", "");
        InputRecord record8 = new InputRecord("CAR004", "Chevrolet", "Impala", "");
        InputRecord record9 = new InputRecord("CAR001", "Toyota", "RAV4", "");
        InputRecord record10 = new InputRecord("CAR002", "Honda", "Pilot", "");

        InputRecord record11 = new InputRecord("CAR001", "", "", "Utilitaria");
        InputRecord record12 = new InputRecord("CAR002", "", "", "Compatta");
        InputRecord record13 = new InputRecord("CAR003", "", "", "Sportiva");
        InputRecord record14 = new InputRecord("CAR004", "", "", "Sedan");

        fileManager.setInputData1(List.of(record1, record2, record3, record4, record5, record6, record7, record8, record9, record10));
        fileManager.setInputData2(List.of(record11, record12, record13, record14));

        fileManager.openFiles();

        // Esegui il metodo main di EsameReverse
        EsameReverse.main(new String[]{});

        // Ripristina l'output originale
        System.setOut(originalOut);

        // Verifica l'output su console
        String expectedConsoleOutput = "Toyota,Corolla,Utilitaria\n" +
                                       "Toyota,Camry,Utilitaria\n" +
                                       "Toyota,RAV4,Utilitaria\n" +
                                       "Honda,Civic,Compatta\n" +
                                       "Honda,Accord,Compatta\n" +
                                       "Honda,Pilot,Compatta\n" +
                                       "Ford,Mustang,Sportiva\n" +
                                       "Ford,F-150,Sportiva\n" +
                                       "Chevrolet,Malibu,Sedan\n" +
                                       "Chevrolet,Impala,Sedan\n";
        assertEquals(expectedConsoleOutput, outContent.toString());

        // Verifica i contenuti del file ordinato
        String outputFileAZContent = Files.readString(Paths.get("ListaAutoAZ.txt"));
        String expectedFileAZContent = "Honda,Accord,Compatta\n" +
                                        "Honda,Civic,Compatta\n" +
                                       "Honda,Pilot,Compatta\n" +
                                       "Toyota,Camry,Utilitaria\n" +
                                       "Toyota,Corolla,Utilitaria\n" +
                                       
                                       "Toyota,RAV4,Utilitaria\n" +
                                       "Ford,F-150,Sportiva\n" +
                                       "Ford,Mustang,Sportiva\n" +
                                       "Chevrolet,Impala,Sedan\n" +
                                       "Chevrolet,Malibu,Sedan\n";
                                       
        assertEquals(expectedFileAZContent, outputFileAZContent);
    }
}
