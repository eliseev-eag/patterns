package Patterns;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by happy on 13.01.2017.
 */
public class InputDataFlyweight {
    private static InputDataFlyweight instance;
    private Map<String,InputDataConverter> fileExtensionToInputMap = new HashMap<String,InputDataConverter>();

    private InputDataFlyweight(){}
    public static InputDataFlyweight getInstance() {
        if(instance == null)
            instance = new InputDataFlyweight();
        return instance;
    }

    private String getFileExtension(File file){
        String path = file.getPath();
        return path.substring(path.lastIndexOf('.')+1);
    }

    public InputDataConverter getInputConverter(File file){
        String extension = getFileExtension(file);
        if(fileExtensionToInputMap.containsKey(extension))
            return fileExtensionToInputMap.get(extension);
        else {
            switch (extension){
                case "csv":{
                    InputDataConverter csvConverter = new CSVDataConverter();
                    fileExtensionToInputMap.put(extension,csvConverter);
                    return csvConverter;
                }
                default:
                    throw new IllegalArgumentException();
            }
        }
    }
}
