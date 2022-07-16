package com.foxminded.formula.managers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.foxminded.formula.formatters.TableFormatter;

public class ConsoleManager {

	private TableFormatter table = new TableFormatter();
	private FileReader reader = new FileReader();
	private RacersResolver resolver = new RacersResolver(reader);

	public void run() throws IOException {

		try {
			reader.setAbbreviations("abbreviations.txt");
			reader.setEndLog("end.log");
			reader.setStartLog("start.log");
			System.out.println("Formatting result table:\n");
			System.out.println(table.formatTable(resolver.createListOfRacers()));

		} catch (Exception ex) {
			System.out.println("Error has been occured: " + ex.getLocalizedMessage());
			ex.printStackTrace();
		} finally {
			try (InputStreamReader streamReader = new InputStreamReader(System.in);
					BufferedReader bufferedReader = new BufferedReader(streamReader);) {
				System.out.println("\nDo you want to try again? (Y/n)");
				String confirm = bufferedReader.readLine();
				if (confirm.equalsIgnoreCase("Y")) {
					System.runFinalization();
					run();
				} else if (confirm.equalsIgnoreCase("n")) {
					System.out.println("Exiting...");
					bufferedReader.close();
					streamReader.close();
				} else {
					System.out.println("Could not resolve the answer. Exiting...");
					bufferedReader.close();
					streamReader.close();
				}
			}
		}
	}

}
