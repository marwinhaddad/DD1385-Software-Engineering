import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test extends TreeFrame {

    static final Pattern pattern = Pattern.compile("(?=.*(?<=<)(.*?)(?= ))(?=.*(?<=\")(.*?)(?=\"))(?=.*((?<=> ).*))");
    static Matcher matcher = null;
    static final String dirtxt = "liv.txt";

    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(new File(dirtxt));


            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                matcher = pattern.matcher(currentLine);
                if (matcher.find()) {
                    System.out.println(matcher.group(1) + " " + matcher.group(2) + " " + matcher.group(3));
                } else {
                    System.out.println(currentLine.substring(2, currentLine.length()-1));
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
