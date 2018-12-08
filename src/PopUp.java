import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class PopUp {

    protected Stage uusAken;

    public PopUp(String ribaNimi) {
        uusAken = new Stage();
        uusAken.initModality(Modality.APPLICATION_MODAL);
        uusAken.setTitle(ribaNimi);
        uusAken.resizableProperty();
    }



}
