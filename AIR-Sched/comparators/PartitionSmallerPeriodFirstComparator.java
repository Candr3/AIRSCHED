package comparators;

import java.util.Comparator;

import models.CartsComponent;

public class PartitionSmallerPeriodFirstComparator implements
		Comparator<CartsComponent> {

	@Override
	public int compare(CartsComponent o1, CartsComponent o2) {
		return 0 - (o1.getPeriod() - o2.getPeriod());
	}

}
