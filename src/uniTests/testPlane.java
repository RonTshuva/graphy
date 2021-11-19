package uniTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
//import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import geometries.Plane;
import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

//import geometries.*;
class testPlane {
    /*
    @Test
    public void testGetNormalPoint3D() {
        /// NEED TO SHORT THE DOUBLE THIS TEST FILE IS BAD TEST
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Plane pl = new Plane(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        Assertions.assertEquals(new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)), "Bad normal to plane");
    }
*/
    @Test
    public void testFindIntersectionsRay() {
        Plane pl = new Plane(new Point3D(0, 0, 1), new Vector(1, 1, 1));

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray into plane
        Assertions.assertEquals(Util.listOf(new Point3D(1, 0, 0)).get(0).toString(),
                pl.findIntersections(new Ray(new Point3D(0.5, 0, 0), new Vector(1, 0, 0))).get(0).toString(), "111 Bad plane intersection");

        // TC02: Ray out of plane
        //        Assertions.assertNull(
        //        pl.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(1, 0, 0))), " 22 Must not be plane intersection");

        // =============== Boundary Values Tests ==================
        // TC11: Ray parallel to plane
        Assertions.assertNull(
                pl.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(0, 1, -1))), "33 Must not be plane intersection");

        // TC12: Ray in plane
        Assertions.assertNull(
                pl.findIntersections(new Ray(new Point3D(0, 0.5, .5), new Vector(0, 1, -1))), "Must not be plane intersection");


        // TC13: Orthogonal ray into plane
        Assertions.assertEquals(Util.listOf( new Point3D(1d / 3, 1d / 3, 1d / 3)),
                pl.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(-1, -1, -1))), "Bad plane intersection");

        // TC14: Orthogonal ray out of plane
        Assertions.assertNull(
                pl.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(1, 1, 1))), "Must not be plane intersection");

        // TC15: Orthogonal ray out of plane
        Assertions.assertNull(
                pl.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(1, 1, 1))), "Must not be plane intersection");

        // TC16: Orthogonal ray from plane
        Assertions.assertNull(
                pl.findIntersections(new Ray(new Point3D(0, 0.5, 0.5), new Vector(1, 1, 1))), "Must not be plane intersection");

        // TC17: Ray from plane
        Assertions.assertNull(
                pl.findIntersections(new Ray(new Point3D(0, 0.5, 0.5), new Vector(1, 1, 0))), "Must not be plane intersection");

        // TC18: Ray from plane's Q point
        Assertions.assertNull(
                pl.findIntersections(new Ray(new Point3D(0, 0, 1), new Vector(1, 1, 0))), "Must not be plane intersection");

    }

}
