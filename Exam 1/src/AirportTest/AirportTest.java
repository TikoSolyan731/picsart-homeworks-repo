package AirportTest;

import Plane.Plane;
import PlaneService.*;

import java.util.Scanner;

public class AirportTest {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("First Plane\n");
        Plane pl1 = new Plane();
        System.out.print("Model: ");
        pl1.setModel(s.next());
        System.out.print("Country: ");
        pl1.setCountry(s.next());
        System.out.print("Year: ");
        pl1.setYear(s.nextInt());
        System.out.print("Hours: ");
        pl1.setHours(s.nextInt());
        System.out.print("isMilitary: ");
        pl1.setMilitary(s.nextBoolean());
        System.out.print("Engine Type: ");
        s.nextLine();
        pl1.setEngineType(s.nextLine());
        System.out.print("Weight: ");
        pl1.setWeight(s.nextInt());
        System.out.println("Wingspan: ");
        pl1.setWingspan(s.nextInt());
        System.out.print("Top Speed: ");
        pl1.setTopSpeed(s.nextInt());
        System.out.print("Seats: ");
        pl1.setSeats(s.nextInt());
        System.out.println("Cost: ");
        pl1.setCost(s.nextDouble());

        System.out.println("Second Plane\n");
        Plane pl2 = new Plane();
        System.out.print("Model: ");
        pl2.setModel(s.next());
        System.out.print("Country: ");
        pl2.setCountry(s.next());
        System.out.print("Year: ");
        pl2.setYear(s.nextInt());
        System.out.print("Hours: ");
        pl2.setHours(s.nextInt());
        System.out.print("isMilitary: ");
        pl2.setMilitary(s.nextBoolean());
        System.out.print("Engine Type: ");
        s.nextLine();
        pl2.setEngineType(s.nextLine());
        System.out.print("Weight: ");
        pl2.setWeight(s.nextInt());
        System.out.println("Wingspan: ");
        pl2.setWingspan(s.nextInt());
        System.out.print("Top Speed: ");
        pl2.setTopSpeed(s.nextInt());
        System.out.print("Seats: ");
        pl2.setSeats(s.nextInt());
        System.out.println("Cost: ");
        pl2.setCost(s.nextDouble());

        System.out.println("Third Plane\n");
        Plane pl3 = new Plane();
        System.out.print("Model: ");
        pl3.setModel(s.next());
        System.out.print("Country: ");
        pl3.setCountry(s.next());
        System.out.print("Year: ");
        pl3.setYear(s.nextInt());
        System.out.print("Hours: ");
        pl3.setHours(s.nextInt());
        System.out.print("isMilitary: ");
        pl3.setMilitary(s.nextBoolean());
        System.out.print("Engine Type: ");
        s.nextLine();
        pl3.setEngineType(s.nextLine());
        System.out.print("Weight: ");
        pl3.setWeight(s.nextInt());
        System.out.println("Wingspan: ");
        pl3.setWingspan(s.nextInt());
        System.out.print("Top Speed: ");
        pl3.setTopSpeed(s.nextInt());
        System.out.print("Seats: ");
        pl3.setSeats(s.nextInt());
        System.out.println("Cost: ");
        pl3.setCost(s.nextDouble());


        Plane[] planes = new Plane[]{pl1, pl2, pl3};
        PlaneService ps = new PlaneService();

        System.out.println("----------Print Plane------------");
        ps.printPlane(pl1);
        System.out.println("-------------------------------");
        System.out.println("----------Military Plane------------");
        ps.militaryPrint(pl1);
        System.out.println();
        System.out.println("-------------------------------");
        System.out.println("----------Newer Plane------------");
        ps.printPlane(ps.newerPlane(pl1, pl2));
        System.out.println("-------------------------------");
        System.out.println("----------Bigger Wingspan Model------------");
        System.out.println(ps.biggerWingspanModel(pl1, pl2));
        System.out.println("-------------------------------");
        System.out.println("----------Highest Cost Plane------------");
        ps.printPlane(ps.highestCost(pl1, pl2, pl3));
        System.out.println("-------------------------------");
        System.out.println("----------Smallest Seat Country------------");
        ps.smallestSeatCountCountry(pl1, pl2, pl3);
        System.out.println("-------------------------------");
        System.out.println("----------Print Not Mil Planes------------");
        ps.printNotMilPlanes(planes);
        System.out.println("-------------------------------");
        System.out.println("----------printMilPlanesLongerThanHundred------------");
        ps.printMilPlanesLongerThanHundred(planes);
        System.out.println("-------------------------------");
        System.out.println("----------minimalWeightPlane------------");
        ps.printPlane(ps.minimalWeightPlane(planes));
        System.out.println("-------------------------------");
        System.out.println("----------minimalCostMilPlane------------");
        ps.printPlane(ps.minimalCostMilPlane(planes));
        System.out.println("-------------------------------");
        System.out.println("----------printAscendingOrderByYear------------");
        ps.printAscendingOrderByYear(planes);
        System.out.println("-------------------------------");
        System.out.println("----------printNotMilBySeatsDescending------------");
        ps.printNotMilBySeatsDescending(planes);
    }
}
