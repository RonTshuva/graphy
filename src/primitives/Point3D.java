package primitives;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;

public class Point3D {
    public final static Point3D ORIGIN = new Point3D(0, 0, 0);

    // Fields:
    Coordinate _x;
    Coordinate _y;
    Coordinate _z;

    // Constructors:
//1. Default - point on Origin

    public Point3D() {
        this._x = new Coordinate(0);
        this._y = new Coordinate(0);
        this._z = new Coordinate(0);
    }

    //2. wt param
    //2. wt param
    public Point3D(double x, double y, double z) {
        System.out.println("            Point3D -> 3 points constructor - double");
        this._x = new Coordinate(x);
        this._y = new Coordinate(y);
        this._z = new Coordinate(z);
        System.out.println("            Point3D -> 3 points constructor - double - Finished");
    }

    public Point3D(float x, float y, float z) {
        System.out.println("            Point3D -> 3 points constructor - float");
        this._x = new Coordinate(x);
        this._y = new Coordinate(y);
        this._z = new Coordinate(z);
        System.out.println("            Point3D -> 3 points constructor - float - Finished");

    }

    //3. Copy
    public Point3D(Point3D other) {
        System.out.println("            Point3D -> Copy constructor - (waste of code)");
        this._x = new Coordinate(other._x._theNumber);
        this._y = new Coordinate(other._y._theNumber);
        this._z = new Coordinate(other._z._theNumber);
    }
// Setters & Getters & equals & toString:

    public Coordinate get_X() {
        return _x;
    }

    public Coordinate get_Y() {
        return _y;
    }

    public Coordinate get_Z() {
        return _z;
    }

    public void set_X(Coordinate x) {
        this._x = new Coordinate(x);
    }

    public void set_Y(Coordinate y) {
        this._y = new Coordinate(y);
    }

    public void set_Z(Coordinate z) {
        this._z = new Coordinate(z);
    }

    /// A long equals method for point3d
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point2 = (Point3D) o;
        _x.equals(point2._x);
        _y.equals(point2._y);
        _z.equals(point2._z);
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_x, _y, _z);
    }

    public String toString() {
        return "{ " + _x + ", " + _y + ", " + _z + " }";
    }

// Methods:

    public Point3D add(@NotNull Vector v) {
        double x = this._x._theNumber + v._head._x._theNumber;
        double y = this._y._theNumber + v._head._y._theNumber;
        double z = this._z._theNumber + v._head._z._theNumber;
        return new Point3D(x, y, z);
    }

    public Vector subtract(Point3D other) {
        double x = _x._theNumber - other._x._theNumber;
        double y = _y._theNumber - other._y._theNumber;
        double z = _z._theNumber - other._z._theNumber;
        return new Vector(x, y, z);
    }

    public double distance(Point3D other) {
        double xSquared = Math.pow(_x._theNumber - other._x._theNumber, 2);
        double ySquared = Math.pow(_y._theNumber - other._y._theNumber, 2);
        double zSquared = Math.pow(_z._theNumber - other._z._theNumber, 2);
        return Math.sqrt(xSquared + ySquared + zSquared);
    }

}
