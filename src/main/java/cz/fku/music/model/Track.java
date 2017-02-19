package cz.fku.music.model;

/**
 * Created by Filip on 11.12.2016.
 *
 *  A single piece of music
 */
public class Track {
    private String name;
    private Integer length;

    //GETTERS
    public String getName() {
        return name;
    }
    public Integer getLength() {
        return length;
    }

    //BUILDER
    public static final class TrackBuilder {
        private String name;
        private Integer length;

        private TrackBuilder() {
        }

        public static TrackBuilder aTrack() {
            return new TrackBuilder();
        }

        public TrackBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public TrackBuilder withLength(Integer length) {
            this.length = length;
            return this;
        }

        public Track build() {
            Track track = new Track();
            track.length = this.length;
            track.name = this.name;
            return track;
        }
    }
}

