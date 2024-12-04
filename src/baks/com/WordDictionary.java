package baks.com;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class WordDictionary implements Dictionary {
    private final List<String> words;

        public WordDictionary() {
        this.words = new ArrayList<>(List.of(
            "java", "program", "server", "network",
            "computer", "algorithm", "database", "interface", "object",
            "method", "variable", "function", "class", "inheritance",
            "polymorphism", "encapsulation", "abstraction", "framework", "library",
            "debugging", "compiler", "runtime", "syntax", "semantics"
        ));
    }
    @Override
    public String randomWord() {
        Random random = new Random();
        int id = random.nextInt(words.size());
        return words.get(id);
    }
}