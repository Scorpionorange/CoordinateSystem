import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * Created by ScorpionOrange on 2017/07/24.
 */
public class CoordinateSystem {
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            JFrame frame = new CoordinateSystemFrame();
            frame.setTitle("Coordinate System");
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            //frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

/**
 * A frame that contains a panel with Coordinate System drawings.
 */
class CoordinateSystemFrame extends JFrame{
    public CoordinateSystemFrame(){
        add(new CoordinateSystemComponent());
        pack();
    }
}

/**
 * A component that draw Coordinate System.
 */
class CoordinateSystemComponent extends JComponent{
    //get screen dimensions
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    int screenWidth = screenSize.width;
    int screenHeight = screenSize.height;

    Point2D center = new Point2D.Double(screenWidth / 2, screenHeight / 2);
    Point2D leftMiddle = new Point2D.Double(0, screenHeight / 2);
    Point2D rightMiddle = new Point2D.Double(screenWidth, screenHeight / 2);
    Point2D topMiddle = new Point2D.Double(screenWidth / 2, 0);
    Point2D bottomMiddle = new Point2D.Double(screenWidth / 2, screenHeight);

    public void paintComponent(Graphics graphics){
        Graphics2D graphics2D = (Graphics2D) graphics;

        // draw X-axis
        graphics2D.draw(new Line2D.Double(leftMiddle,rightMiddle));

        // draw Y-axis
        graphics2D.draw(new Line2D.Double(topMiddle, bottomMiddle));
    }

    public Dimension getPreferreiSize(){
        return new Dimension(screenWidth-100,screenHeight-100);
    }
}
