import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SalvestaFaili {

    public SalvestaFaili(ArrayList<String> küsimused, ArrayList<String> tulemused) {

        String failiPath = "resources" + File.separatorChar + "testid" + File.separatorChar;
        try (BufferedWriter output = new BufferedWriter(new FileWriter(failiPath + "nimed.txt", true))){
            output.write(küsimused.get(0).replace("?", "") + "\n");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedWriter output = new BufferedWriter(new FileWriter(failiPath + küsimused.get(0) + ".txt", true))) {
            for (int i = 2; i < küsimused.size(); i++) {
                output.write(küsimused.get(i) + "\n");
            }
            output.write("\n");
            for (int i = 0; i < tulemused.size(); i++) {
                output.write(tulemused.get(i) + "\n");
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
