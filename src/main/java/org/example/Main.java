package org.example;

public class Main {
    public static void main(String[] args) {
        ApiCalls apiCalls = new ApiCalls();

        System.out.println("Objective 1:\n");
        System.out.println(apiCalls.createBook().toString());

        System.out.println("Objective 2:\n");
        apiCalls.createBookWithSameType()
                .getJokes()
                .forEach(j -> System.out.println(j.getType() + "\n" + j));

        System.out.println("Objective 3:\n");
        apiCalls.giveRatings(apiCalls.createBook())
                .entrySet()
                .forEach((entry -> System.out.println(entry.getKey() + " " + entry.getValue())));

        System.out.println("Objective 4:\n");
        Book book = apiCalls.createBook();
        System.out.println(book);
        System.out.println(apiCalls.reviewBook(book));
    }
}