package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TransferQueue;

public class Triangle extends Geometry {
    //Fields:
    Point3D _p1;
    Point3D _p2;
    Point3D _p3;
    Plane _plane;

    // Constructors:
    //1. Default - point on Origin
    public Triangle() {
        _p1 = new Point3D();
        _p2 = new Point3D();
        _p3 = new Point3D();
        _plane = new Plane(_p1,_p2,_p3);
        System.out.println(this.toString());
    }

    //2. wt param
    public Triangle(Point3D a, Point3D b, Point3D c) {
        System.out.println("\nTriangle -> 3 points constructor ##################################");
        if((a == null) || (b == null) || (c == null))
            System.out.println("ERROR: BAD POINTS CONTAINING NULL!!!");
        System.out.println("Setting values to p1 p2 p3 ");
        this._p1 = a;
        this._p2 = b;
        this._p3 = c;
        System.out.println("creating a plane from 3 points \n");
        this._plane = new Plane(a,b,c);
        System.out.println(this.toString());
        System.out.println("Triangle -> 3 points constructor - FINISHED- Null is not from here ######################################");

    }

    //3. Copy
    public Triangle(Triangle triangle) {
        _p1 = new Point3D(triangle._p1);
        _p2 = new Point3D(triangle._p2);
        _p3 = new Point3D(triangle._p3);
        _plane = new Plane(_p1,_p2,_p3);
        System.out.println(this.toString());
    }

    // Setters & Getters & equals & toString:
    public Point3D getP1() {
        return _p1;
    }

    public Point3D getP2() {
        return _p2;
    }

    public Point3D getP3() {
        return _p3;
    }

    public void setP1(Point3D point) {
        _p1 = new Point3D(point);
    }

    public void setP2(Point3D point) {
        _p2 = new Point3D(point);
    }

    public void setP3(Point3D point) {
        _p3 = new Point3D(point);
    }


    @Override
    public Vector getNormal(Point3D P0)
    {   // create vectors from p0 to triangle vertex
        return  new Plane(_p1, _p2, _p3)._n;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Plane p = new Plane(this._p1,this._p2,this._p3);
        if(p.findIntersections(ray) != null){

            Vector v1 = _p1.subtract(ray.get_p0());
            Vector v2 = _p2.subtract(ray.get_p0());
            Vector v3 = _p3.subtract(ray.get_p0());

            Vector n1 = v1.crossProduct(v2).normalize();
            Vector n2 = v2.crossProduct(v3).normalize();
            Vector n3 = v3.crossProduct(v1).normalize();

            float sign1 = Math.signum(n1.dotProduct(ray.getDirection()));
            float sigh2 = Math.signum(n2.dotProduct(ray.getDirection()));
            float sigh3 = Math.signum(n3.dotProduct(ray.getDirection()));

            if(sign1 == 0.0 || sigh2 == 0.0 || sigh3 == 0.0){
                return null;
            }
            if(sign1 == sigh2 && sign1 == sigh3){
                return p.findIntersections(ray);
            }
        }
        return null;
    }

    public String toString() {
        return ("Triangle:\n" + _plane + "\nThis are kodkodim:\n p1: " + _p1 + "\np2: " + _p2 + "\np3: " + _p3 );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle2 = (Triangle) o;
        return  this._p1.equals(triangle2._p1) && this._p2.equals(triangle2._p2) && this._p3.equals(triangle2._p3) ||
                this._p1.equals(triangle2._p1) && this._p2.equals(triangle2._p3) && this._p3.equals(triangle2._p2) ||
                this._p1.equals(triangle2._p2) && this._p2.equals(triangle2._p1) && this._p3.equals(triangle2._p3) ||
                this._p1.equals(triangle2._p2) && this._p2.equals(triangle2._p3) && this._p3.equals(triangle2._p1) ||
                this._p1.equals(triangle2._p3) && this._p2.equals(triangle2._p1) && this._p3.equals(triangle2._p2) ||
                this._p1.equals(triangle2._p3) && this._p2.equals(triangle2._p2) && this._p3.equals(triangle2._p1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_p1, _p2, _p3);
    }
}


/*
@Override
    public List<Point3D> findIntersections(Ray ray) {
        System.out.println("Triangle -> findIntersections -> plane: \n " + this._plane);
        // Does Ray have intersection with the plane of triangle?
        List<Point3D> intersections = this._plane.findIntersections(ray);
        System.out.println("This is the Point of intersect that Ray with Plane of triangle! :: ");
        System.out.println(intersections.get(0).toString());
        System.out.println("This is p0 Starting point of the Ray::\n"+ray.get_p0().toString());
        System.out.println("\n Now we check if its within the triangle area ::\n ");
        /// Build the Intersection vector from P0 (Origin or camera?)  to intersection point
        Vector V_intrsct = intersections.get(0).subtract(ray.get_p0());
        // Now we create a plane for every side of the triangle with P0, and take the normal to this plane:
        Vector N12 = new Plane(ray.get_p0(), _p1, _p2)._n;
        Vector N13 = new Plane(ray.get_p0(), _p1, _p3)._n;
        Vector N23 = new Plane(ray.get_p0(), _p2, _p3)._n;
        System.out.println("The intersect Vector "+ V_intrsct +"\nNormals:: \n N12 " + N12 + "\n N13 " + N13 +"\n N23 " + N23);
        // Get the dot product for every normal with the intersection Vector
        float d12 = V_intrsct.dotProduct(N12);
        float d13 = V_intrsct.dotProduct(N13);
        float d23 = V_intrsct.dotProduct(N23);
        System.out.println("Dot(N12 * V_intrsct) = " + d12);
        System.out.println("Dot(N13 * V_intrsct)) = " + d13);
        System.out.println("Dot(N23 * V_intrsct)) = " + d23);

        if((d12 > 0 && d13>0 && d23>0) || (d12 <0 && d13<0 && d23<0)){
            System.out.println("Case 1: Point is intersection within Triangle space, return ready list");
            return intersections;
        }
        else{
            System.out.println("Case 2: There are no intersection points within the triangle area - RETURN Null");
            return null;
        }
    }
 */
