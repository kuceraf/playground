package cz.fku.java8;

import cz.fku.java8.function.stream.StreamExamples;
import cz.fku.music.model.Album;
import cz.fku.music.model.Track;
import cz.fku.music.service.AlbumService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by Filip on 18.12.2016.
 */
public class CodeRefactoringExamples {

    final private org.slf4j.Logger logger = LoggerFactory.getLogger(StreamExamples.class);

    private List<Album> albums;
    @Before
    public void prepareData() {
        albums = Arrays.asList(
                Album.AlbumBuilder.anAlbum()
                        .withName("Tets Album 1")
                        .withTracks(Arrays.asList(
                                Track.TrackBuilder.aTrack()
                                        .withName("A1-Track 1")
                                        .withLength(80)
                                        .build(),
                                Track.TrackBuilder.aTrack()
                                        .withName("A1-Track 2")
                                        .withLength(30)
                                        .build(),
                                Track.TrackBuilder.aTrack()
                                        .withName("A1-Track 3")
                                        .withLength(70)
                                        .build(),
                                Track.TrackBuilder.aTrack()
                                        .withName("A1-Track 4")
                                        .withLength(20)
                                        .build())).build(),
                Album.AlbumBuilder.anAlbum()
                        .withName("Tets Album 2")
                        .withTracks(Arrays.asList(
                                Track.TrackBuilder.aTrack()
                                        .withName("A2-Track 1")
                                        .withLength(55)
                                        .build(),
                                Track.TrackBuilder.aTrack()
                                        .withName("A2-Track 2")
                                        .withLength(100)
                                        .build())).build()
        );
    }

    @Test
    public void findLongTracksTest() {
        logger.info("OLD: ");
        Set<String> result = AlbumService.findLongTracksOld(albums);
        result.forEach(logger::info);
        Assert.assertEquals(result.size(),3);

        logger.info("NEW: ");
        Set<String> result2 = AlbumService.findLongTracksNew(albums);
        result2.forEach(logger::info);
        Assert.assertEquals(result2.size(),3);
    }
}
