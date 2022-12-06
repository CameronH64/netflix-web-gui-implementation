// Cameron Holbrook
// CSCI 3381

package servletPackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.Iterator;

public class ShowCollection {

	private ArrayList<ShowInWeek> showStorage;
	private String readFileName;
	private String writeFileName;
	
	// Added this for better coding practices.
	private Random random;
	
	// Arguments: None
	// Return: None
	public ShowCollection() {
		showStorage = new ArrayList<ShowInWeek>();
		readFileName = "./completeDataSet.txt";
		writeFileName = "./outputData.txt";
		
		// Added this for better coding practices.
		random = new Random();
		
	}
	
	// Arguments: ShowInWeek
	// Return: None
	public void addNewShow(ShowInWeek newShowInWeek) {
		showStorage.add(newShowInWeek);
	}
	
	// Arguments: String
	// Return: None
	public void purgeShow(String showToPurge) {
		// Append " [PURGED]" to end of each show title.

		for (ShowInWeek showInWeek : showStorage) {
			if (showInWeek.getShowTitles().equals(showToPurge)) {
				showInWeek.setShowTitles(showInWeek.getShowTitles() + " [PURGED]");		
			}
		}
	}
	
	// Arguments: String
	// Return: None
	public void unpurgeShow(String showToUnpurge) {
		
		// Remove " [PURGED]" from the end of each show title (that is chosen to be unpurged);
		for (ShowInWeek showInWeek : showStorage) {
			if (showInWeek.getShowTitles().equals(showToUnpurge + " [PURGED]")) {
				showInWeek.setShowTitles(showInWeek.getShowTitles().replace(" [PURGED]", ""));
			}
		}
	}

	// Arguments: None
	// Return: String
	public String suggestRandomShow() {

		// Generate a number the size of the ShowCollection instance.
		Random random = new Random();

		// Then, get the length of the ShowCollection.
		// With this length, get a random number with a maximum of the ShowCollection length.
		int length_random_number = random.nextInt(showStorage.size());
		String suggestedShow = showStorage.get(length_random_number).getShowTitles();		// Use the random number to access that index.

		return suggestedShow;

	}

	// Arguments: ShowInWeek
	// Return: String
	public String suggestPredictive(ShowInWeek show) {

		// Design to suggest a show given a single show:
		// Must be the same category (which to say, TV or Films and language).

		// Create an ArrayList object of only same category as given show.
		// This will be used as a pool to pull from later.
		ArrayList<ShowInWeek> categoryList = new ArrayList<ShowInWeek>();		
		int count = 0;
		String showCategory = show.getCategory();

		// Filter out category
		if (showCategory.contains("TV (English)")) {
			for (ShowInWeek showInWeek : showStorage) {
				if (showInWeek.getCategory().contains("TV (English)") && !showInWeek.getShowTitles().equals(show.getShowTitles())) {
					categoryList.add(showInWeek);
					count++;
				}
			}

		} else if (showCategory.contains("TV (Non-English)")) {
			for (ShowInWeek showInWeek : showStorage) {
				if (showInWeek.getCategory().contains("TV (Non-English)") && !showInWeek.getShowTitles().equals(show.getShowTitles())) {
					categoryList.add(showInWeek);
					count++;
				}
			}
		} else if (showCategory.contains("Films (English)")) {

			for (ShowInWeek showInWeek : showStorage) {
				if (showInWeek.getCategory().contains("Films (English)") && !showInWeek.getShowTitles().equals(show.getShowTitles())) {
					categoryList.add(showInWeek);
					count++;
				}
			}
		} else if (showCategory.contains("Films (Non-English)")) {

			for (ShowInWeek showInWeek : showStorage) {
				if (showInWeek.getCategory().contains("Films (Non-English)") && !showInWeek.getShowTitles().equals(show.getShowTitles())) {
					categoryList.add(showInWeek);
					count++;
				}
			}
		}

		Random random = new Random();

		// Generate a number the size of the ShowCollection instance.
		// With this length, generate a random number with that as its maximum.
		int length_random_number = random.nextInt(count);
		String suggestedShow = categoryList.get(length_random_number).getShowTitles();		// Use the random number to access that index.

		return suggestedShow;

	}

	// Arguments: ShowCollection
	// Return: ShowCollection
	public ShowCollection suggestPredictive(ShowCollection shows) {
		// Design to suggest a show given a collection of shows:
		// Must be greater than or equal to the average of the whole weeklyHoursViewed of collection shows.

		ShowCollection suggestionList = new ShowCollection();
		int hoursViewedAverage = 0;
		int max = 0;

		// Cycle through shows showStorage ArrayList data member; add up each weeklyHoursViewed.
		for (ShowInWeek showInWeek : shows.showStorage) {
			hoursViewedAverage += showInWeek.getWeeklyHoursViewed();
		}

		hoursViewedAverage /= shows.showStorage.size();
//		System.out.println("shows' showStorage Size: " + shows.showStorage.size());		// Personal debugging
//		System.out.println("Shows Average: " + hoursViewedAverage);		

		// Cycle through shows showStorage, returning first 5 shows with greater than average viewing time.
		// I just couldn't quite get this right, though.
		for (ShowInWeek showInWeek : showStorage) {
			if (showInWeek.getWeeklyHoursViewed() >= hoursViewedAverage && max < 5) {
				suggestionList.addNewShow(showInWeek);
				max++;
			}
		}
		return suggestionList;
	}

	// Arguments: String
	// Return: ArrayList<ShowInWeek>
	public ArrayList<ShowInWeek> getShows(String week) {

		// Create an ArrayList that holds shows of specified week.
		ArrayList<ShowInWeek> categoryList = new ArrayList<ShowInWeek>();		

		// Cycle through the whole data; get the ShowInWeek objects that have the specified week.
		for (ShowInWeek showInWeek : showStorage) {
			if (showInWeek.getWeek().equals(week)){
				categoryList.add(showInWeek);
			}
		}

		return categoryList;			// Need to return a ShowCollection (this is an ArrayList).
	}


	// ============================ TESTING ============================
	
	// Needed to create an iterator.
	public Iterator<ShowInWeek> getIterator () {
		return showStorage.iterator();
	}
	
	
	
	// Arguments: String, String
	// Return: String
	public String suggestPredictive(String language, String format) {

		// Design to suggest a show given a single show:
		// Must be the same category (which to say, TV or Films and language).

		// Create an ArrayList object of only same category as given show.
		// This will be used as a pool to pull from later.
		ArrayList<ShowInWeek> categoryList = new ArrayList<ShowInWeek>();		
		int count = 0;
		
		// Filter out category
		if ((format + " " + language).equals("TV (English)")) {
			for (ShowInWeek showInWeek : showStorage) {
				if (showInWeek.getCategory().equals("TV (English)")) {
					categoryList.add(showInWeek);
					count++;
				}
			}

		} else if ((format + " " + language).equals("TV (Non-English)")) {
			for (ShowInWeek showInWeek : showStorage) {
				if (showInWeek.getCategory().equals("TV (Non-English)")) {
					categoryList.add(showInWeek);
					count++;
				}
			}
		} else if ((format + " " + language).equals("Films (English)")) {

			for (ShowInWeek showInWeek : showStorage) {
				if (showInWeek.getCategory().equals("Films (English)")) {
					categoryList.add(showInWeek);
					count++;
				}
			}
		} else if ((format + " " + language).equals("Films (Non-English)")) {

			for (ShowInWeek showInWeek : showStorage) {
				if (showInWeek.getCategory().equals("Films (Non-English)")) {
					categoryList.add(showInWeek);
					count++;
				}
			}
		}
		
		int random_number = random.nextInt(count);
		String suggestedShow = categoryList.get(random_number).getShowTitles();		// Use the random number to access that index.
		
		return suggestedShow;

	}
	
	
	// ============================ / TESTING ============================
	

	// Arguments: None
	// Return: String
	public String toString() {

		String toReturn = "ShowCollection: [\n";

		for (ShowInWeek showInWeek : showStorage) {
			toReturn += showInWeek.toString();
		}

		toReturn += "]";
		return toReturn;
	}


	
	
	


	// =========================== File Reading and Writing ===========================

	// Arguments: None
	// Return: None
	public void readFile() {

		BufferedReader lineReader = null;
		
		try {
			FileReader fileRead = new FileReader(readFileName);				// Set the file to read from here.
			lineReader = new BufferedReader(fileRead);
			String line = null;

			while ((line = lineReader.readLine())!=null) {
				try {
					String week = line;					// Use line because it contains the week.
					String category = lineReader.readLine();
					String weeklyRank = lineReader.readLine();
					String showTitles = lineReader.readLine();
					String seasonTitle = lineReader.readLine();
					String weeklyHoursViewed = lineReader.readLine();
					String cumulativeWeeks = lineReader.readLine();

					showStorage.add(new ShowInWeek(week, category, Integer.parseInt(weeklyRank), showTitles, seasonTitle, 
							Integer.parseInt(weeklyHoursViewed), Integer.parseInt(cumulativeWeeks)));
							
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println("Error: Unknown person type.");
				}
			}
		} catch (Exception e1) {
			System.err.println("there was a problem with the file reader, try different read type.");

			try {
				lineReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(readFileName.substring(1))));
				String line = null;

				while ((line = lineReader.readLine())!=null) {
					try {
						String week = line;							// Use line because it contains the week.
						String category = lineReader.readLine();
						String weeklyRank = lineReader.readLine();
						String showTitles = lineReader.readLine();
						String seasonTitle = lineReader.readLine();
						String weeklyHoursViewed = lineReader.readLine();
						String cumulativeWeeks = lineReader.readLine();
						
						showStorage.add(new ShowInWeek(week, category, Integer.parseInt(weeklyRank), showTitles, seasonTitle, 
								Integer.parseInt(weeklyHoursViewed), Integer.parseInt(cumulativeWeeks)));

					} catch (Exception e2) {
						System.err.println("Error: Unknown person type.");
					}
				}
			} catch (Exception e2) {
				System.err.println("there was a problem with the file reader, try again.  either no such file or format error");

			} finally {
				if (lineReader != null)
					try {
						lineReader.close();
					} catch (IOException e2) {
						System.err.println("could not close BufferedReader");
					}
			}
		} finally {
			if (lineReader != null)
				try {
					lineReader.close();
				} catch (IOException e) {
					System.err.println("could not close BufferedReader");
				}
		}
	}

	// Arguments: None
	// Return: None
	public void writeFile () {
		// overloaded method: this calls doWrite with file used to read data
		// use this for saving data between runs
		doWrite(writeFileName);
	}

	// Arguments: None
	// Return: None
	public void writeFile(String writeFileName) {
		// overloaded method: this calls doWrite with different file name 
		// use this for testing write
		doWrite(writeFileName);
	}

	// Arguments: String
	// Return: None
	private void doWrite(String writeFileName) {
		// this method writes all of the data in the ShowCollection array to a file
		try	{
			FileWriter write = new FileWriter(writeFileName);
			BufferedWriter outputFile = new BufferedWriter(write);			

			Iterator<ShowInWeek> showStorageIterator = showStorage.iterator();

			for (int i = 0; i < showStorage.size(); i++) {
				try {
					if (showStorageIterator.hasNext()) {

						ShowInWeek dataPoint = showStorage.get(i);				// Convert to iterator

						// Write each private data value to the file.
						outputFile.write(dataPoint.getWeek() + "\n");
						outputFile.write(dataPoint.getCategory()+"\n");
						outputFile.write(dataPoint.getWeeklyRank()+"\n");
						outputFile.write(dataPoint.getShowTitles()+"\n");
						outputFile.write(dataPoint.getSeasonTitle() + "\n");
						outputFile.write(dataPoint.getWeeklyHoursViewed() + "\n");
						outputFile.write(dataPoint.getCumulativeWeeksTop10() + "\n");

					}

				} catch (Exception e) {
					System.err.println("error in array, instance type not found");
				}
			}
			outputFile.flush();
			outputFile.close();

		}
		catch (Exception e) {
			e.printStackTrace();			// Really nifty debugging code.
			System.err.println("Didn't save to " + writeFileName);
		}
	}
}