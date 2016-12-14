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

    private  Stream<String> linesOfSentence;

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

        //Load file from resources
        ClassLoader classLoader = StreamExamples.class.getClassLoader();
        URL url = classLoader.getResource("sentences.txt");

        try {
            linesOfSentence = Files.lines(Paths.get(url.toURI()));
        } catch (URISyntaxException | IOException e) {
            logger.error(e.toString());
        }
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
        Stream<String> words = linesOfSentence
                        .flatMap(line -> Stream.of(line.split(" +")));
        words.forEach(System.out::println);
    }
}