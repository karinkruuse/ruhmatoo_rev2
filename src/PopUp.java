import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopUp {

    protected Stage uusAken;

    public PopUp(String ribaNimi) {
        uusAken = new Stage();
        uusAken.initModality(Modality.APPLICATION_MODAL);
        uusAken.setTitle(ribaNimi);
        uusAken.resizableProperty();
    }

    public void confirmation() {
        Button jah = new Button("Jah");
        Button ei = new Button("Ei");
        Label tekst = new Label("Kas olete kindel, et soovite mängu sulgeda?");

        VBox layout = new VBox(10, tekst, ei, jah);
        layout.setAlignment(Pos.CENTER);

        Scene pisikeStseen = new Scene(layout);

        uusAken.setScene(pisikeStseen);
        uusAken.show();

        // Kui saaks siin oodata enne, kui selle kutsunud meetod saa jätkata, ss imo see lahendaks palju probleeme

        jah.setOnAction(event -> {
            uusAken.close();
        });
        ei.setOnAction(event -> {
            uusAken.close();
        });
    }


}
