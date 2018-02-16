package exception;
//Folosim această excepție pentru a nu lucra cu culori diferite de cele pe care le acceptă programul.
public class NoSuchColorException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NoSuchColorException(String color){
    	super("There is no color named" + color + "!");
    }	
}
