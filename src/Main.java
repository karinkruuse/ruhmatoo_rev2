import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage aken) throws Exception{

        Label pealkiri = new Label();
        aken.setTitle("Testi mäng");
        Group juur = new Group();
        Scene titleScene = new Scene(juur);





    }


    public static void main(String[] args) {
        launch(args);
    }
}
