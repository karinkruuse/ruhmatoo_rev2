import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Tulemus  {

    Stage aken;
    private int küsimusteArv;
    private int maxTulemus;
    private ArrayList<String> küsimused;
    private ArrayList<String> tulemused;

    public Tulemus(Stage aken, ArrayList<String> küsimused) {
        this.aken = aken;
        küsimusteArv = küsimused.size()-1;
        int summa = 0;
        for (String s : küsimused) {
            summa += s.split(";").length - 1;
        }
        maxTulemus = summa;
        this.küsimused = küsimused;
        tulemused = new ArrayList<>();

        tulemusteAlgus();
    }

    public void tulemusteAlgus() {
        Label küsimus = new Label("Testis on võimalik saada " + küsimusteArv + " kuni " + maxTulemus + " punkti.");
        VBox vbox = new VBox();
        Scene tulemuseStseen = new Scene(vbox);
        vbox.getChildren().addAll(küsimus);
        vbox.setAlignment(Pos.TOP_CENTER);
        Button alusta = new Button("tulemusi sisestama");
        aken.setScene(tulemuseStseen);

        alusta.setOnAction(e -> tulemuseSisestamine());

    }

    public void tulemuseSisestamine() {
        VBox välimine = new VBox();
        HBox layout = new HBox();
        layout.setAlignment(Pos.BASELINE_RIGHT);
        Label palve = new Label("Palun alustage tulemusest, milleks on vaja kõige vähem punkte.");
        välimine.getChildren().addAll(palve, layout);

        TextField tulemuseKoht = new TextField("Siia sisesta tulemus");
        TextField minTulemus = new TextField("Tulemuse alampiir");
        TextField maxTulemus = new TextField("Tulemuse ülempiir");

        Button järgmineTulemus = new Button("järgmine");
        Button valmis = new Button("Valmis");

        layout.getChildren().addAll(tulemuseKoht, minTulemus, maxTulemus, järgmineTulemus, valmis);
        Scene tulemuseLisamiseStseen = new Scene(välimine);
        aken.setScene(tulemuseLisamiseStseen);

        järgmineTulemus.setOnAction(e -> {
            if (tulemuseKoht.getText().length() > 0 && minTulemus.getText().length() > 0 && maxTulemus.getText().length() > 0) {
                String rida = tulemuseKoht.getText() + ";" + minTulemus.getText() + ";" +  maxTulemus.getText() + "\n";
            }
            else {
                Label hoiatus = new Label("Palun täitke kõik väljad!");
                välimine.getChildren().addAll(hoiatus);
            }
        });

    }

}
