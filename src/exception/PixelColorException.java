package exception;
//Folosim această excepție pentru a nu introduce valori ce nu se incadreaza intervalului dorit.
public class PixelColorException extends Exception {
 
	private static final long serialVersionUID = 1L;

	public PixelColorException(String color, int value){
    	super("The " + color + " value must be lower than " + value + " and greater than -" + value + ", as an integer!");
    			
    }
}
