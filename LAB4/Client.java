import java.util.*;

class Client {

    public static void main(String[] arg) {

        // level 1
        Composite suitcase = new Composite("Suitcase", 2.0);

        // level 2
        Composite toiletBag = new Composite("Toilet bag", 0.200);
        Composite sportsBag = new Composite("Sports bag", 0.4);
        Composite handBag = new Composite("Hand bag", 1.0);

        // level 3
        Leaf deodorant = new Leaf("Deodorant", 0.100);
        Leaf toothbrush = new Leaf("Toothbrush", 0.010);
        Leaf shampoo = new Leaf("Shampoo", 0.300);

        Composite plasticBag = new Composite("Plastic bag", 0.005);
        Leaf clothes = new Leaf("Clothes", 5.0);

        Composite wallet = new Composite("Wallet", 0.020);
        Leaf phone = new Leaf("Phone", 0.200);
        Leaf charger = new Leaf("Charger", 0.050);


        // level 4
        Leaf shoes = new Leaf("Shoes", 1.0);

        Leaf idCard = new Leaf("ID-Card", 0.005);


        // adding all items in their respective container
        plasticBag.add(shoes);
        wallet.add(idCard);

        handBag.add(wallet);
        handBag.add(phone);
        handBag.add(charger);

        sportsBag.add(plasticBag);
        sportsBag.add(clothes);

        toiletBag.add(toothbrush);
        toiletBag.add(deodorant);
        toiletBag.add(shampoo);

        suitcase.add(sportsBag);
        suitcase.add(toiletBag);
        suitcase.add(handBag);

        System.out.println(suitcase);
        System.out.println(suitcase.getWeight());

        System.out.println();


        // X4
        System.out.println();
        System.out.println("Iterator output:");
        for (Component current : suitcase) {
            System.out.println(current.getName());
        }

        handBag.remove(wallet);
        sportsBag.remove(plasticBag);
        suitcase.remove(toiletBag);

        System.out.println(suitcase);
        System.out.println(suitcase.getWeight());


        // X4
        System.out.println();
        System.out.println("Iterator output:");
        for (Component current : suitcase) {
            System.out.println(current.getName());
        }
    }
}