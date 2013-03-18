package raytracer.utilities;

//Copyright (C) Helen Hu 2011.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.

public class Ray {
	public final Point3D origin;
	public final Vector3D direction; 

	public Ray (Point3D origin, Vector3D direction) {
		this.origin = origin;
		this.direction = direction;
	}

	public boolean equals(Object obj) {
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		else {
			Ray other = (Ray)obj;
			return (origin.equals(other.origin) && direction.equals(other.direction));
		}
	}

	public String toString() {
		return "Ray: origin (" + origin + ")\tdir (" + direction + ")";
	}
}