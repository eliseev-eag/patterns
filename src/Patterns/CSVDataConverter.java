package Patterns;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.*;

/**
 * Created by Елисеев on 04.01.2017.
 */
public class CSVDataConverter implements InputDataConverter {
    @Override
    public TableUserData Convert(File file) throws IOException {

        Reader in = new FileReader(file);
        CSVParser records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        final Map<String, Integer> headerMap = records.getHeaderMap();

        List<RowUserData> userData = new ArrayList<>(headerMap.size());

        for (CSVRecord record : records) {
            String[] row = new String[record.size()];
            for (int i = 0; i < record.size(); i++) {
                row[i] = record.get(i);
            }
            userData.add(new RowUserData(Arrays.asList(row)));
        }
        return new TableUserData(headerMap, userData);
    }

}
