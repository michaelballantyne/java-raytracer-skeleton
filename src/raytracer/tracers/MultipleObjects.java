package raytracer.tracers;

import raytracer.utilities.*;
import raytracer.world.World;

//Copyright (C) Helen Hu 2013.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.


public class MultipleObjects extends Tracer {

	// -------------------------------------------------------------------- default constructor

	public MultipleObjects() {
		super();
	}

	// -------------------------------------------------------------------- constructor

	public MultipleObjects(World world) {
		super(world);
	}



	// -------------------------------------------------------------------- trace_ray

	public RGBColor traceRay(Ray ray) {
		if (world != null) {
			ShadeRec sr = world.hitBareBonesObjects(ray); 

			if (sr.hitAnObject)   
				return (sr.color);   
			else
				return (world.backgroundColor);
		}
		return RGBColor.BLACK;
	}

	// -------------------------------------------------------------------- trace_ray

	public RGBColor traceRay(Ray ray, int depth) {
		return traceRay(ray);
	}

}
