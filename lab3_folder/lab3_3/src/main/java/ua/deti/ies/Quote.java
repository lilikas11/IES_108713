package ua.deti.ies;


import jakarta.persistence.*;

@Entity
@Table(name = "quotes")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quote_id")
    private Long quoteId;

    @Column(name = "quotetext", nullable = false)
    private String quotetext;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    // Constructors, getters, and setters as before

    @Override
    public String toString() {
        return "Quote{quoteId=" + quoteId + ", quotetext=" + quotetext + ", movie=" + movie.getTitle() + "}";
    }
}
