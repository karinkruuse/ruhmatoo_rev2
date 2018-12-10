import javafx.event.ActionEvent;
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

    public String getKüsimus() {
        return küsimus;
    }
    public void setKüsimus(String küsimus) {
        this.küsimus = küsimus;
    }
    public String getVastus() {
        return vastus;
    }
    public void setVastus(String vastus) {
        this.vastus = vastus;
    }
    public String[] getKoguKüsimus() {
        return koguKüsimus;
    }
    public void setKoguKüsimus(String info, int indeks) {
        this.koguKüsimus[indeks] = info;
    }

    //ma ei tea kas see t;;tab aga vist t;;tab
    private int vastusteArv = 0;
    public int getVastusteArv() {
        return vastusteArv;
    }
    public void setVastusteArv(int vastusteArv) {
        this.vastusteArv = vastusteArv;
    }

    private String küsimus;
    private String vastus;
    String[] koguKüsimus = new String[31]; //eeldus, et rohkem kui 30 vastusevarianti ei tule, sest see poleks kuigi m6istlik


    //kogu testiinfo, mille korraga faili saab salvestada
    private ArrayList<String> testiInfo = new ArrayList();
    public ArrayList getTestiInfo() {
        return testiInfo;
    }
    public void setTestiInfo(String info) {
        this.testiInfo.add(info);
    }

    public void setTestiInfo(ArrayList testiInfo) {
        this.testiInfo = testiInfo;
    }



    public void LisaKüsimus() {
            setVastusteArv(0); //uue lisamisel vastuste arv 0

            GridPane grid = new GridPane();
            Label küsimuseSilt = new Label("Sisesta küsimus nr " + (getKüsimusteArv()+1) + "  ");
            TextField küsimustxt = new TextField();
            grid.add(küsimuseSilt, 0, 0);
            grid.add(küsimustxt, 1, 0);
            Label küsimuseSalvestamine = new Label("Küsimuse salvestamiseks vajuta ENTER");
            grid.add(küsimuseSalvestamine, 0, 1, 3, 4);
            Scene küsmuseLisamine = new Scene(grid);
            uusAken.setScene(küsmuseLisamine);

            Button lisaVariant = new Button("Lisa vastusevariant");
            Button lõpetaVastusteLisamine = new Button("Lõpeta variantide lisamine");

            küsmuseLisamine.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent ke) {
                    if (ke.getCode() == KeyCode.ENTER && küsimustxt.getText().length() > 0) {
                        setKüsimus(küsimustxt.getText());
                        grid.add(lisaVariant, 0, 2);
                        grid.add(lõpetaVastusteLisamine, 1, 2);
                        küsimuseSalvestamine.setText("Küsimus salvestatud");

                    }
                }
            });

            lisaVariant.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    LisaVariant(grid);
                }
            });

            setKüsimusteArv(getKüsimusteArv()+1);



        }
    public void LisaVariant(GridPane grid) {

        Label variant = new Label("Sisesta vastusevariant");
        TextField varianttxt = new TextField((getVastusteArv()+1)+"punkti andev vastus");
        grid.add(variant, 0, (getVastusteArv()+5));
        grid.add(varianttxt, 1, (getVastusteArv()+5));
        setVastusteArv(getVastusteArv()+1);

        /*küsmuseLisamine.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ENTER && küsimustxt.getText().length() > 0) {
                    setKüsimus(küsimustxt.getText());
                    grid.add(lisaVariant, 0, 2);
                    grid.add(lõpetaVastusteLisamine, 1, 2);
                }
            }
        });*/


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
                    grid.add(lisaKüsimus, 0, 3);
                    grid.add(lõpetaKüsimusteLisamine, 1, 3);
                    nimeSalvestamine.setText("Testi nimi salvestatud");
                    setTestiInfo(testinimitxt.getText());
                }
            }
        });

        lisaKüsimus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LisaKüsimus();

                //salvesta k[simus arraysse
                //salvesta viimane vastus

            }
        });
        //pooleli
        //lõpetaKüsimusteLisamine.setOnAction(event -> );


        }


}

//testiInfo
//0 kohal on testi nimi
//1-.. on küsimus vastustega formaadis küs;vvariant1;variant2 jne



