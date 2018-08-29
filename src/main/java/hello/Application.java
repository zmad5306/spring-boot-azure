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
    public CloudStorageAccount cloudStorageAccount() throws InvalidKeyException, URISyntaxException {
    	return CloudStorageAccount.parse(connectionString);
    }

}
