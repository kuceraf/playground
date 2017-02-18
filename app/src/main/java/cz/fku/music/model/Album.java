package cz.fku.music.model;

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

//    GETTERS
    public String getName() {
        return name;
    }
    public List<Track> getTracks() {
        return tracks;
    }
    public List<Artist> getMusicians() {
        return musicians;
    }

    //BUILDER
    public static final class AlbumBuilder {
        //  The name of the album (e.g., “Revolver”)
            private String name;
        //    A list of tracks
            private List<Track> tracks;
        //    A list of artists who helped create the music on this album
            private List<Artist> musicians;

        private AlbumBuilder() {
        }

        public static AlbumBuilder anAlbum() {
            return new AlbumBuilder();
        }

        public AlbumBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public AlbumBuilder withTracks(List<Track> tracks) {
            this.tracks = tracks;
            return this;
        }

        public AlbumBuilder withMusicians(List<Artist> musicians) {
            this.musicians = musicians;
            return this;
        }

        public Album build() {
            Album album = new Album();
            album.tracks = this.tracks;
            album.name = this.name;
            album.musicians = this.musicians;
            return album;
        }
    }
}
