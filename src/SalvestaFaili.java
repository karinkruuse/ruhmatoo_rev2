import java.io.*;
import java.util.ArrayList;

public class SalvestaFaili {

    public SalvestaFaili(ArrayList<String> tekst) {

        String failiPath = "resources" + File.separatorChar + "testid" + File.separatorChar;
        try (BufferedWriter output = new BufferedWriter(new FileWriter(failiPath + "nimed.txt", true))){
            output.write(tekst.get(0));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedWriter output = new BufferedWriter(new FileWriter(failiPath + tekst.get(0) + ".txt", true))) {
            for (int i = 1; i < tekst.size(); i++) {
                output.write(tekst.get(i));
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
