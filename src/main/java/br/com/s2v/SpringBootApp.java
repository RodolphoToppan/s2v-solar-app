package br.com.s2v;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

import static org.springframework.boot.SpringApplication.run;

/**
 * Top-level class - Spring Boot Application starter
 * Spring Boot and Java - Template / Human Resources Application
 */
@SpringBootApplication
@EnableRetry
public class SpringBootApp {

    public static final long STARTUP_TIMESTAMP = System.currentTimeMillis();

    /**
     * Spring Boot Java Template - Human Resources Application
     *
     * @param args the amount of incoming damage
     */
    public static void main(String[] args) {
        run(SpringBootApp.class, args);
    }

}
