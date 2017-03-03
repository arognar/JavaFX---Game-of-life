import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by hyperman on 22.02.2017.
 */
public class GameBoard extends Region implements Observer{
    private Map<Point2D,Node> nodes = new HashMap<>();
    private static final int WIDTH = 600;
    private static final int HEIGTH = 600;
    private int rowCount = 60;
    private int colCount =60;

    public GameBoard(){

        setPrefHeight(HEIGTH);
        setPrefWidth(WIDTH);


        for (int x = 0; x < rowCount; x++) {
            for (int y = 0; y < colCount; y++) {
                Rectangle r = new Rectangle(10,10,Color.WHITE);
                r.setStroke(Color.LIGHTGRAY);
                nodes.put(new Point2D(x,y),r);
                getChildren().add(nodes.get(new Point2D(x,y)));
            }
        }
    }

    public void update(Observable o, Object arg) {
        Map<Point2D,Integer> nodes = (Map<Point2D,Integer>)arg;
        for (int x = 0; x < rowCount; x++) {
            for (int y = 0; y < colCount; y++) {
                if(nodes.get(new Point2D(x,y)) == 1) {
                    ((Rectangle)this.nodes.get(new Point2D(x,y))).setFill(Color.BLACK);
                } else {
                    ((Rectangle)this.nodes.get(new Point2D(x,y))).setFill(Color.WHITE);
                }
            }
        }
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        for (int x = 0; x < rowCount; x++) {
            for (int y = 0; y < colCount; y++) {
                Node n = nodes.get(new Point2D(x,y));
                n.relocate(x * 10, y * 10);
            }
        }


    }
}
