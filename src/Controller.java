import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Created by hyperman on 22.02.2017.
 */
public class Controller {

    public Controller(View view, Model model){
        model.addObserver(view.getGameBoard());
        view.getGameBoard().setOnMouseClicked(event -> {
            model.markCell((int)event.getX()/10,(int)event.getY()/10);
        });
        view.getGameBoard().setOnMouseDragged(event -> {
            model.markCell((int)event.getX()/10,(int)event.getY()/10);
        });


        view.getSpeedSlider().valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                model.setSpeed(new_val.intValue());
            }
        });

        view.getStartButton().setOnMouseClicked(event -> {
            model.startIterations();
        });

        view.getResetButton().setOnMouseClicked(event -> {
            model.reset();
        });

    }
}
