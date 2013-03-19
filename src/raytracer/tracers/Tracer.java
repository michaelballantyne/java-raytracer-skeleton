package raytracer.tracers;
import raytracer.utilities.RGBColor;
import raytracer.utilities.Ray;
import raytracer.world.World;

//Copyright (C) Helen Hu 2011.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.

public abstract class Tracer {
	protected final World world;

	public Tracer(World w){
		world = w;
	}

	public abstract RGBColor traceRay(Ray ray);
}
