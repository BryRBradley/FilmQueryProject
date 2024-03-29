package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid";

	static {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Film> findFilmsByActorId(int actorId) {
		String USER = "student";
		String PASS = "student";

		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			String sql = "SELECT *  FROM film JOIN film_actor ON film.id = film_actor.film_id " + " WHERE actor_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				short releaseYear = rs.getShort("release_year");
				int languageId = rs.getInt("language_id");
				int rentalDuration = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double replacementCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String specialFeatures = rs.getString("special_features");
				Film film = new Film(filmId, title, description, releaseYear, languageId, rentalDuration, rate, length,
						replacementCost, rating, specialFeatures);
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	@Override
	public Film findFilmById(int filmId) {
		String USER = "student";
		String PASS = "student";
		Scanner sc = new Scanner(System.in);
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			film = null;
			String sql = "SELECT *  FROM film  WHERE film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet filmResult = stmt.executeQuery();
			if (filmResult.next()) {
				film = new Film();
				film.setTitle() = (filmResult.getString("title"));
				film.setDescription() = (filmResult.getString("description"));
				film.setReleaseYear() = (filmResult.getShort("release_year"));
				film.setLanguageId() = (filmResult.getInt("language_id"));
				film.setRentalDuration() = (filmResult.getInt("rental_duration"));
				film.setRentalRate() = (filmResult.getDouble("rental_rate"));
				film.setLength() = (filmResult.getInt("length"));
				film.setReplacmentCost() = (filmResult.getDouble("replacement_cost"));
				film.setRating() = (filmResult.getString("rating"));
				film.setSpecialFeatures() = (filmResult.getString("special_features"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (film);
	}

	@Override
	public Actor findActorById(int actorId) {
		String USER = "student";
		String PASS = "student";
		Actor actor = null;
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			actor = null;
			// ...
			String sql = "SELECT * FROM actor WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet actorResult = stmt.executeQuery();
			if (actorResult.next()) {
				actor = new Actor(); // Create the object
				// Here is our mapping of query columns to our object fields:
				actor.setId(actorResult.getInt("id"));
				actor.setFirstName(actorResult.getString("first_name"));
				actor.setLastName(actorResult.getString("last_name"));

				// if(actorResult.next){
				// String firstName = actorResult.getString.("first_name");
				// String lastName = actorResult.getString.("last_name");
				// actor = new Actor(actorId, firstName, lastName);
				// }

			}
			return actor;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actor;
	}

	@SuppressWarnings("unchecked")
	public List<Actor> findActorsByFilmId(int filmId) {
		String USER = "student";
		String PASS = "student";
		Actor actor = null;
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			// ...
			String sql = "SELECT * FROM actor WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet actorResult = stmt.executeQuery();
			if (actorResult.next()) {
				actor = new Actor(); // Create the object
				// Here is our mapping of query columns to our object fields:
				actor.setId(actorResult.getInt("id"));
				actor.setFirstName(actorResult.getString("first_name"));
				actor.setLastName(actorResult.getString("last_name"));

				// if(actorResult.next){
				// String firstName = actorResult.getString.("first_name");
				// String lastName = actorResult.getString.("last_name");
				// actor = new Actor(actorId, firstName, lastName);
				// }

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return (List<Actor>) actor;
	}
}