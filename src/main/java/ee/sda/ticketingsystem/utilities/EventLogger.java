package ee.sda.ticketingsystem.utilities;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventLogger {
    @Value("${DB_USER}")
    private String dbUser;

    @PostConstruct
    public void logEnvVars() {
        System.out.println("DB_USER: " + dbUser);
    }
}
