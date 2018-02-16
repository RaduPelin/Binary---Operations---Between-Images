package exception;
//Folosim această excepție pentru a nu realiza operații cu imagini de dimensiuni prea mici.
public class ImageSizeException extends Exception {

	private static final long serialVersionUID = 1L;

	public ImageSizeException(int value){
    	super("To see the image propertly, the width and the height must be greater then " + 300 +" !");
    }	
}
