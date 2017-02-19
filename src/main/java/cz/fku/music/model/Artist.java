package cz.fku.music.model;

import com.sun.istack.internal.NotNull;

import java.util.Collection;

/**
 * Created by Filip on 11.12.2016.
 *
 * An individual or group who creates music
 */
public class Artist implements Comparable<Artist> {
    //The name of the artist (e.g., “The Beatles”)
    private String name;
    //A set of other artists who comprise this group (e.g., “John Lennon”); this field might be empty
    private Collection<String> member;
    //The primary location of origin of the group (e.g., “Liverpool”).
    private String origin;


    public Artist() {

    }

    public Artist(String name) {
        this.name = name;
    }


    public boolean isFrom(@NotNull String location) {
            return location.equals(origin);
    }

    @Override
    public int compareTo(Artist artist) {
        return this.name.compareTo(artist.getName());
    }

    //  *** Getters and Setters ***
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<String> getMember() {
        return member;
    }

    public void setMember(Collection<String> member) {
        this.member = member;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
