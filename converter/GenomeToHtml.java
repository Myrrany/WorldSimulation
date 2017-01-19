package WorldSimulation.converter;

import WorldSimulation.orderchaos.Being;
import WorldSimulation.orderchaos.Gene;
import WorldSimulation.orderchaos.GeneticsUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by simon on 17.01.17.
 */
public class GenomeToHtml {

	public static void main(String[] args) {
		Being jonah = new Being("Jonah Groenewoud", 23);
		jonah.setGenome(GeneticsUtils.generateGenome());
		GenomeToHtml.showInbrowser(GenomeToHtml.toHtml(jonah, true));
	}

	/**
	 * Opens firefox with a given filename.
	 *
	 * @param fileName
	 *            Relative path to the file you want to display.
	 */
	public static void showInbrowser(String fileName) {
		// Get the file you want to display
		File file = new File(fileName.replaceAll(" ", "_"));
		// Get the system Runtime, so you can execute commands.
		Runtime runtime = Runtime.getRuntime();
		try {
			// Run the command with the absolute path to the file
			Process process = runtime.exec("firefox " + file.getAbsolutePath());

			// Handle output and errors (optional, but useful during debugging)
			BufferedReader er = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			// Wait for the process to die (if firefox opens a new tab, that
			// happens instantly)
			process.waitFor();
			// Print all the output to StdOut
			while (br.ready()) {
				System.out.println(br.readLine());
			}
			// Print all the errors to ErrOut
			while (er.ready()) {
				System.err.println(er.readLine());
			}
			// Always close your streamReaders!
			br.close();
			er.close();
		} catch (IOException | InterruptedException e) {
			// Complain if stuff goes wrong.
			e.printStackTrace();
		}
	}

	/**
	 * Convert a being to a file and use the name of that being as the filename.
	 * Does not print the character while operating.
	 * 
	 * @param being
	 *            The being to be printed.
	 * @return The file name of the generated file.
	 */
	public static String toHtml(Being being) {
		// Just call the other method with added parameters
		return toHtml(being.getName(), being.getGeneArray(), false);
	}

	/**
	 * Convert a being to a file and use the name of that being as the filename.
	 * 
	 * @param being
	 *            The being to be printed.
	 * @param printStuff
	 *            If you want it to print the bing to the StdOut, set this to
	 *            true.
	 * @return The file name of the generated file.
	 */
	public static String toHtml(Being being, boolean printStuff) {
		return toHtml(being.getName(), being.getGeneArray(), printStuff);
	}

	/**
	 * Convert a being to a file and use the name of that being as the filename.
	 * 
	 * @param charName
	 *            The name of the character you want to print.
	 * @param genes
	 *            The genes you want to print.
	 * @param printStuff
	 *            If you want it to print the bing to the StdOut, set this to
	 *            true.
	 * @return The file name of the generated file.
	 */
	public static String toHtml(String charName, Gene[] genes, boolean printStuff) {
		// Create a HashMap, to store all the data before I can work with it.
		HashMap<String, HashMap<String, Integer>> convertedGenes = new HashMap<>();

		// Go through the genes. one by one
		for (Gene gene : genes) {
			// Split the name of the genome by Spaces to later detect if it's a
			// color or a binary value
			String[] split = gene.getGeneName().split(" ");
			// Just to make it shorter: assign the first word to a variable.
			String splitA = split[0];
			// SplitB is empty for now, since it could just be one word.
			String splitB = "";
			// If there are two words:
			if (split.length == 2)
				// Assign the second to SplitB
				splitB = split[1];
			// If there are two words and the second word is either "red",
			// "green" or "blue":
			if (split.length == 2 && (splitB.equalsIgnoreCase("red") || splitB.equalsIgnoreCase("green")
					|| splitB.equalsIgnoreCase("blue"))) {
				// If the name of the property is not already in the HashMap:
				if (!convertedGenes.containsKey(splitA))
					// Put it in
					convertedGenes.put(splitA, new HashMap<>());
				// Then put in the average of the two genomes as the value
				convertedGenes.get(splitA).put(splitB, avg(gene));
			} else {
				// If the name of the property is not already in the HashMap:
				if (!convertedGenes.containsKey(splitA))
					// Create it
					convertedGenes.put(gene.getGeneName(), new HashMap<>());
				// And then put the value in again, but this time it's names
				// boolean
				convertedGenes.get(gene.getGeneName()).put("boolean", add(gene));
			}
		}
		// If printStuff is true:
		if (printStuff) {
			// Go through each entry:
			for (Map.Entry<String, HashMap<String, Integer>> entry : convertedGenes.entrySet()) {
				// and print it's values
				System.out.println(entry.getKey());
				System.out.println(entry.getValue());
			}
		}

		// Create a File object with the name of the file
		File outputFile = new File("character_" + charName.replaceAll(" ", "_") + ".html");
		// If that file exists:
		if (outputFile.exists())
			// Delete it
			outputFile.delete();

		try {
			// Setup the PrintWriter with the file
			PrintWriter pw = new PrintWriter(outputFile);
			// And print HTML code to it!
			pw.println("<h1>" + charName + "</h1>");
			pw.println("<table style=\"border: 1px solid black\">");
			for (Map.Entry<String, HashMap<String, Integer>> item : convertedGenes.entrySet()) {
				pw.println("<tr>");
				pw.println("<td>");
				pw.println(item.getKey());
				pw.println("</td>");
				if (item.getKey().equalsIgnoreCase("Wings")) {
					if (item.getValue().get("boolean") == 2) {
						pw.println("<td style=\"width: 20px; background-color: rgb(" + item.getValue().get("red") + ", "
								+ item.getValue().get("green") + ", " + item.getValue().get("blue") + ");\">" + ""
								+ "</td>");
					} else {
						pw.println("<td>No</td>");
					}
				} else {
					pw.println("<td style=\"width: 20px; background-color: rgb(" + item.getValue().get("red") + ", "
							+ item.getValue().get("green") + ", " + item.getValue().get("blue") + ");\">"
							+ ((item.getValue().get("boolean") != null)
									? (item.getValue().get("boolean") == 2 ? "Yes" : "No") : "")
							+ "</td>");
				}
				pw.println("</tr>");
			}
			pw.println("</table>");
			// And of course, flush and close!
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// Return the name of the file
		return "character_" + charName + ".html";
	}

	/**
	 * Yes, I wrote a function that literally just adds the chromosomes.
	 * 
	 * @param gene
	 *            The gene that you want to use.
	 * @return The added value of both chromosomes.
	 */
	private static int add(Gene gene) {
		return gene.getChromA() + gene.getChromB();
	}

	/**
	 * Takes the average of two chromosomes.
	 * 
	 * @param gene
	 *            The gene you want to use.
	 * @return The average of both chromosomes.
	 */
	private static int avg(Gene gene) {
		return add(gene) / 2;
	}
}
