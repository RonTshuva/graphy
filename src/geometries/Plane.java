package geometries;

import primitives.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;


public class Plane extends Geometry {
    //Fields: Parameters that define a plane
    Point3D _q; // A point on the plane
    Vector _n;  // A vector not on that plane - it's the normal to this plane.

    // Constructors:
    //1. Default - point on Origin
    public Plane() {
        _q = new Point3D(); /// POINT ON THE PLANE
        _n = new Vector().normalize();; /// THIS IS THE NORMAL
    }

    //2. wt param
    public Plane(Point3D p, Vector n) {
        _n = n.normalize();
        _q = new Point3D(p);
    }

    // Constructor from hani's file:
    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        System.out.println("    Plane -> 3 points constructor");
        // if p1=p2 or p1=p3 - an exception will be thrown
        System.out.println("    1.Plane -> 3 points constructor:: Creating Vector V1 (1 out 3) ...");
        Vector v1 = p1.subtract(p2).normalize();
        System.out.println("    2.Plane -> 3 points constructor:: Creating Vector V2 (2 out 3) ...");
        Vector v2 = p1.subtract(p3).normalize();
        System.out.println("    3.Plane -> 3 points constructor:: Creating Vector _n (3 out 3) Normal ... ");
        // if the points are in the same line - X-product will throw an exception
        this._n = v1.crossProduct(v2).normalize();
        System.out.println("    4.Plane -> 3 points constructor:: Creating Point3d On the plane!  ...");
        this._q = new Point3D(p1);
        System.out.println("point check " + this._q);
        System.out.println("    Plane -> 3 points constructor - FINISHED --------------------------------------------------------\n");
    }

    //3. Copy
    public Plane(Plane plane) {
        _q = new Point3D(plane._q);
        _n = new Vector(plane._n).normalize();
    }

    // Setters & Getters & equals & toString:
    public Point3D getPoint() {
        return _q;
    }

    public void setPoint(Point3D point3D) {
        _q = new Point3D(point3D);
    }

    public Vector getVector() {
        return _n;
    }

    public void setVector(Vector vector) {
        _n = new Vector(vector);
    }

    public String toString() {
        return ("\nPlane:\n   Point3D: " + this._q + "\n  Vector: " + this._n);
    }


    @Override
    public boolean equals(Object o) {          // To compare 2 planes we need to 1. see if the 2 vertices to the planes are equals. Because we normalize every vector we can use equals method.
        if (this == o) return true;            //2. Determine whether a point is in the other plane.  if the vertices are equals but the point is not in the other plane => the planes are parallel.
        if (o == null || getClass() != o.getClass()) return false;
        Plane plane2 = (Plane) o;
        return this.isPointInPlane(plane2._q) && Objects.equals(this._n, plane2._n);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_q, _n);
    }

    ///EX 4: GET NORMAL TO PLANE
    @Override
    public Vector getNormal(Point3D p) {
        return this._n;
    }
    /// get all intersection of plane with given ray


    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersections = new ArrayList<>();
        if (!this._q.equals(ray.get_p0())) {
            double numerator = this._n.dotProduct(this._q.subtract(ray.get_p0()));
            double denominator = this._n.dotProduct(ray.getDirection());
            if (denominator == 0) {       // The Ray is contained or parallel to the plane.
                return null;
            } else if ( 0 < numerator / denominator) {
                Point3D p = ray.get_p0().add(ray.getDirection().scale(numerator / denominator));
                intersections.add(p);
                return intersections;
            }
        }
        return null;

    }

    private boolean isPointInPlane(Point3D otherPoint){
        if(otherPoint.equals(this._q))
            return true;
        else{
            Vector vp = otherPoint.subtract(this._q);
            return(isZero(vp.dotProduct(this._n)));

        }
    }
}

/*
    @Override
   public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersections = new ArrayList<Point3D>();
        /// We want to check if N dot (Q0 - P ) = 0 (means P is on the plane).
        /// P is the point of intersection. P = (po + t * v) we will use the expression
        /// first get p0 and V:
        Point3D P;
        Point3D P0 = ray.get_p0();
        Vector V = ray.getDirection();
        Vector N = this._n; // normal to plane
        System.out.println("this is normal N " + N.toString());
        Vector V_Q0_P0;
        if (P0.toString() == _q.toString()) {
            System.out.println("This creates Zero in the fraction RETURN NULL");
            return null;}
        else {
            V_Q0_P0 = _q.subtract(P0); // To later use in formula
        }

        /// lets calculate "t" :
        //1. we need NV for denominator:
        double NV = N.dotProduct(V);
        System.out.println("this is NV denominator" + NV);
        if (NV == 0.0) {
            System.out.println("Denominator: cosinus is zero, hence V and the Plane are parallel - no intersection RETURN NULL");
            return null; // Why not return empty list tough???
        }
        //1. we need N dot VQ0-P0 for nominator:
        double N_Q0_P0 = N.dotProduct(V_Q0_P0);
        System.out.println("this is N_Q0_P0 nominator" + N_Q0_P0);
        if (isZero(N_Q0_P0)) {
            System.out.println("nominator: cosinus is zero, hence t is ZERO");
        }
        /// get "t" (t = how much to scale the direction vector "v" in order to get it to "P" )
        double t = ((N_Q0_P0) / (NV));
        System.out.println("this is t scalar " + t);
        /// Lets create P:
        P = (P0.add(V.scale(t)));
        System.out.println("this is P point " + P.toString());
        /// now we need a vector from point P (expression) of ray, to point Q that is on the plane.
        Vector V_qP = P.subtract(_q);
        System.out.println("this is V PQ " + V_qP.toString());

        int cosinus_Dot = (int)V_qP.dotProduct(N);
        System.out.println("P is a point on the plane! because Cos is = " + cosinus_Dot);
        /// return list containing the point of intersection
        if (cosinus_Dot == 0) {
            intersections.add(P);
            System.out.println("Point was added to List + + + ");
        }
        System.out.println("\nThis is the list: \n" + intersections+ "\n \n \n \n");
        return intersections;

    }
 */
