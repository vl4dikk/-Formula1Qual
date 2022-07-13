package com.foxminded.formula.managers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.foxminded.formula.models.Racer;

class FileReaderTest {

	private FileReader reader = new FileReader();

	@AfterEach
	void afterEach() {
		reader = new FileReader();
	}

	@Test
	void testCheckingEmptyFile_ShouldThrowException_WhenFileIsEmpty() {
		reader.setAbbreviations("abbreviationEmpty.txt");
		assertThrows(IllegalArgumentException.class, () -> reader.getRacersFromFile());
	}

	@Test
	void parseRacersFromFile_ShouldReturnListOfRacers_WhenGivenFileWithRacers() {
		reader.setAbbreviations("abbreviationsTest.txt");
		List<Racer> actual = reader.getRacersFromFile();
		List<Racer> expected = Arrays.asList(
				new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER"),
				new Racer("SVF", "Sebastian Vettel", "FERRARI"));
		assertEquals(actual, expected);
	}

	@Test
	void parseStartLogFromFile_ShouldReturnMapOfStartLog_WhenGivenFileWithStartLog() {
		reader.setStartLog("startTest.log");
		Map<String, Long> actual = reader.getStartTime();
		Map<String, Long> expected = new HashMap<String, Long>();
		expected.put("SVF", 1527152578917L);
		expected.put("DRR", 1527153252054L);
		assertEquals(actual, expected);
	}

	@Test
	void parseEndtLogFromFile_ShouldReturnMapOfEndLog_WhenGivenFileWithEndLog() {
		reader.setEndLog("endTest.log");
		Map<String, Long> actual = reader.getEndTime();
		Map<String, Long> expected = new HashMap<String, Long>();
		expected.put("SVF", 1527152643332L);
		expected.put("DRR", 1527153324067L);
		assertEquals(actual, expected);
	}

}
