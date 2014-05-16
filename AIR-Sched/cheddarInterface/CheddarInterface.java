package cheddarInterface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import src.Airsched;
import utils.BashUtils;

public class CheddarInterface {

	private static final String CHEDDAR_ROOT = "/home/andre/Cheddar/Cheddar_compil/terminal/src";
	private static final String FILENAME = "litecheddar.sh";

	public static boolean analyse() {

		createBash();
		String[] cmd_array = { "chmod", "755", FILENAME };
		BashUtils.cmdInterpreter(cmd_array);

		File dir = new File(Airsched.getOutput_dir());
		int i = 0;
		for (File f : dir.listFiles()) {
			String[] cmd_cheddar = { "./" + FILENAME, "-file",
					f.getAbsolutePath(), "-request", "all", ">",
					"output" + i + ".txt" };
			BashUtils.cmdInterpreter(cmd_cheddar);
			i++;
		}
		//destroyBash();

		return true;
	}

	public static boolean createBash() {
		File f = new File(FILENAME);
		if (f.exists()) {
			f.delete();
		}

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));

			bw.write("#!/bin/bash\n");
			bw.write("\n");
			bw.write("export CHEDDAR_PATH=" + CHEDDAR_ROOT + "\n");
			bw.write("export CHEDDAR_INSTALL_PATH=$CHEDDAR_PATH/graphical_editor/\n");
			bw.write("$CHEDDAR_PATH/binaries/bin/release/cheddarlite $1 $2 $3 $4\n");

			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static boolean destroyBash() {
		File f = new File(FILENAME);
		if (f.exists()) {
			return f.delete();
		}
		return false;
	}

}
