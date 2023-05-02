package Main;

import Database.Database;
import Utils.LoadConfig;
import java.util.Collections;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarketplaceApplication {
    public static void main(String[] args) {
        Map<String, String> config = LoadConfig.getConfig(".env");
        Database.initDatabase();
        SpringApplication app = new SpringApplication(MarketplaceApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", config.get("apiPort")));
        app.run(args);
    }
}
