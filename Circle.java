import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Circle {

    private Color color;
    private int diameter = 40; 
    private int x, y;
    private int dx, dy;

    public Circle(int x, int y, int v, Color c) {
        this.x = x;
        this.y = y;
        this.color = c;
        
        Random ran = new Random();
        dx = ran.nextInt((v*2) - v);
        dy = (int) Math.sqrt((v*v) - (dx*dx));
        if (ran.nextBoolean()) {
            dx = -dx;
        }
        if (ran.nextBoolean()) {
            dy = -dy;
        }
    }

    public void paint(Graphics g) {
        g.setColor(color);
        g.fillOval(x - diameter, y - diameter, diameter, diameter);
    }

    public void position(int Max_X, int Max_Y) {
        x += dx;
        y += dy;
        if (x - diameter < 0 || x + diameter > Max_X) {
            dx = -dx;
        }
        if (y - diameter < 0 || y + diameter > Max_Y) {
            dy = -dy;
        }
    }
}
