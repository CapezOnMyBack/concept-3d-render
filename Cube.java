
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Cube {

    final Vector3D p1;
    final Vector3D p2;
    final Vector3D p3;
    final Vector3D p4;
    final Vector3D p5;
    final Vector3D p6;
    final Vector3D p7;
    final Vector3D p8;

    final List<Vector3D> pointsList = new ArrayList<>();

    final List<Edge> edgeList = new ArrayList<>();



    public Cube(int scale) {
        //creates a cube with 8 points
        this.p1 = new Vector3D(0, 0, 0);
        this.p2 = new Vector3D(scale, 0, 0);
        this.p3 = new Vector3D(0, scale, 0);
        this.p4 = new Vector3D(0, scale, scale);
        this.p5 = new Vector3D(0, 0, scale);
        this.p6 = new Vector3D(scale, 0, scale);
        this.p7 = new Vector3D(0, scale, scale);
        this.p8 = new Vector3D(scale, scale, scale);
        this.pointsList.add(this.p1);
        this.pointsList.add(this.p2);
        this.pointsList.add(this.p3);
        this.pointsList.add(this.p4);
        this.pointsList.add(this.p5);
        this.pointsList.add(this.p6);
        this.pointsList.add(this.p7);
        this.pointsList.add(this.p8);
        createEdges();
    }

    public List<Vector3D> getPointsAsList() {
        return this.pointsList;
    }

    public int getAmountPoints() {
        return this.pointsList.size();
    }

    public List<Edge> getEdgeList() {
        return this.edgeList;
    }

    public int getAmountEdges() {
        return this.edgeList.size();
    }

    private void createEdges() {
        //bottom edges
        edgeList.add(new Edge(this.p1, this.p2));
        edgeList.add(new Edge(this.p1, this.p3));
        edgeList.add(new Edge(this.p4, this.p2));
        edgeList.add(new Edge(this.p4, this.p3));
        //top edges
        edgeList.add(new Edge(this.p8, this.p6));
        edgeList.add(new Edge(this.p8, this.p7));
        edgeList.add(new Edge(this.p6, this.p5));
        edgeList.add(new Edge(this.p7, this.p5));
        //side edges
        edgeList.add(new Edge(this.p5, this.p1));
        edgeList.add(new Edge(this.p8, this.p4));
        edgeList.add(new Edge(this.p6, this.p2));
        edgeList.add(new Edge(this.p7, this.p3));
    }

}
