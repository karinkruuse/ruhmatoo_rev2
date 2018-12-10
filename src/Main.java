import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;

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

        BackgroundImage bgi = new BackgroundImage(new Image("pildid" + File.separatorChar + "taust.jpeg"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Scene peaStseen = new Scene(layout, 640, 426);



        layout.setBackground(new Background(bgi));

        aken.setScene(peaStseen);
        aken.show();

        valiTest.setOnAction(event -> new ValiTest("Psühhoanalüüs"));
        lisaTest.setOnAction(event -> new LisaTest("Testi lisamine"));
    }
}
