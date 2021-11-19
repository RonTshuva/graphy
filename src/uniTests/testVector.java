package uniTests;

import static org.junit.Assert.*;

import org.junit.Test;

//import static primitives.Util.*;
import primitives.*;

public class testVector {
    @Test
    public void testLength() {
        // TC01: Simple test
        assertEquals("length() wrong value", 5d, new Vector(0, 3, 4).length(), 0.00001);
    }

    @Test
    public void testNormalized() {
        Vector v = new Vector(0, 3, 4);
        Vector n = v.normalize();
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertFalse("normalized() changes the vector itself", v == n);
        assertEquals("wrong normalized vector length", 1d, n.length(), 0.00001);
    }

    @Test
    public void testAdd() {
        // TC01: Simple test
        assertEquals("Wrong vector add", new Vector(1, 1, 1).equals(new Vector(2, 3, 4).add(new Vector(-1, -2, -3))), true);
        // TC11: test adding v + (-v)
        try {
            new Vector(1, 2, 3).add(new Vector(-1, -2, -3));
            fail("Add v plus -v must throw exception");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testSubtract() {
        // TC01: Simple test
        assertEquals("Wrong vector subtract", new Vector(1, 1, 1).equals(new Vector(2, 3, 4).subtract(new Vector(1, 2, 3))),
                true);

        // TC11: test subtracting same vector
        try {
            new Vector(1, 2, 3).subtract(new Vector(1, 2, 3));
            fail("Subtract v from v must throw exception");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testScale() {
        // TC01: Simple test
        assertEquals("Wrong vector scale", new Vector(2, 4, 6).equals(new Vector(1, 2, 3).scale(2)),
                true);

        // TC11: testmult by 0
        try {
            new Vector(1, 2, 3).scale(0);
            fail("Scale by 0 must throw exception");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testDotProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // TC01: Simple dotProduct test
        Vector v2 = new Vector(-2, -4, -6);
        assertEquals("dotProduct() wrong value", -28, v1.dotProduct(v2), 0.00001);

        // TC11: dotProduct for orthogonal vectors
        Vector v3 = new Vector(0, 3, -2);
        assertEquals("dotProduct() for orthogonal vectors is not zero", 0, v1.dotProduct(v3), 0.00001);
    }

    @Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v2.length(), vr.length(), 0.00001);

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", vr.dotProduct(v1) == 0);// isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", vr.dotProduct(v2) == 0);// isZero(vr.dotProduct(v2)));

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        try {
            v1.crossProduct(v3);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {
        }
    }

    @Test
    public void testPointSubtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals("Wrong point subtract", new Vector(1, 1, 1).equals(new Point3D(2, 3, 4).subtract(new Point3D(1, 2, 3))),
                true);

        // =============== Boundary Values Tests ==================
        // TC11: test subtracting same point
        try {
            new Point3D(1, 2, 3).subtract(new Point3D(1, 2, 3));
            fail("Subtract P from P must throw exception");
        } catch (IllegalArgumentException e) {
        }
    }

    /*@Test
    public void testListGeometries(){
        List<Geometry> testList1 = new ArrayList<>();
        Point3D p1 = new Point3D(1,2,3);
        Point3D p2 = new Point3D(4,5,6);
        Point3D p3 = new Point3D(7,8,9);
        Triangle triangle1 = new Triangle(p1,p2,p3);
        Triangle triangle2 = new Triangle(p1,p2,p3);
        testList1.add(triangle1);
        Scene scene = new Scene("test scene", testList1 );
        scene.addGeometry(triangle2);
        System.out.println(scene);
    }*/

}
