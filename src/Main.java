import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by hyperman on 22.02.2017.
 */
public class Main extends Application {

    public static void main (String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        View view  = new View();
        Model model = new Model();
        Controller controller = new Controller(view,model);
        Scene scene = new Scene(view);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
    }
}
