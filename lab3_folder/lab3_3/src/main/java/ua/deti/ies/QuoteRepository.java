package ua.deti.ies;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.deti.ies.Movie;
import ua.deti.ies.Quote;
import ua.deti.ies.NewQuote;


public interface QuoteRepository extends JpaRepository<Quote, Long> {
    List<Quote> findByMovie(Movie m);
}
