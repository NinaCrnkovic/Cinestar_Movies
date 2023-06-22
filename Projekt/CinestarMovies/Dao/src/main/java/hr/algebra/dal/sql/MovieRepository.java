/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;
import hr.algebra.dal.Repository;
import hr.algebra.model.Movie;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

/**
 *
 * @author Nina
 */
public class MovieRepository implements Repository<Movie> {

    private static final String ID_MOVIE = "IDMovie";
    private static final String TITLE = "Title";
    private static final String ORIGINAL_TITLE = "OriginalTitle";
    private static final String DESCRIPTION = "Description";
    private static final String DURATION = "Duration";
    private static final String YEAR = "Year";
    private static final String POSTER = "Poster";
    private static final String RATING = "Rating";
    private static final String LINK = "Link";
    private static final String GUID = "Guid";
    private static final String RESERVATION = "Reservation";
    private static final String DISPLAY_DATE = "DisplayDate";
    private static final String PERFORMANCES = "Performances";
    private static final String SORT = "Sort";
    private static final String TRAILER = "Trailer";

    private static final String CREATE_MOVIE = "{ CALL CreateMovie (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
    private static final String UPDATE_MOVIE = "{ CALL UpdateMovie (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
    private static final String DELETE_MOVIE = "{ CALL DeleteMovie (?) }";
    private static final String SELECT_MOVIE = "{ CALL SelectMovie (?) }";
    private static final String SELECT_MOVIES = "{ CALL SelectMovies }";

   

    @Override
    public int create(Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_MOVIE)) {

            stmt.setString(TITLE, movie.getTitle());
            stmt.setString(ORIGINAL_TITLE, movie.getOriginalTitle());
            stmt.setString(DESCRIPTION, movie.getDescription());
            stmt.setInt(DURATION, movie.getDuration());
            stmt.setInt(YEAR, movie.getYear());
            stmt.setString(POSTER, movie.getPoster());
            stmt.setString(RATING, movie.getRating());
            stmt.setString(LINK, movie.getLink());
            stmt.setString(GUID, movie.getGuid());
            stmt.setString(RESERVATION, movie.getReservation());
            stmt.setString(DISPLAY_DATE, movie.getDisplayDate().format(Movie.DATE_FORMATTER));
            stmt.setString(PERFORMANCES, movie.getPerformances());
            stmt.setInt(SORT, movie.getSort());
            stmt.setString(TRAILER, movie.getTrailer());
            stmt.registerOutParameter(ID_MOVIE, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(ID_MOVIE);
        }
    }
    
    

   public void createManny(List<Movie> movies) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_MOVIE)) {

            for (Movie movie : movies) {
                stmt.setString(TITLE, movie.getTitle());
                stmt.setString(ORIGINAL_TITLE, movie.getOriginalTitle());
                stmt.setString(DESCRIPTION, movie.getDescription());
                stmt.setInt(DURATION, movie.getDuration());
                stmt.setInt(YEAR, movie.getYear());
                stmt.setString(POSTER, movie.getPoster());
                stmt.setString(RATING, movie.getRating());
                stmt.setString(LINK, movie.getLink());
                stmt.setString(GUID, movie.getGuid());
                stmt.setString(RESERVATION, movie.getReservation());
                stmt.setString(DISPLAY_DATE, movie.getDisplayDate().format(Movie.DATE_FORMATTER));
                stmt.setString(PERFORMANCES, movie.getPerformances());
                stmt.setInt(SORT, movie.getSort());
                stmt.setString(TRAILER, movie.getTrailer());
                stmt.registerOutParameter(ID_MOVIE, Types.INTEGER);

                stmt.executeUpdate();
            }
        }
    }

   

    @Override
    public void update(int id, Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_MOVIE)) {

            stmt.setString(TITLE, movie.getTitle());
            stmt.setString(ORIGINAL_TITLE, movie.getOriginalTitle());
            stmt.setString(DESCRIPTION, movie.getDescription());
            stmt.setInt(DURATION, movie.getDuration());
            stmt.setInt(YEAR, movie.getYear());
            stmt.setString(POSTER, movie.getPoster());
            stmt.setString(RATING, movie.getRating());
            stmt.setString(LINK, movie.getLink());
            stmt.setString(GUID, movie.getGuid());
            stmt.setString(RESERVATION, movie.getReservation());
            stmt.setString(DISPLAY_DATE, movie.getDisplayDate().format(Movie.DATE_FORMATTER));
            stmt.setString(PERFORMANCES, movie.getPerformances());
            stmt.setInt(SORT, movie.getSort());
            stmt.setString(TRAILER, movie.getTrailer());
            stmt.setInt(ID_MOVIE, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_MOVIE)) {

            stmt.setInt(ID_MOVIE, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Movie> select(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_MOVIE)) {

            stmt.setInt(ID_MOVIE, id);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(new Movie(
                            rs.getInt(ID_MOVIE),
                            rs.getString(TITLE),
                            rs.getString(ORIGINAL_TITLE),
                            rs.getString(DESCRIPTION),
                            rs.getInt(DURATION),
                            rs.getInt(YEAR),
                            rs.getString(POSTER),
                            rs.getString(RATING),
                            rs.getString(LINK),
                            rs.getString(GUID),
                            rs.getString(RESERVATION),
                            LocalDateTime.parse(rs.getString(DISPLAY_DATE), Movie.DATE_FORMATTER),
                            rs.getString(PERFORMANCES),
                            rs.getInt(SORT),
                            rs.getString(TRAILER)));
                }
            }
        }
        return Optional.empty();
    }


    public List<Movie> selectAll() throws Exception {
        List<Movie> movies = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_MOVIES); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt(ID_MOVIE),
                        rs.getString(TITLE),
                        rs.getString(ORIGINAL_TITLE),
                        rs.getString(DESCRIPTION),
                        rs.getInt(DURATION),
                        rs.getInt(YEAR),
                        rs.getString(POSTER),
                        rs.getString(RATING),
                        rs.getString(LINK),
                        rs.getString(GUID),
                        rs.getString(RESERVATION),
                        LocalDateTime.parse(rs.getString(DISPLAY_DATE), Movie.DATE_FORMATTER),
                        rs.getString(PERFORMANCES),
                        rs.getInt(SORT),
                        rs.getString(TRAILER)));
            }
        }
        return movies;
    }
}
