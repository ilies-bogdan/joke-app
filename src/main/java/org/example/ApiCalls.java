package org.example;

import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

public class ApiCalls {
    private static final String jokeUrl = "https://official-joke-api.appspot.com/jokes";
    private static final String gptUrl = "https://europe-west4-nomadic-pathway-385517.cloudfunctions.net/internship?question=";

    private static final String gptRateMessage =
            "Rate this joke using only a number from 1 to 5 (the answer should only show a digit):";
    private static final String gptReviewMessage =
            "Give a short review of this joke book containing 5 jokes: ";

    private RestTemplate restTemplate = new RestTemplate();

    private Joke getOne() {
        return restTemplate.getForObject(String.format("%s/%s", jokeUrl, "random"), Joke.class);
    }

    public Book createBook() {
        return new Book(
                Arrays.stream(restTemplate
                        .getForObject(String.format("%s/%s", jokeUrl, "ten"), Joke[].class))
                        .limit(5)
                        .collect(Collectors.toSet())
        );
    }

    public Book createBookWithSameType() {
        String type = getOne().getType();
        return new Book(
                Arrays.stream(restTemplate
                        .getForObject(String.format("%s/%s/%s", jokeUrl, type, "ten"), Joke[].class))
                        .limit(5)
                        .collect(Collectors.toSet())
        );
    }

    public Map<Joke, String> giveRatings(Book book) {
        var jokeRatings = new HashMap<Joke, String>();

        for (var joke : book.getJokes()) {
            String response = restTemplate
                    .getForObject(String.format("%s%s%s", gptUrl, gptRateMessage, joke.getSetup() + " " + joke.getPunchline()), String.class);
            jokeRatings.put(joke, response);
        }

        return jokeRatings;
    }

    public String reviewBook(Book book) {
        var urlLink = new StringBuilder(gptUrl);
        urlLink.append(gptReviewMessage);

        for (var joke : book.getJokes()) {
            urlLink.append(joke.getSetup())
                    .append(" ")
                    .append(joke.getPunchline())
                    .append(" ");
        }

        return restTemplate.getForObject(urlLink.toString(), String.class);
    }
}
