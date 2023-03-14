public class Edge {

    final double x1;
    final double y1;
    final double z1;

    final double x2;
    final double y2;
    final double z2;
    final Vector3D vector;

    public Edge(double x1, double y1, double z1, double x2, double y2, double z2) {
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;

        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;

        this.vector = new Vector3D(x2 - x1, y2 - y1, z2 - z1);
    }

    /**
     * Creates an edge from given coordinate (first point) and the directional vector, which points to the second point.
     * @param x1 coordinate
     * @param y1 coordinate
     * @param z1 coordinate
     * @param vector the directional vector
     */

    public Edge(double x1, double y1, double z1, Vector3D vector) {
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;

        this.vector = vector;

        this.x2 = x1 + vector.getX();
        this.y2 = y1 + vector.getY();
        this.z2 = z1 + vector.getZ();
    }

    public Edge(Vector3D vector1, Vector3D vector2) {
        this.x1 = vector1.getX();
        this.y1 = vector1.getY();
        this.z1 = vector1.getZ();

        this.x2 = vector2.getX();
        this.y2 = vector2.getY();
        this.z2 = vector2.getZ();

        this.vector = vector2.subtract(vector1);
    }

    public Vector3D getVector() {
        return this.vector;
    }

    public double getX1() {
        return this.x1;
    }

    public double getY1() {
        return this.y1;
    }

    public double getZ1() {
        return this.z1;
    }

    public double getX2() {
        return this.x1;
    }

    public double getY2() {
        return this.y1;
    }

    public double getZ2() {
        return this.z1;
    }

    public double length() {
        return this.vector.getLength();
    }
}
