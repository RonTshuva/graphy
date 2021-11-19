package elements;

import primitives.*;

import static primitives.Util.*;

public class Camera {
    private Point3D _p0;
    private Vector _vTo;
    private Vector _vUp;
    private Vector _vLeft;

    /// Constructors:
    public Camera(Point3D p0, Vector vTo, Vector vUp) {
        if (!isZero(vTo.dotProduct(vUp)))
            throw new IllegalArgumentException("Camera V_UP and V_TOWARDS direction aren't orthogonal");
        _p0 = new Point3D(p0);
        _vTo = new Vector(vTo).normalize();
        _vUp = new Vector(vUp).normalize();
        _vLeft = new Vector(vTo.crossProduct(_vUp)).normalize();
    }

    ///Methods:
    public Ray constructRayThroughPixel(int Nx, int Ny, int j, int i, double
            screenDistance, double screenWidth, double screenHeight) {
        //Fix pixel locations
        //i = Nx - i - 1;

        Point3D p0 = _p0;
        Vector direction = _vTo;
        Vector up = this._vUp;
        Vector right = this._vLeft; // from point of view its going right but in reality i



        //A. Image center point
        Point3D Pc = p0.add(direction.scale(screenDistance));

        //B. Pixel ratios
        double Rx = (screenWidth / Nx); //Pixel width
        double Ry = (screenHeight / Ny); //Pixel height

        //Center pixel
        double Xj = (j - Nx / 2.0) * Rx + (Rx / 2.0);
        double Yi = (i - Ny / 2.0) * Ry + (Ry / 2.0);


        /// a vector in the exact length to direct from Pc to Pij

        Vector vProduct = (
                (right.scale(Xj)).subtract(up.scale(Yi))
        );
        Point3D p_ij = new Point3D(Pc.add(vProduct));

        Vector v_ij = p_ij.subtract(p0);

        return new Ray(p0, v_ij.normalize());
    }
}