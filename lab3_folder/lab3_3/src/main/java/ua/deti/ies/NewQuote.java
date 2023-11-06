package ua.deti.ies;

public class NewQuote {
    private String quotetext;
    private Long movieId;

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public void setQuotetext(String quotetext) {
        this.quotetext = quotetext;
    }

    public Long getMovieId() {
        return movieId;
    }

    public String getQuotetext() {
        return quotetext;
    }
}
