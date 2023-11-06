package ua.deti.ies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable(name = "id") int id) {
        return movieService.getMovieById(id);
    }

    @PostMapping("/add")
    public Movie addMovie(@RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }

    @PostMapping("/addList")
    public List<Movie> addMovieList(@RequestBody List<Movie> movieList) {
        return movieService.saveAllMovies(movieList);
    }

    @PutMapping("/update/{id}")
    public Movie updateMovie(@PathVariable(name = "id") int id, @RequestBody Movie movie) {
        return movieService.updateMovie(id, movie);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteMovie(@PathVariable(name = "id") int id) {
        return movieService.deleteMovie(id);
    }
}
    
@RestController
@RequestMapping("/quotes")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @GetMapping
    public List<Quote> getAllQuotes() {
        return quoteService.getAllQuotes();
    }

    @GetMapping("/{id}")
    public Quote getQuoteById(@PathVariable(name = "id") int id) {
        return quoteService.getQuoteById(id);
    }

    @PostMapping("/add")
    public Quote addQuote(@RequestBody NewQuote newQuote) {
        return quoteService.saveQuote(newQuote);
    }

    @PostMapping("/addList")
    public List<Quote> addQuoteList(@RequestBody List<NewQuote> newQuoteList) {
        return quoteService.saveAllQuotes(newQuoteList);
    }

    @PutMapping("/update/{id}")
    public Quote updateQuote(@PathVariable(name = "id") int id, @RequestBody NewQuote newQuote) {
        return quoteService.updateQuote(id, newQuote);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteQuote(@PathVariable(name = "id") int id) {
        return quoteService.deleteQuote(id);
    }
}