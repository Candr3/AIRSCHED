package utils;

import java.util.List;

import src.Partition;

public class PartitionUtils {

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

}
