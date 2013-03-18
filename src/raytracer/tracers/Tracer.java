package raytracer.tracers;
import raytracer.utilities.RGBColor;
import raytracer.utilities.Ray;
import raytracer.world.World;

//Copyright (C) Helen Hu 2011.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.


/**
 * This abstract base class has no copy constructor or assignment operator because 
 * of the world reference, which should not be assigned or copy constructed
 */
public abstract class Tracer {
	protected World world;
	

	// -------------------------------------------------------------------- default constructor

	public Tracer() {
		world = null;
	}


	// -------------------------------------------------------------------- constructor

	public Tracer(World w){
		world = w;
	}


	// -------------------------------------------------------------------- trace_ray

	public abstract RGBColor traceRay(Ray ray);


	// -------------------------------------------------------------------- trace_ray

	public abstract RGBColor traceRay(Ray ray, int depth);

	
}
