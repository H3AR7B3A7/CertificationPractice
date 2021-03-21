package be.dog.d.steven.OracleProgrammingComplete.chapter14.data;

import java.io.Serializable;

public class Review implements Comparable<Review>, Serializable {
    private final Rating rating;
    private final String comment;

    public Review(Rating rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

    public Rating getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "Review: rating=" + rating +
                ", comment=" + comment;
    }

    @Override
    public int compareTo(Review r) {
        return r.getRating().ordinal() - rating.ordinal();
    }
}