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
        lisaTest();
    }

    private int küsimusteArv = 0;
    private StringBuilder koguKüsimus = new StringBuilder();
    private int vastusteArv = 0;
    private ArrayList<String> testiInfo = new ArrayList();

    private String testiPealkiri;
    private String küsimus;
    private StringBuilder vastused = new StringBuilder();

    public String getTestiPealkiri() {
        return testiPealkiri;
    }

    public void setTestiPealkiri(String testiPealkiri) {
        this.testiPealkiri = testiPealkiri;
    }

    public String getKüsimus() {
        return küsimus;
    }

    public void setKüsimus(String küsimus) {
        this.küsimus = küsimus;
    }

    public StringBuilder getVastused() {
        return vastused;
    }

    public void setVastused(StringBuilder vastused) {
        this.vastused = vastused;
    }

    public int getKüsimusteArv() {
        return küsimusteArv;
    }
    public void setKüsimusteArv(int küsimusteArv) {
        this.küsimusteArv = küsimusteArv;
    }
    public int getVastusteArv() {
        return vastusteArv;
    }
    public void setVastusteArv(int vastusteArv) {
        this.vastusteArv = vastusteArv;
    }
    public StringBuilder getKoguKüsimus() {
        return koguKüsimus;
    }
    public void setKoguKüsimus(StringBuilder koguKüsimus) {
        this.koguKüsimus = koguKüsimus;
    }

    //kogu testiinfo, mille korraga faili saab salvestada
    public ArrayList getTestiInfo() {
        return testiInfo;
    }
    public void setTestiInfo(String info) {
        this.testiInfo.add(info);
    }
    public void setTestiInfo(ArrayList testiInfo) {
        this.testiInfo = testiInfo;
    }



    public void lisaKüsimus() {
            setVastusteArv(0); //uue lisamisel vastuste arv 0
            getKoguKüsimus().setLength(0);
            getVastused().setLength(0);

            GridPane grid = new GridPane();
            Label küsimuseSilt = new Label("Sisesta küsimus nr " + (getKüsimusteArv()+1) + "     ");
            TextField küsimustxt = new TextField();
            grid.add(küsimuseSilt, 0, 0);
            grid.add(küsimustxt, 1, 0);
            Label küsimuseSalvestamine = new Label("Variantide lisamiseks vajuta ENTER");
            grid.add(küsimuseSalvestamine, 0, 1, 6, 1);
            Scene küsmuseLisamine = new Scene(grid, 640, 800);

            Button lisaKüsimus = new Button("Lõpeta vastuste lisamine ja lisa uus küsimus");
            Button lisaTulemus = new Button("Alusta testi tulemuste lisamist");
            grid.add(lisaKüsimus, 0, 2);
            grid.add(lisaTulemus, 1, 2);
            uusAken.setScene(küsmuseLisamine);

            küsmuseLisamine.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent ke) {
                    if (ke.getCode() == KeyCode.ENTER && küsimustxt.getText().length() > 0) {
                        setKüsimus(küsimustxt.getText());
                        küsimuseSalvestamine.setText("");
                        lisaVariant(grid);
                    }
                }
            });

            lisaKüsimus.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    setKüsimusteArv(getKüsimusteArv()+1);
                    setKoguKüsimus(getKoguKüsimus().append(getKüsimus()+";"+getVastused()));
                    setTestiInfo(getKoguKüsimus().toString());
                    lisaKüsimus();
                }
            });

            lisaTulemus.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    setKoguKüsimus(getKoguKüsimus().append(getKüsimus()+";"+getVastused()));
                    setTestiInfo(getKoguKüsimus().toString());
                    Tulemus tulemus = new Tulemus(uusAken, getTestiInfo());
                }
            });
        }

    public void lisaVariant(GridPane grid) {

        Label variant = new Label("Sisesta "+(getVastusteArv()+1)+" punkti andev vastus");
        TextField varianttxt = new TextField();
        Button salvesta = new Button("Salvesta vastus");
        grid.add(variant, 0, (getVastusteArv()+6));
        grid.add(varianttxt, 1, (getVastusteArv()+6));
        grid.add(salvesta, 2, (getVastusteArv())+6);
        salvesta.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setVastused(getVastused().append(varianttxt.getText()+";"));
                setVastusteArv(getVastusteArv()+1);
                lisaVariant(grid);
            }
        });
    }


    public void lisaTest() {
        GridPane grid = new GridPane();
        Label testiSilt = new Label("Sisesta testi nimi:  ");
        TextField testinimitxt = new TextField();
        Scene algus = new Scene(grid, 640, 425);
        grid.add(testiSilt, 0, 0);
        grid.add(testinimitxt, 1, 0);
        Label nimeSalvestamine = new Label("Nime salvestamiseks ja küsimuste lisamiseks vajuta ENTER");
        grid.add(nimeSalvestamine, 0, 1, 3, 1);
        uusAken.setScene(algus);
        uusAken.show();

        uusAken.setOnCloseRequest(e -> {
            e.consume();
            PopUp p = new PopUp("Kindel?");
            p.confirmation("Olete kindel, et soovite mängust lahkuda?");
            if (p.valik == true) {
                uusAken.close();
            }
        });

        algus.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ENTER && testinimitxt.getText().length() > 0) {
                    setTestiPealkiri(testinimitxt.getText());
                    setTestiInfo(getTestiPealkiri());
                    lisaKüsimus();
                }
            }
        });
    }

}