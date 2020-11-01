package entity;

import java.util.List;

public class Movie {

    private int movieId;
    private String name;
    private List<Member> members;

    public Movie(int movieId, String name, List<Member> members){
        this.movieId = movieId;
        this.name = name;
        this.members = members;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
