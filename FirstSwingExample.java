import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class FirstSwingExample extends Canvas {

    int x_res = 1280;
    int y_res = 720;
    int blockSize = 50;
    int x_grid;
    int y_grid;

    int start_x;

    int start_y;

    Color darkish_gray = new Color(128, 128, 128);
    Color lightish_gray = new Color(150, 150, 150);

    Map<Integer, Double> snakeXcords = new HashMap<>();
    Map<Integer, Double> snakeYcords = new HashMap<>();

    public FirstSwingExample() {
        x_grid = calculateGridSize(blockSize, x_res);
        y_grid = calculateGridSize(blockSize, y_res);
        System.out.println(x_grid + " and " + y_grid);
        start_x = x_grid / 2;
        start_y = y_grid / 2;
        System.out.println(start_x + " and " + start_y);
        snakeXcords.put(0, 1.0 * start_x);
        snakeYcords.put(0, 1.0 * start_y);
    }

    public void paint(Graphics g) {
        setBackground(Color.WHITE);

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
        g.setColor(Color.green);

    }

    private int calculateGridSize(int blockSize, int resolution) {
        return resolution / blockSize;
    }


    private void updateFrame() {
        paint(getGraphics());
    }

    private Cube CubesInit(int x) {
        return new Cube(x);
    }


    public static void main(String[] args) throws InterruptedException {
        FirstSwingExample m = new FirstSwingExample();
        JFrame f=new JFrame();//creating instance of JFrame


        f.add(m);
        f.setSize(m.calculateGridSize(m.blockSize, m.x_res) * m.blockSize + 16, m.calculateGridSize(m.blockSize, m.y_res) * m.blockSize + 38);
        //f.setLayout(null);
        f.setVisible(true);
        f.setResizable(false);
        m.updateFrame();

        Cube cube = m.CubesInit(120);

        do {

        } while (!UserInput.isKeyPressed(KeyEvent.VK_ESCAPE));

        System.exit(69);
    }
}