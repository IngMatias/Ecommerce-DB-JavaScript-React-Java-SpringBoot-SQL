
package Utils;

import java.util.HashMap;
import java.util.Map;

public class LoadConfig {
    public static Map<String, String> getConfig(String path) {
        Map<String, String> config = new HashMap<>();
        String[] file = LoadFile.getFile(path);
        for (String line: file) {
            String[] command = line.split(" ");
            config.put(command[0].trim(), command[1].trim());
        }
        return config;
    }
}
