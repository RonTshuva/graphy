package geometries;

import primitives.*;

import java.util.List;

public class Cylinder extends Geometry {
    //Fields:
    double _radius;
    Ray _axis;
    double _height;
    // Constructors:
    //1. Default - not in use"
    /*public Cylinder(){
        _axis = new Ray();
    }
    */

    //2. wt param
    public Cylinder(double radius, Ray axis, double height) {
        _radius = radius;
        _axis = new Ray(axis);
        _height = height;
    }

    //3. Copy
    public Cylinder(Cylinder cylinder) {
        _radius = cylinder._radius;
        _axis = new Ray(cylinder._axis);
        _height = cylinder._height;
    }

    public double getRadius() {
        return _radius;
    }

    public void setRadius(double radius) {
        _radius = radius;
    }

    public Ray getAxis() {
        return _axis;
    }

    public void setAxis(Ray axis) {
        _axis = new Ray(axis);
    }

    public double getHeight() {
        return _height;
    }

    public void setHeight(double height) {
        _height = height;
    }

    public boolean equals(Cylinder cylinder) {
        return _radius == cylinder._radius && _axis.equals(cylinder._axis) && _height == cylinder._height;
    }


    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }

    public String toString() {
        return "Cylinder:\nRadius: " + _radius + "\nAxis: " + _axis + "\nHeight: " + _height;
    }
}
