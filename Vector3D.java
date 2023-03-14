

public class Vector3D {

    double x;
    double y;
    double z;

    public Vector3D (final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getLength() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2) );
    }

    public Vector3D subtract(Vector3D vector) {
        double x = this.getX() - vector.getX();
        double y = this.getY() - vector.getY();
        double z = this.getZ() - vector.getZ();
        return new Vector3D(x, y, z);
    }

}
