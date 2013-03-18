package raytracer.utilities;
import java.util.ArrayList;
import java.util.List;

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
	public List<Point3D> vertices = new ArrayList<Point3D>();				// mesh vertices 
	public List<Normal> normals = new ArrayList<Normal>();				// average normal at each vertex;
	public List<Float> u = new ArrayList<Float>();						// u texture coordinate at each vertex
	public List<Float> v = new ArrayList<Float>();						// v texture coordinate at each vertex
	public List<List<Integer>> vertexFaces;			// the triangles shared by each vertex
	public int numTriangles = 0; 			// number of triangles
	
	public String toString() {
		return "mesh with " + vertices.size() + " vertices and " + 
		numTriangles + " triangles";
	}	

}
