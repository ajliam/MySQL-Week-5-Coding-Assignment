package dao;

import entity.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    private Connection connection;
    private final String GET_MEMBERS_BY_MOVIE_ID_QUERY = "SELECT * FROM members WHERE movie_id = ?";
    private final String DELETE_MEMBERS_BY_MOVIE_ID_QUERY = "DELETE FROM members WHERE team_id = ?";
    private final String CREATE_NEW_MEMBER_QUERY = "INSERT INTO members(first_name, last_name, team_id) VALUES(?,?,?)";
    private final String DELETE_MEMBER_BY_ID_QUERY = "DELETE FROM members WHERE id = ?";


    public MemberDAO(){
        connection = DBConnection.getConnection();
    }
    public List<Member> getMembersByMovieId(int movieId) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(GET_MEMBERS_BY_MOVIE_ID_QUERY);
        ps.setInt(1, movieId);
        ResultSet rs = ps.executeQuery();
        List<Member> members = new ArrayList<Member>();

        while (rs.next()) {
            members.add(new Member(rs.getInt(1), rs.getString(2), rs.getString(3)));
        }
        return members;
    }

    public void createNewMember(String firstName, String lastName, int movieId) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(CREATE_NEW_MEMBER_QUERY);
        ps.setString(1, firstName);
        ps.setString(2, lastName);
        ps.setInt(3, movieId);
        ps.executeUpdate();
    }

    public void deleteMembersByTeamId(int movieId) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(DELETE_MEMBERS_BY_MOVIE_ID_QUERY);
        ps.setInt(1, movieId);
        ps.executeUpdate();

    }

    public void deleteMemberById(int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(DELETE_MEMBER_BY_ID_QUERY);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}
