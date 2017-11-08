package domain;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
public class FileUpload {
	
	 private List<MultipartFile> uploadedFiles;

	public List<MultipartFile> getUploadedFiles() {
		return uploadedFiles;
	}

	public void setUploadedFiles(List<MultipartFile> uploadedFiles) {
		this.uploadedFiles = uploadedFiles;
	}
	 
	 

}
