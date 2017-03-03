import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * Created by hyperman on 02.03.2017.
 */
public class View extends Region {
    private BorderPane gameFrame;
    private HBox buttonBar;
    private GameBoard gameBoard;
    private Button startButton;
    private Button resetButton;
    private Slider speedSlider;


    public View(){
        initGui();



    }

    private void initGui(){
        gameFrame = new BorderPane();
        gameBoard = new GameBoard();
        buttonBar = new HBox();

        resetButton = new Button("rest");
        startButton = new Button("start");
        speedSlider = new Slider(0,1000,1);
        speedSlider.setValue(500);
        buttonBar.getChildren().addAll(startButton,resetButton,speedSlider);
        buttonBar.setSpacing(50);
        gameFrame.setCenter(gameBoard);
        gameFrame.setBottom(buttonBar);
        getChildren().add(gameFrame);
    }

    public GameBoard getGameBoard(){
        return gameBoard;
    }

    public Slider getSpeedSlider(){
        return speedSlider;
    }

    public Button getStartButton(){
        return startButton;
    }

    public Button getResetButton(){
        return resetButton;
    }
}
