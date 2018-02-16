package exception;
//Folosim această excepție pentru a nu lua dimensiunile din header pe mai multi sau mai putini octeti.
public class NoSuchSizeException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NoSuchSizeException(String dimension){
    	super("Image " + dimension + " is represented on 4 bytes!");
    }	
}
