package comparators;

import java.util.Comparator;

import models.CartsComponent;

public class PartitionCriticalityFirstComparator implements
		Comparator<CartsComponent> {
	
	@Override
	public int compare(CartsComponent o1, CartsComponent o2) {
		return o1.getCriticality() - o2.getCriticality();
	}

}