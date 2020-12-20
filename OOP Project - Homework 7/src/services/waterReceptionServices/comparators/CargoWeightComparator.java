package services.waterReceptionServices.comparators;

import reception.waterReception.CargoDock;

import java.util.Comparator;

public class CargoWeightComparator implements Comparator<CargoDock> {
    @Override
    public int compare(CargoDock o1, CargoDock o2) {
        return (int) (o1.getCurrentCargoWeight() - o2.getCurrentCargoWeight());
    }
}
