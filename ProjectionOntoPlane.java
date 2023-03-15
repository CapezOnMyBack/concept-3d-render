import org.ejml.simple.SimpleMatrix;

import java.awt.geom.Point2D;

public class ProjectionOntoPlane {

    public Display display;

    public ProjectionOntoPlane() {
        display = new Display();
    }

    public Display getDisplay() {
        return this.display;
    }

    public double calculateK(double x, double y, double z) {
        return (display.getD() - (display.getA() * x) - (display.getB() * y) - (display.getC() * z)) / (Math.pow(display.getA(), 2) + Math.pow(display.getB(), 2) + Math.pow(display.getC(), 2));
    }

    public Vector3D calculateProjection(double x, double y, double z, double k) {
        double xp = x + k * display.getA();
        double yp = y + k * display.getB();
        double zp = z + k * display.getC();
        return new Vector3D(xp, yp, zp);
    }

    public SimpleMatrix getDisplayCoords(Vector3D vector) {
        SimpleMatrix eMatrix = new SimpleMatrix(
                new double[][] {
                        new double[] {display.e1.getX(), display.e1.getY(), display.e1.getZ()},
                        new double[] {display.e2.getX(), display.e2.getY(), display.e2.getZ()}
                }
        );

        SimpleMatrix pMatrix = new SimpleMatrix(
                new double[][] {
                        new double[] {vector.getX()},
                        new double[] {vector.getY()},
                        new double[] {vector.getZ()}
                }
        );

        return eMatrix.mult(pMatrix);
    }

    public Vector3D getLocation(SimpleMatrix matrix) {
        return new Vector3D(matrix.get(0), matrix.get(1), 0);
    }

    public Vector3D projectToPlaneCords(Vector3D vector) {
        double k = calculateK(vector.getX(), vector.getY(), vector.getZ());
        Vector3D v = calculateProjection(vector.getX(), vector.getY(), vector.getZ(), k);
        SimpleMatrix sm = getDisplayCoords(v);
        return getLocation(sm);
    }
}
