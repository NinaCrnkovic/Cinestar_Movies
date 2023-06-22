/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 *
 * @author Nina
 */
public final class Movie {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private int id;
    private String title;
    private String originalTitle;
    private String description;
    private int duration;
    private int year;
    private String poster;
    private String rating;
    private String link;
    private String guid;
    private String reservation;
    private LocalDateTime displayDate;
    private String performances;
    private int sort;
    private String trailer;

    public Movie(String title, String originalTitle, String description, int duration, int year, String poster, String rating, String link, String guid, String reservation, LocalDateTime displayDate, String performances, int sort, String trailer) {
        this.title = title;
        this.originalTitle = originalTitle;
        this.description = description;
        this.duration = duration;
        this.year = year;
        this.poster = poster;
        this.rating = rating;
        this.link = link;
        this.guid = guid;
        this.reservation = reservation;
        this.displayDate = displayDate;
        this.performances = performances;
        this.sort = sort;
        this.trailer = trailer;
    }

    public Movie(int id, String title, String originalTitle, String description, int duration, int year, String poster, String rating, String link, String guid, String reservation, LocalDateTime displayDate, String performances, int sort, String trailer) {
        this(title, originalTitle, description, duration, year, poster, rating, link, guid, reservation, displayDate, performances, sort, trailer);
        this.id = id;
    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getReservation() {
        return reservation;
    }

    public void setReservation(String reservation) {
        this.reservation = reservation;
    }

    public LocalDateTime getDisplayDate() {
        return displayDate;
    }

    public void setDisplayDate(LocalDateTime displayDate) {
        this.displayDate = displayDate;
    }

    public String getPerformances() {
        return performances;
    }

    public void setPerformances(String performances) {
        this.performances = performances;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    @Override
    public String toString() {
        return id + " - " + title;
    }

   
    
    

}
