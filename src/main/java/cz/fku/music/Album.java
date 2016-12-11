package cz.fku.music;

import java.util.List;

/**
 * Created by Filip on 11.12.2016.
 *
 * A single release of music, comprising several tracks
 */
public class Album {
//  The name of the album (e.g., “Revolver”)
    private String name;
//    A list of tracks
    private List<Track> tracks;
//    A list of artists who helped create the music on this album
    private List<Artist> musicians;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Artist> getMusicians() {
        return musicians;
    }

    public void setMusicians(List<Artist> musicians) {
        this.musicians = musicians;
    }
}
