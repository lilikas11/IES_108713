package ua.deti.ies;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.deti.ies.Movie;
import ua.deti.ies.Quote;
import ua.deti.ies.NewQuote;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByTitle(String title);
}
