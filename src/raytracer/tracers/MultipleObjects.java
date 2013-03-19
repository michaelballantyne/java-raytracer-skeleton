package raytracer.tracers;

import raytracer.geometricObjects.GeometricObject;
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
		double tMin = Double.MAX_VALUE;
		RGBColor frontColor = world.backgroundColor;

		for (GeometricObject object : world.objects) {
			HitInfo hit = object.hit(ray);
			if (hit != null && (0 < hit.t) && (hit.t < tMin)) {
				tMin = hit.t;
				frontColor = hit.color;
			}
		}

		return frontColor;
	}
}
