package primitives;

public class Ray {
    //Fields:
    Point3D _p0; /// starting point - not head
    Vector _v; /// the Direction

    // Constructors:

    //2. wt params:
    public Ray(Point3D p, Vector v) {
        _p0 = new Point3D(p);
        _v = v.normalize();
    }

    //3. Copy:
    public Ray(Ray ray) {
        _p0 = new Point3D(ray._p0);
        _v = ray._v.normalize();
    }

    // Setters & Getters & equals & toString:
    public Point3D get_p0() {
        return this._p0;
    }

    public void setPoint(Point3D point3D) {
        _p0 = new Point3D(point3D);
    }

    public Vector getDirection() {
        return this._v;
    }

    public void setVector(Vector vector) {
        _v = new Vector(vector);
    }

    public boolean equals(Ray ray) {
        return _p0.equals(ray._p0) && _v.equals(ray._v);
    }

    public String toString() {
        return "Ray:\nVector Head: " + _v + "\nPoint3D: " + _p0;
    }
}
