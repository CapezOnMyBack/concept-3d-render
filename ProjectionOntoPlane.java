import org.ejml.simple.SimpleMatrix;

import java.awt.geom.Point2D;

public class ProjectionOntoPlane {

    Display display;

    public ProjectionOntoPlane() {
        display = new Display();
    }

    public double calculateK(double x, double y, double z) {
        return (display.getD() - display.a * x - display.b * y - display.c * z) / (Math.pow(display.getA(), 2) + Math.pow(display.getB(), 2) + Math.pow(display.getC(), 2));
    }

    public Vector3D calculateProjection(double x, double y, double z, double k) {
        double xp = x + k * display.getA();
        double yp = y + k * display.getB();
        double zp = z + k * display.getC();
        return new Vector3D(xp, yp, zp);
    }

    public SimpleMatrix getDisplayCoords(Vector3D vector) {
        //TODO: return is wrong, just the first matrix containing e1 and e2...
        return new SimpleMatrix(
                new double[][] {
                        new double[] {display.e1.getX(), display.e1.getY(), display.e1.getZ()},
                        new double[] {display.e2.getX(), display.e2.getY(), display.e2.getZ()}
                }
        );
    }
}
