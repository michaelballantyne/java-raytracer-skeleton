package raytracer.tracers;

import raytracer.utilities.*;
import raytracer.world.World;

//Copyright (C) Helen Hu 2013.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.

public class MultipleObjects extends Tracer {
	public MultipleObjects(World world) {
		super(world);
	}

	public RGBColor traceRay(Ray ray) {
		ShadeRec sr = hitBareBonesObjects(ray); 

		if (sr.hitAnObject) {
			return sr.color;   
		} else {
			return world.backgroundColor;
		}
	}
	
	public ShadeRec hitBareBonesObjects(Ray ray) {
		ShadeRec	sr = new ShadeRec(world); 
		double		t; 			
		double		tMin 			= Constants.HUGE_VALUE;
		int 		numObjects  	= world.objects.size();
		RGBColor	frontColor = null;

		for (int j = 0; j < numObjects; j++) {
			sr.color = world.objects.get(j).color;
			t = world.objects.get(j).hit(ray, sr);
			if ((0 < t) && (t < tMin)) {
				sr.hitAnObject	= true;
				tMin 				= t;
				frontColor = sr.color;
			}
		}
		sr.color = frontColor;
		return sr;   
	}
}
