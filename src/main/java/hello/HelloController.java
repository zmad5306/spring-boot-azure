package hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class HelloController {
	
	@Autowired
	private PetRepository petRepo;
    
	//curl https://spring-boot-azure.azure-api.net/pets/ -H "Ocp-Apim-Subscription-Key: <key here>"
    @RequestMapping(value="/", produces=MimeTypeUtils.APPLICATION_JSON_VALUE)
    public List<Pet> pets() {
        List<Pet> pets = petRepo.findAll();
        return pets;
    }
    
    // curl -F file=@"src\main\resources\pets.csv" https://spring-boot-azure.azure-api.net/pets/upload -H "Ocp-Apim-Subscription-Key: <key here>"
    @PostMapping(value="/upload", consumes=MimeTypeUtils.MULTIPART_FORM_DATA_VALUE)
    public void upload(@RequestParam("file") MultipartFile file) throws IOException {
    	InputStream in = file.getInputStream();
    	BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    	String line;
    	while ((line = reader.readLine()) != null) {
    		String[] elements = line.split(",");
    		Pet p = new Pet(elements[0]);
    		petRepo.save(p);
    	}
    }
    
}
