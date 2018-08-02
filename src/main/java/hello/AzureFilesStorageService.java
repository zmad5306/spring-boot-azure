package hello;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.file.CloudFile;
import com.microsoft.azure.storage.file.CloudFileClient;
import com.microsoft.azure.storage.file.CloudFileDirectory;
import com.microsoft.azure.storage.file.CloudFileShare;

@Service
public class AzureFilesStorageService implements StorageService {
	
	@Value("${azure.storage.share-name}")
	private String shareName;		
	
	@Autowired
	private CloudStorageAccount cloudStorageAccount;

	@Override
	public Optional<InputStream> openObject(String name) {
		CloudFileClient client = cloudStorageAccount.createCloudFileClient();
		CloudFileShare share;
		CloudFileDirectory rootDir;
		CloudFile file;
		InputStream result = null;
		try {
			share = client.getShareReference(shareName);
			rootDir = share.getRootDirectoryReference();
			file = rootDir.getFileReference(name);
			if (file.exists()) {
				result = file.openRead();
			}
		} catch (StorageException e) {
			throw new RuntimeException(e);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
		
		return Optional.of(result);
	}

}
