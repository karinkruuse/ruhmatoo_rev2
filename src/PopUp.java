import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
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

    // Seda booleani tuleb alamklassis kontrollida, sest confirmation ei returni midagi
    boolean valik;
    public void confirmation(String tegevus) {
        Button jah = new Button("Jah");
        Button ei = new Button("Ei");
        Label tekst = new Label("Kas olete kindel, et soovite " + tegevus + "?");

        VBox layout = new VBox(10, tekst, ei, jah);
        layout.setAlignment(Pos.CENTER);

        Scene pisikeStseen = new Scene(layout, 200, 200);

        uusAken.setScene(pisikeStseen);
        uusAken.show();

        jah.setOnAction(event -> {
            setValik(true);
            uusAken.close();
        });
        ei.setOnAction(event -> {
            setValik(false);
            uusAken.close();
        });
    }

    private void setValik(boolean väärtus) {
        valik = väärtus;
    }



}
