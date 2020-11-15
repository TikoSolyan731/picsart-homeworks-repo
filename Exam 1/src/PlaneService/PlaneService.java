package PlaneService;

import Plane.*;

public class PlaneService {
    public void printPlane(Plane p) {
        System.out.println("Model: " + p.getModel());
        System.out.println("Country: " + p.getCountry());
        System.out.println("Year: " + p.getYear());
        System.out.println("Hours in the air: " + p.getHours());
        System.out.println("IsMilitary: " + p.isMilitary());
        System.out.println("Engine Type: " + p.getEngineType());
        System.out.println("Weight: " + p.getWeight());
        System.out.println("Wingspan: " + p.getWingspan());
        System.out.println("Top Speed: " + p.getTopSpeed());
        System.out.println("Seats: " + p.getSeats());
        System.out.println("Cost: " + p.getCost());
    }

    public void militaryPrint(Plane p) {
        if (p.isMilitary())
            System.out.print("Cost: " + p.getCost() + "\nTop Speed: " + p.getTopSpeed());
        else
            System.out.print("Model: " + p.getModel() + "\nCountry: " + p.getCountry());
    }

    public Plane newerPlane(Plane p1, Plane p2) {
        return p1.getYear() >= p2.getYear() ? p1 : p2;
    }

    public String biggerWingspanModel(Plane p1, Plane p2) {
        return p1.getWingspan() > p2.getWingspan() ? p1.getModel() : p2.getModel();
    }

    public Plane highestCost(Plane p1, Plane p2, Plane p3) {
        if (p1.getCost() >= p2.getCost()) {
            if (p1.getCost() >= p3.getCost())
                return p1;
            else
                return p3;
        } else {
            if (p2.getCost() >= p3.getCost())
                return p2;
            else
                return p3;
        }
    }

    public void smallestSeatCountCountry(Plane p1, Plane p2, Plane p3) {
        if (p1.getSeats() <= p2.getSeats() && p1.getSeats() <= p3.getSeats()) {
            System.out.println("Country: " + p1.getCountry());
        } else if (p2.getSeats() <= p1.getSeats() && p2.getSeats() <= p3.getSeats())
            System.out.println("Country: " + p2.getCountry());
        else
            System.out.println("Country: " + p3.getCountry());
    }

    public void printNotMilPlanes(Plane[] planes) {
        for (Plane p : planes) {
            if (!p.isMilitary()) {
                printPlane(p);
                System.out.println("\n");
            }
        }
    }

    public void printMilPlanesLongerThanHundred(Plane[] planes) {
        for (Plane p : planes) {
            if (p.isMilitary() && p.getHours() > 100) {
                printPlane(p);
                System.out.println("\n");
            }
        }
    }

    public Plane minimalWeightPlane(Plane[] planes) {
        Plane minWeightPlane = planes[0];
        for (Plane p : planes) {
            if (p.getWeight() <= minWeightPlane.getWeight())
                minWeightPlane = p;
        }

        return minWeightPlane;
    }

    public Plane minimalCostMilPlane(Plane[] planes) {
        Plane minCostMilPlane = null;

        for (Plane p : planes) {
            if (p.isMilitary()) {
                minCostMilPlane = p;
                break;
            }
        }
        for (Plane p : planes) {
            if (p.getCost() < minCostMilPlane.getCost())
                minCostMilPlane = p;
        }

        return minCostMilPlane;
    }

    public void printAscendingOrderByYear(Plane[] planes) {
        Plane[] temp = new Plane[planes.length];

        for (int i = 0; i < planes.length; i++)
            temp[i] = planes[i];

        boolean swapped = true;

        while (swapped) {
            swapped = false;

            for (int i = 0; i < temp.length - 1; i++) {
                if (temp[i].getYear() > temp[i + 1].getYear()) {
                    Plane p = temp[i];
                    temp[i] = temp[i + 1];
                    temp[i + 1] = p;

                    swapped = true;
                }
            }
        }

        for (Plane p : temp) {
            printPlane(p);
            System.out.println("-----");
        }
    }

    public void printNotMilBySeatsDescending(Plane[] planes) {
        Plane[] temp = new Plane[planes.length];

        for (int i = 0; i < planes.length; i++) {
            temp[i] = planes[i];
        }

        boolean swapped = true;

        while (swapped) {
            swapped = false;

            for (int i = 0; i < temp.length - 1; i++) {
                if (temp[i].getSeats() < temp[i + 1].getSeats()) {
                    Plane p = temp[i];
                    temp[i] = temp[i + 1];
                    temp[i + 1] = p;

                    swapped = true;
                }
            }
        }

        printNotMilPlanes(temp);
    }
}
