import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;

public class ValiTest extends PopUp {

    private static Scanner s = new Scanner(System.in);

    private int pikkus;
    private int tulemus;
    private List<Integer> vastused = new ArrayList<>();
    private VirtualFile testiFail;

    public ValiTest(String ribaNimi) {
        super(ribaNimi);

        VBox testiLayout = new VBox();
        Scene testid = new Scene(testiLayout);
        ObservableList<String> testideNimed = VirtualFile.testiNimed();
        ListView listiVaade = new ListView(testideNimed);
        Button kinnita = new Button("Vali");
        testiLayout.getChildren().addAll(new Label("Olemas olevad testid:"), listiVaade, kinnita);
        listiVaade.setPrefHeight(26*testideNimed.size());


        uusAken.setScene(testid);
        uusAken.show();

        kinnita.setOnAction(e -> {
            PopUp kindel = new PopUp("");
            kindel.confirmation("Kas soovite proovida testi: '" +
                    listiVaade.getSelectionModel().getSelectedItem().toString() + "'?");
            try {
                mängi(listiVaade.getSelectionModel().getSelectedItem());
            }
            catch (FileNotFoundException f) {
                listiVaade.getItems().remove(listiVaade.getSelectionModel().getSelectedItem());
            }
        });
    }

    private void mängi(Object test) throws FileNotFoundException {

        testiFail = new VirtualFile(test.toString() + ".txt");
        pikkus = testiFail.getPikkus();

        küsi(1);

    }

    public void küsi(int küsimuseNr) {
        // Küsimuse ja vastusevariantide väljastamine
        String[] küsimus = testiFail.getKüsimus(küsimuseNr);

        List<String> list = new ArrayList<>(Arrays.asList(küsimus));  // küsimuste list
        list.remove(küsimus[0]);  // esimene on küsimus
        String[] segatud = list.toArray(new String[0]);  // see on segamiseks list
        segatud = Utilities.shuffle(segatud);  // siin toimub päriselt segamine
        List<String> segatudList = new ArrayList<>(Arrays.asList(segatud));

        VBox testiLayout = new VBox();
        Scene küsimuseStseen = new Scene(testiLayout);
        ObservableList<String> vastused = FXCollections.observableList(segatudList);
        ListView listiVaade = new ListView(vastused);
        Button kinnita = new Button("Vasta");
        testiLayout.getChildren().addAll(new Label(küsimus[0]), listiVaade, kinnita);
        listiVaade.setPrefHeight(26*vastused.size());

        uusAken.setScene(küsimuseStseen);

        // See on naq rekursiivne küsimuste küsimine ja kui enam pole küsimust, mida küsida, siis genereeritakse tulemus, mis hetkel kuvatakse terminalis
        kinnita.setOnAction(e -> {
            setVastus(küsimuseNr, küsimus, segatudList, listiVaade.getSelectionModel().getSelectedItem());
            try {
                küsi(küsimuseNr+1);
            }
            catch (IndexOutOfBoundsException e2) {
                System.out.println(genereeriTulemus());
                uusAken.close();
            }
        });

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
                return "Tulemus: " + testiFail.getTulemus(i);
            }
        }
        return "";

    }



}
