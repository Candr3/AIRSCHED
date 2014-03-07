package src;

import utils.MathUtils;

public class CompositionalAnalyzer {

	public static void PRM(Partition p) {
		
	}

	private static double getPRMsbf(int t, int period, int supply) {
		int k = getK(t, period, supply);
		if (t >= (((k + 1) * period) - (2 * supply))
				&& t <= (((k + 1) * period) - supply))
			return t - ((k + 1) * (period - supply));
		else
			return (k - 1) * supply;
	}

	private static int getK(int t, int period, int supply) {
		int k = MathUtils.upperBoundDivision((t - (period - supply)), period);
		if (k < 1)
			return 1;
		return k;
	}

}
