package raytracer.utilities;
import raytracer.world.World;

//Copyright (C) Helen Hu 2013.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.


public class ShadeRec {
	public boolean 			hitAnObject;		// did the ray hit an object?
	public Point3D			localHitPoint;		// world coordinates of hit point 
	public Normal			normal;				// normal at hit point
	public RGBColor			color;				// used in Chapter 3 only
	public World			w;					// world reference for shading
	public double			t;					// ray parameter

	// ------------------------------------------------------------------ constructor

	public ShadeRec(World wr) { 
		hitAnObject = false;
		w = wr;
	}
	
	public String toString() {
		if (hitAnObject)	
			return "ShadeRec: hit " + localHitPoint + " at " + normal;
		else
			return "ShadeRec: nothing hit";
	}
}
