package main;

import transport.*;
import transport.landTransport.*;
import transport.waterTransport.*;

public class Homework4 {
    public static void main(String[] args) {
        // Task 1. Create your own project using inheritance (minimum 5 objects).
        Transport tr1 = new Transport(120, 50, 60);
        Car c1 = new Car(300, 50);
        Train train1 = new Train();
        Metro m1 = new Metro(true, 5, "Barekamutyun");
        m1.setSpeed(300);
        m1.setWeight(900);
        m1.setVolume(800);

        Ship sh1 = new Ship();
        CargoShip cargoSh1 = new CargoShip(20000, "USA", "England");
        CruiseShip cruiseShip1 = new CruiseShip();
        cruiseShip1.setVisitingPlaces(new String[]{"Hawaii", "Mexican Coast", "Cuba"});

        System.out.println(tr1);
        System.out.println();
        System.out.println(c1);
        System.out.println();
        System.out.println(train1);
        System.out.println();
        System.out.println(m1);
        System.out.println();
        System.out.println(sh1);
        System.out.println();
        System.out.println(cargoSh1);
        System.out.println();
        System.out.println(cruiseShip1);
        System.out.println();
        System.out.println();
        System.out.println();

        // Task 2. Investigate String functions.

        String s = new String(new char[]{'H', 'e', 'l', 'l', 'o'}, 2, 2);
        System.out.println(s);

        String str1 = "Hello World!";
        System.out.println(str1.length());
        System.out.println("abc".length());

        for (int i = 0; i < str1.length(); i++) {
            System.out.print(str1.charAt(i) + " ");
        }

        System.out.println();
        char[] buf = new char[7 - 3];
        str1.getChars(3, 7, buf, 0);
        System.out.println(buf);

        char[] chars = str1.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            System.out.print(chars[i] + " ");
        }
        System.out.println();

        System.out.println(str1.equals("Hello World!"));
        System.out.println(str1.equalsIgnoreCase("hello world!"));

        System.out.println(str1.startsWith("World"));
        System.out.println(str1.endsWith("!"));

        System.out.println(str1.compareTo(s) > 0 ? "Comes Before" : str1.compareTo(s) < 0 ? "Comes After" : "Same");
        System.out.println(str1.indexOf('l'));
        System.out.println(str1.lastIndexOf('l'));
        System.out.println(str1.indexOf(" Wo"));
        System.out.println(str1.lastIndexOf("ld"));

        System.out.println(str1.substring(6));
        System.out.println(str1.substring(0, 4));

        System.out.println(str1.substring(0, 6).concat(str1.substring(0, 4)));
        System.out.println(str1.replace('l', 'w'));
        System.out.println("    HI!!   ".trim().toLowerCase());

        System.out.println(String.valueOf(456));
        System.out.println(String.valueOf(456.2));
        System.out.println("hello World".toUpperCase());

        System.out.println("".isEmpty());
        System.out.println(String.join("|", "Hello", "World", "How", "Are", "You?"));

        StringBuffer str2 = new StringBuffer("Hello");
        System.out.println(str2);
        System.out.println("length " + str2.length());
        System.out.println("capacity " + str2.capacity());

        str2.setLength(100);
        System.out.println(str2.length());
        String str3 = str2.append(" World").append("!").toString();
        System.out.println(str3);
        System.out.println(str2.reverse().deleteCharAt(0));
    }
}
