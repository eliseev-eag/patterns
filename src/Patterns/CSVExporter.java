package Patterns;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Елисеев on 08.01.2017.
 */
public class CSVExporter implements Exporter {
    @Override
    public void Export(File file, TableUserData table) throws IOException {
        FileWriter fileWriter = null;
        CSVPrinter csvWriter = null;
        try {
            fileWriter = new FileWriter(file);
            csvWriter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            List<String> sortHeaders = table.getSortHeaders();
            csvWriter.printRecord(sortHeaders);
            for (RowUserData row : table.getUserData()) {
                csvWriter.printRecord(row.getValues());
            }
        } finally {
            fileWriter.flush();
            fileWriter.close();
            csvWriter.close();
        }
    }
}
