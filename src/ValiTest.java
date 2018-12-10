import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
import java.util.*;

public class ValiTest extends PopUp {

    private static Scanner s = new Scanner(System.in);
    private static Utilities u = new Utilities();

    private Object test;
    private int pikkus;
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

        PopUp valimine = new PopUp("Kindel?");
        kinnita.setOnAction(e -> {
            setTest(listiVaade.getSelectionModel().getSelectedItem());
            valimine.confirmation("valida testi: '" + test.toString().replace("?","") + "'");
            System.out.println(valimine.valik);
            if (valimine.valik == true) {
                mängi();
            }
        });


    }


    private void mängi() {
        try {
            testiFail = new VirtualFile(test.toString() + ".txt");
        }
        catch (FileNotFoundException e) {
            System.exit(0);
        }

        System.out.println(testiFail.getKüsimus(2));

    }


    private void setTest(Object nimi) {
        test = nimi;
    }


    public void küsi(int küsimuseNr) {

        // Küsimuse ja vastusevariantide väljastamine
        String[] küsimus = testiFail.getKüsimus(küsimuseNr);
        System.out.println(küsimus[0]);


        List<String> list = new ArrayList<>(Arrays.asList(küsimus));  // küsimuste list
        list.remove(küsimus[0]);  // esimene on küsimus
        String[] segatud = list.toArray(new String[0]);  // see on segamiseks list
        segatud = u.shuffle(segatud);  // siin toimbu päriselt segamine


        for (int i = 0; i < segatud.length; i++) {  // segatud list pinditakse
            System.out.println("[" + (i+1) + "] " + segatud[i]);
        }

        // user input
        int vastus = 0;
        while (true) {
            try {
                vastus = Integer.parseInt(s.nextLine());
                break;
            }
            catch (NumberFormatException e) {
                System.out.println("Palun sisestage vastusevariandi number!");
            }
        }

        int punkte = Arrays.asList(küsimus).indexOf(segatud[vastus-1]);
        // leitakse antud variandi asukohe algses listis ja seellest tuleneb saadav punktide arv

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
