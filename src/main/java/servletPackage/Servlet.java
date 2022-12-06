// Cameron Holbrook
// CSCI 3381
// 11/15/22

package servletPackage;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.TryCatchFinally;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	// Instantiating the show collection.
	private ShowCollection allShows;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet() {
		super();
		allShows = new ShowCollection();
		// Need to call this method to load the data; it's not part of the ShowCollection constructor.
		allShows.readFile();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// String variable = request.getParameter("parameter from HTML/JSP");
		
		// Now, these two string variables can be used in this servlet.
		// In the if statements, which are basically action listeners for functionality, the 
		// segment "request.setAttribute("keyString", value) sets that value-parameter pair to the
		// HTTP response, when dynamically generating HTML.
		
		// =============================== LOGIN PAGE BUTTON ===============================
		
		// index.html, Enter button -> home.jsp or index.html.
		if(request.getParameter("indexButton") != null) {
			
			// Get the username from the index.html.
			String username = request.getParameter("usernameTextbox");
			request.setAttribute("usernameTextbox", username);
			
			// Get the username from the index.html.
			String password = request.getParameter("password");
			request.setAttribute("password", password);
			
			// How this code works:
			// If the username and password are correct,
			// request is sent to home.jsp
			// If not, goes back to index.html.
			if (username.equals("md") && password.equals("pw")) {
				RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
				rd.forward(request, response);					
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.forward(request, response);
			}
			
		}
		
		// =============================== / LOGIN PAGE BUTTON ===============================
		
		
		
		// =============================== HOME PAGE BUTTONS ===============================
		
		// home.jsp, User Mode Button -> user_mode.jsp
		if(request.getParameter("userModeButton") != null) {
			
			request.setAttribute("recommendation", "No recommendations available.");
			request.setAttribute("movieText", allShows.toString());
			
			RequestDispatcher rd = request.getRequestDispatcher("/user_mode.jsp");
			rd.forward(request, response);
		}
		
		// home.jsp, Developer Mode Button -> developer_mode.jsp
		if(request.getParameter("developerModeButton") != null) {
			
			request.setAttribute("movieText", allShows.toString());
			
			String value = "<select name=\"movies\">";
			Iterator<ShowInWeek> iter = allShows.getIterator();

			while (iter.hasNext()) {
				ShowInWeek show = iter.next();
				value += "<option value=\"" + show.getShowTitles() + "\">" + show.getShowTitles() + "</option>";
			}
			
			value += "</select>\r\n";
			
			request.setAttribute("dropDownOptions", value);
			
			RequestDispatcher rd = request.getRequestDispatcher("/developer_mode.jsp");
			rd.forward(request, response);
		}
		
		// home.jsp, Logout Button -> index.html
		if(request.getParameter("logoutButton") != null) {
			RequestDispatcher rd = request.getRequestDispatcher("/index.html");
			rd.forward(request, response);
		}

		// =============================== / HOME PAGE BUTTONS ===============================
		
		
		
		// =============================== USER PAGE BUTTONS ===============================
		
		if(request.getParameter("userToDeveloperModeButton") != null) {
			
			String value = "<select name=\"movies\">";
			Iterator<ShowInWeek> iter = allShows.getIterator();

			while (iter.hasNext()) {
				ShowInWeek show = iter.next();
				value += "<option value=\"" + show.getShowTitles() + "\">" + show.getShowTitles() + "</option>";
			}
			
			value += "</select>\r\n";
			
			request.setAttribute("dropDownOptions", value);
			request.setAttribute("movieText", allShows.toString());
			
			RequestDispatcher rd = request.getRequestDispatcher("/developer_mode.jsp");
			rd.forward(request, response);
			
		}
		
		if(request.getParameter("userToLogout") != null) {
			
			RequestDispatcher rd = request.getRequestDispatcher("/index.html");
			rd.forward(request, response);
			
		}
		
		if(request.getParameter("recommendButton") != null) {
			
			// Get the parameter value for the two radio buttons; it's english or nonEnglish
			String languageChoice = request.getParameter("language");
			String formatChoice = request.getParameter("filmOrTV");
			
			request.setAttribute("recommendation", allShows.suggestPredictive(languageChoice, formatChoice));
			request.setAttribute("movieText", allShows.toString());
			
			RequestDispatcher rd = request.getRequestDispatcher("/user_mode.jsp");
			rd.forward(request, response);
			
		}
		
		// =============================== / USER PAGE BUTTONS ===============================
		
		
		
		// =============================== DEVELOPER PAGE BUTTONS ===============================
		
		if(request.getParameter("developerToUserModeButton") != null) {
			
			request.setAttribute("recommendation", "No recommendations available.");
			request.setAttribute("movieText", allShows.toString());
			
			RequestDispatcher rd = request.getRequestDispatcher("/user_mode.jsp");
			rd.forward(request, response);
			
		}
		
		if(request.getParameter("developerToLogout") != null) {
			
			RequestDispatcher rd = request.getRequestDispatcher("/index.html");
			rd.forward(request, response);
			
		}

		if(request.getParameter("addMovieButton") != null) {
			
			try {
				
				// Get the user input from the text boxes in developer_mode.jsp.
				String textBoxWeek = request.getParameter("textBoxWeek");
				String textBoxCategory = request.getParameter("textBoxCategory");
				int textBoxWeeklyRank = Integer.parseInt(request.getParameter("textBoxWeeklyRank"));
				String textBoxShowTitles = request.getParameter("textBoxShowTitles");
				String textBoxSeasonTitles = request.getParameter("textBoxSeasonTitles");
				int textBoxHoursViewed = Integer.parseInt(request.getParameter("textBoxHoursViewed"));
				int textBoxCumulative = Integer.parseInt(request.getParameter("textBoxCumulative"));
				
				// Create a show that will be added to the ShowCollection class.
				ShowInWeek tempShow = new ShowInWeek(textBoxWeek, textBoxCategory, textBoxWeeklyRank, 
						textBoxShowTitles, textBoxSeasonTitles, textBoxHoursViewed, textBoxCumulative);
				
				allShows.addNewShow(tempShow);
				
				// This needs to come after the movie adding so that the viewport is updated after a movie is added.
				request.setAttribute("movieText", allShows.toString());
				
				// Update the dropdown box.
				String value = "<select name=\"movies\">";
				Iterator<ShowInWeek> iter = allShows.getIterator();
				
				while (iter.hasNext()) {
					ShowInWeek show = iter.next();
					value += "<option value=\"" + show.getShowTitles() + "\">" + show.getShowTitles() + "</option>";
				}
				
				value += "</select>\r\n";
				
				request.setAttribute("dropDownOptions", value);
				
				RequestDispatcher rd = request.getRequestDispatcher("developer_mode.jsp");
				rd.forward(request, response);
				
			// If the user enters a blank textbox.
			} catch (Exception e) {

				// Update the dropdown box.
				String value = "<select name=\"movies\">";
				Iterator<ShowInWeek> iter = allShows.getIterator();
				
				while (iter.hasNext()) {
					ShowInWeek show = iter.next();
					value += "<option value=\"" + show.getShowTitles() + "\">" + show.getShowTitles() + "</option>";
				}
				
				value += "</select>\r\n";
				request.setAttribute("dropDownOptions", value);
				request.setAttribute("movieText", allShows.toString());

				RequestDispatcher rd = request.getRequestDispatcher("developer_mode.jsp");
				rd.forward(request, response);
				
			}
				
		}
		
		if(request.getParameter("editButton") != null) {
			
			String editChoice = request.getParameter("purgeUnpurgeButton");			// Purge/unpurge radio button
			String movieToEdit = request.getParameter("movies");					// Dropdown selection
			
			// First, check if the movie ACTUALLY needs to be purge/unpurged.
			boolean needsToBePurged = false;
			
			if (movieToEdit.contains(" [PURGED]")) {
				needsToBePurged = true;
			}
			
			// First expression is the user input. The second is input validation.
			if (editChoice.equals("purge") && !needsToBePurged){
				
				allShows.purgeShow(movieToEdit);
				
			} else if (editChoice.equals("unpurge") && needsToBePurged) {
				
				movieToEdit = movieToEdit.substring(0, movieToEdit.length() - 9);
				allShows.unpurgeShow(movieToEdit);
			}
			
			// Update the dropdown box.
			String value = "<select name=\"movies\">";
			Iterator<ShowInWeek> iter = allShows.getIterator();
			
			while (iter.hasNext()) {
				ShowInWeek show = iter.next();
				value += "<option value=\"" + show.getShowTitles() + "\">" + show.getShowTitles() + "</option>";
			}
			
			value += "</select>\r\n";
			
			// Update the dropdown box and the viewport.
			request.setAttribute("dropDownOptions", value);
			request.setAttribute("movieText", allShows.toString());
			
			RequestDispatcher rd = request.getRequestDispatcher("developer_mode.jsp");
			rd.forward(request, response);
			
		}
		
		
		// =============================== / DEVELOPER PAGE BUTTONS ===============================

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
