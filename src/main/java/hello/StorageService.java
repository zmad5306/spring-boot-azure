package hello;

import java.io.InputStream;
import java.util.Optional;

public interface StorageService {
	
	public Optional<InputStream> openObject(String name);

}
