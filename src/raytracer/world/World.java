package raytracer.world;
import raytracer.utilities.*;
import raytracer.geometricObjects.*;
import raytracer.tracers.*;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

//Copyright (C) Helen Hu 2013.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.

public abstract class World {
	public ViewPlane				vp;
	public RGBColor					backgroundColor;
	public Tracer					tracer;
	public Sphere 					sphere;		// for Chapter 3 only
	public List<GeometricObject>	objects;	
	
	public BufferedImage image;

	protected static final int ROWS = 300, COLS = 300;

	public World(){
		vp = new ViewPlane();
		backgroundColor = RGBColor.BLACK;
		tracer= null;
		objects = new ArrayList<GeometricObject>();
	}

	public void addObject(GeometricObject object) {  
		objects.add(object);	
	}

	/** Implement this method if you want to build a scene. */
	protected abstract void buildScene();

	public int[] build()
	{
		vp.setHres(COLS);
		vp.setVres(ROWS);
		vp.setPixelSize(1.f);
		vp.setGamma(1.f);

		buildScene();

		int[] resolution = new int[2];
		resolution[0] = vp.hres;
		resolution[1] = vp.vres;

		// for saving the image to a file
		image = new BufferedImage(vp.hres,vp.vres, BufferedImage.TYPE_INT_RGB);

		return resolution;
	}
	
	/** This uses orthographic viewing along the zw axis. */
	public void renderScene()  {

		RGBColor	pixelColor;	 	
		double		zw		= 100.0;			// hardwired in
		double		x, y;

		Vector3D direction = new Vector3D(0, 0, -1);

		for (int r = 0; r < vp.vres; r++)			// up
			for (int c = 0; c < vp.hres; c++) {	// across 					
				x = vp.s * (c - 0.5 * (vp.hres - 1.0));
				y = vp.s * (r - 0.5 * (vp.hres - 1.0));
				Point3D origin = new Point3D(x, y, zw);
				pixelColor = tracer.traceRay(new Ray(origin, direction));
				displayPixel(r, c, pixelColor);
			}	
	}  

	/** Normalizes the color such that its maximum component is not larger than one. */
	public RGBColor maxToOne(RGBColor c) {
		float maxValue = Math.max(c.r, Math.max(c.g, c.b));

		if (maxValue > 1.0)
			return c.divide(maxValue);
		else
			return c;
	}


	/** Set color to red if any component is greater than one. */
	public RGBColor clampToColor(RGBColor rawColor) {
		RGBColor result = rawColor;
		if (rawColor.r > 1.0 || rawColor.g > 1.0 || rawColor.b > 1.0) {
			result = new RGBColor(1.0f, 0.0f, 0.0f);
		}

		return result;
	}

	/**
	 * Normalizes the color value and colors the pixel in the image.
	 * @param rawColor The pixel color computed by the ray tracer, with arbitrarily large RGB values.
	 */
	public void displayPixel(int row, int column, RGBColor rawColor) {
		// will have all components in the range [0, 1], but is still floating point
		RGBColor mappedColor;

		if (vp.showOutOfGamut) {
			mappedColor = clampToColor(rawColor);
		} else {
			mappedColor = maxToOne(rawColor);
		}
		
		if (vp.gamma != 1.0) {
			mappedColor = mappedColor.powc(vp.invGamma);
		}

		// have to start from max y coordinate to convert to screen coordinates
		int x = column;
		int y = vp.vres - row - 1;

		// Java color, with each component an int in the range [0, 255]
		Color displayColor = new Color((int)(mappedColor.r * 255),
				(int)(mappedColor.g * 255),
				(int)(mappedColor.b * 255));

		image.setRGB(x, y, displayColor.getRGB());
	}
}

