package Patterns;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by happy on 13.01.2017.
 */
public class InputDataFlyweight {
    private static Map<String,InputDataConverter> fileExtensionToInputMap = new HashMap<String,InputDataConverter>();

    private static String getFileExtension(File file){
        String path = file.getPath();
        return path.substring(path.lastIndexOf('.')+1);
    }

    public static InputDataConverter getInputConverter(File file){
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
