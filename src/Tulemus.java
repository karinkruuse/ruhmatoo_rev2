import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class Tulemus  {

    Stage aken;
    private int küsimusteArv;
    private int maxTulemus;
    private ArrayList<String> küsimused;
    private ArrayList<String> tulemused;

    public Tulemus(Stage aken, ArrayList<String> küsimused) {
        this.aken = aken;
        küsimusteArv = küsimused.size()-2;
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
        vbox.getChildren().add(alusta);
        aken.setScene(tulemuseStseen);

        alusta.setOnAction(e -> tulemuseSisestamine());

    }

    public void tulemuseSisestamine() {

        BackgroundImage bgi = new BackgroundImage(new Image("pildid" + File.separatorChar + "kirju.jpeg"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        VBox välimine = new VBox();
        välimine.setBackground(new Background(bgi));
        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER);
        Label palve = new Label("Palun alustage tulemusest, milleks on vaja kõige vähem punkte.");
        välimine.getChildren().addAll(palve, layout);

        TextField tulemuseKoht = new TextField("Siia sisesta tulemus");
        TextField minTulemuseKoht = new TextField("Tulemuse alampiir");
        TextField maxTulemuseKoht = new TextField("Tulemuse ülempiir");

        Button järgmineTulemus = new Button("järgmine");
        Button valmis = new Button("Valmis");

        layout.getChildren().addAll(tulemuseKoht, minTulemuseKoht, maxTulemuseKoht, järgmineTulemus, valmis);
        Scene tulemuseLisamiseStseen = new Scene(välimine, 640, 426);
        aken.setScene(tulemuseLisamiseStseen);

        aken.setOnCloseRequest(e -> {
            e.consume();
            PopUp p = new PopUp("Kindel?");
            p.confirmation("Olete kindel, et soovite mängust lahkuda ilma salvestamata?");
            if (p.valik == true) {
                aken.close();
            }
        });

        järgmineTulemus.setOnAction(e -> {
            if (tulemuseKoht.getText().length() > 0 && minTulemuseKoht.getText().length() > 0 && maxTulemuseKoht.getText().length() > 0) {
                if (Integer.parseInt(minTulemuseKoht.getText()) != küsimusteArv) {
                    Label hoiatus = new Label("Miinimum tulemus peaks olema " + küsimusteArv + ".");
                    välimine.getChildren().addAll(hoiatus);
                }
                else {
                    String rida = tulemuseKoht.getText() + ";" + minTulemuseKoht.getText() + ";" +  maxTulemuseKoht.getText() + "\n";
                    tulemused.add(rida);
                    tulemuseSisestamine();
                }
            }
            else {
                Label hoiatus = new Label("Palun täitke kõik väljad!");
                välimine.getChildren().addAll(hoiatus);
            }
        });

        valmis.setOnAction(e -> {
            if (tulemuseKoht.getText().length() > 0 && minTulemuseKoht.getText().length() > 0 && maxTulemuseKoht.getText().length() > 0) {
                if (Integer.parseInt(maxTulemuseKoht.getText()) == maxTulemus) {
                    String rida = tulemuseKoht.getText() + ";" + minTulemuseKoht.getText() + ";" +  maxTulemuseKoht.getText() + "\n";
                    tulemused.add(rida);
                    lõpp();
                }
                else {
                    Label hoiatus = new Label("Maksimum tulemus peaks olema " + maxTulemus + ".");
                    välimine.getChildren().addAll(hoiatus);
                }

            }
            else {
                Label hoiatus = new Label("Palun täitke kõik väljad!");
                välimine.getChildren().addAll(hoiatus);
            }
        });

    }

    public void lõpp() {

        BackgroundImage bgi = new BackgroundImage(new Image("pildid" + File.separatorChar + "kirju.jpeg"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        SalvestaFaili salvesta = new SalvestaFaili(küsimused, tulemused);
        HBox layout = new HBox();
        layout.setBackground(new Background(bgi));
        Scene lõppuStseen = new Scene(layout, 640, 426);
        Label label = new Label("Salvestan testi!");
        Button sulge = new Button("Menüü");
        layout.getChildren().addAll(label, sulge);

        aken.setScene(lõppuStseen);

        sulge.setOnAction(e -> aken.close());
    }

}
