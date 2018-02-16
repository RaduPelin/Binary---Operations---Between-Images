package image;

import java.io.IOException;
import java.nio.file.Files;
import exception.NoSuchSizeException;
import exception.ImageSizeException;
import exception.NoSuchDimensionException;
import exception.NotTheRightImageException;
import interaction.MyFile;
import resource.MyImage;

public class MyBitmap implements MyImage {
	private int width;
	private int height;
	private byte [] header;
	private MyPixel [] pixels;

	public MyBitmap() {
		this(0,0,null,null);
	}
	
	public MyBitmap(int width, int height, byte[] header, MyPixel[] pixels) {
		this.width = width;
		this.height = height;
		this.header = header;
		this.pixels = pixels;
	}
	
	// Creare constructor personalizat, cu preluarea informatiei din fisier.
	public MyBitmap(MyFile f) {
		try {
			// Se citesc toti bitii din fisier.
			byte[] image = Files.readAllBytes(f.getPath());
			
			// Se seteaza header-ul, latime si inaltimea imaginii.
			setHeader(image, "bmp");
			setWidth(getSpecificBytes(image,18, 4));
			setHeight(getSpecificBytes(image,22,4));
			
			// Se salveaza pixele intr-un vector de tip MyPixel.
			MyPixel [] pixels = new MyPixel[width*height];
			
			//Fiecare pixel este impartit in 3 octeti (R G B). Generam un pixel la trei octeti.
			for (int i = 0; i < width*height*3; i = i+3) {
				// Ordinea culorilor este albastru, verde, rosu, octetii pixel-ului fiind in ordinea urmatoare:
				int red = (int) image[i+2];
				int green = (int) image[i+1];
				int blue = (int) image[i];
				
				pixels[i/3] = new MyPixel(red, green, blue);
			}
			// Se seteaza pixelii generati.
			setPixels(pixels);
		} catch(IOException e) {
			System.out.println(e.getMessage());	
		}
	}

	public void setPixels(MyPixel[] pixels){
		this.pixels = pixels;	
	}
	
	public MyPixel[] getPixels(){
		return pixels;	
	}
	
	@Override
	public void setWidth(byte [] img_width) {
		try {
			// Decodificãm latiemea din header.
			int width = decodeDimension(img_width, "width");
			
			if (width < 300) {
				throw new ImageSizeException(300);
			} else {
				this.width = width;
			}
		} catch (ImageSizeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public int getWidth() {
		return width;
	}
	
	@Override
	public void setHeight(byte [] img_height) {
		try {
			// Decodificam inaltimea din header.
			int height = decodeDimension(img_height, "height");
			if (height < 300) {
				throw new ImageSizeException(300);
			} else {
				this.height = height;
			}
		} catch (ImageSizeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public int getHeight() {
		return height;
	}
	
	// Creare metoda de decodificare a latimii si inaltimii.
	public int decodeDimension(byte [] coded, String dimension){
		int decoded = 0;
		
		try {
			if (!dimension.equals("width") && !dimension.equals("height")) {
				throw new NoSuchDimensionException(dimension);	
			}
			// Codul latimii si al inaltimi este pe 4 octeti.
			if (coded.length > 4) {
				throw new NoSuchSizeException(dimension);
			}
			decoded = (coded[3]<<24) & 0xff000000 | (coded[2]<<16) & 0x00ff0000 | (coded[1]<< 8) & 0x0000ff00 | (coded[0]<< 0) & 0x000000ff;
		} catch (NoSuchDimensionException | NoSuchSizeException e) {
			System.out.println(e.getMessage());
		}
		return decoded;
	}
	
	public void setHeader(byte [] bitmap, String type) {
		byte [] header = null;
		try{
			if (type.equals("bmp")) {
				// Header-ul este pe 138 de octeti.
				header = new byte [138];
				for (int i = 0; i < 138; i++) {
					header[i] = bitmap[i];	
				}
				this.header = header;
			} else {
				throw new NotTheRightImageException(type);
			}
		} catch(NotTheRightImageException e) {
			System.out.println(e.getMessage());
		}	
	}
	
	public byte [] getHeader(){
		return header;
	}
	
	// Creare metoda de preluarea a unui subsir de octeti.
	public byte [] getSpecificBytes(byte [] bytes, int index, int length) {
		byte [] specific = new byte [length];
		
		for (int i = 0; i < length; i++){
			specific[i] = bytes[index+i];
		}
		return specific;
	}
}
