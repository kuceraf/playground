package cz.fku.java8;

import cz.fku.music.model.Album;
import cz.fku.music.model.Track;
import cz.fku.other.Calculator;
import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Filip on 27.11.2016.
 */
public class LambdaExamples {
    final private org.slf4j.Logger logger = LoggerFactory.getLogger(LambdaExamples.class);

    //implementation of my functional interface
    @Test
    public void testCalculator() {
        Calculator addition = (a, b) -> (a + b);
        logger.info("RESULT: " + String.valueOf(addition.calculate(1, 1)));
    }

    //implementation of predefined interface from java.util.function
    @Test
    public void testFunction() {
        //parametr type of lambda can be infered from generic type of function interface (Integer may be omitted)
        //return type is infered from generic type of function interface (by default is returned the result of lambda expression)
        Function<Integer, Double> convert = (Integer miles) -> (miles * 1.6);
        logger.info("RESULT: " + String.valueOf(convert.apply(3)));
    }

    //implementation of predefined interface from java.util.function
    @Test
    public void testBiFunction() {
        BiFunction<Integer, Integer, Integer> area = (width, height) -> (width * height);
        logger.info("RESULT: " + area.apply(4, 8));
        area.apply(5, 8);
    }

    //???
    @Test
    public void testAfterFunction() {
        Function<Integer, Double> convert = (Integer miles) -> (miles * 1.6);
        logger.info("RESULT: " + String.valueOf(convert.apply(5)));
    }

    @Test
    public void testConsumer() {
        Function<Integer, String> spacer = (count) -> {
            StringBuilder sb = new StringBuilder(count);
            for (int i = 0; i < count; i++) {
                sb.append(" ");
            }
            return sb.toString();
        };

        int lineLength = 60; // characters
        Consumer<String> printCentered =
                (input) -> {
                    int length = input.length();
                    String spaces = spacer.apply(
                            (lineLength - length) / 2);
                    System.out.println(spaces + input);
                };

        printCentered.accept("A lambda expression a day");
        printCentered.accept("makes you");
        printCentered.accept("look smarter");

    }


    @Test
    public void testEcho() {
        Function<Integer, Integer> echo = (x) -> {
            return 2;
        };
        Integer result = echo.apply(4);
        logger.info("RESULT: " + result);
    }

    @Test
    public void testIndentityFunction() {
        Assert.assertEquals("aa", identityFunction().apply("aa"));
        Assert.assertEquals("aa", identityFunction2.apply("aa"));
    }

    private static Function<String, String> identityFunction2 = vale -> vale;

    private static <V> Function<V, V> identityFunction() {
        return value -> value;
    }

    @Test
    @Benchmark
    public void testCollectors() {
        List<Track> tracks = new ArrayList<>();
        tracks.add(
                Track.TrackBuilder.aTrack()
                        .withName("Track 1")
                        .withLength(3)
                        .build()
        );

        tracks.add(
                Track.TrackBuilder.aTrack()
                        .withName("Track 2")
                        .withLength(4)
                        .build()
        );

        tracks.add(
                Track.TrackBuilder.aTrack()
                        .withName("Track 3")
                        .withLength(3)
                        .build()
        );


        Album album = Album.AlbumBuilder.anAlbum()
                .withTracks(tracks)
                .withName("album")
                .build();

    //lambda - create
        Map<Integer, List<Track>> tracksByLength = album.getTracks().stream()
                .collect(Collectors.groupingBy(Track::getLength));

        Assert.assertEquals(tracksByLength.get(3).size(), 2);
    }
}
