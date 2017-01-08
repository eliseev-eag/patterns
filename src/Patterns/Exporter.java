package Patterns;

import java.io.File;
import java.io.IOException;

/**
 * Created by Елисеев on 08.01.2017.
 */
public interface Exporter {
    void Export(File file,TableUserData table) throws IOException;
}
