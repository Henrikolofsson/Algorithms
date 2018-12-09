import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestAlgorithmLab1 {

    public static String filterOutChar(String in, char charToGetRidOf) {
        String out = "";
        for (int i = 0; i < in.length(); i++) {
            char c = in.charAt(i);
            if (c != charToGetRidOf) {
                out += c;
            }
        }
        return out;
    }

    public static void main(String[] args) {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get("bible-en.txt"));
            String s = new String(encoded, 0, 51200, StandardCharsets.US_ASCII);
            long before = System.currentTimeMillis();
            System.out.println("Before: " + before);
            String t = filterOutChar(s, 'e');
            long after = System.currentTimeMillis();
            System.out.println("After: " + after);
            System.out.println("Difference: " + ((after - before) / 1000.0));
            System.out.println("Efter: " + t);

            System.out.println("Tid för 100 tecken = 0.041");
            System.out.println("Tid för 200 tecken = 0.039");
            System.out.println("Tid för 400 tecken = 0.037");
            System.out.println("Tid för 800 tecken = 0.043");
            System.out.println("Tid för 1600 tecken = 0.045");
            System.out.println("Tid för 3200 tecken = 0.048");
            System.out.println("Tid för 6400 tecken = 0.059");
            System.out.println("Tid för 12800 tecken = 0.082");
            System.out.println("Tid för 25600 tecken = 0.165");
            System.out.println("Tid för 51200 tecken = 0.392");
        } catch(IOException e){
            e.printStackTrace();
        }

    }
}
