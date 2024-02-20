import HumanPackage.*;

public class HumanFactory {
    public static  void main(String[] args) {

        // Human h = new Human() {};

        Human billie = Human.create("Billie", "xxxxxx-560x");
        Human anna = Human.create("Anna", "xxxxxx-642x");
        Human magnus = Human.create("Magnus","xxxxxx-011x");
        System.out.println(billie);
        System.out.println(anna);
        System.out.println(magnus);

        /*
        NonBinary blahonga = new NonBinary("Blahonga", "xxxxxx-560x");
        Woman blahongina = new Woman("Blahongina", "xxxxxx-642x");
        Man blahongurt = new Man("Blahongurt", "xxxxxx-011x");
        System.out.println(blahonga);
        System.out.println(blahongina);
        System.out.println(blahongurt);
         */
    }
}
