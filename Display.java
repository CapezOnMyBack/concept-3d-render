public class Display {

    double a;
    double b;
    double c;

    final double d;

    final Vector3D e1;
    final Vector3D e2;
    final Vector3D center;
//    final Vector3D origin;

    public Display() {
        this.a = 6;
        this.b = 1;
        this.c = 1;
        this.d = 1;
        this.e1 = new Vector3D(-(1/Math.sqrt(37)), 6/Math.sqrt(37), 0);
        this.e2 = new Vector3D(-(1/Math.sqrt(37)), 0, 6/Math.sqrt(37));
        this.center = new Vector3D(0, 1, 0);
//        this.origin = calcOrigin();
    }

    public double getA() {
        return this.a;
    }
    public void addA(double a) {
        this.a = this.a + a;
    }

    public double getB() {
        return this.b;
    }
    public void addB(double b) {
        this.b = this.b + b;
    }

    public double getC() {
        return this.c;
    }
    public void addC(double c) {
        this.c = this.c + c;
    }

    public double getD() {
        return this.d;
    }

    private Vector3D calcOrigin() {
        return (center.add(e1.multiply(-640).add(e2.multiply(-360))));
    }

    public Vector3D getNormal() {
        return new Vector3D(this.a, this.b, this.c);
    }
}
