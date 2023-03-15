import org.ejml.simple.SimpleMatrix;

import java.math.MathContext;

public class RotateOnAxis {

    public RotateOnAxis() {
    }

    public Vector3D rotateOnX(Vector3D vector, double angle) {
        SimpleMatrix rotMatX = createRotMatX(angle);
        SimpleMatrix pointToRotate = convertVecToMat(vector);
        SimpleMatrix rotatedPointMat = rotMatX.mult(pointToRotate);
        return convertMatToVec(rotatedPointMat);
    }

    public Vector3D rotateOnY(Vector3D vector, double angle) {
        SimpleMatrix rotMatY = createRotMatY(angle);
        SimpleMatrix pointToRotate = convertVecToMat(vector);
        SimpleMatrix rotatedPointMat = rotMatY.mult(pointToRotate);
        return convertMatToVec(rotatedPointMat);
    }

    public Vector3D rotateOnZ(Vector3D vector, double angle) {
        SimpleMatrix rotMatZ = createRotMatZ(angle);
        SimpleMatrix pointToRotate = convertVecToMat(vector);
        SimpleMatrix rotatedPointMat = rotMatZ.mult(pointToRotate);
        return convertMatToVec(rotatedPointMat);
    }

    private SimpleMatrix createRotMatZ(double angle) {
        return new SimpleMatrix(
                new double[][] {
                        new double[] {Math.cos(Math.toRadians(angle)), -(Math.sin(Math.toRadians(angle))), 0, 0},
                        new double[] {Math.sin(Math.toRadians(angle)), Math.cos(Math.toRadians(angle)), 0, 0},
                        new double[] {0, 0, 1, 0},
                        new double[] {0, 0, 0, 1}
                }
        );
    }

    private SimpleMatrix createRotMatY(double angle) {
        return new SimpleMatrix(
                new double[][] {
                        new double[] {Math.cos(Math.toRadians(angle)), 0, Math.sin(Math.toRadians(angle)), 0},
                        new double[] {0, 1, 0, 0},
                        new double[] {-(Math.sin(Math.toRadians(angle))), 0, Math.cos(Math.toRadians(angle)), 0},
                        new double[] {0, 0, 0, 1}
                }
        );
    }

    private SimpleMatrix createRotMatX(double angle) {
        return new SimpleMatrix(
                new double[][] {
                        new double[] {1, 0, 0, 0},
                        new double[] {0, Math.cos(Math.toRadians(angle)), -(Math.sin(Math.toRadians(angle))), 0},
                        new double[] {0, Math.sin(Math.toRadians(angle)), Math.cos(Math.toRadians(angle)), 0},
                        new double[] {0, 0, 0, 1}
                }
        );
    }

    private SimpleMatrix convertVecToMat(Vector3D vector) {
        return new SimpleMatrix(
                new double[][] {
                        new double[] {vector.getX()},
                        new double[] {vector.getY()},
                        new double[] {vector.getZ()},
                        new double[] {1}
                }
        );
    }

    private Vector3D convertMatToVec(SimpleMatrix sm) {
        return new Vector3D(sm.get(0), sm.get(1), sm.get(2));
    }
}
