import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// pm testi tekst on saadav läbi selle klassi
public class VirtualFile {

    // see on alguses nimede printimiseks 'nimed.txt' failist
    public static List<String[]> testiNimed() throws Exception {

        File fail = new File("nimed.txt");
        Scanner sc = new Scanner(fail);
        List<String[]> testideNimed = new ArrayList<>();

        int i  = 0;
        while(sc.hasNextLine()) {
            i++;
            String[] test = sc.nextLine().trim().split(":");
            testideNimed.add(test);
        }
        sc.close();
        return testideNimed;

    }


    private List<String[]> küsimused = new ArrayList<>();
    private List<String> tulemused = new ArrayList<>();
    private List<Integer> vahemikÜlempiir = new ArrayList<>();
    private List<Integer> vahemikAlampiir = new ArrayList<>();
    private int pikkus;
    private int tulemusteArv;


    // Kogu fail loetaks erinevatesse listidesse (need listid ongi justkui virtuaalne fail siin koodi sees)
    public VirtualFile(String textFail) throws Exception {
        File fail = new File(textFail);
        Scanner s = new Scanner(fail);

        // küsimused
        String rida = "";
        int i = 0;
        while (s.hasNextLine()) {
            rida = s.nextLine().trim();
            if (rida.isEmpty()) { break; }
            küsimused.add(i, rida.split(";"));
            i++;
        }

        pikkus = i;

        while (s.hasNextLine()) {
            rida = s.nextLine().trim();
            vahemikAlampiir.add(Integer.parseInt(rida.split(";")[1]));
            vahemikÜlempiir.add(Integer.parseInt(rida.split(";")[2]));
            tulemused.add(rida.split(";")[0]);
            i++;
        }

        tulemusteArv = i - pikkus;
    }


    public String[] getKüsimus(int küsimuseNr) {
        return küsimused.get(küsimuseNr-1);
    }


    public String suvalineTulemus() {
        int r = (int)Math.floor((Math.random() * tulemusteArv));
        return tulemused.get(r);
    }


    public String getTulemus(int index) {
        return tulemused.get(index);
    }

    public int getPikkus() {
        return pikkus;
    }


    public int getTulemusteArv() {
        return tulemusteArv;
    }


    public int getVahemikÜlempiir(int index) {
        return vahemikÜlempiir.get(index);
    }


    public int getVahemikAlampiir(int index) {
        return vahemikAlampiir.get(index);
    }
}