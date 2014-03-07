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
		return (dividendo / divisor);
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

	public static int lcm(int a, int b) {
		return (a / gcd(a, b)) * b;
	}

}