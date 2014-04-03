package utils;

import java.util.List;

import src.Airsched;
import src.Partition;

public class PartitionUtils {

	public static int getPartsHp(List<Partition> lop) {
		if (lop.size() == 1)
			return lop.get(0).getHyperPeriod();
		if (lop.size() == 2)
			return MathUtils.lcm(lop.get(0).getHyperPeriod(), lop.get(1)
					.getHyperPeriod());

		int ret = MathUtils.lcm(lop.get(0).getHyperPeriod(), lop.get(1)
				.getHyperPeriod());

		for (int i = 2; i < lop.size(); i++)
			ret = MathUtils.lcm(ret, lop.get(i).getHyperPeriod());

		// larger values may induce a crash
		if (ret > Airsched.MAX_PERIOD)
			return Airsched.MAX_PERIOD;
		return ret;
	}

}
