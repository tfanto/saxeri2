package se.fanto.saxeri;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

	public void exec(String filename) {
		ValidationRequestParser saxParser = new ValidationRequestParser();
		try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(filename);) {

			List<DocElement> doc = saxParser.parse(is);

			// for demo
			doc.forEach(element -> {
				System.out.println(element.path);

				String info = "";

				if ((element.value != null) && (element.value.trim().length() > 0)) {
					info += "value=" + element.value;
				}
				if ((element.attributes != null) && (element.attributes.size() > 0)) {
					info += " attributes=";
					info += element.attributes.toString();
				}
				if (info.length() > 0) {
					System.out.println("   " + info + "");
				}

			});

			boolean writeThis = false;
			if (writeThis) {

				Set<String> uniqueController = new HashSet<>();

				String fileName = "C:/wrk/Cst.java";

				try (FileWriter writer = new FileWriter(fileName);) {

					doc.forEach(element -> {
						String k = element.path.replace("/", "_");
						k = k.substring(0, k.length() - 1);
						String constant = "public static final String " + k.toUpperCase() + " = \"" + element.path + "\";";

						if (!uniqueController.contains(constant)) {
							uniqueController.add(constant);

							try {
								writer.write(constant);
								writer.write("\n");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					});

				}
				try (FileWriter writer = new FileWriter(fileName, true);) {

					uniqueController.forEach(constant -> {
						try {
							writer.write("// LIST " + constant);
							writer.write("\n");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					});

				}

				// generate key constants for java
				System.out.println("******************************");
				System.out.println("*** Generate key-constants *** " + doc.size());
				System.out.println("******************************");
				doc.forEach(element -> {
					String k = element.path.replace("/", "_");
					k = k.substring(0, k.length() - 1);
					System.out.println("public static final String " + k.toUpperCase() + " = \"" + element.path + "\";");

				});
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * try
	 * 
	 * { FileWriter writer = new FileWriter("C:/Your/Absolute/Path/YourFile.txt");
	 * writer.write("Wow, this is so easy!"); writer.close(); }catch( IOException e)
	 * { e.printStackTrace(); }
	 */

	public static void main(String[] args) {

		Main main = new Main();
		main.exec("ValidationRequest.xml");
		// main.exec("fil.xml");
	}

}
