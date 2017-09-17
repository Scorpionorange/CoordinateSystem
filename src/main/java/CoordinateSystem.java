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

    //int X0, Y0;
    int X0 = (int)center.getX();
    int Y0 = (int)center.getY();

    Graphics2D graphics2D;

    public void paintComponent(Graphics graphics) {
        graphics2D = (Graphics2D) graphics;

        setOrigin();

        // draw a testing line
        Coordinate2D C2D01 = new Coordinate2D(100, 100);
        Coordinate2D C2D02 = new Coordinate2D(500, 400);
        Coordinate2D C2D03 = new Coordinate2D(500, 100);
        graphics2D.drawLine(C2D01.getPixelPointX(), C2D01.getPixelPointY(), C2D02.getPixelPointX(), C2D02.getPixelPointY());
        graphics2D.drawLine(C2D02.getPixelPointX(), C2D02.getPixelPointY(),C2D03.getPixelPointX(), C2D03.getPixelPointY());
        graphics2D.drawLine(C2D03.getPixelPointX(), C2D03.getPixelPointY(),C2D01.getPixelPointX(), C2D01.getPixelPointY());

        int A = 0, B;
        for(int i = 0; i > -100; i--){
            A += i;
            B= 2*A + 1;
            drawPoint(A , B);
        }
    }

    //switching Coordinate System to Origin point (X0, Y0)
    public class Coordinate2D{
        int x, y;
        public Coordinate2D(int x, int y){
            this.x = x;
            this.y = y;
        }
        public int getPixelPointX(){
            return X0 + x;
        }
        public int getPixelPointY(){
            return Y0 - y;
        }
    }

    // default Origin Point at Center
    public void setOrigin(){
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

    }

    public void drawPoint(int x, int y){
        int X = new Coordinate2D(x, y).getPixelPointX();
        int Y = new Coordinate2D(x, y).getPixelPointY();
        graphics2D.drawLine(X, Y, X, Y);
    }

    public void drawPoint(Point2D point2D){
        int x = (int)point2D.getX();
        int y = (int)point2D.getY();
        drawPoint(x, y);
    }

    public Dimension getPreferreiSize(){
        return new Dimension(screenWidth-100,screenHeight-100);
    }
}
