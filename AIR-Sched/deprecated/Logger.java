package deprecated;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//logger
//not used
public class Logger {

	private final String folder = "logs/";

	private String file_path;
	private File file;

	public Logger() {

	}

	public Logger(String file_name) {
		this.file_path = folder + file_name;
		this.file = new File(file_path);
		System.out.println(file.getAbsolutePath());
	}

	public void log(String str) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
		bw.write(str + "\n");
		bw.close();
		// BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		// bw.append(str + "\n");
		// bw.close();
	}

	public void plot() {

	}
}