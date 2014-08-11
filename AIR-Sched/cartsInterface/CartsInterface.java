package cartsInterface;

import src.Airsched;
import utils.BashUtils;

public class CartsInterface {

	public static void CartsAnalyse() {
		String[] cmd = { "java", "-jar", "carts/Carts.jar",
				"carts/xml/input.xml", getModel(), "carts/xml/output.xml" };
		System.out.println(BashUtils.cmdInterpreter(cmd));
	}

	private static String getModel() {
		if (Airsched.getModel() == Airsched.EXPLICIT_DEADLINE_MODEL)
			return "EDP";
		else if (Airsched.getModel() == Airsched.PERIODIC_RESOURCE_MODEL)
			return "PRM";
		else
			return "PRM";
	}

}