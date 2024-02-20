package HumanPackage;

public abstract class Human {

    Human(){} // Becomes package private by leaving out modifiers
              // Hence, new Human(){} is not allowed in HumanFactory
              // https://stackoverflow.com/questions/34605696/define-package-private-access-modifier-in-java-class

    public static Human create(String name, String number) {

        int digit = Integer.parseInt(number.charAt(number.length() - 2) + "");

        if (digit == 0) {
            return new NonBinary(name);
        } else if (digit % 2 == 1) {
            return new Man(name);
        } else {
            return new Woman(name);
        }
    }
}
