package exception;
//Folosim această exceptie, pentru a nu efectua operații între imagini, ce nu există în program.
public class NoSuchOperationException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NoSuchOperationException(String operation){
    	super("There is no operation named " + operation + "!");
    }	
}
