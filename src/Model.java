import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Point2D;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * Created by hyperman on 22.02.2017.
 */
public class Model extends Observable {
    private Map<Point2D,Integer> cells = new HashMap<>();
    private Map<Point2D,Integer> tempCells = new HashMap<>();
    private int rowCount = 60;
    private int colCount =60;
    private boolean running = false;
    private int speed = 500;

    public Model(){

        for (int x = 0; x < rowCount; x++) {
            for (int y = 0; y < colCount; y++) {
                cells.put(new Point2D(x,y),0);
                tempCells.put(new Point2D(x,y),0);
            }
        }


    }

    public void markCell(int x, int y){
        cells.put(new Point2D(x,y),1);
        setChanged();
        notifyObservers(cells);
    }

    public void startIterations(){
        running = true;
        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                return null;
            }

            @Override
            public void run() {
                while (running){
                    tempCells.putAll(cells);
                    if(computed()){
                        Platform.runLater(() -> {
                            setChanged();
                            notifyObservers(cells);
                        });
                    }
                    try  {
                        Thread.sleep(1000-speed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }




        };

        new Thread(task).start();
    }

    private boolean computed(){
        for (int x = 0; x < rowCount; x++) {
            for (int y = 0; y < colCount; y++) {
                int neighbours = 0;
                if(cells.get(new Point2D(Math.floorMod((x+1),rowCount),Math.floorMod((y),colCount))) == 1){
                    neighbours++;
                }
                if(cells.get(new Point2D(Math.floorMod((x+1),rowCount),Math.floorMod((y-1),colCount))) == 1){
                    neighbours++;
                }
                if(cells.get(new Point2D(Math.floorMod((x+1),rowCount),Math.floorMod((y+1),colCount))) == 1){
                    neighbours++;
                }
                if(cells.get(new Point2D(Math.floorMod((x),rowCount),Math.floorMod((y+1),colCount))) == 1){
                    neighbours++;
                }
                if(cells.get(new Point2D(Math.floorMod((x),rowCount),Math.floorMod((y-1),colCount))) == 1){
                    neighbours++;
                }
                if(cells.get(new Point2D(Math.floorMod((x-1),rowCount),Math.floorMod((y-1),colCount))) == 1){
                    neighbours++;
                }
                if(cells.get(new Point2D(Math.floorMod((x-1),rowCount),Math.floorMod((y+1),colCount))) == 1){
                    neighbours++;
                }
                if(cells.get(new Point2D(Math.floorMod((x-1),rowCount),Math.floorMod((y),colCount))) == 1){
                    neighbours++;
                }

                if((neighbours<2 || neighbours>3) && cells.get(new Point2D(x,y))==1){
                    tempCells.put(new Point2D(x,y),0);
                }  //lebend stirbt

                else if(neighbours == 3 && cells.get(new Point2D(x,y)) == 0){
                    tempCells.put(new Point2D(x,y),1);
                }        //tot lebt
                else{
                    tempCells.put(new Point2D(x,y),cells.get(new Point2D(x,y)));
                }
            }
        }
        cells.putAll(tempCells);
        return true;
    }

    public void setSpeed(int i){
        speed = i;
    }

    public void reset(){
        running = false;
        for (int x = 0; x < rowCount; x++) {
            for (int y = 0; y < colCount; y++) {
                cells.put(new Point2D(x, y), 0);
            }
        }
        Platform.runLater(() -> {
            setChanged();
            notifyObservers(cells);
        });

    }


}
