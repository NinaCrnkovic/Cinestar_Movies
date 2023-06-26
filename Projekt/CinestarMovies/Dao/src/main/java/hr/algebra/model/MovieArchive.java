/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nina
 */
@XmlRootElement(name = "moviearchive")
@XmlAccessorType(XmlAccessType.FIELD)
public final class MovieArchive {
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public MovieArchive() {
    }

    public MovieArchive(List<Movie> movies) {
        this.movies = movies;
    }
    
}
