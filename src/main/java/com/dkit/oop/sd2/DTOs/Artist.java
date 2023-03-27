package com.dkit.oop.sd2.DTOs;

import java.util.Objects;

/**                                                     OOP Feb 2022
 *  Data Transfer Object (DTO)
 *
 * This POJO (Plain Old Java Object) is called the Data Transfer Object (DTO).
 * (or, alternatively, the Model Object or the Value Object).
 * It is used to transfer data between the DAO and the Business Objects.
 * Here, it represents a row of data from the User database table.
 * The DAO creates and populates a User object (DTO) with data retrieved from
 * the resultSet and passes the User object to the Business Layer.
 *
 * Collections of DTOs( e.g. ArrayList<User> ) may also be passed
 * between the Data Access Layer (DAOs) and the Business Layer objects.
 */

public class Artist implements Comparable<Artist> {
    private int id;
    private String name;
    private String country;
    private String genre;
    private int active_since;
    private String biography;
    private double rating;

    public Artist(int id, String name, String country, String genre, int active_since, String biography, double rating) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.genre = genre;
        this.active_since = active_since;
        this.biography = biography;
        this.rating = rating;
    }
    public Artist(String name, String country, String genre, int active_since, String biography, double rating) {
        this.id = 0;
        this.name = name;
        this.country = country;
        this.genre = genre;
        this.active_since = active_since;
        this.biography = biography;
        this.rating = rating;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getActive_since() {
        return active_since;
    }

    public void setActive_since(int active_since) {
        this.active_since = active_since;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", genre='" + genre + '\'' +
                ", active_since=" + active_since +
                ", biography='" + biography + '\'' +
                ", rating=" + rating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist)) return false;
        Artist artist = (Artist) o;
        return getId() == artist.getId() && getActive_since() == artist.getActive_since() && Double.compare(artist.getRating(), getRating()) == 0 && getName().equals(artist.getName()) && getCountry().equals(artist.getCountry()) && getGenre().equals(artist.getGenre()) && Objects.equals(getBiography(), artist.getBiography());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCountry(), getGenre(), getActive_since(), getBiography(), getRating());
    }

    @Override
    public int compareTo(Artist o) {
        return this.getName().compareToIgnoreCase(o.getName());
    }
}


