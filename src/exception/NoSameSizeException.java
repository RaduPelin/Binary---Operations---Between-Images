package exception;
//Folosim această excepție pentru a nu se folosi imagini de dimensiuni diferite.
public class NoSameSizeException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoSameSizeException(){
    	super("The images must have the same size!");
    }	
}
