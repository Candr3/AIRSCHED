package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BashUtils {
	private static final String[] BASH_CMD = { "/bin/bash", "-c" };

	public static String cmdInterpreter(String[] arg) {

		try {

			String[] cmd = new String[arg.length + 2];
			System.arraycopy(BASH_CMD, 0, cmd, 0, BASH_CMD.length);
			System.arraycopy(arg, 0, cmd, BASH_CMD.length, arg.length);

			for (String str : cmd)
				System.out.println(str);

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

		} catch (Exception e) {
			return "deu merda";
		}
	}

}