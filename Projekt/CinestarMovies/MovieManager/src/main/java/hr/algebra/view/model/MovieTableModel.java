/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.view.model;

import hr.algebra.model.Genre;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import hr.algebra.model.Movie;
import hr.algebra.model.Person;
import java.util.stream.Collectors;

/**
 *
 * @author Nina
 */
public class MovieTableModel extends AbstractTableModel {

    private static final String[] COLUMN_NAMES = {"Id", "Title", "PubDate", "Original tile", "Description",
        "Directors", "Actors", "Duration", "Genre", "Year", "Poster", "Link",
        "Reservation", "Display date", "Perormaces", "Trailer"};

    private List<Movie> movies;

    public MovieTableModel(List<Movie> movies) {
        this.movies = movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return movies.size();
    }

    @Override
    public int getColumnCount() {
        
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Movie movie = movies.get(rowIndex);
        switch (columnIndex) {
            case 0 -> {
                return movie.getId();
            }
            case 1 -> {
                return movie.getTitle();
            }
            case 2 -> {
                return movie.getPubDate();
            }
            case 3 -> {
                return movie.getOriginalTitle();
            }
            case 4 -> {
                return movie.getDescription();
            }
            case 5 -> {
                return String.join(", ", movie.getDirectors().stream().map(Person::getName).collect(Collectors.toList()));
            }
            case 6 -> {
                return String.join(", ", movie.getActors().stream().map(Person::getName).collect(Collectors.toList()));
            }
            case 7 -> {
                return movie.getDuration();
            }
            case 8 -> {
                return String.join(", ", movie.getGenres().stream().map(Genre::getName).collect(Collectors.toList()));
            }
            case 9 -> {
                return movie.getYear();
            }
            case 10 -> {
                return movie.getPoster();
            }
            
            case 11 -> {
                return movie.getLink();
            }
            
            case 12 -> {
                return movie.getReservation();
            }
            case 13 -> {
                return movie.getDisplayDate();
            }
            case 14 -> {
                return movie.getPerformances();
            }
            
            case 15 -> {
                return movie.getTrailer();
            }
            default ->
                throw new RuntimeException("No such column");
        }
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    // important for the id ordering
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0 -> {
                return Integer.class;
            }
        }
        return super.getColumnClass(columnIndex);
    }
}
