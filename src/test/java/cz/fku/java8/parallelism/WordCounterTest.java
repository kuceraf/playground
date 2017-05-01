package cz.fku.java8.parallelism;

import cz.fku.java8.function.stream.ReductionTest;
import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.TearDown;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Filip on 30.04.2017.
 */
public class WordCounterTest {
    final private org.slf4j.Logger logger = LoggerFactory.getLogger(WordCounterTest.class);

    final String SENTENCE ="Veta  ve ktere  se spocitaji slova a bude jich 10  ";
    @Test
    public void countWordsIteratively() {
        WordCounter wordCounter = new WordCounter(0,false);

        int wordsIteratively = wordCounter.countWordsIteratively(SENTENCE);
        Assert.assertEquals(10
                ,wordsIteratively);
    }

    @Test
    public void countWordsFunctionally() {
        WordCounter wordCounter = new WordCounter(0, true);

        Stream<Character> stream = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);

        Assert.assertEquals(10
                ,wordCounter.countWords(stream));
    }

    @Test
    public void countWordsWithLambda() {
        WordCounter wordCounter = new WordCounter(0, true);

        Stream<Character> stream = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);

        Assert.assertEquals(10
                ,wordCounter.countWordsWithLambda(stream));
    }

    @Test
    public void countWordsWithInstance() {
        WordCounter wordCounter = new WordCounter(0, true);

        Stream<Character> stream = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);

        Assert.assertEquals(10
                ,wordCounter.countWordsWithInstance(stream));
    }
}
