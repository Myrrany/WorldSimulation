package WorldSimulation.orderchaos;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class is the view for the simulation. It will start up the TUI and parse commands.
 *
 * @author Myrthe Hultermans
 * @version 1.0
 */
public class SimulationTUI extends Thread {

	private String help = "Possible commands: \n"
			+ "\nINITIATE [amount]: Initiates a population of [amount] people with random names."
			+ "\nROMANCE: Gives singles a partner." + "\nFIND [name]: Finds a specific being." +
			"\nAGE [amount]: Lets a year [* amount] pass." + "\nALL [ALIVE/DEAD]: Gives a list of all [ALIVE/DEAD] " +
			"beings in the simulation.";

	private Simulation simul;

	/**
	 * This method will create a new instance of the class.
	 */
	private SimulationTUI() {

	}

	/**
	 * The main method of the class. This will start a new simulation and TUI.
	 *
	 * @param args Any arguments that could be passed into the method. Not that they will be used, but still.
	 */
	public static void main(String[] args) {
		SimulationTUI tui = new SimulationTUI();
		Thread simulation = new Thread(tui);
		simulation.start();

	}

	/**
	 * This method will run the actual TUI and will give the user the possibility to type in text commands. These
	 * will then be given to a parser.
	 */
	public void run() {

		Scanner s = new Scanner(System.in);
		System.out.println("Welcome!");
		System.out.println("Starting simulation!");
		this.simul = new Simulation();
		System.out.println("Simulation started!");
		System.out.println(help);
		try {
			while (true) {
				String com = s.next();
				String rest = s.nextLine();
				// parse(com, rest);

				if (parse(com, rest)) {
					break;
				}
			}
		} catch (InterruptedException | IOException e) {
			s.close();
			e.printStackTrace();
		}
	}

	/**
	 * This method will parse text commands and then trigger the right methods to do the wanted things.
	 *
	 * @param com  The keyword of the command.
	 * @param rest The rest of the command, usually extra parameters.
	 * @return Whether the system should shut down or not.
	 * @throws InterruptedException This method might interrupt the thread.
	 * @throws IOException          As this method gets its information from a scanner, there might be exceptions in
	 *                              I/O.
	 */
	private boolean parse(String com, String rest) throws InterruptedException, IOException {
		if (com.equalsIgnoreCase("INITIATE")) {
			simul.initialPopulation(Integer.parseInt(rest.substring(1)));
		} else if (com.equalsIgnoreCase("ROMANCE")) {
			simul.makeMatches();
		} else if (com.equalsIgnoreCase("FIND")) {
			simul.findBeing(rest.substring(1));
		} else if (com.equalsIgnoreCase("AGE")) {
			if (rest.length() != 0) {
				simul.multipleYears(Integer.parseInt(rest.substring(1)));
			} else {
				simul.time();
			}
		} else if (com.equalsIgnoreCase("ALL")) {
			if (rest.equalsIgnoreCase(" ALIVE")) {
				simul.getLivingPopulation();
			} else if (rest.equalsIgnoreCase(" DEAD")) {
				simul.getDeadPopulation();
			} else {
				simul.getPopulation();
			}
		} else if (com.equalsIgnoreCase("EXIT")) {
			System.out.println("Goodbye system");
			this.interrupt();
			return true;
		} else if (com.equalsIgnoreCase("HELP")) {
			System.out.println(help);
		}
		return false;

	}
}
