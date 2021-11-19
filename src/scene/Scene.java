package scene;

import elements.Camera;
import geometries.Geometry;

import java.util.ArrayList;
import java.util.List;

public class Scene {

    ///Fields:
    String _name;
    List<Geometry> _geometries;
    Camera _camera;

    // Constructors:
    //1. Default
    public Scene() {
        _name = "default scene name";
        _geometries = new ArrayList<>();
    }

    //2. wt param
    public Scene(String name, List<Geometry> geometries) {
        _name = name;
        _geometries = geometries;
    }

    // Setters & Getters & equals & toString:
    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public List<Geometry> getGeometries() {
        return _geometries;
    }

    public void setGeometries(List<Geometry> geometries) {
        _geometries = geometries;
    }

    public String toString() {
        StringBuilder stringToReturn = new StringBuilder("Geometries Scene:\n");
        _geometries.forEach(geometry -> stringToReturn.append(geometry.toString()).append("\n"));
        return stringToReturn.toString();
    }

    //Methods:
    public void addGeometry(Geometry geometry) {
        _geometries.add(geometry);
    }

    public void print() {
        _geometries.forEach(System.out::println);
    }
}
