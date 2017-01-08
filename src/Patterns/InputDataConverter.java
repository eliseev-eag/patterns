package Patterns;

import java.io.File;
import java.io.IOException;

/**
 * Created by Елисеев on 04.01.2017.
 */
public interface InputDataConverter {
    TableUserData Convert(File file) throws IOException;
}
