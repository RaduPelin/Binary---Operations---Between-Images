package exception;
//Folosim această excepție pentru a nu încărca o imagine ce nu este de tipul acceptat de program.
public class NotTheRightImageException extends Exception {
    
	private static final long serialVersionUID = 1L;

	public NotTheRightImageException(String type){
    	super("The image must be a " + type);
    }
}
