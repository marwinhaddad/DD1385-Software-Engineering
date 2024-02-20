package HumanPackage;

public class NonBinary extends Human {

    String name;

    NonBinary(String name) {this.name = name;}

    @Override
    public String toString() {return "Jag är icke-binär och heter " + this.name;}
}
