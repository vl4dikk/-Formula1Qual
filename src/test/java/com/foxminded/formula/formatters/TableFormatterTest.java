package com.foxminded.formula.formatters;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.foxminded.formula.models.Racer;

class TableFormatterTest {
	
	private TableFormatter formatter = new TableFormatter();
	private List<Racer> racers = new LinkedList<Racer>();

	
	@Test
	void testDividing_ShouldResultOfDividing_WhenDividentIsBiggerThanDivisor() {
		Racer racer1 = new Racer("SVF","Sebastian Vettel" , "Ferrari", 10000L);
		Racer racer2 = new Racer("LHM", "Lewis Hamilton", "Mercedes", 20000L);
		racers.add(racer1);
		racers.add(racer2);
		String expected = "1. Sebastian Vettel   | Ferrari                     | 00:10.000\n"
				        + "2. Lewis Hamilton     | Mercedes                    | 00:20.000";
		assertEquals(expected, formatter.formatTable(racers));
	}

}
