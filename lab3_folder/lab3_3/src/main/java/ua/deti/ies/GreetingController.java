package ua.deti.ies;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private String quote = "";
    private final AtomicLong counter = new AtomicLong();
    private HashMap<String, String> quotes = new HashMap<String, String>(){{
        put("Blazing Saddles", "What's a dazzling urbanite like you doing in a rustic setting like this? ");
        put("Star Wars: Episode III – Revenge of the Sith", "I am the senate!");
        put("The Godfather", "I'm gonna make him an offer he can't refuse.");
        put("The Wizard of Oz", "Toto, I've a feeling we're not in Kansas anymore.");
        put("Taxi Driver", "You talkin' to me?");
    }};

@GetMapping("/greeting")
public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
	return new Greeting(counter.incrementAndGet(), String.format(template, name));
}

@GetMapping("api/quote")
public Greeting quote() {
	Random rand = new Random();
	int randomnumber = rand.nextInt(quotes.size());
	String randomQuote = quotes.get(quotes.keySet().toArray()[randomnumber]);
	return new Greeting(counter.incrementAndGet(), randomQuote);
}

@GetMapping("api/shows")
public Greeting shows() {
	return new Greeting(counter.incrementAndGet(), String.format(quotes.keySet().toString()));
}

@GetMapping("api/quotes")
public Greeting quotes(@RequestParam(value = "id", defaultValue = "1") String id) {

	int intID = Integer.parseInt(id);
	String quote = quotes.get(quotes.keySet().toArray()[intID]);
	return new Greeting(counter.incrementAndGet(), quote);
}
}
