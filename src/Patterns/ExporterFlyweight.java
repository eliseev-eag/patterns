package Patterns;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by happy on 13.01.2017.
 */
public class ExporterFlyweight {
    private static ExporterFlyweight instance;

    private Map<String,Exporter> fileExtensionToExporterMap = new HashMap<String,Exporter>();

    private ExporterFlyweight(){};
    public static ExporterFlyweight getInstance() {
        if(instance == null)
            instance = new ExporterFlyweight();
        return instance;
    }

    private String getFileExtension(File file){
        String path = file.getPath();
        return path.substring(path.lastIndexOf('.')+1);
    }

    public Exporter getExporter(File file){
        String extension = getFileExtension(file);
        if(fileExtensionToExporterMap.containsKey(extension))
            return fileExtensionToExporterMap.get(extension);
        else {
            switch (extension){
                case "csv":{
                    Exporter csvExporter = new CSVExporter();
                    fileExtensionToExporterMap.put(extension,csvExporter);
                    return csvExporter;
                }
                default:
                    throw new IllegalArgumentException();
            }
        }
    }
}
