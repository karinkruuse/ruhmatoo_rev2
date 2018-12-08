import javafx.scene.Scene;
import javafx.scene.layout.HBox;

public class ValiTest extends PopUp {

    public ValiTest(String ribaNimi) {
        super(ribaNimi);
        mdea();
    }

    public void mdea() {
        HBox hb = new HBox(10);
        Scene uks = new Scene(hb,500, 400);

        uusAken.setScene(uks);
        super.näita();
    }





}
