public class Utilities {

    public static boolean isInt(String strNum) {
        boolean r = true;
        try {
            Integer.parseInt(strNum);
        }
        catch (NumberFormatException e) {
            r = false;
        }
        return r;
    }


    public static String[] shuffle(String[] segada) {
        for (int i = segada.length - 1; i > 0; i--)
        {
            int index = (int) Math.floor(Math.random()*(i+1));
            String a = segada[index];
            segada[index] = segada[i];
            segada[i] = a;
        }
        return segada;
    }

}
