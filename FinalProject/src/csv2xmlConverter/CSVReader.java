package csv2xmlConverter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader {
	private static List<List<String>> records = new ArrayList<>();
	private static List<String> headers = new ArrayList<>();

	public static List<List<String>> readCsvFile(String csvFilename) {
		BufferedReader br = null;
		String line; // create an empty line

		try {
			br = new BufferedReader(new FileReader(csvFilename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// go line by line
		try {
			while ((line = br.readLine()) != null) {

				// we break each line with comma into small values
				String[] values = line.split(",");

				// add all the array of values from each line into the file array
				records.add(Arrays.asList(values)); // array of values
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// call this member method to set the headers
		setHeaders();

		return records;
	}

	private static void setHeaders() {
		// Get the first line of the CSV file and consider them as heading
		// for this task we always consider that the first line of file is always
		// headings.
		headers = records.get(0);

		// loop each heading one by one and replace spaces with dash
		for (int i = 0; i < headers.size(); i++) {

			String value = headers.get(i)
					// As XML tags(nodes) shouldn't contain spaces we replace spaces with dash (-)
					.replace(' ', '-')
					// remove any special characters that are unnecessary
					.replaceAll("[^a-zA-Z0-9-]", "");

			// if XML tags(nodes) are blank
			if (value.isEmpty()) {
				value = "header-" + i;
			}

			// replace the old value with spaces with the new value with dashes
			headers.set(i, value);

		}
	}

	public static List<String> getHeaders() {
		return headers;
	}
}
