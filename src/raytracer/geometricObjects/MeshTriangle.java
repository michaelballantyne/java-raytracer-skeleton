package raytracer.geometricObjects;

import raytracer.utilities.*;

//Copyright (C) Helen Hu 2013.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.

/**
 * Triangle classes derived from MeshTriangle, such as FlatMeshTriangle and SmoothMeshTriangle, 
 * are stored in a regular grid for rendering triangle meshes
 * A mesh triangle stores integer indices into the vertices array in a Mesh object
 * A mesh triangle also stores a reference to the Mesh object
 * A mesh triangle also stores the normal for use in FlatMeshTriangle and for 
 * computing the average normal at each vertex for SmoothMeshTriangles
 * A mesh triangle defines the shadow hit function because this is independent of whether the
 * triangle is flat shaded or smooth shaded or uv mapped or not uv mapped
 * 
 * MeshTriangle doesn't inherit from the Triangle class discussed in Chapter 19, 
 * because Triangle stores the vertices and a normal
 */
public abstract class MeshTriangle extends GeometricObject {	
	public  Mesh 		mesh;					    // stores all the data
	public int			index0, index1, index2;  	// indices into the vertices array in the mesh
	public Normal		normal;					
	public float		area;						// required for translucency

	// ---------------------------------------------------------------- constructor
	// the normal is computed in Grid::read_file

	public MeshTriangle(Mesh m,  int i0,  int i1,  int i2, RGBColor color){
		super(color);
		mesh = m;
		index0 = i0;
		index1 = i1;
		index2 = i2;
		Point3D v0 = mesh.vertices.get(index0);
		Point3D v1 = mesh.vertices.get(index1);
		Point3D v2 = mesh.vertices.get(index2);
		
		Vector3D edge0 = v1.subtract(v0);
		Vector3D edge1 = v2.subtract(v0);
		normal = new Normal(edge0.cross(edge1).hat());
	}

	// ------------------------------------------------------------------------------ shadow_hit
	// this function is independent of the derived triangle type:
	// flat, smooth, flat uv, smooth uv

	public double shadowHit(Ray ray) {	
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
		
		if (gamma < 0.0 )
		 	return (-1.0);
		
		if (beta + gamma > 1.0)
			return (-1.0);
				
		double e3 = a * p - b * r + d * s;
		double t = e3 * inv_denom;
		
		if (t < Constants.EPSILON) 
			return (-1.0);
	                                                                                                       							
		return (t);	
	}   
	
	public String toString() {
		return "mesh triangle: [" + index0 + ", "+ index1 + ", " + index2 + "] with " + normal;
	}	


	// ---------------------------------------------------------------- interpolate_u
	// this is used for texture mapping in Chapter 29

	protected float interpolateU(float beta, float gamma) {	
		return( (1 - beta - gamma) * mesh.u.get(index0) 
					+ beta * mesh.u.get(index1) 
						+ gamma * mesh.u.get(index2) );
	}


	// ---------------------------------------------------------------- interpolate_v
	// this is used for texture mapping in Chapter 29

	protected float interpolateV(float beta, float gamma) {	
		return( (1 - beta - gamma) * mesh.v.get(index0) 
					+ beta * mesh.v.get(index1) 
						+ gamma * mesh.v.get(index2) );
	}
}
