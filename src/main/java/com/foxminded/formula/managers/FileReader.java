package com.foxminded.formula.managers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.foxminded.formula.models.Racer;

public class FileReader {

	private File abbreviations;
	private File start;
	private File end;

	public void setAbbreviations(URL url) {
		File file = new File(url.getFile());
		this.abbreviations = file;
	}

	public void setStartLog(URL url) {
		this.start = new File(url.getFile());
	}

	public void setEndLog(URL url) {
		this.end = new File(url.getFile());
	}

	public List<Racer> getRacersFromFile() {
		checkFile(abbreviations);
		List<Racer> result = new LinkedList<Racer>();
		try (Stream<String> dataFromFile = Files.lines(Paths.get(abbreviations.getAbsolutePath()))) {
			result = dataFromFile.map(line -> line.split("_"))
					.map(racer -> new Racer(racer[0], racer[1], racer[2], 0))
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Map<String, Long> getStartTime() {
		checkFile(start);
		return getTimeFromFile(start);
	}

	public Map<String, Long> getEndTime() {
		checkFile(end);
		return getTimeFromFile(end);
	}

	private Map<String, Long> getTimeFromFile(File file) {
		checkFile(file);
		List<String> dataFromFile;
		Map<String, Long> result = new HashMap<String, Long>();
		try {
			dataFromFile = Files.readAllLines(Paths.get(file.getAbsolutePath()));
			result = dataFromFile.stream().collect(Collectors.toMap(key -> key.substring(0, 3), value -> {
				String date = value.substring(3);
				LocalDateTime localDateTime = LocalDateTime.parse(date,
						DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS"));
				return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
			}));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	private void checkFile(File file) {
		checkForExistance(file);
		checkForEmptyFile(file);
		checkForNotBeingNull(file);
	}

	private void checkForNotBeingNull(File file) {
		if (file == null) {
			throw new IllegalArgumentException("File cannot be null");
		}
	}

	private void checkForEmptyFile(File file) {
		if (file.length() == 0) {
			throw new IllegalArgumentException("File is empty");
		}
	}

	private void checkForExistance(File file) {
		if (!file.exists()) {
			throw new IllegalArgumentException("File does not exist");
		}
	}

}
