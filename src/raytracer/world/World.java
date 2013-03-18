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


//This file contains the declaration of the class World
//The World class does not have a copy constructor or an assignment operator, for the following reasons:

//1 	There's no need to copy construct or assign the World
//2 	We wouldn't want to do this anyway, because the world can contain an arbitrary amount of data
//3 	These operations wouldn't work because the world is self-referencing:
//	 	the Tracer base class contains a pointer to the world. If we wrote a correct copy constructor for the 
//	  	Tracer class, the World copy constructor would call itself recursively until we ran out of memory. 

public abstract class World {
	public ViewPlane				vp;
	public RGBColor					backgroundColor;
	public Tracer					tracer;
	public Sphere 					sphere;		// for Chapter 3 only
	public List<GeometricObject>	objects;	
	
	// for saving image
	public BufferedImage image;

	protected static final int ROWS = 300, COLS = 300;


	// -------------------------------------------------------------------- default constructor

	public World(){
		vp = new ViewPlane();
		backgroundColor = RGBColor.BLACK;
		tracer= null;
		sphere = new Sphere(new Point3D(0, 0, 0), 5, RGBColor.RED);
		objects = new ArrayList<GeometricObject>();
	}


	// ------------------------------------------------------------------ add_object

	public void addObject(GeometricObject object) {  
		objects.add(object);	
	}

	// ------------------------------------------------------------------ build

	/*
	 * implement this method if you want to build different items
	 */
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
	
	//------------------------------------------------------------------ render_scene

	// This uses orthographic viewing along the zw axis

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

	// ------------------------------------------------------------------ clamp

	public RGBColor maxToOne(RGBColor c) {
		float maxValue = Math.max(c.r, Math.max(c.g, c.b));

		if (maxValue > 1.0)
			return c.divide(maxValue);
		else
			return c;
	}


	// ------------------------------------------------------------------ clamp_to_color
	// Set color to red if any component is greater than one

	public RGBColor clampToColor(RGBColor rawColor) {
		RGBColor result = rawColor;
		if (rawColor.r > 1.0 || rawColor.g > 1.0 || rawColor.b > 1.0) {
			result = new RGBColor(1.0f, 0.0f, 0.0f);
		}

		return result;
	}


	// ---------------------------------------------------------------------------display_pixel

	// rawColor is the pixel color computed by the ray tracer
	// its RGB floating point components can be arbitrarily large
	// mappedColor has all components in the range [0, 1], but still floating point
	// display color has integer components for computer display
	// the Mac's components are in the range [0, 65535]
	// a PC's components will probably be in the range [0, 255]
	// the system-dependent code is in the function convert_to_display_color


	public void displayPixel(int row, int column, RGBColor rawColor) {
		RGBColor mappedColor;

		if (vp.showOutOfGamut)
			mappedColor = clampToColor(rawColor);
		else
			mappedColor = maxToOne(rawColor);

		if (vp.gamma != 1.0)
			mappedColor = mappedColor.powc(vp.invGamma);

		//have to start from max y coordinate to convert to screen coordinates
		int x = column;
		int y = vp.vres - row - 1;

		// for Java window
		Color color = new Color((int)(mappedColor.r * 255),
				(int)(mappedColor.g * 255),
				(int)(mappedColor.b * 255));

		image.setRGB(x, y, color.getRGB());

//		graphics.setColor(color);
//		graphics.fillRect(x, y, 1, 1);

		
	}


	// ----------------------------------------------------------------------------- hit_bare_bones_objects

	public ShadeRec hitBareBonesObjects(Ray ray) {
		ShadeRec	sr = new ShadeRec(this); 
		double		t; 			
		double		tMin 			= Constants.HUGE_VALUE;
		int 		numObjects  	= objects.size();

		for (int j = 0; j < numObjects; j++) {
			t = objects.get(j).hit(ray, sr);
			if ((0 < t) && (t < tMin)) {
				sr.hitAnObject	= true;
				tMin 				= t; 
			}
		}

		return (sr);   
	}


	//------------------------------------------------------------------ delete_objects

	// Deletes the objects in the objects array, and erases the array.
	// The objects array still exists, because it's an automatic variable, but it's empty 

	public void deleteObjects() {
		objects.clear();
	}

}

