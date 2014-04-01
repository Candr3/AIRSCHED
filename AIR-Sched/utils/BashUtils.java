package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BashUtils {

	public static String cmdInterpreter(String[] cmd) throws IOException,
			InterruptedException {
		// String[] cmd = { "/bin/bash", "-c", "ls | grep log" };
		Process p = Runtime.getRuntime().exec(cmd);
		p.waitFor();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				p.getInputStream()));
		StringBuilder sb = new StringBuilder("");
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line + "\n");
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
	
}
