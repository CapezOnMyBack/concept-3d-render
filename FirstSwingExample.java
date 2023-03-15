import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.security.Key;
import java.util.*;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class FirstSwingExample extends Canvas {

    int x_res = 1280;
    int y_res = 720;
    int blockSize = 50;
    int x_grid;
    int y_grid;

    int start_x;

    int start_y;

    List<Edge> list;
    Vector3D vector;

    Color darkish_gray = new Color(128, 128, 128);
    Color lightish_gray = new Color(150, 150, 150);

    ProjectionOntoPlane potp;
    RotateOnAxis roa;

    public FirstSwingExample() {
        x_grid = calculateGridSize(blockSize, x_res);
        y_grid = calculateGridSize(blockSize, y_res);
        start_x = x_grid / 2;
        start_y = y_grid / 2;
        this.list = new ArrayList<>();
        this.vector = new Vector3D(0, 0, 0);
        potp = new ProjectionOntoPlane();
        roa = new RotateOnAxis();
    }

    public void setList(List<Edge> list) {
        this.list = list;
    }

    public void setVector(Vector3D vector) {
        this.vector = vector;
    }

    public void paint(Graphics g) {
        super.paint(g);
        setBackground(Color.BLACK);

        for (int i = 0; i < x_grid; i++) {
            for (int j = 0; j < y_grid; j++) {
                if ((i + j) % 2 == 0) {
                    g.setColor(darkish_gray);
                } else {
                    g.setColor(lightish_gray);
                }
                g.fillRect(i * blockSize, j * blockSize, blockSize, blockSize);

            }
        }
        g.setColor(Color.black);
        drawShape(g, list);
        g.setColor(Color.cyan);
        g.drawLine(640, 360,(int) (640 + vector.getX()), (int) (360 - vector.getY()));
    }

    private void drawShape(Graphics g, List<Edge> list) {

        for (int i = 0; i < list.size(); i++) {
            g.drawLine(640 + (int) list.get(i).getX1(), 360 - (int) list.get(i).getY1(), (int) (640 + list.get(i).getX2()), (int) (360 - list.get(i).getY2()));
        }
    }

    private int calculateGridSize(int blockSize, int resolution) {
        return resolution / blockSize;
    }


    private void updateFrame(List<Edge> list) {
        setList(list);
        paint(getGraphics());
    }

    private void updateFrameV(Vector3D vector, List<Edge> list) {
        setVector(vector);
        updateFrame(list);
    }


    private Cube CubesInit(int x) {
        return new Cube(x);
    }

    public List<Edge> projectEdgesOntoPlane(List<Edge> list) {
        List<Edge> resList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {

            Edge currentEdge = list.get(i);

            double xPoint = currentEdge.getX1();
            double yPoint = currentEdge.getY1();
            double zPoint = currentEdge.getZ1();
            Vector3D firstPoint = new Vector3D(xPoint, yPoint, zPoint);

            double xPoint2 = currentEdge.getX2();
            double yPoint2 = currentEdge.getY2();
            double zPoint2 = currentEdge.getZ2();
            Vector3D secondPoint = new Vector3D(xPoint2, yPoint2, zPoint2);

            Vector3D v1 = potp.projectToPlaneCords(firstPoint);
            Vector3D v2 = potp.projectToPlaneCords(secondPoint);

            resList.add(new Edge(v1, v2));
        }

        return resList;
    }

    public List<Edge> rotateEdges(List<Edge> list, int direction, double angle) {
        List<Edge> resList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {

            Edge currentEdge = list.get(i);

            double xPoint = currentEdge.getX1();
            double yPoint = currentEdge.getY1();
            double zPoint = currentEdge.getZ1();
            Vector3D firstPoint = new Vector3D(xPoint, yPoint, zPoint);

            double xPoint2 = currentEdge.getX2();
            double yPoint2 = currentEdge.getY2();
            double zPoint2 = currentEdge.getZ2();
            Vector3D secondPoint = new Vector3D(xPoint2, yPoint2, zPoint2);
            Vector3D v1;
            Vector3D v2;

            if (direction == 0) {
                v1 = roa.rotateOnX(firstPoint, angle);
                v2 = roa.rotateOnX(secondPoint, angle);
            } else if (direction == 1) {
                v1 = roa.rotateOnY(firstPoint, angle);
                v2 = roa.rotateOnY(secondPoint, angle);
            } else if (direction == 2) {
                v1 = roa.rotateOnZ(firstPoint, angle);
                v2 = roa.rotateOnZ(secondPoint, angle);
            } else {
                v1 = new Vector3D(0, 0, 0);
                v2 = new Vector3D(0, 0, 0);
            }


            resList.add(new Edge(v1, v2));
        }

        return resList;
    }


    public static void main(String[] args) throws InterruptedException {
        FirstSwingExample m = new FirstSwingExample();
        JFrame f=new JFrame();//creating instance of JFrame
        ProjectionOntoPlane pp = new ProjectionOntoPlane();


        f.add(m);
        f.setSize(m.calculateGridSize(m.blockSize, m.x_res) * m.blockSize + 16, m.calculateGridSize(m.blockSize, m.y_res) * m.blockSize + 38);
        //f.setLayout(null);
        f.setVisible(true);
        f.setResizable(false);


        Cube cube = m.CubesInit(120);
        double x = -1;
        double y = 0;
        double z = 0;
        Vector3D testCords = new Vector3D(x * 100, y * 100, z * 100);
        Vector3D finalCords = pp.projectToPlaneCords(testCords);
        List<Edge> cubeEdgeList = cube.getEdgeList();

        do {

            if (UserInput.isKeyPressed(KeyEvent.VK_D)) {
                cubeEdgeList = m.rotateEdges(cubeEdgeList, 0, 5);
            } else if (UserInput.isKeyPressed(KeyEvent.VK_A)) {
                cubeEdgeList = m.rotateEdges(cubeEdgeList, 0, -5);
            } else if (UserInput.isKeyPressed(KeyEvent.VK_W)) {
                cubeEdgeList = m.rotateEdges(cubeEdgeList, 1, 5);
            } else if (UserInput.isKeyPressed(KeyEvent.VK_S)) {
                cubeEdgeList = m.rotateEdges(cubeEdgeList, 1, -5);
            } else if (UserInput.isKeyPressed(KeyEvent.VK_E)) {
                cubeEdgeList = m.rotateEdges(cubeEdgeList, 2, 5);
            } else if (UserInput.isKeyPressed(KeyEvent.VK_Q)) {
                cubeEdgeList = m.rotateEdges(cubeEdgeList, 2, -5);
            }
            List<Edge> list2 = m.projectEdgesOntoPlane(cubeEdgeList);
            m.updateFrameV(finalCords, list2);
            TimeUnit.MILLISECONDS.sleep(50);

        } while (!UserInput.isKeyPressed(KeyEvent.VK_ESCAPE));

        System.exit(69);
    }
}