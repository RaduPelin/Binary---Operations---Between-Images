package image;

import exception.PixelColorException;

public class MyPixel {
	
	private int red;
	private int green;
	private int blue;
	
	public MyPixel () {
		this(0,0,0);
	}
		
	public MyPixel(int red, int green, int blue) {
		setRed(red);
		setGreen(green);
		setBlue(blue);
	}
	
	public void setRed(int red) {
		try {
			if (red > 255 || red < -255) {
				throw new PixelColorException("red", 256);
			} else {
				this.red = red;
			}
		} catch (PixelColorException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getRed() {
		return red;
	}
	
	public void setGreen(int green) {
		try {
			if (green > 255 || green < -255) {
				throw new PixelColorException("green", 256);
			} else {
				this.green = green;
			}
		} catch (PixelColorException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getGreen() {
		return green;
	}
	
	public void setBlue(int blue) {
		try {
			if (blue > 255 || blue < -255) {
				throw new PixelColorException("blue", 256);
			} else {
				this.blue = blue;
			}
		} catch (PixelColorException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getBlue() {
		return blue;
	}	
}
