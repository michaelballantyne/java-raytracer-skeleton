package raytracer.geometricObjects;

import raytracer.utilities.Constants;
import raytracer.utilities.Mesh;
import raytracer.utilities.Normal;
import raytracer.utilities.Point3D;
import raytracer.utilities.Ray;
import raytracer.utilities.ShadeRec;

//Copyright (C) Helen Hu 2013.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.

/**
 * 	SmoothMeshTriangle is a MeshTriangle that uses smooth shading with an interpolated normal at each hit point
 */
public class SmoothMeshTriangle extends MeshTriangle {

	// ----------------------------------------------------------------  default constructor

	public SmoothMeshTriangle(){
		super();
	}


	// ---------------------------------------------------------------- constructor

	public SmoothMeshTriangle (Mesh mesh_ptr, int i0, int i1, int i2){
		super(mesh_ptr, i0, i1, i2);
	}


	// ---------------------------------------------------------------- clone

	public SmoothMeshTriangle clone () {
		return (new SmoothMeshTriangle (this));
	}


	// ---------------------------------------------------------------- copy constructor

	public SmoothMeshTriangle (SmoothMeshTriangle fmt) {
		super(fmt);
	}


	// ---------------------------------------------------------------- assignment operator

	public void set(SmoothMeshTriangle rhs) {
		if (this != rhs)
			super.set(rhs);
	}


	// ---------------------------------------------------------------- interpolate_normal

	protected Normal interpolateNormal(double beta, double gamma) {	
		double alpha = 1 - beta - gamma;
		Normal normal = new Normal(mesh.normals.get(index0).multiply(alpha).add
				(mesh.normals.get(index1).multiply(beta).add
						(mesh.normals.get(index2).multiply(gamma))));
		normal.normalize();

		return(normal);
	}

	// ---------------------------------------------------------------- hit

	public double hit(Ray ray, ShadeRec sr) {
		Point3D v0 = mesh.vertices.get(index0);
		Point3D v1 = mesh.vertices.get(index1);
		Point3D v2 = mesh.vertices.get(index2);
		
		double a = v0.x - v1.x, b = v0.x - v2.x, c = ray.d.x, d = v0.x - ray.o.x; 
		double e = v0.y - v1.y, f = v0.y - v2.y, g = ray.d.y, h = v0.y - ray.o.y;
		double i = v0.z - v1.z, j = v0.z - v2.z, k = ray.d.z, l = v0.z - ray.o.z;
			
		double m = f * k - g * j, n = h * k - g * l, p = f * l - h * j;
		double q = g * i - e * k, s = e * j - f * i;
		
		double invDenom  = 1.0 / (a * m + b * q + c * s);
		
		double e1 = d * m - b * n - c * p;
		double beta = e1 * invDenom;
		
		if (beta < 0.0)
		 	return (-1.0);
		
		double r = e * l - h * i;
		double e2 = a * n + d * q + c * r;
		double gamma = e2 * invDenom;
		
		if (gamma < 0.0)
		 	return (-1.0);
		
		if (beta + gamma > 1.0)
			return (-1.0);
				
		double e3 = a * p - b * r + d * s;
		double t = e3 * invDenom;
		
		if (t < Constants.EPSILON)
			return (-1.0);

		sr.normal 			= interpolateNormal(beta, gamma); // for smooth shading
		sr.localHitPoint 	= ray.o.add(ray.d.multiply(t));	
		
		return (t);	
	}  

}
