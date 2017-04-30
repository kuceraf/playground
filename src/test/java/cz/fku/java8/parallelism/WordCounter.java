package cz.fku.java8.parallelism;

import java.util.stream.Stream;

/**
 * Created by Filip on 30.04.2017.
 */
public class WordCounter {
    //ITERATIVE WAY
    public int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
//                the counter is incremented when a new nonspace is met and the
//                last character encountered is a space
                if (lastSpace) counter++;
                lastSpace = false;
            }
        }
        return counter;
    }

    //FUNCTIONAL WAY
    private final int counter;
    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    public int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);
        return wordCounter.counter;
    }

    //here is defined the new state of newly created WordCounter immutable object
    private WordCounter accumulate(Character c) {
        if(Character.isWhitespace(c)) {
            return lastSpace ?
                    this :
                    new WordCounter(counter, true);
        } else {
            return lastSpace ?
                    new WordCounter(counter+1, false) :
                    this;
        }
    }

//    aggregate the partial results of two
//    WordCounters operating on two different subparts of the stream of Characters
    private WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(counter + wordCounter.counter
                ,wordCounter.lastSpace /*does not matter*/);
    }

}
