
package Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadFile {
    public static String[] getFile(String path) {
        FileReader fr;
        //InputStream fr;
        ArrayList<String> lines = new ArrayList<>();
        try {
            fr = new FileReader(path);
            //fr = LoadFile.class.getResourceAsStream(path);
            BufferedReader br = new BufferedReader(fr);
            String index = br.readLine();
            while (index != null) {
                lines.add(index);
                index = br.readLine();
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error de path al leer el archivo " + path);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo " + path);
        }
        return lines.toArray(new String[0]);
    }
}
