package program.SyncServer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SyncServerApplication {
    @Bean
    public DataBaseManager dataBaseManager() {
        return new DataBaseManager();
    }

    @Bean
    public CommandLineRunner init(DataBaseManager dataBaseManager) {
        return args -> {
            dataBaseManager.connect();
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SyncServerApplication.class, args);
    }
}