package cz.fku.java8.parallelism;

import java.util.stream.Stream;

/**
 * Created by Filip on 01.05.2017.
 */
public class WordCounterEx {
    private final int counter;
    private final boolean lastSpace;

    public WordCounterEx(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    //1. LAMBDA 
    public int countWordsWithLambda(Stream<Character> stream) {
        WordCounterEx wordCounter = stream.reduce(new WordCounterEx(0, true),
                (WordCounterEx wc,Character ch) -> {
                    if (Character.isWhitespace(ch)) {
                        return new WordCounterEx(wc.counter, true);
                    } else {
                        return wc.lastSpace ?
                                //new words begin only if the whitespace is followed by char
                                new WordCounterEx(wc.counter + 1, false) :
                                new WordCounterEx(wc.counter, false);
                    }

                },
                WordCounterEx::combine);
        return wordCounter.counter;
    }
    
    //2. REFERENCE TO AN INSTANCE METHOD AT GIVEN INSTANCE
    public int countWordsWithInstance(Stream<Character> stream) {
        WordCounterEx wordCounter = stream.reduce(new WordCounterEx(0, true),
                this::accumulate,
                WordCounterEx::combine);
        return wordCounter.counter;
    }

    public WordCounterEx accumulate(WordCounterEx wc,Character c) {
        if(Character.isWhitespace(c)) {
            return wc.lastSpace ?
                    wc :
                    new WordCounterEx(wc.counter, true);
        } else {
            return wc.lastSpace ?
                    new WordCounterEx(wc.counter+1, false) :
                    wc;
        }
    }

    //3. REFERENCE TO AN INSTANCE METHOD
    public int countWords(Stream<Character> stream) {
        WordCounterEx wordCounter = stream.reduce(new WordCounterEx(0, true),
                WordCounterEx::accumulate,
                WordCounterEx::combine);
        return wordCounter.counter;
    }

    public WordCounterEx accumulate(Character c) {
        if(Character.isWhitespace(c)) {
            return lastSpace ?
                    this :
                    new WordCounterEx(counter, true);
        } else {
            return lastSpace ?
                    new WordCounterEx(counter+1, false) :
                    this;
        }
    }

    public WordCounterEx combine(WordCounterEx wordCounter) {
        return new WordCounterEx(counter + wordCounter.counter
                ,wordCounter.lastSpace /*does not matter*/);
    }
}
