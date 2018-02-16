package interaction;
import java.io.FileOutputStream;
import java.io.IOException;

import exception.NoSameSizeException;
import exception.NoSuchColorException;
import exception.NoSuchOperationException;
import exception.ShiftValueSizeException;
import image.MyBitmap;
import image.MyPixel;

public class Interactions {
	
	// Creare metoda de obtinere a imaginii negative.
	public void negate(MyBitmap destination) {
		
		MyPixel [] pixels = destination.getPixels();
		
		int dimension  = pixels.length;
		// Negare octet cu octet din pixel.
		for (int i = 0; i < dimension; i++) {
			int red = ~pixels[i].getRed();
			int green = ~pixels[i].getGreen();
			int blue = ~pixels[i].getBlue();
		 
			pixels[i] = new MyPixel(red, green, blue);
		}
	}
	
	// Creare metoda de realizare a interactiunii logice intre imagini.
	public void addOperation(String operation, MyBitmap destination, MyBitmap source) {	
		MyPixel[] image1Array = destination.getPixels();
		MyPixel[] image2Array = source.getPixels();
		MyPixel [] pixels = image1Array;
		
		int dimension = pixels.length;
		
		try {
			if (destination.getWidth() != source.getWidth() || destination.getHeight() != source.getHeight()) {
				throw new NoSameSizeException();	
			} else {
				if (!operation.equals("and") && !operation.equals("nand") && !operation.equals("or") && !operation.equals("nor") && !operation.equals("xor") && !operation.equals("xnor")) {
					throw new NoSuchOperationException(operation);
				// Se realizeaza operatii logice pe octeti.
				} else {
					if(operation.equals("and")) {
					 
						for (int i = 0; i < dimension; i++) {
							int red = image1Array[i].getRed() & image2Array[i].getRed();
							int green = image1Array[i].getGreen() & image2Array[i].getGreen();
							int blue = image1Array[i].getBlue() & image2Array[i].getBlue();
						    
							pixels[i] = new MyPixel(red, green, blue);
						} 	
					} else {
						if (operation.equals("nand")) {
							
							for (int i = 0; i < dimension; i++) {
								int red = ~(image1Array[i].getRed() & image2Array[i].getRed());
								int green = ~(image1Array[i].getGreen() & image2Array[i].getGreen());
								int blue = ~(image1Array[i].getBlue() & image2Array[i].getBlue());
							    
								pixels[i] = new MyPixel(red, green, blue);
							} 	
						} else {
							if(operation.equals("or")) {
								
								for (int i = 0; i < dimension; i++) {
									int red = image1Array[i].getRed() | image2Array[i].getRed();
									int green = image1Array[i].getGreen() | image2Array[i].getGreen();
									int blue = image1Array[i].getBlue() | image2Array[i].getBlue();
									
									pixels[i] = new MyPixel(red, green, blue);
								} 		
							} else {
								if (operation.equals("nor")) {
									
									for (int i = 0; i < dimension; i++) {
										int red = ~(image1Array[i].getRed() | image2Array[i].getRed());
										int green = ~(image1Array[i].getGreen() | image2Array[i].getGreen());
										int blue = ~(image1Array[i].getBlue() | image2Array[i].getBlue());
										
										pixels[i] = new MyPixel(red, green, blue);
									}
								} else {
									if (operation.equals("xor")) {
										
										for (int i = 0; i < dimension; i++) {
											int red = image1Array[i].getRed() ^ image2Array[i].getRed();
											int green = image1Array[i].getGreen() ^ image2Array[i].getGreen();
											int blue = image1Array[i].getBlue() ^ image2Array[i].getBlue();
											
											pixels[i] = new MyPixel(red, green, blue);
										}
									} else {
										
										for(int i = 0; i < dimension; i++){
											int red = ~(image1Array[i].getRed() ^ image2Array[i].getRed());
											int green = ~(image1Array[i].getGreen() ^ image2Array[i].getGreen());
											int blue = ~(image1Array[i].getBlue() ^ image2Array[i].getBlue());
											
											pixels[i] = new MyPixel(red, green, blue);
										}	
									}
									
								}	
							}	
						}
					}
					
				}
			}
		} catch (NoSameSizeException | NoSuchOperationException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// Creare metoda de shiftare la stanga si la dreapta cu x, a octetului ce reprezinta o anmita culoare din pixel.
	public void addOperation(String color, String operator, MyBitmap destination, int shiftValue) {
		MyPixel [] pixels = destination.getPixels();
		int dimension = pixels.length;
		
		try {
			if (!operator.equals(">>") && !operator.equals("<<")) {
				throw new NoSuchOperationException(operator);
			} else {
				if (shiftValue > 8) {
					throw new ShiftValueSizeException();
				} else {
					if (!color.equals("red") && !color.equals("green") && !color.equals("blue")) {
						throw new NoSuchColorException(color);
					} else {
						if (operator.equals("<<")) {
							
							for (int i = 0; i < dimension; i++) {
								int red = pixels[i].getRed();
								int green = pixels[i].getGreen();
								int blue = pixels[i].getBlue();
								
								if (color.equals("red")) {
									red = (red << shiftValue) & 255;
								} else {
									if (color.equals("blue")) {
										green = (green << shiftValue) & 255;
									} else {
										blue = (blue << shiftValue) & 255;
									}
								}
								
								pixels[i] = new MyPixel(red, green, blue);
							}
						} else {
							
							for (int i = 0; i < dimension; i++) {
								int red = pixels[i].getRed();
								int green = pixels[i].getGreen();
								int blue = pixels[i].getBlue();
								
								if (color.equals("red")) {
									red = (red >> shiftValue) & 255;
								} else {
									if(color.equals("blue")) {
										green = (green >> shiftValue) & 255;
									} else {
										blue = (blue >> shiftValue) & 255;
									}
								}
								
								pixels[i] = new MyPixel(red, green, blue);
							}
						}
					}
				}
				
			}	
		} catch (NoSuchOperationException | ShiftValueSizeException | NoSuchColorException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// Creare metoda de shiftare la stanga si la dreapta cu x, a octetilor din pixel.
	public void addOperation(String operation, MyBitmap image, int shiftValue) {
		MyPixel [] pixels = image.getPixels();
		int dimension = pixels.length;
		
		try {
			if (!operation.equals(">>") && !operation.equals("<<")) {
				throw new NoSuchOperationException(operation);
			} else {
				if (shiftValue > 8) {
					throw new ShiftValueSizeException();
				} else {
					if (operation.equals("<<")) {
						
						for (int i = 0; i < dimension; i++) {
							int red = (pixels[i].getRed() << shiftValue) & 255;
							int green = (pixels[i].getGreen() << shiftValue) & 255;
							int blue = (pixels[i].getBlue() << shiftValue) & 255;
						 
							pixels[i] = new MyPixel(red, green, blue);
						}
					} else {
						
						for (int i = 0; i < dimension; i++) {
							int red = (pixels[i].getRed() >> shiftValue) & 255;
							int green = (pixels[i].getGreen() >> shiftValue) & 255;
							int blue = (pixels[i].getBlue() >> shiftValue) & 255;
						 
							pixels[i] = new MyPixel(red, green, blue);
						}
					}
				}
				
			}	
		} catch (NoSuchOperationException | ShiftValueSizeException e) {
			System.out.println(e.getMessage());
		}
	}
    
	// Creare metoda de docodificare din tip de data MyPixel in octeti.
	private  byte [] myPixelToBytes(MyPixel[] pixelArray){
		
		byte[] content = new byte[pixelArray.length*3];
		
		for (int i = 0; i < pixelArray.length; i++) {
			content[i*3] = (byte) pixelArray[i].getBlue();
			content[i*3+1] = (byte) pixelArray[i].getGreen();
			content[i*3+2] = (byte) pixelArray[i].getRed();
		}	
		return content;
	}
	
	// Creare metoda de scriere in fisier a rezultatului obtinut.
	public void myBitmapToFile(MyBitmap image, String pathName, String fileName){
		
		try{
			byte [] header = image.getHeader();
			byte [] pixels = myPixelToBytes(image.getPixels());
			int size = header.length + pixels.length;
			byte[] data = new byte [size];
			
			for (int i = 0; i < header.length; i++) {
					data[i] = header[i];
			}
				
			for (int i = header.length; i < pixels.length; i++) {
					data[i] = pixels[i];
			}
					
			FileOutputStream file = new FileOutputStream(pathName + "\\" + fileName);
			file.write(data);
			file.close();
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}       
}
