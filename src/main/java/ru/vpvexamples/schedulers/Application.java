package ru.vpvexamples.schedulers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

/**
 * java -jar <filename of jar> --spring.config.location=file:./app.yml
 *
 * --spring.config.location=file:./app.yml
 */

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static Logger log = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(Application.class, args);
    }

    //access command line arguments
    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n\nPress ENTER for exit");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        System.exit(0);
    }
}
