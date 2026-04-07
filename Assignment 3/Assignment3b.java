class Places{
    String name;
    Places(String n){
        name = n;
    }

    void showName(){
        System.out.println("Place: " + name);
    }

    void famousfood(){
        System.out.println("Famous food: Not specified");
    }

    void famousfor(){
        System.out.println("Famous for: Not specified");
    }
}


class Delhi extends Places{
    Delhi(){
        super("Delhi");
    }

    void famousfood(){
        System.out.println("Famous food: Chole bhature and Butter Chicken");
    }

    void famousfor(){
        System.out.println("Famous for: Historical monuments, culture, & food");
    }
}


class Spain extends Places{
    Spain(){
        super("Spain");
    }

    void famousfood(){
        System.out.println("Famous food: Paella and Tapas");
    }

    void famousfor(){
        System.out.println("Famous for: Football and festivals");
    }
}


class Mumbai extends Places{
    Mumbai() {
        super("Mumbai");
    }
    void famousfood(){
        System.out.println("Famous food: Vada Pav");
    }
    void famousfor(){
        System.out.println("Famous for: Bollywood, Marine Drive,");
    }
}

class Pune extends Places{
    Pune(){
        super("Pune");
    }
    void famousfood(){
        System.out.println("Famous food: Misal Pav");
    }
    void famousfor(){
        System.out.println("Famous for: Education hub and IT parks");
    }
}



public class Assignment3b {
    public static void main(String[] args) {

        Places arr[] = {
                new Delhi(),
                new Spain(),
                new Mumbai(),
                new Pune()
        };


        for (Places p : arr) {
            p.showName();
            p.famousfood();
            p.famousfor();
            System.out.println();
        }
    }
}