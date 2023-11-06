package ua.deti.ies;
import jakarta.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long movieId;
    
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "year", nullable = false)
    private int year;

    public Long getMovieId() {
        return movieId;
    }

    public int getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Movie{movieId=" + movieId + ", title=" + title + ", year=" + year + "}";
    }
}
