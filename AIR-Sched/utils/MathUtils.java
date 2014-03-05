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
	
}
