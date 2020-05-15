package com.weather.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.json.*;

import com.weather.beans.*;

@WebServlet(name = "Servlets", urlPatterns = { "/Servlets" })
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Boolean error = false;
	private String token = "b62df3fe008f1f99e7f64505321259d01135ac7d0b795618db702ba8dc43d8e7";
	private String insee;
	HashMap<Integer, String[]> condition;
	private String date;
	private String hour;

	public Servlet() {
		super();
		this.setHourDate();
		Integer[] indexes = { 0, 1, 2, 3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 15, 16, 20, 21, 22, 30, 31, 32, 40, 41, 42,
				43, 44, 45, 46, 47, 48, 60, 61, 62, 63, 64, 65, 66, 67, 68, 70, 71, 72, 73, 74, 75, 76, 77, 78, 100,
				101, 102, 103, 104, 105, 106, 107, 108, 120, 121, 122, 123, 124, 125, 126, 127, 128, 130, 131, 132, 133,
				134, 135, 136, 137, 138, 140, 141, 142, 210, 211, 212, 220, 221, 222, 230, 231, 232, 235 };
		String[] text = { "Ensoleillé", "Peu nuageux", "Ciel voilé", "Nuageux", "Très nuageux", "Couvert", "Brouillard",
				"Brouillard givrant", "Pluie faible", "Pluie modérée", "Pluie forte", "Pluie faible verglaçante",
				"Pluie modérée verglaçante", "Pluie forte verglaçante", "Bruine", "Neige faible", "Neige modérée",
				"Neige forte", "Pluie et neige mêlées faibles", "Pluie et neige mêlées modérées",
				"Pluie et neige mêlées fortes", "Averses de pluie locales et faibles", "Averses de pluie locales",
				"Averses locales et fortes", "Averses de pluie faibles", "Averses de pluie", "Averses de pluie fortes",
				"Averses de pluie faibles et fréquentes", "Averses de pluie fréquentes",
				"Averses de pluie fortes et fréquentes", "Averses de neige localisées et faibles",
				"Averses de neige localisées", "Averses de neige localisées et fortes", "Averses de neige faibles",
				"Averses de neige", "Averses de neige fortes", "Averses de neige faibles et fréquentes",
				"Averses de neige fréquentes", "Averses de neige fortes et fréquentes",
				"Averses de pluie et neige mêlées localisées et faibles", "Averses de pluie et neige mêlées localisées",
				"Averses de pluie et neige mêlées localisées et fortes", "Averses de pluie et neige mêlées faibles",
				"Averses de pluie et neige mêlées", "Averses de pluie et neige mêlées fortes",
				"Averses de pluie et neige mêlées faibles et nombreuses", "Averses de pluie et neige mêlées fréquentes",
				"Averses de pluie et neige mêlées fortes et fréquentes", "Orages faibles et locaux", "Orages locaux",
				"Orages fort et locaux", "Orages faibles", "Orages", "Orages forts", "Orages faibles et fréquents",
				"Orages fréquents", "Orages forts et fréquents", "Orages faibles et locaux de neige ou grésil",
				"Orages locaux de neige ou grésil", "Orages locaux de neige ou grésil",
				"Orages faibles de neige ou grésil", "Orages de neige ou grésil", "Orages de neige ou grésil",
				"Orages faibles et fréquents de neige ou grésil", "Orages fréquents de neige ou grésil",
				"Orages fréquents de neige ou grésil", "Orages faibles et locaux de pluie et neige mêlées ou grésil",
				"Orages locaux de pluie et neige mêlées ou grésil",
				"Orages fort et locaux de pluie et neige mêlées ou grésil",
				"Orages faibles de pluie et neige mêlées ou grésil", "Orages de pluie et neige mêlées ou grésil",
				"Orages forts de pluie et neige mêlées ou grésil",
				"Orages faibles et fréquents de pluie et neige mêlées ou grésil",
				"Orages fréquents de pluie et neige mêlées ou grésil",
				"Orages forts et fréquents de pluie et neige mêlées ou grésil", "Pluies orageuses",
				"Pluie et neige mêlées à caractère orageux", "Neige à caractère orageux", "Pluie faible intermittente",
				"Pluie modérée intermittente", "Pluie forte intermittente", "Neige faible intermittente",
				"Neige modérée intermittente", "Neige forte intermittente", "Pluie et neige mêlées",
				"Pluie et neige mêlées", "Pluie et neige mêlées", "Averses de grêle" };
		String[] icons = { "Sunny", "Cloudy_Sunny", "Cloudy_Sunny", "Cloudy", "Cloudy", "Cloudy", "Foggy", "Foggy",
				"Drip", "Raining", "Raining", "Drip", "Raining", "Raining", "Drip", "Snowing", "Snow_Clody",
				"Snow_Clody", "Snowing", "Snow_Clody", "Snow_Clody", "Raining", "Raining", "Raining", "Raining",
				"Raining", "Raining", "Raining", "Raining", "Raining", "Snowing", "Snow_Clody", "Snow_Clody", "Snowing",
				"Snow_Clody", "Snow_Clody", "Snow_Clody", "Snow_Clody", "Snow_Clody", "Snowing", "Snow_Clody",
				"Snow_Clody", "Snowing", "Snow_Clody", "Snow_Clody", "Snow_Clody", "Snow_Clody", "Snow_Clody",
				"Lightning_Cloudy", "Lightning_Cloudy", "Lightning_Cloudy", "Lightning_Cloudy", "Lightning_Cloudy",
				"Lightning_Cloudy", "Lightning_Cloudy", "Lightning_Cloudy", "Lightning_Cloudy", "Lightning_Cloudy",
				"Lightning_Cloudy", "Lightning_Cloudy", "Lightning_Cloudy", "Lightning_Cloudy", "Lightning_Cloudy",
				"Lightning_Cloudy", "Lightning_Cloudy", "Lightning_Cloudy", "Lightning_Cloudy", "Lightning_Cloudy",
				"Lightning_Cloudy", "Lightning_Cloudy", "Lightning_Cloudy", "Lightning_Cloudy", "Lightning_Cloudy",
				"Lightning_Cloudy", "Lightning_Cloudy", "Lightning_Cloudy", "Lightning_Cloudy", "Lightning_Cloudy",
				"Drip", "Raining", "Raining", "Snowing", "Snow_Clody", "Snow_Clody", "Snow_Clody", "Snow_Clody",
				"Snow_Clody", "Snowing" };
		this.condition = new HashMap<Integer, String[]>();
		for (Integer i = 0; i < indexes.length; i++) {
			String[] temp = { text[i], icons[i] };
			this.condition.put(indexes[i], temp);
		}
	}

	private void setHourDate() {
		DateTime dt = new DateTime();
		StringBuilder dateSb = new StringBuilder();
		if (String.valueOf(dt.getDayOfMonth()).length() == 1) {
			dateSb.append('0');
		}
		dateSb.append(dt.getDayOfMonth());
		dateSb.append('/');
		if (String.valueOf(dt.getMonthOfYear()).length() == 1) {
			dateSb.append('0');
		}
		dateSb.append(dt.getMonthOfYear());
		dateSb.append('/');
		dateSb.append(dt.getYear());
		this.date = dateSb.toString();

		StringBuilder hourSb = new StringBuilder();
		if (String.valueOf(dt.getHourOfDay()).length() == 1) {
			hourSb.append('0');
		}
		hourSb.append(dt.getHourOfDay());
		hourSb.append(':');
		if (String.valueOf(dt.getMinuteOfHour()).length() == 1) {
			hourSb.append('0');
		}
		hourSb.append(dt.getMinuteOfHour());
		this.hour = hourSb.toString();
	}

	private boolean setData(City city) throws IOException {
		if (this.setCurrentData(city) && this.setNextDaysData(city)) {
			return true;
		}
		return false;
	}

	private String formatCity(String name) {
		char[] array = name.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (Integer i = 0; i < array.length; i++) {
			if (array[i] == ' ') {
				sb.append("%20");
			} else {
				sb.append(array[i]);
			}
		}
		return sb.toString();
	}

	private JSONObject getCurrentData(City city) throws IOException {
		URL searchUrl = new URL("https://api.meteo-concept.com/api/location/cities?token=" + this.token + "&search="
				+ this.formatCity(city.getName()));
		HttpURLConnection searchConnection = (HttpURLConnection) searchUrl.openConnection();
		searchConnection.setRequestMethod("GET");
		searchConnection.setRequestProperty("Content-Type", "application/json");
		BufferedReader in = new BufferedReader(new InputStreamReader(searchConnection.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		JSONObject json = new JSONObject(content.toString());
		if (json.getJSONArray("cities").length() == 0) {
			return null;
		}
		this.insee = json.getJSONArray("cities").getJSONObject(0).getString("insee");
		in.close();
		searchConnection.disconnect();

		URL resultUrl = new URL("https://api.meteo-concept.com/api/forecast/nextHours?token=" + this.token + "&insee="
				+ this.insee + "&hourly=true");
		HttpURLConnection resultConnection = (HttpURLConnection) resultUrl.openConnection();
		resultConnection.setRequestMethod("GET");
		resultConnection.setRequestProperty("Content-Type", "application/json");
		BufferedReader on = new BufferedReader(new InputStreamReader(resultConnection.getInputStream()));
		String reInputLine;
		StringBuffer reContent = new StringBuffer();
		while ((reInputLine = on.readLine()) != null) {
			reContent.append(reInputLine);
		}
		JSONObject reJson = new JSONObject(reContent.toString());
		on.close();
		resultConnection.disconnect();

		return reJson;
	}

	private JSONObject getNextDaysData(City city) throws IOException {
		URL resultUrl = new URL(
				"https://api.meteo-concept.com/api/forecast/daily?token=" + this.token + "&insee=" + this.insee);
		HttpURLConnection resultConnection = (HttpURLConnection) resultUrl.openConnection();
		resultConnection.setRequestMethod("GET");
		resultConnection.setRequestProperty("Content-Type", "application/json");
		BufferedReader on = new BufferedReader(new InputStreamReader(resultConnection.getInputStream()));
		String reInputLine;
		StringBuffer reContent = new StringBuffer();
		while ((reInputLine = on.readLine()) != null) {
			reContent.append(reInputLine);
		}
		JSONObject reJson = new JSONObject(reContent.toString());
		on.close();
		resultConnection.disconnect();
		return reJson;
	}

	private boolean setCurrentData(City city) throws IOException {
		if (city.getName().length() > 0 && this.getCurrentData(city) != null) {
			JSONObject json = this.getCurrentData(city);

			city.setTemperature(json.getJSONArray("forecast").getJSONObject(0).getInt("temp2m"));
			city.setConditions(this.condition.get(json.getJSONArray("forecast").getJSONObject(0).getInt("weather"))[0]);
			city.setIcon("https://cdn4.iconfinder.com/data/icons/weatherful/72/"
					+ this.condition.get(json.getJSONArray("forecast").getJSONObject(0).getInt("weather"))[1]
					+ "-512.png");
			city.setProbaRain(json.getJSONArray("forecast").getJSONObject(0).getInt("probarain"));
			return true;
		}
		return false;
	}

	private boolean setNextDaysData(City city) throws IOException {
		if (city.getName().length() > 0) {
			JSONObject json = this.getNextDaysData(city);

			@SuppressWarnings("unchecked")
			HashMap<String, String>[] hm = new HashMap[8];
			for (Integer i = 0; i < 8; i++) {
				hm[i] = new HashMap<String, String>();
				hm[i].put("min", String.valueOf(json.getJSONArray("forecast").getJSONObject(i).getInt("tmin")));
				hm[i].put("max", String.valueOf(json.getJSONArray("forecast").getJSONObject(i).getInt("tmax")));
				hm[i].put("icon", "https://cdn4.iconfinder.com/data/icons/weatherful/72/"
						+ this.condition.get(json.getJSONArray("forecast").getJSONObject(i).getInt("weather"))[1]
						+ "-512.png");
				hm[i].put("date",
						this.dateFormater(json.getJSONArray("forecast").getJSONObject(i).getString("datetime")));
			}
			city.setNextDays(hm);
			return true;
		}
		return false;
	}

	private String dateFormater(String date) {
		char[] array = date.toCharArray();
		StringBuilder sb = new StringBuilder();
		sb.append(array[8]);
		sb.append(array[9]);
		sb.append('/');
		sb.append(array[5]);
		sb.append(array[6]);
		return sb.toString();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		City city = new City();
		if (request.getParameter("city") != null) {
			city.setName(request.getParameter("city").toUpperCase());
		}
		if (!this.setData(city)) {
			this.error = true;
			city.setName("Une erreur est survenue, veuillez réessayer");
		} else {
			this.error = false;
		}
		request.setAttribute("city", city);
		request.setAttribute("error", this.error);
		request.setAttribute("date", this.date);
		request.setAttribute("hour", this.hour);
		this.getServletContext().getRequestDispatcher("/WEB-INF/TestPage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
