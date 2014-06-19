package utils;

import java.util.List;

import deprecated.MathUtils;

import models.Partition;
import models.PeriodicTask;

public class PartitionUtils {

	// used to bound the period analysis, larger periods may induce a crash
	private static final int MAX_PERIOD = 200;

	public static int getPartsHp(List<Partition> lop) {

		int ret;

		if (lop.size() == 1)
			ret = lop.get(0).getHyperPeriod();
		if (lop.size() == 2)
			ret = MathUtils.lcm(lop.get(0).getHyperPeriod(), lop.get(1)
					.getHyperPeriod());

		ret = MathUtils.lcm(lop.get(0).getHyperPeriod(), lop.get(1)
				.getHyperPeriod());

		for (int i = 2; i < lop.size(); i++)
			ret = MathUtils.lcm(ret, lop.get(i).getHyperPeriod());

		// larger values may induce a crash
		if (ret > MAX_PERIOD)
			ret = MAX_PERIOD;
		return ret;
	}

	public static int getLargestPeriod(List<Partition> lop) {
		int ret = 0;
		for (Partition p : lop) {
			for (PeriodicTask pt : p.getWorkload()) {
				if (pt.getPeriod() > ret)
					ret = pt.getPeriod();
			}
		}
		return ret;
	}

	public static int getCriticalityByName(String name, List<Partition> lop) {
		for (Partition p : lop) {
			if (p.getName().equals(name))
				return p.getCriticality();
		}
		return -1;
	}

	public static Partition getPartition(List<Partition> lop, String name) {
		for (Partition p : lop) {
			if (p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}

}
