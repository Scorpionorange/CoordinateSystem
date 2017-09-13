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
            frame.setVisible(true);
            frame.setResizable(false);
        });
    }
}

/**
 * A frame that contains a panel with Coordinate System drawings.
 */
class CoordinateSystemFrame extends JFrame{
    public CoordinateSystemFrame(){

        //默认最大化窗口
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //集成化至JFrame内部
        this.setTitle("Coordinate System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

    Point2D center = new Point2D.Double(screenWidth / 2, (screenHeight - 150) / 2);
    Point2D leftMiddle = new Point2D.Double(0, (screenHeight - 150) / 2);
    Point2D rightMiddle = new Point2D.Double(screenWidth, (screenHeight - 150) / 2);
    Point2D topMiddle = new Point2D.Double(screenWidth / 2, 0);
    Point2D bottomMiddle = new Point2D.Double(screenWidth / 2, screenHeight - 75);

    int X0 = (int)center.getX();
    int Y0 = (int)center.getY();

    public void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        // draw X-axis
        graphics2D.draw(new Line2D.Double(leftMiddle, rightMiddle));

        // draw string "+X" and "-X"
        graphics2D.drawString("+X", (int) rightMiddle.getX() - 10, (int) rightMiddle.getY() - 5);
        graphics2D.drawString("-X", (int) leftMiddle.getX() + 10, (int) leftMiddle.getY() + 10);

        // draw Y-axis
        graphics2D.draw(new Line2D.Double(topMiddle, bottomMiddle));

        // draw string "+Y" and "-Y"
        graphics2D.drawString("+Y", (int) topMiddle.getX() + 5, (int) topMiddle.getY() + 10);
        graphics2D.drawString("-Y", (int) bottomMiddle.getX() - 15, (int) bottomMiddle.getY());

        // draw a testing line
        //graphics2D.drawLine(X0, Y0, X0 + 100, Y0 - 100);

        // testing F(x)
        for(int i = 0; i <= 150; i++){
            graphics2D.drawLine(X0 + i, F(X0 +i), X0 + i, F(X0 - i));
        }

    }

    // y = F(x) = 3x + 2;
    public int F(int x) {
        int y = 3 * x + 2;
        return y;
    }

    public Dimension getPreferreiSize(){
        return new Dimension(screenWidth-100,screenHeight-100);
    }
}
