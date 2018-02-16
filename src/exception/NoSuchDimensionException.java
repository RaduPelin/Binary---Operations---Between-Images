package exception;
//Folosim această excepție pentru a nu seta dimensiuni diferite de cele pe care le acceptă programul.
public class NoSuchDimensionException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NoSuchDimensionException(String dimension){
    	super("There is no dimension named " + dimension + "!");
    }	
}
