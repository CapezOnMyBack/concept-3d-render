public class Display {

    final double a = 6;
    final double b = 1;
    final double c = 1;

    final double d = 1;

    final Vector3D e1 = new Vector3D(-(1/Math.sqrt(37)), 6/Math.sqrt(37), 0);
    final Vector3D e2 = new Vector3D(-(1/Math.sqrt(37)), 0, 6/Math.sqrt(37));
    final Vector3D center = new Vector3D(0, 1, 0);
    final Vector3D origin;

    public Display() {
        this.origin = calcOrigin();
    }

    public double getA() {
        return this.a;
    }

    public double getB() {
        return this.b;
    }

    public double getC() {
        return this.c;
    }

    public double getD() {
        return this.d;
    }

    private Vector3D calcOrigin() {
        return (center.add(e1.multiply(640).add(e2.multiply(360))));
    }

    public Vector3D getNormal() {
        return new Vector3D(this.a, this.b, this.c);
    }
}
