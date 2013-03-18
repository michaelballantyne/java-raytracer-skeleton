package raytracer.tracers;

import raytracer.utilities.*;
import raytracer.world.World;

//Copyright (C) Helen Hu 2013.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.

public class SingleSphere extends Tracer {

	// -------------------------------------------------------------------- default constructor

	public SingleSphere() {
		super();
	}


	// -------------------------------------------------------------------- constructor

	public SingleSphere(World world){
		super(world);
	}

	// -------------------------------------------------------------------- trace_ray

	public RGBColor traceRay(Ray ray) {
		ShadeRec	sr = new ShadeRec(world); 	// not used
		double    	t = world.sphere.hit(ray, sr);  				// not used
			
		if (t > 0)		
			return (RGBColor.RED);  
		else
			return (RGBColor.BLACK);   
	}

	// -------------------------------------------------------------------- trace_ray
	
	public RGBColor traceRay(Ray ray, int depth) {
		return (RGBColor.BLACK);
	}

}
