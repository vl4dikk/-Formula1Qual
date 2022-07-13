package com.foxminded.formula.managers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.foxminded.formula.formatters.TableFormatter;

public class ConsoleManager {
	
	private TableFormatter table = new TableFormatter();
	private RacersResolver resolver = new RacersResolver();
	private FileReader reader = new FileReader();
	private ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	
	public void run() throws IOException {
		try(InputStreamReader streamReader = new InputStreamReader(System.in);
		BufferedReader bufferedReader = new BufferedReader(streamReader);) {
		try {

			reader.setAbbreviations(classLoader.getResource("abbreviations.txt"));
			reader.setEndLog(classLoader.getResource("end.log"));
			reader.setStartLog(classLoader.getResource("start.log"));
			System.out.println("Formatting result table:\n");
			System.out.println(table.formatTable(resolver.createListOfRacers(reader)));

		} catch (Exception ex) {
			System.out.println("Error has been occured: " + ex.getLocalizedMessage());
			ex.printStackTrace();
		} finally {
			System.out.println("\nDo you want to try again? (Y/n)");
			String confirm = bufferedReader.readLine();
			if (confirm.equalsIgnoreCase("Y")) {
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