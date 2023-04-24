import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class Main extends JPanel implements Runnable {
    private ArrayList<Circle> cirs;
    private Thread animationThread;
    private boolean isRunning;
    

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bouncing Ball");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Main());
        frame.setVisible(true);
    }

    public Main() {
        cirs = new ArrayList<Circle>();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    Random ran = new Random();
                    int x = e.getX();
                    int y = e.getY();
                    int v = ran.nextInt((8)+1);
                    Color c = new Color(ran.nextInt(128), ran.nextInt(173), ran.nextInt(135));
                    Circle cir = new Circle(x, y, v, c);
                    cirs.add(cir);
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    if (!cirs.isEmpty()) {
                         cirs.remove(cirs.size() - 1);
                    }
                }
            }
        });

        isRunning = true;
        animationThread = new Thread(this);
        animationThread.start();
    }

    @Override
    public void run() {
        while (isRunning) {
            for (Circle cir : cirs) {
                cir.position(getWidth(), getHeight());
            }
            repaint();
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Circle cir : cirs) {
            cir.paint(g);
        }
    }

    @Override
    public void addNotify() {
        super.addNotify();
        isRunning = true;
        animationThread = new Thread(this);
        animationThread.start();
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
        isRunning = false;
        try {
            animationThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
