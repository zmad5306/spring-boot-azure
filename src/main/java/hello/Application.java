package hello;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.microsoft.azure.storage.CloudStorageAccount;

@SpringBootApplication
public class Application {
	
	@Value("${azure.storage.connection-string}")
	public String connectionString;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }
    
    @Bean
    public CloudStorageAccount cloudStorageAccount() throws InvalidKeyException, URISyntaxException {
    	return CloudStorageAccount.parse(connectionString);
    }

}
