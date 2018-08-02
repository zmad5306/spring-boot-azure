package hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@Autowired
	private PetRepository petRepo;
    
    @RequestMapping(value="/", produces=MimeTypeUtils.APPLICATION_JSON_VALUE)
    public List<Pet> pets() {
        List<Pet> pets = petRepo.findAll();
        return pets;
    }
    
}
