package comparators;

import java.util.Comparator;

import models.CartsComponent;

public class PartitionLargerPeriodFirstComparator implements
		Comparator<CartsComponent> {

	@Override
	public int compare(CartsComponent o1, CartsComponent o2) {
		return o1.getPeriod() - o2.getPeriod();
	}

}
