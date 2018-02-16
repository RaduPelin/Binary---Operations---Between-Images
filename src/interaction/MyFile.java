package interaction;
import java.nio.file.Path;
import java.nio.file.Paths;
import exception.NotTheRightImageException;

public class MyFile {
	
	private String type;
	private Path path;
	
	public MyFile(String type, String pathName) {
		setType(type);
		setPath(pathName);
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

	public void setPath(String pathName) {
		try{
			if (!pathName.contains(type)) {
				throw new NotTheRightImageException(type);
			}else {
				this.path = Paths.get(pathName);
			}
		} catch (NotTheRightImageException e) {
			System.out.println(e.getMessage());
		}
	}
		
	public Path getPath() {
		return path;
	}	
}
	
	 
	


