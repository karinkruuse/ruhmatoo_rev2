import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage aken) {
        Button valiTest = new Button("Tee testi");
        Button lisaTest = new Button("Lisa test");


        HBox layout = new HBox(10, valiTest, lisaTest);
        layout.setAlignment(Pos.CENTER);

        Scene peaStseen = new Scene(layout, 200, 100);

        aken.setScene(peaStseen);
        aken.show();
    }
}
