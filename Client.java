
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Client extends JFrame implements MouseListener
{
    private static int point_width = 10;
    private static int point_height = 10;
    private static int width = 500;
    private static int height = 500;
    private static Color line_color = Color.GREEN;
    private static Color text_color = Color.RED;

    Canvas canvas = new Canvas();
    ArrayList<Point> points = new ArrayList<>();
    Graphics2D g2d;

    public Client()
    {
        canvas.setPreferredSize(new Dimension(width, height));
        add(canvas);
        pack();
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g2d = (Graphics2D) canvas.getGraphics();
        init();
    }

    private void init()
    {
        addMouseListener(this);
        canvas.addMouseListener(this);
    }

    private void draw()
    {
        int size = points.size();
        if(size > 1)
        {
            g2d.clearRect(0, 0, width, height);

            for(Point p : points)
            {
                g2d.setColor(text_color);
                g2d.fillOval(p.X() - point_width / 2, p.Y() - point_height / 2, point_width, point_height);
            }

            for(int i = 0; i < size - 1; i++)
            {
                g2d.setColor(line_color);
                g2d.drawLine(points.get(i).X(),
                        points.get(i).Y(),
                        points.get(i + 1).X(),
                        points.get(i + 1).Y());
                g2d.setColor(text_color);
                g2d.drawString(String.valueOf(dist(i)),
                        Math.abs(points.get(i).x + points.get(i + 1).x) / 2,
                        Math.abs(points.get(i).y + points.get(i + 1).y) / 2);
            }
            g2d.setColor(line_color);
            g2d.drawLine(points.get(size - 1).X(),
                    points.get(size - 1).Y(),
                    points.get(0).X(),
                    points.get(0).Y());
            g2d.setColor(text_color);
            g2d.drawString(String.valueOf(dist(size - 1)),
                    Math.abs(points.get(size - 1).x + points.get(0).x) / 2,
                    Math.abs(points.get(size - 1).y + points.get(0).y) / 2);
        }
    }

    private int dist(int i)
    {
        int result = 0;

        if(i == points.size() - 1)
        {
            Point p1 = points.get(i);
            Point p2 = points.get(0);
            result = (int) Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
        }
        else
        {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            result = (int) Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
        }

        return result;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        System.out.println("CLICKED");
        points = Point.generate(5, width, height);
        draw();
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {

    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {

    }

    @Override
    public void mouseEntered(MouseEvent e) 
    {

    }

    @Override
    public void mouseExited(MouseEvent e) 
    {

    }

    public static void main(String[] args)
    {
        Client c = new Client();
    }
}
