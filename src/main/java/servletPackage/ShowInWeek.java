// Cameron Holbrook
// CSCI 3381

package servletPackage;

import java.util.Iterator;

public class ShowInWeek {

	// Private data members
	private String 		week;
	private String 		category;
	private int 		weeklyRank;
	private String 		showTitles;
	private String 		seasonTitle;
	private int 		weeklyHoursViewed;
	private int 		cumulativeWeeksTop10;

	// Arguments: None
	// Return: None
	public ShowInWeek() {

		week = "Undefined Week";
		category = "Undefined Category";
		weeklyRank = 0;
		showTitles = "Undefined Show Titles";
		seasonTitle = "Undefined Season Title";
		weeklyHoursViewed = 0;
		cumulativeWeeksTop10 = 0;
	}

	// Arguments: String, String, int, String, String, int, int
	// Return: None
	public ShowInWeek(String week, String category, int weeklyRank, String showTitles, String seasonTitle,
			int weeklyHoursViewed, int cumulativeWeeksTop10) {

		this.week = week;
		this.category = category;
		this.weeklyRank = weeklyRank;
		this.showTitles = showTitles;
		this.seasonTitle = seasonTitle;
		this.weeklyHoursViewed = weeklyHoursViewed;
		this.cumulativeWeeksTop10 = cumulativeWeeksTop10;
	}

	// Getters and Setters
	public String getWeek() { return week; }
	public String getCategory() { return category; }
	public int getWeeklyRank() { return weeklyRank; }
	public String getShowTitles() {	return showTitles; }
	public String getSeasonTitle() { return seasonTitle; }
	public int getWeeklyHoursViewed() {	return weeklyHoursViewed; }
	public int getCumulativeWeeksTop10() { return cumulativeWeeksTop10; }
	
	public void setWeek(String week) { this.week = week; }
	public void setCategory(String category) { this.category = category; }
	public void setWeeklyRank(int weeklyRank) {	this.weeklyRank = weeklyRank; }
	public void setSeasonTitle(String seasonTitle) { this.seasonTitle = seasonTitle; }
	public void setShowTitles(String showTitles) { this.showTitles = showTitles; }
	public void setWeeklyHoursViewed(int weeklyHoursViewed) { this.weeklyHoursViewed = weeklyHoursViewed; }
	public void setCumulativeWeeksTop10(int cumulativeWeeksTop10) {	this.cumulativeWeeksTop10 = cumulativeWeeksTop10; }

	// Arguments: None
	// Return: String
	public String toString() {
		return "ShowInWeek [week=" + week + ", category=" + category + ", weeklyRank=" + weeklyRank + ", showTitles="
				+ showTitles + ", seasonTitle=" + seasonTitle + ", weeklyHoursViewed=" + weeklyHoursViewed
				+ ", cumulativeWeeksTop10=" + cumulativeWeeksTop10 + "]\n";
	}

	// Arguments: ShowInWeek
	// Return: boolean
	public boolean equals(ShowInWeek show) {
		return ((getWeek()+getShowTitles()).equals(show.getWeek()+show.getShowTitles()));
	}

}
