
/**
 * 여기에 Graphics 클래스 설명을 작성하십시오.
 * 
 * @author (작성자 이름) 
 * @version (버전번호나 날짜)
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;
import java.util.*;
public class GraphicsEx extends JFrame
{
    private MyPanel panel = new MyPanel();
    Vector<String> shape = new Vector<String>();
    String draw = "";
    
    public GraphicsEx()
    {
        setTitle("Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        panel.setBackground(Color.WHITE);
        
        JButton rect = new JButton("Rect");
        JButton line = new JButton("Line");
        rect.addActionListener(new MyButton());
        line.addActionListener(new MyButton());
        rect.setOpaque(true);
        line.setOpaque(true);
        
        add(rect);
        add(line);
        
        setSize(300,300);
        setVisible(true);
    }
    class MyButton implements ActionListener
    {
        Graphics g = getGraphics();
        public void actionPerformed(ActionEvent e)
        {
            JButton b = (JButton)e.getSource();
            if(b.getText().equals("draw"))
                draw = "rect";
            else if(b.getText().equals("line"))
                draw = "line";
        }
    }
    class MyPanel extends JPanel
    {
        private Vector<Point> vStart = new Vector<Point>();
        private Vector<Point> vEnd = new Vector<Point>();
        public MyPanel()
        {
            addMouseListener(new MouseAdapter()
            {
                public void mousePressed(MouseEvent e)
                {
                    Point startP = e.getPoint();
                    vStart.add(startP);
                    shape.add((String)draw);
                }
                public void mouseReleased(MouseEvent e)
                {
                    Point endP = e.getPoint();
                    vEnd.add(endP);
                    repaint();
                }
            });
        }
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.setColor(Color.BLUE);
            for(int i=0;i<vStart.size();i++)
            {
                Point s = vStart.elementAt(i);
                Point e = vEnd.elementAt(i);
                if(draw == "rect")
                {
                    g.drawRect((int)s.getX(), (int)s.getY(), (int)e.getX()-(int)s.getX(), (int)e.getY()-(int)s.getY());
                    //shape.add((String)draw);
                }
                else if(draw == "line")
                {    
                    g.drawLine((int)s.getX(), (int)s.getY(), (int)e.getX(), (int)e.getY());
                    //shape.add((String)draw);
                }
            }
            for(int j=0;j<shape.size();j++)
            {
                String s = shape.get(j);
                System.out.println(s);
            }
        }
    }
    public static void main(String[] args)
    {
        new GraphicsEx();
    }
}
