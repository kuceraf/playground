package cz.fku.lambda;

import cz.fku.music.Artist;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Created by Filip on 11.12.2016.
 *<br><br>
 * Stream = a sequence of elements from a source that supports aggregate operations
 * <ul>
 *     <li>Sequence of elements: A stream provides an interface to a sequenced set of values of a specific element type. However, streams don’t actually store elements; they are computed on demand.</li>
 *     <li>Source: Streams consume from a data-providing source such as collections, arrays, or I/O resources.</li>
 *     <li>Aggregate operations: Streams support SQL-like operations and common operations from functional programing languages, such as filter, map, reduce, find, match, sorted, and so on. </li>
 * </ul>
 * more on: http://www.oracle.com/technetwork/articles/java/ma14-java-se-8-streams-2177646.html
 */
public class StreamExamples {

    final private org.slf4j.Logger logger = LoggerFactory.getLogger(StreamExamples.class);

    private Collection<Artist> allArtists = new HashSet<>();

    @Before
    public void createAllArtists() {
        Artist a = new Artist("Bob Dylan");
        a.setOrigin("USA");
        allArtists.add(a);
        a = new Artist("Xavier Bumaxa");
        a.setOrigin("USA");
        allArtists.add(a);
        allArtists.add(new Artist("Jaromir Nohavica"));
        a = new Artist("John Lennon");
        //a.setOrigin("USA");
        allArtists.add(a);
    }

    private Stream<String> getNewLinesOfSentence() {
        Stream<String> linesOfSentence = Stream.of("");

        //Load file from resources
        ClassLoader classLoader = StreamExamples.class.getClassLoader();
        URL url = classLoader.getResource("sentences.txt");

        try {
            linesOfSentence = Files.lines(Paths.get(url.toURI()));
        } catch (URISyntaxException | IOException e) {
            logger.error(e.toString());
        }

        return linesOfSentence;
    }

// Pipelining: Many stream operations return a stream themselves.
// This allows operations to be chained to form a larger pipeline.
    @Test
    public void streamTest() {
        allArtists.stream()
                //returns Stream<T>
                .filter(
                        //lambda implementation of predicate functional interface
                        artist -> {return artist.isFrom("USA");
                        })
                //Artist implements Comparable (would be used if no comparator supplied)
                //returns Stream<T>
                .sorted(Comparator.comparing(Artist::getName).reversed())
                //intermediate operation
                //lambda implementation of function functional interface
                .map(artist -> (artist.getName()))
                //terminal operation
                //method reference representing implementation of consumer functional interface
                //Consumer<? super T> action, where
                //forEach calls accept method on Consumer interface for each element in stream
                .forEach(System.out::println);
    }

    @Test
    public void collectorTest() {
        List<String> collected = Stream.of("a", "b", "c")
                .collect(Collectors.toList());

        assertEquals(Arrays.asList("a", "b", "c"), collected);


        collected = Stream.of("a", "b", "hello")
                .map(string -> string.toUpperCase())
                .collect(Collectors.toList());

        assertEquals(Arrays.asList("A", "B", "HELLO"), collected);

    }

    @Test
    public void findAnyTest() {
        Optional<Artist> result = allArtists.stream()
                .filter(a -> a.getOrigin() == null)
                .findFirst();

        result.ifPresent(x -> System.out.println(x.getName()));
    }

    @Test
    public void flatMapTest() {
        //The mapper function passed to flatMap splits a line, using a simple regular expression, into an array of words, and then creates a stream of words from that array.
        Stream<String> words = getNewLinesOfSentence()
                        .flatMap(line -> Stream.of(line.split(" +")));
        words.forEach(System.out::println);
    }

    @Test
    public void maxTest(){
        //Finde longest sentence

        /** max() is specialized form of reduction operations
         *
         <U> U reduce(U identity,
             BiFunction<U, ? super T, U> accumulator,
             BinaryOperator<U> combiner);
         *
         * where:
         * identity = initial seed value for the reduction and a default result if there are no input elements
         * accumulator = function that takes a partial result and the next element, and produces a new partial result
         * combiner = function that combines two partial results to produce a new partial result - necessary only form parallel streams
         * (BinaryOperator is specialization of BiFunction, where BiFunction<T, T, T>)
         *
         * vs
         *
         * Mutable reduction
         *
         <R> R collect(Supplier<R> supplier,
            BiConsumer<R, ? super T> accumulator,
            BiConsumer<R, R> combiner);
         *
         *  where:
         *  supplier = function to construct new instances of the result container
         *  accumulator = function to incorporate an input element into a result container
         *  combiner = function to merge the contents of one result container into another
         *
         *  The result is accumulated into mutable container instead of copying into new result set as in "odinary" reduction
         *  **/

        //Implemented as anonymous inner class
        Optional<String> sentence1 = getNewLinesOfSentence().max(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length() == o2.length())
                    return 0;
                else if(o1.length() > o2.length())
                    return 1;
                else
                    return  -1;
            }
        });

        logger.info("sentence1: " + sentence1.get());

        //Implemented as lambda
        Optional<String> sentence2 = getNewLinesOfSentence().max(
                Comparator.comparing(s -> s.length())
        );

        sentence2.ifPresent(value -> logger.info("sentence2: " + value));

        //Implemented as method reference
        Optional<String> sentence3 = getNewLinesOfSentence().max(
                Comparator.comparing(String::length)
        );
        sentence3.ifPresent(logger::info);
    }
}
