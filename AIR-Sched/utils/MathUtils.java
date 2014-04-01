package utils;

public class MathUtils {

	public static int upperBoundDivision(int dividendo, int divisor) {
		if ((dividendo % divisor) == 0) {
			return (dividendo / divisor);
		} else {
			return (dividendo / divisor) + 1;
		}
	}

	public static int lowerBoundDivision(int dividendo, int divisor) {
		return Math.round(dividendo / divisor);
	}

	public static double upperBoundDivision(double dividendo, double divisor) {
		if ((dividendo % divisor) == 0.0) {
			return (dividendo / divisor);
		} else {
			Double ret = (dividendo / divisor);
			return ret.intValue() + 1.0;
		}
	}

	public static double lowerBoundDivision(double dividendo, double divisor) {
		Double ret = (dividendo / divisor);
		return ret.intValue() * 1.0;
	}

	// lcm - euclides algorithm
	public static int gcd(int a, int b) {
		while (a != b) {
			if (a > b)
				a = a - b;
			else
				b = b - a;
		}
		return a;
	}

	public static int lcm(int[] v) {
		if (v.length == 1)
			return v[0];
		if (v.length == 2)
			return lcm(v[0], v[1]);
		int ret = lcm(v[0], v[1]);
		for (int i = 2; i < v.length; i++)
			ret = lcm(ret, v[i]);
		return ret;
	}

	public static int lcm(int a, int b) {
		return (a / gcd(a, b)) * b;
	}

}