package cartsInterface;

import utils.BashUtils;

public class CartsInterface {

	public static void CartsAnalyse() {
		String[] cmd = { "java", "-jar", "carts/Carts.jar",
				"carts/xml/input.xml", "PRM", "carts/xml/output.xml" };
		System.out.println(BashUtils.cmdInterpreter(cmd));
	}
	
}