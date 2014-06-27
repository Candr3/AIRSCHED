package src;

import java.io.IOException;

import utils.MathUtils;

import deprecated.Logger;

import models.Partition;


// not used
public class CompositionalAnalyzer {

	public static void PRM(Partition p) {
		double hyperperiod = p.getHyperPeriod() * 1.0;

		Logger log = new Logger("plot1");

		for (double i = 1.0; i <= hyperperiod; i += 1.0) {

			double per = 0.01;
			System.out.println(p.getPartitionDBF(p.getHyperPeriod()));
			System.out.println(getPRMsbf(p.getHyperPeriod(), i * per, i));

			while ((per <= 1.00)
					&& (p.getPartitionDBF(p.getHyperPeriod()) > getPRMsbf(
							p.getHyperPeriod(), i, i * per))) {
				per += 0.01;
			}

			try {
				log.log(i + "\t" + per);
			} catch (IOException e) {
				System.out.println("deu merda");
				e.printStackTrace();
			}

			System.out.println("resource period: " + i + " resource supply: "
					+ per);
		}
	}

	private static double getPRMsbf(int t, double period, double supply) {
		int k = getK(t, period, supply);
		if (t >= (((k + 1) * period) - (2 * supply))
				&& t <= (((k + 1) * period) - supply))
			return t - ((k + 1) * (period - supply));
		else
			return (k - 1) * supply;
	}

	private static int getK(int t, double period, double supply) {
		int k = (int) MathUtils.upperBoundDivision((t - (period - supply)),
				period);
		if (k < 1)
			return 1;
		return k;
	}

}
