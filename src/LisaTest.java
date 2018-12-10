import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;

public class LisaTest extends PopUp{
    //konstruktor, kutsub v;lja esimese stseeni
    public LisaTest(String ribaNimi) {
        super(ribaNimi);
        esimene();
    }

    private int küsimusteArv = 0;
    public int getKüsimusteArv() {
        return küsimusteArv;
    }
    public void setKüsimusteArv(int küsimusteArv) {
        this.küsimusteArv = küsimusteArv;
    }

    //ma ei tea kas see t;;tab aga vist t;;tab
    private int vastusteArv = 0;
    public int getVastusteArv() {
        return vastusteArv;
    }
    public void setVastusteArv(int vastusteArv) {
        this.vastusteArv = vastusteArv;
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
            setVastusteArv(0); //uue lisamisel vastuste arv 0

            GridPane grid = new GridPane();
            String[] koguKüsimus = new String[30];
            Label küsimus = new Label("Sisesta küsimus nr " + getKüsimusteArv() + "  ");
            TextField küsimustxt = new TextField();
            Button lõpp = new Button("Lõpeta küsimuste lisamine");
            grid.add(küsimus, 0, 0);
            grid.add(küsimustxt, 1, 0);
            grid.add(lõpp, 3, 0);
            grid.add(new Label("Vastusevariandi salvestamiseks vajuta ENTER"), 0, 1, 3, 4);


            Scene küsmuseLisamine = new Scene(grid);
            uusAken.setScene(küsmuseLisamine);
            koguKüsimus[0] = küsimustxt.getText();
            küsmuseLisamine.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent ke) {
                    if (ke.getCode() == KeyCode.ENTER && küsimustxt.getText().length() > 0) {

                        System.out.println("evenhandleri sees");
                        String knimi = küsimustxt.getText();
                        grid.add(new Label("Lisa variant  "), 0, (getVastusteArv() + 2));
                        grid.add(new TextField(), 1, getVastusteArv() + 2);
                        grid.add(new Label("Antud vastuse eest saab " + (getVastusteArv()+1) + " punkti."), 2, (getVastusteArv() + 2));
                        setVastusteArv(getVastusteArv() + 1);
                    }
                }
            });

            setKüsimusteArv(getKüsimusteArv()+1);


            //koguKüsimus[0] = knimi;


            //uusAken.setScene(aaaaaaaaaaaaaaaaaaaaaaaaaaa)
        }


    public void esimene() {
        GridPane grid = new GridPane();
        Label testiSilt = new Label("Sisesta testi nimi:  ");
        TextField testinimitxt = new TextField();
        Scene algus = new Scene(grid);
        System.out.println("olen esimeses meetodis");
        grid.add(testiSilt, 0, 0);
        grid.add(testinimitxt, 1, 0);
        Label nimeSalvestamine = new Label("Testi nime salvestamiseks vajuta ENTER");
        grid.add(nimeSalvestamine, 0, 1, 3, 1);
        uusAken.setScene(algus);
        uusAken.show();

        Button lisaKüsimus = new Button("Lisa küsimus");
        Button lõpetaKüsimusteLisamine = new Button("Lõpeta küsimuste lisamine");

        algus.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ENTER && testinimitxt.getText().length() > 0) {
                    String testinimi = testinimitxt.getText();
                    grid.add(lisaKüsimus, 0, 3);
                    grid.add(lõpetaKüsimusteLisamine, 1, 3);
                    nimeSalvestamine.setText("Testi nimi salvestatud");
                }
            }
        });

        lisaKüsimus.setOnAction(event -> LisaKüsimus());
        //pooleli
        //lõpetaKüsimusteLisamine.setOnAction(event -> );


        }


}



