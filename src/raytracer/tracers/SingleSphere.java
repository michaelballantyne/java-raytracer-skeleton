package raytracer.tracers;

import raytracer.utilities.*;
import raytracer.world.World;

//Copyright (C) Helen Hu 2013.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.

public class SingleSphere extends Tracer {
	public SingleSphere(World world){
		super(world);
	}

	public RGBColor traceRay(Ray ray) {
		HitInfo hit = world.sphere.hit(ray);
			
		if (hit != null) {
			return RGBColor.RED; 
		} else {
			return RGBColor.BLACK;   
		}
	}

	public RGBColor traceRay(Ray ray, int depth) {
		return RGBColor.BLACK;
	}
}
