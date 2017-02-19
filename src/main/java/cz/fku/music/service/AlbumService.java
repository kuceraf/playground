package cz.fku.music.service;

import cz.fku.music.model.Album;
import cz.fku.music.model.Track;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by Filip on 18.12.2016.
 */
public class AlbumService {

    private AlbumService() {}

    public static Set<String> findLongTracksOld(List<Album> albums) {
        Set<String> trackNames = new HashSet<>();
        for(Album album : albums) {
            for (Track track : album.getTracks()) {
                if (track.getLength() > 60) {
                    String name = track.getName();
                    trackNames.add(name);
                }
            }
        }
        return trackNames;
    }

    public static Set<String> findLongTracksNew(List<Album> albums) {
        return albums.stream()
                .flatMap(album -> album.getTracks().stream())
                .filter(track -> track.getLength() > 60)
                .map(Track::getName)
                .collect(Collectors.toSet());
    }
}
