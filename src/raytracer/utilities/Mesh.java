package raytracer.utilities;
import java.util.Vector;

/**
 * 
// 	Copyright (C) Kevin Suffern 2000-2007.
//	This C++ code is for non-commercial purposes only.
//	This C++ code is licensed under the GNU General Public License Version 2.
//	See the file COPYING.txt for the full license.


// A mesh object stores the data for mesh triangles:
//
//			vertices
//			normals
//			uv texture coordinates
//			lists of triangles that share each vertex
//
// Mesh triangles are stored in a grid, which has a pointer to the mesh
// Each mesh triangle also has a pointer to the mesh
// The Mesh class does not inherit from GeometricObject
 * 
 * @author hhh
 *
 */
public class Mesh {
	public Vector<Point3D> 			vertices;				// mesh vertices 
	public Vector<Normal> 			normals;				// average normal at each vertex;
	public Vector<Float>			u;						// u texture coordinate at each vertex
	public Vector<Float>			v;						// v texture coordinate at each vertex
	public Vector<Vector<Integer> > 	vertexFaces;			// the triangles shared by each vertex
	public int 						numVertices; 			// number of vertices
	public int 						numTriangles; 			// number of triangles
	


	// ----------------------------------------------------------------  default constructor

	public Mesh(){
		vertices = new Vector<Point3D>();
		normals = new Vector<Normal>();
		u = new Vector<Float>();
		v = new Vector<Float>();
		vertexFaces = new Vector<Vector<Integer>>();
	 	numVertices = 0;
	 	numTriangles = 0;
	}

	// ---------------------------------------------------------------- copy constructor
	// this doesn't handle the vertex_faces

	public Mesh(Mesh m) {
		vertices = new Vector<Point3D>(m.vertices);
		normals = new Vector<Normal>(m.normals);
		u = new Vector<Float>(m.u);
		v = new Vector<Float>(m.v);
		numVertices = m.numVertices;
		numTriangles = m.numTriangles;
	}

	// ---------------------------------------------------------------- assignment operator
	// this doesn't handle the vertex_faces

	public void set (Mesh rhs) {
		if (this != rhs) {
			vertices = new Vector<Point3D>(rhs.vertices);
			normals = new Vector<Normal>(rhs.normals);
			u  				= (Vector<Float>)rhs.u;
			v  				= (Vector<Float>)rhs.v;
			numVertices	= rhs.numVertices;
			numTriangles	= rhs.numTriangles;
		}
	}

	// not really a good idea to compare Meshes - this would take a long time!
	// let's just compare the number of vertices & triangles
	public boolean equals(Object obj) {
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		else {
			Mesh other = (Mesh)obj;
			return (numVertices == other.numVertices &&
					numTriangles == other.numTriangles);
		}
	}
	
	public String toString() {
		return "mesh with " + numVertices + " vertices and " + 
		numTriangles + " triangles";
	}	

}
