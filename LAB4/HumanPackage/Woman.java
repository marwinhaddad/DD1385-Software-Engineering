package HumanPackage;

public class Woman extends Human{

    String name;

    Woman(String name) {this.name = name;}

    @Override
    public String toString() {return "Jag är en kvinna och heter " + this.name;}
}
