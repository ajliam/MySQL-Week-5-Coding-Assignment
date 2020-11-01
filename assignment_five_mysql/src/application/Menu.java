package application;

import dao.MemberDAO;
import dao.MovieDAO;
import entity.Member;
import entity.Movie;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private MovieDAO movieDAO = new MovieDAO();
    private MemberDAO memberDAO = new MemberDAO();
    private Scanner scanner = new Scanner(System.in);
    private List<String> options = Arrays.asList
            ("Display Movies", "Display a Movie", "Create a Movie", "Delete a Movie", "Create a Member", "Delete Member");

    public void start(){
        String selection = "";

         do {
            printMenu();
             selection = scanner.nextLine();

             try {
                 if (selection.equals("1")) {
                     displayMovies();
                 } else if (selection.equals("2")){
                     displayMovie();
                 } else if (selection.equals("3")){
                     createMovie();
                 } else if (selection.equals("4")){
                     deleteMovie();
                 } else if (selection.equals("5")){
                     createMember();
                 } else if (selection.equals("6")){
                     deleteMember();
                 }
             }  catch (SQLException e){
                 e.printStackTrace();
             }


             System.out.println("Press enter to continue.....");
             scanner.nextLine();
         } while (!selection.equals("-1"));
    }
    private void printMenu(){
        System.out.println("Select an Option:\n------------------------------------");
        for(int i = 0; i < options.size(); i++) System.out.println(i + 1 + ") " + options.get(i));
    }

    private void displayMovies() throws SQLException {
        List<Movie> movies = Collections.unmodifiableList(movieDAO.getMovie());
        for (Movie movie: movies) System.out.println(movie.getMovieId() + ": " + movie.getName());
    }

    private void displayMovie() throws SQLException {
        System.out.print("Enter movie id: ");
        int id = Integer.parseInt(scanner.nextLine());
        Movie movie = movieDAO.getMovieById(id);
        System.out.println(movie.getMovieId() + ": " + movie.getName());
        for (Member member : movie.getMembers())
            System.out.println("\tMemberId: " + member.getMemberId() + " - Name: " + member.getFirstName() + " " + member.getLastName());
    }

    private void createMovie() throws SQLException {
        System.out.println("Enter new movie name:");
        String movieName = scanner.nextLine();
        movieDAO.createNewMovie(movieName);
    }

    private void deleteMovie() throws SQLException {
        System.out.print("Enter movie id to delete");
        int id = Integer.parseInt(scanner.nextLine());
        movieDAO.deleteMovieById(id);
    }

    private void createMember() throws SQLException {
        System.out.print("Enter first name of new member:");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name of new member");
        String lastName = scanner.nextLine();
        System.out.println("Enter team id of new member");
        int teamId = Integer.parseInt(scanner.nextLine());
        memberDAO.createNewMember(firstName, lastName, teamId);
    }

    private void deleteMember() throws SQLException {
        System.out.println("Enter member id to delete:");
        int id = Integer.parseInt(scanner.nextLine());
        memberDAO.deleteMemberById(id);

    }
}
