package cz.fku.java8.parallelism;

import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

/**
 * Created by Filip on 30.04.2017.
 */
public class WordCounter {
    final private org.slf4j.Logger logger = LoggerFactory.getLogger(WordCounter.class);


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

    public int countWordsWithLambda(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                (WordCounter wc,Character ch) -> {
                    logger.info(String.valueOf(wc.getCounter()));
                    logger.info(ch.toString());

                    if (Character.isWhitespace(ch)) {
                        return new WordCounter(wc.getCounter(), true);
                    } else {
                        return wc.isLastSpace() ?
                                //new words begin only if the whitespace is followed by char
                                new WordCounter(wc.getCounter() + 1, false) :
                                new WordCounter(wc.getCounter(), false);
                    }

                },
                WordCounter::combine);
        return wordCounter.getCounter();
    }

    public int countWordsWithInstance(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                this::accumulate,
                WordCounter::combine);
        return wordCounter.counter;
    }

    public WordCounter accumulate(WordCounter wc,Character c) {
        if(Character.isWhitespace(c)) {
            return wc.lastSpace ?
                    wc :
                    new WordCounter(wc.counter, true);
        } else {
            return wc.lastSpace ?
                    new WordCounter(wc.counter+1, false) :
                    wc;
        }
    }

    public int countWords(Stream<Character> stream) {
//        While reducing the stream, you’ll have to carry a state consisting of two variables: an
//        int counting the number of words found so far and a boolean to remember if the lastencountered
//        Character was a space or not.
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                //HOW CAN THIS WORK? here must come BiFunction - R apply(T t, U u);
                //http://stackoverflow.com/questions/43717600/method-reference-does-not-fulfil-the-functional-interface-contract-but-it-compil
                WordCounter::accumulate,
                WordCounter::combine);
        return wordCounter.counter;
    }

    //here is defined the new state of newly created WordCounter immutable object
    public WordCounter accumulate(Character c) {
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
    public WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(counter + wordCounter.counter
                ,wordCounter.lastSpace /*does not matter*/);
    }


    public boolean isLastSpace() {
        return lastSpace;
    }

    public int getCounter() {
        return counter;
    }
}
