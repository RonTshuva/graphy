package primitives;

import org.junit.Assert;

import java.text.DecimalFormat;

public class Coordinate {
    //Fields ::
    float _theNumber;

    // Constructors:
    //1. Default
    public Coordinate() {
        this._theNumber = 0;
    }

    //2. wt param
    public Coordinate(double coordinate) {
        System.out.println("                Coordinate -> Constructor from double");
        String format_number = new DecimalFormat("##.#####").format(coordinate);
        float cor = Float.parseFloat(format_number);
        this._theNumber = cor;
    }

    public Coordinate(float coordinate) {
        String format_number = new DecimalFormat("##.#####").format(coordinate);
        float cor = Float.parseFloat(format_number);
        this._theNumber = cor;
    }

    //3. Copy
    public Coordinate(Coordinate other) {
        this._theNumber = (float) other._theNumber;
    }

    // Setters & Getters & equals & toString:
    public double getCoordinate() {
        return this._theNumber;
    }

    public void setCoordinate(double number) {
        this._theNumber = (float) number;
    }



    public void equals(Coordinate coordinate2) {
        Assert.assertEquals("Coordinates are not Equal", this._theNumber, coordinate2._theNumber, 0.0004f);
    }




    public String toString() {
        return "" + _theNumber;
    }


}
