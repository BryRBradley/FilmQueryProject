package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();
		app.launch();
	}

	// private void test()
	// Film film = db.findFilmById(275);
	// System.out.println(film);
//	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);
		menu(input);
		input.close();
	}

	private void startUserInterface(Scanner input) {

	}

	public void menu(Scanner input) {
		System.out.println("Welcome to FilmQueryApp");
		boolean running = true;

		while (running) {
			System.out.println("Please select from the options bellow.");
			System.out.println("\n1. Search for a film by its title ID? ");
			System.out.println("2. Search for a film by a keyword ? ");
			System.out.println("3. Exit App");
			String choice = input.next();
			input.next();
			switch (choice) {
			case "1":
				int numId = input.nextInt();
				System.out.println("Enter the movie Id number:\n");
				Film film = db.findFilmById(numId);
				break;
			case "2":
				String userIn = input.nextLine();
				System.out.println("Enter the movie search keyword:\n");
				List<Film> film2 = db.findFilmByKeyWord(userIn);
				break;
			case "3":
				System.out.println("\nThank you for using FilmQueryAPP");
				System.out.println("\nNow go watch some movies, Goodbye");
				running = false;
				input.close();
				break;
			default:
				System.out.println("Not a valid option\n\n");
			}
		}
	}

}
