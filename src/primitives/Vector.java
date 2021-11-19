// חישוב אורך הוקטור	public double length()
//נרמול הוקטור	public Vector normalize()
//חיבור וקטורי	public Vector add(Vector other)
//חיסור וקטורי	public Vector subtract(Vector other)
//מכפלת וקטור בסקלאר  (scale)	public Vector scale(double scalar)
//מכפלה וקטורית
//מחזיר וקטור שלישי שניצב לשני הוקטורים  הקיימים	public Vector crossProduct(Vector other)
//מכפלה סקלארית
//מחזיר 0 כאשר הוקטורים ניצבים	public double dotProduct(Vector other)
package primitives;

import java.awt.*;
import java.util.Objects;

public class Vector {
    //Fields :
    Point3D _head;

    // Constructors:
    public Vector() {
        this._head = new Point3D(1, 1, 1);
    }

    //0. Default
    public Vector(double x, double y, double z) {
        System.out.println("        Vector -> 3 points constructor");
        System.out.println("        Vector -> 3 points constructor:: creating _head point");
        this._head = new Point3D(x, y, z);
        System.out.println("        Vector -> 3 points constructor:: creating _head point #Ended");
        if (x == 0 && y==0 && z==0)
            throw new IllegalArgumentException("1. Zero vector is not allowed") {
            };
        System.out.println("        Vector -> 3 points constructor - FINISHED\n");
    }

    public Vector(Point3D p) {
        if (Point3D.ORIGIN.equals(p))
            throw new IllegalArgumentException("2. Zero vector is not allowed");
        this._head = new Point3D(p);
    }

    //1. Vector from point
    //public Vector(Point3D head) {
    //   new Vector(head._x._theNumber, head._y._theNumber, head._z._theNumber);
    //}

    //2. copy.
    public Vector(Vector other) {
        //if (Point3D.ORIGIN.equals(other._head))
        //throw new IllegalArgumentException("ERROR: This head creates the zero vector");
        this._head = other._head;
    }

    // Setters & Getters & equals & toString:
    public Point3D getHead() {
        return this._head;
    }

    public void setHead(Point3D head) {
        this._head = new Point3D(head);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector2 = (Vector) o;
        return this.normalize()._head.equals(vector2.normalize()._head);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_head);
    }

    public String toString() {
        return _head.toString();
    }

    // Methods:
    private double lengthSquared() {
        double x = _head._x._theNumber * _head._x._theNumber;
        double y = _head._y._theNumber * _head._y._theNumber;
        double z = _head._z._theNumber * _head._z._theNumber;
        return (x + y + z);
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public Vector normalize() {
        System.out.println("        Vector -> normalize Method::");
        double len = length();

        double x =(this._head._x._theNumber / len);
        double y = (this._head._y._theNumber / len);
        double z= (this._head._z._theNumber / len);
        System.out.println("        Vector -> normalize Method:: Finished");
        return new Vector(x,y,z);
    }

    public Vector add(Vector other) {
        double x = (_head._x._theNumber + other._head._x._theNumber);
        double y = (_head._y._theNumber + other._head._y._theNumber);
        double z = (_head._z._theNumber + other._head._z._theNumber);
        Vector V = new Vector(x, y, z);
        return V;
    }

    public Vector subtract(Vector other) {
        double x = _head._x._theNumber - other._head._x._theNumber;
        double y = _head._y._theNumber - other._head._y._theNumber;
        double z = _head._z._theNumber - other._head._z._theNumber;
        return new Vector(x, y, z);
    }

    public Vector scale(double scalar) {
        return new Vector((_head._x._theNumber * scalar), (_head._y._theNumber * scalar), (_head._z._theNumber * scalar));
    }

    public Vector crossProduct(Vector other) {
        System.out.println("        Vector -> crossProduct Method::");
        return new Vector(
                ((_head._y._theNumber * other._head._z._theNumber) - (_head._z._theNumber * other._head._y._theNumber)),
                ((_head._z._theNumber * other._head._x._theNumber) - (_head._x._theNumber * other._head._z._theNumber)),
                ((_head._x._theNumber * other._head._y._theNumber) - (_head._y._theNumber * other._head._x._theNumber))
        );
    }

    public float dotProduct(Vector other) {
        float[] x = {this._head._x._theNumber, (this._head._y._theNumber),this._head._z._theNumber};
        float[] y = {other._head._x._theNumber, (other._head._y._theNumber),other._head._z._theNumber};
        int N = x.length;
        float sum = 0;
        for(int i =0; i<N; i++){
            sum = sum + x[i]*y[i];
        }
        return sum;
    }


}
