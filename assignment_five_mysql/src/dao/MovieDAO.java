package dao;

import entity.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    private Connection connection;
    private MemberDAO memberDAO;
    private final String GET_MOVIES_QUERY = "SELECT * FROM movies";
    private final String GET_MOVIE_BY_ID_QUERY = "SELECT * FROM movies WHERE id = ?";
    private final String CREATE_NEW_MOVIE_QUERY = "INSERT INTO movies(name) VALUES(?)";
    private final String DELETE_MOVIE_BY_ID_QUERY = "DELETE FROM movies WHERE id = ?";

    public MovieDAO(){
        connection = DBConnection.getConnection();
        memberDAO = new MemberDAO();
    }

    public List<Movie> getMovie() throws SQLException {
        ResultSet rs = connection.prepareStatement(GET_MOVIES_QUERY).executeQuery();
        List<Movie> movies = new ArrayList<Movie>();

        while(rs.next()) {
            movies.add(populateMovie(rs.getInt(1), rs.getString(2)));
        }

        return movies;
    }

    public Movie getMovieById(int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(GET_MOVIE_BY_ID_QUERY);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return populateMovie(rs.getInt(1), rs.getString(2));
    }

    public void createNewMovie(String movieName) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(CREATE_NEW_MOVIE_QUERY);
        ps.setString(1, movieName);
        ps.executeUpdate();
    }

    public void deleteMovieById(int id) throws SQLException {
        memberDAO.deleteMembersByTeamId(id);
        PreparedStatement ps = connection.prepareStatement(DELETE_MOVIE_BY_ID_QUERY);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    private Movie populateMovie(int id, String name) throws SQLException {
        return new Movie(id, name, memberDAO.getMembersByMovieId(id));
    }



}
