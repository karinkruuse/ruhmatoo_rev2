import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;

public class ValiTest extends PopUp {
    private List<Integer> vastused = new ArrayList<>();
    private VirtualFile testiFail;

    BackgroundImage bgi;
    private int laius;
    private int kõrgus;

    public ValiTest(String ribaNimi) {
        // PopUp loob uue akna
        super(ribaNimi);

        bgi = new BackgroundImage(new Image("pildid" + File.separatorChar + "naine.jpeg"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        laius = 640;
        kõrgus = 426;

        VBox testiLayout = new VBox();
        Scene testid = new Scene(testiLayout,laius, kõrgus);
        ObservableList<String> testideNimed = VirtualFile.testiNimed();
        ListView listiVaade = new ListView(testideNimed);
        Button kinnita = new Button("Vali");
        Label tekst = new Label("Olemas olevad testid:");
        tekst.setTextFill(Color.WHITE);
        testiLayout.getChildren().addAll(tekst, listiVaade, kinnita);
        listiVaade.setPrefHeight(26*testideNimed.size());

        testiLayout.setBackground(new Background(bgi));

        uusAken.setScene(testid);
        uusAken.show();


        kinnita.setOnAction(e -> {
            PopUp kindel = new PopUp("");
            kindel.confirmation("Kas soovite proovida testi: '" +
                    listiVaade.getSelectionModel().getSelectedItem().toString() + "'?");

            if (kindel.valik == true) {
                try {
                    mängi(listiVaade.getSelectionModel().getSelectedItem());
                }
                catch (FileNotFoundException f) {
                    listiVaade.getItems().remove(listiVaade.getSelectionModel().getSelectedItem());
                }
            }
        });
    }


    private void mängi(Object test) throws FileNotFoundException {
        testiFail = new VirtualFile(test.toString() + ".txt");
        küsi(1);
    }


    public void küsi(int küsimuseNr) {
        // Küsimuse ja vastusevariantide väljastamine
        String[] küsimus = testiFail.getKüsimus(küsimuseNr);

        // See on vastusevariantide segamiseks mõeldud osa
        List<String> list = new ArrayList<>(Arrays.asList(küsimus));  // küsimuste list
        list.remove(küsimus[0]);  // esimene on küsimus
        String[] segatud = list.toArray(new String[0]);  // see on segamiseks list
        segatud = Utilities.shuffle(segatud);  // siin toimub päriselt segamine
        List<String> segatudList = new ArrayList<>(Arrays.asList(segatud));

        VBox testiLayout = new VBox();
        Scene küsimuseStseen = new Scene(testiLayout, laius, kõrgus);
        ObservableList<String> vastused = FXCollections.observableList(segatudList);
        ListView listiVaade = new ListView(vastused);
        Button kinnita = new Button("Vasta");
        Label küsimuseTekst = new Label(küsimus[0]);
        küsimuseTekst.setTextFill(Color.WHITE);
        testiLayout.getChildren().addAll(küsimuseTekst, listiVaade, kinnita);
        listiVaade.setPrefHeight(26*vastused.size());

        testiLayout.setBackground(new Background(bgi));
        uusAken.setScene(küsimuseStseen);

        // taoline popUp on mitmetes kohtades, see on selleks, et kasutaja kogemata kinni ei paneks akent
        uusAken.setOnCloseRequest(e -> {
            e.consume();
            PopUp p = new PopUp("Kindel?");
            p.confirmation("Olete kindel, et soovite mängust lahkuda?");
            if (p.valik == true) {
                uusAken.close();
            }
        });

        // See on naq rekursiivne küsimuste küsimine ja kui enam pole küsimust, mida küsida, siis tuleb tulemus
        // Kui sellise numbriga küsimust enam ei ole, siis tuleb meetodi alguses error kohe
        kinnita.setOnAction(e -> {
            setVastus(küsimuseNr, küsimus, segatudList, listiVaade.getSelectionModel().getSelectedItem());
            try {
                küsi(küsimuseNr+1);
            }
            catch (IndexOutOfBoundsException e2) {
                tulemus(genereeriTulemus());
            }
        });
    }


    public void tulemus(String t) {
        VBox vbox = new VBox();
        Scene tulemuseStseen = new Scene(vbox, laius, kõrgus);

        bgi = new BackgroundImage(new Image("pildid" + File.separatorChar + "grupp.jpeg"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Label tt = new Label(t);
        tt.setScaleX(5);
        tt.setScaleY(5);
        tt.setTextFill(Color.WHITE);

        vbox.setBackground(new Background(bgi));
        vbox.getChildren().addAll(tt);
        vbox.setAlignment(Pos.CENTER);
        uusAken.setScene(tulemuseStseen);


    }


    public void setVastus(int küsimuseNr, String[] segamata, List segatud, Object valitudVastus) {
        int vastus = segatud.indexOf(valitudVastus.toString());
        int punkte = Arrays.asList(segamata).indexOf(segatud.get(vastus));
        vastused.add(küsimuseNr-1, punkte);
    }


    public String genereeriTulemus() {

        int summa = 0;
        // Vastuste listi on küsimustest saadud punktid salvestatud ja nende summa järgi tuleb tulemus
        for (int e : vastused) {
            summa += e;
        }

        for (int i = 0; i < testiFail.getTulemusteArv(); i++) {
            if (summa >= testiFail.getVahemikAlampiir(i) && summa <= testiFail.getVahemikÜlempiir(i)) {
                return testiFail.getTulemus(i);
            }
        }
        return "";

    }



}
