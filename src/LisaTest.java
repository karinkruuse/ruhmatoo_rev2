import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.ArrayList;

public class LisaTest extends PopUp{
    //konstruktor, kutsub v;lja esimese stseeni
    public LisaTest(String ribaNimi) {
        super(ribaNimi);
        esimene();
    }

    //ma ei tea kas see t;;tab aga vist t;;tab
    private int küsimusteArv = 0;
    public int getKüsimusteArv() {
        return küsimusteArv;
    }
    public void setKüsimusteArv(int küsimusteArv) {
        this.küsimusteArv = küsimusteArv;
    }

    // kusimuste lisamise lopetamiseks
    private static boolean kloop = true;
    public static void setKloop(boolean kloop) {
        LisaTest.kloop = kloop;
    }

    //kogu testiinfo, mille korraga faili saab salvestada
    private ArrayList testiInfo; 
    public ArrayList getTestiInfo() {
        return testiInfo;
    }
    public void setTestiInfo(ArrayList testiInfo) {
        this.testiInfo = testiInfo;
    }

    public void LisaVariant() {
        Label variant = new Label("Sisesta vastusevariant");
        TextField varianttxtx = new TextField();
    }

    public void LisaKüsimus() {
        while (kloop) {

            setKüsimusteArv(0);

            GridPane grid = new GridPane();
            String[] koguKüsimus;
            Label küsimus = new Label("Sisesta küsimus");
            TextField küsimustxt = new TextField();
            Button lõpp = new Button("Lõpeta küsimuste lisamine");
            lõpp.setOnAction(event -> setKloop(false));
            grid.add(küsimus, 0,0);
            grid.add(küsimustxt, 1,0);
            grid.add(lõpp, 3, 0);
            grid.add(new Label("Uue vastusevariandi sisestamiseks vajuta ENTER"), 0, 1, 3, 4);

            Scene küsmuseLisamine = new Scene(grid);
            uusAken.setScene(küsmuseLisamine);

            küsmuseLisamine.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent ke) {
                    if (ke.getCode() == KeyCode.ENTER) {

                        grid.add(new Label("Lisa variant"), 0, getKüsimusteArv()+2);
                        grid.add(new TextField(), 1, getKüsimusteArv()+2);
                        grid.add(new Label("Antud vastuse eest saab "+ getKüsimusteArv() + " punkti."), 2, getKüsimusteArv()+2);
                        setKüsimusteArv(getKüsimusteArv()+1);
                    }
                }
            });
            String knimi = küsimustxt.getText();
            //koguKüsimus[0] = knimi;
        }

        //uusAken.setScene(aaaaaaaaaaaaaaaaaaaaaaaaaaa)
    }


    public void esimene() {
        GridPane grid = new GridPane();
        Label testiSilt = new Label("Sisesta testi nimi:");
        TextField testinimitxt = new TextField();
        String testinimi = testiSilt.getText();
        Scene algus = new Scene(grid);
        uusAken.show();

        grid.add(testiSilt, 0, 0);
        grid.add(testinimitxt, 0, 1);
        grid.add(new Label("Jätkamiseks vajuta ENTER"), 0, 1, 3, 1);



        algus.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ENTER) {
                    LisaKüsimus();
                }
            }
        });

    }
}



