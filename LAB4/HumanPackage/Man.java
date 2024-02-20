package HumanPackage;

public class Man extends Human {

    String name;

    Man(String name) {this.name = name;}

    @Override
    public String toString() {return "Jag är en man och heter " + this.name;}
}
