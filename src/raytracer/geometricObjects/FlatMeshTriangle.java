package raytracer.geometricObjects;

import raytracer.utilities.*;


//Copyright (C) Helen Hu 2013.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.

/**
 * FlatMeshTriangle is a MeshTriangle that uses flat shading with one normal for each triangle
 */
public class FlatMeshTriangle extends MeshTriangle {
	public FlatMeshTriangle (Mesh m, int i0, int i1, int i2) {
		super(m, i0, i1, i2);
	}

	public double hit(Ray ray, ShadeRec sr) {
			Point3D v0 = mesh.vertices.get(index0);
			Point3D v1 = mesh.vertices.get(index1);
			Point3D v2 = mesh.vertices.get(index2);
			
			double a = v0.x - v1.x, b = v0.x - v2.x, c = ray.direction.x, d = v0.x - ray.origin.x; 
			double e = v0.y - v1.y, f = v0.y - v2.y, g = ray.direction.y, h = v0.y - ray.origin.y;
			double i = v0.z - v1.z, j = v0.z - v2.z, k = ray.direction.z, l = v0.z - ray.origin.z;
				
			double m = f * k - g * j, n = h * k - g * l, p = f * l - h * j;
			double q = g * i - e * k, s = e * j - f * i;
			
			double inv_denom  = 1.0 / (a * m + b * q + c * s);
			
			double e1 = d * m - b * n - c * p;
			double beta = e1 * inv_denom;
			
			if (beta < 0.0)
			 	return (-1.0);
			
			double r = e * l - h * i;
			double e2 = a * n + d * q + c * r;
			double gamma = e2 * inv_denom;
			
			if (gamma < 0.0)
			 	return (-1.0);
			
			if (beta + gamma > 1.0)
				return (-1.0);
					
			double e3 = a * p - b * r + d * s;
			double t = e3 * inv_denom;
			
			if (t < Constants.EPSILON)
				return (-1.0);
							
			sr.normal 			= normal;  				// for flat shading
			sr.localHitPoint 	= ray.origin.add(ray.direction.multiply(t));	
			
			return (t);	
		}  
	
	public String toString() {
		return "flat " + super.toString();
	}	

}
