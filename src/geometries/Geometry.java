package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public abstract class Geometry {
    public abstract Vector getNormal(Point3D point);
    public abstract List<Point3D> findIntersections(Ray ray) ;
    public abstract String toString();
}
