import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class LisaTest extends PopUp {


    public LisaTest(String ribaNimi) {
        super(ribaNimi);
        pfft();

    }
    public void lisaVariant() {
        Label variant = new Label("Sisesta vastusevariant");
        TextField varianttxtx = new TextField();
    }

    public static void LisaKüsimus() {
        while (true) {
            GridPane grid = new GridPane();

            Label küsimus = new Label("Sisesta küsimus");
            TextField küsimustxt = new TextField();
            //Button lõpp = new Button();
            //lõpp.setOnAction(event -> break

            Scene küsmuseLisamine = new Scene(grid);
        }

        //uusAken.setScene(aaaaaaaaaaaaaaaaaaaaaaaaaaa)
    }

    public void pfft() {
        GridPane grid = new GridPane();
        Label testiSilt = new Label("Sisesta testi nimi:");
        TextField testinimitxt = new TextField();
        String testinimi = testiSilt.getText();
        Scene algus = new Scene(grid);
        uusAken.show();

        grid.add(testiSilt, 0, 0);
        grid.add(testinimitxt, 0, 1);
        grid.add(new Label("Jätkamiseks vajuta ENTER"), 0, 1, 3, 1);

        algus.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ENTER) {
                    LisaKüsimus();
                    ke.consume();
                }
            }
        });

    }
}



