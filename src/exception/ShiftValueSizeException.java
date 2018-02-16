package exception;
//Folosim această excepție pentru a nu shifta  mai mult de 8 biti.
public class ShiftValueSizeException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ShiftValueSizeException(){
    	super("The shift value must be lower than 8 to see the result propertly!");
    }	
}
