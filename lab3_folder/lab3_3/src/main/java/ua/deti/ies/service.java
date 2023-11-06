package ua.deti.ies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import ua.deti.ies.Movie;
import ua.deti.ies.Quote;
import ua.deti.ies.NewQuote;
import ua.deti.ies.MovieRepository;
import ua.deti.ies.QuoteRepository;

@Service
public class service {
    // movies
    @Autowired
    private MovieRepository movierepo;

    public Movie saveMovie(Movie m) {
        return movierepo.save(m);
    }

    public List<Movie> saveAllMovies(List<Movie> ml) {
        return movierepo.saveAll(ml);
    }

    public List<Movie> getAllMovies() {
        return movierepo.findAll();
    }

    public Movie getMovieById(int id) {
        return movierepo.findById((long) id).orElse(null);
    }

    public Boolean deleteMovie(int id) {
        Movie m = movierepo.findById((long) id).orElse(null);
        if(m == null) return false;
        
        List<Quote> ql = quoterepo.findByMovie(m);
        for(Quote q : ql)
            quoterepo.deleteById(q.getQuoteId());

        movierepo.deleteById((long) id);
        return true;
    }

    public Movie updateMovie(int id, Movie m) {
        Movie movie = movierepo.findById((long) id).orElse(null);
        movie.setTitle(m.getTitle());
        movie.setYear(m.getYear());
        return movierepo.save(m);
    }

    // quotes
    @Autowired
    private QuoteRepository quoterepo;

    public Quote saveQuote(NewQuote nq) {
        return quoterepo.save(new Quote(nq.getQuotetext(), getMovieId((int) nq.getMovieId())));
    }

    public List<Quote> saveAllQuotes(List<NewQuote> nql) {
        List<Quote> addedQuotes = new ArrayList<>();
        for(NewQuote nq : nql) {
            Quote q = new Quote(nq.getQuotetext(), movierepo.findById(nq.getMovie_id()).orElse(null));
            quoterepo.save(q);
            addedQuotes.add(q);
        }
        return addedQuotes;
    }

    public List<Quote> getAllQuotes() {
        return quoterepo.findAll();
    }

    public Quote getQuoteById(int id) {
        return quoterepo.findById((long) id).orElse(null);
    }

    public Boolean deleteQuote(int id) {
        quoterepo.deleteById((long) id);
        return true;
    }

    public Quote updateQuote(int id, NewQuote nq) {
        Quote quote = quoterepo.findById((long) id).orElse(null);
        Movie movie = movierepo.findById((long) nq.getMovie_id()).orElse(null);
        quote.setMovieId(movie);
        quote.setQuotetext(nq.getQuotetext());
        return quoterepo.save(quote);
        // return quoterepo.save(quote);
    }
}
