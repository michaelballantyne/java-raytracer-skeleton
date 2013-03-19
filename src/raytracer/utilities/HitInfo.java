package raytracer.utilities;

public class HitInfo {
	public final Normal normal;
	public final Point3D localHitPoint;
	public final RGBColor color;
	public final double t;
	
	public HitInfo(Normal normal, Point3D localHitPoint, RGBColor color, double t) {
		this.normal = normal;
		this.localHitPoint = localHitPoint;
		this.color = color;
		this.t = t;
	}
}
