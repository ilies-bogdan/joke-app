package org.example;

import java.util.Set;

public class Book {
    private Set<Joke> jokes;

    public Book() {}

    public Book(Set<Joke> jokes) {
        this.jokes = jokes;
    }

    public Set<Joke> getJokes() {
        return jokes;
    }

    public void setJokes(Set<Joke> jokes) {
        this.jokes = jokes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Joke joke : jokes) {
            sb.append(joke.toString());
        }

        return sb.toString();
    }
}
