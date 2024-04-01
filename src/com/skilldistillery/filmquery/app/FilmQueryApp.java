package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	private Scanner input = new Scanner(System.in);
	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();

		app.launch();
	}

	private void launch() {

		menu(input);

		input.close();
	}

	public void menu(Scanner input) {
		System.out.println("Welcome to FilmQueryApp");
		boolean running = true;

		while (running) {
			input.reset();
			System.out.println("Please select from the options below.");
			System.out.println("\n1. Search for a film by its title ID? ");
			System.out.println("2. Search for a film by a keyword ? ");
			System.out.println("3. Exit App");
			String choice = input.nextLine();
			switch (choice) {
			case "1":
				System.out.println("Enter the movie Id number:\n");
				int numId = input.nextInt();
				Film film = db.findFilmById(numId);
				System.out.println(film);
				break;
			case "2":
				printFilmsByKeyWord();
				break;
			case "3":
				System.out.println("\nThank you for using FilmQueryAPP");
				System.out.println("\nNow go watch some movies, Goodbye");
				running = false;
				break;
			default:
				System.out.println("Not a valid option\n\n");
				break;
			}

			// System.out.println("Not a valid option\n\n");

		}
	}

	public void printFilmsByKeyWord() {
		System.out.println("Enter the movie search keyword:\n");
		String userIn = input.nextLine();
		List<Film> films = db.findFilmsByKeyWord(userIn);
		// List<Actor> actorList = db.findActorsByFilmId(film.getId());
		for (Film film : films) {
			film.setFilmCast(db.findActorsByFilmId(film.getId()));
		}
		// if list.lenght.equals(0)
		if (films.size() == 0) {
			System.out.println("No results matching that keyword.");
		}
		// else
		else {
			System.out.println(films);
		}
	}
}
