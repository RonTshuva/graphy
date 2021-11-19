import elements.Camera;
import primitives.Point3D;
import primitives.Vector;

public class Main {

    public static void main(String[] args){

        Camera c1 = new Camera(Point3D.ORIGIN, new Vector(0,0,1),new Vector(0,-1,0));
    }
}
