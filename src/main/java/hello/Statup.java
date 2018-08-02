package hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Statup {
	
	@Autowired
	private PetRepository petRepo;
	
	@Autowired
	private StorageService storageService;
	
	@PostConstruct
	public void init() throws IOException {
		Optional<InputStream> stream = storageService.openObject("pets.csv");
		if (stream.isPresent()) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream.get()));
			String line;
			while ((line = reader.readLine()) != null) {
				petRepo.save(new Pet(line));
			}
		}
		System.out.println("Created pets..............");
	}

}
