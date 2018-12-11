import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopUp {

    protected Stage uusAken;

    // Uue akna avamiseks
    public PopUp(String ribaNimi) {
        uusAken = new Stage();
        uusAken.initModality(Modality.APPLICATION_MODAL);
        uusAken.setTitle(ribaNimi);
        uusAken.resizableProperty();
    }

    // Seda booleani tuleb kontrollida, et seda saada, mis user vastas
    // töötava näide on ValiTest klassis konstruktoris
    public boolean valik = false;
    // See sätib uue akna nii, et oleks võimalik mingile küsimuseks kasutajalt kinnitust saada
    public void confirmation(String tekst) {
        Button jah = new Button("Jah");
        Button ei = new Button("Ei");
        Label t = new Label(tekst);

        VBox layout = new VBox(10, t, ei, jah);
        layout.setAlignment(Pos.CENTER);

        Scene pisikeStseen = new Scene(layout);
        uusAken.setScene(pisikeStseen);

        jah.setOnAction(event -> {
            setValik();
            uusAken.close();
        });
        ei.setOnAction(event -> {
            uusAken.close();
        });

        uusAken.showAndWait();
    }

    public void setValik() {
        valik = true;
    }  // See on vajalik, sest mitte staatilise muutuja väärtust ei saa hästi lambdaga muuta

}
