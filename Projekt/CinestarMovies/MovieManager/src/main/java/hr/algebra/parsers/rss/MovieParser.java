/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.parsers.rss;

import hr.algebra.factory.ParserFactory;
import hr.algebra.factory.UrlConnectionFactory;
import hr.algebra.model.Genre;
import hr.algebra.model.Movie;
import hr.algebra.model.Person;
import hr.algebra.utilities.FileUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MovieParser {

    private static final String RSS_URL = "https://www.blitz-cinestar-bh.ba/rss.aspx?id=2682";
    //private static final String ATTRIBUTE_URL = "url";
    private static final String EXT = ".jpg";
    private static final String DIR = "assets";

    public static List<Movie> parse() throws IOException, XMLStreamException {
        List<Movie> movies = new ArrayList<>();
        HttpURLConnection con = UrlConnectionFactory.getHttpUrlConnection(RSS_URL);
        try (InputStream is = con.getInputStream()) { // stream will close the connection
            XMLEventReader reader = ParserFactory.createStaxParser(is);

            Optional<TagType> tagType = Optional.empty();
            Movie movie = null;
            StartElement startElement = null;
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT -> {
                        startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        tagType = TagType.from(qName);
                        // put breakpoint here
                        if (tagType.isPresent() && tagType.get().equals(TagType.ITEM)) {
                            movie = new Movie();
                            movies.add(movie);
                        }
                    }
                    case XMLStreamConstants.CHARACTERS -> {
                        if (tagType.isPresent() && movie != null) {
                            Characters characters = event.asCharacters();
                            String data = characters.getData().trim();
                            switch (tagType.get()) {
                                case TITLE -> {
                                    if (!data.isEmpty()) {
                                        movie.setTitle(data);
                                    }
                                }
                                case LINK -> {
                                    if (!data.isEmpty()) {
                                        movie.setLink(data);
                                    }
                                }
                                case DESCRIPTION -> {
                                    if (!data.isEmpty()) {
                                        Document document = Jsoup.parse(data);
                                        String description = document.text();
                                        movie.setDescription(description);
                                    }
                                }
                                case PLAKAT -> {
                                    if (!data.isEmpty() && startElement != null && movie.getPoster() == null) {
                                        handlePicture(movie, data);
                                    }

                                }
                                case PUB_DATE -> {
                                    if (!data.isEmpty()) {
                                        LocalDateTime publishedDate = LocalDateTime.parse(data, DateTimeFormatter.RFC_1123_DATE_TIME);

                                        movie.setPubDate(publishedDate);
                                    }
                                }
//                                case DATUMPRIKAZIVANJA -> {
//                                    if (!data.isEmpty()) {
//                                        //LocalDateTime publishedDate = LocalDateTime.parse(data, DateTimeFormatter.RFC_1123_DATE_TIME);
//                                        LocalDateTime publishedDate = LocalDateTime.parse("2023-12-12T12:12:12");
//                                        movie.setDisplayDate(publishedDate);
//                                    }
//                                }
                                case DATUMPRIKAZIVANJA -> {
                                    if (!data.isEmpty()) {
                                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                                        LocalDate date = LocalDate.parse(data, formatter);
                                        LocalDateTime publishedDate = date.atStartOfDay();

                                        movie.setDisplayDate(publishedDate);
                                    }
                                }

                                case ORIGNAZIV -> {
                                    if (!data.isEmpty()) {
                                        movie.setOriginalTitle(data);
                                    }
                                }
                                case TRAJANJE -> {
                                    if (!data.isEmpty()) {
                                        movie.setDuration(Integer.parseInt(data));
                                    }
                                }
                                case GODINA -> {
                                    if (!data.isEmpty()) {
                                        movie.setYear(Integer.parseInt(data));
                                    }
                                }
                                case PREDSTAVE -> {
                                    if (!data.isEmpty()) {
                                        movie.setPerformances(data);
                                    }
                                }
                                case TRAILER -> {
                                    if (!data.isEmpty()) {
                                        movie.setTrailer(data);
                                    }
                                }
                                case REZERVACIJA -> {
                                    if (!data.isEmpty()) {
                                        movie.setReservation(data);
                                    }
                                }
                                case ZANR -> {
                                    if (!data.isEmpty()) {
                                        List<Genre> genres = handleGenre(data);
                                        movie.setGenres(genres);

                                    }
                                }
                                case GLUMCI -> {
                                    if (!data.isEmpty()) {
                                        List<Person> actors = handlePerson(data);
                                        movie.setActors(actors);

                                    }
                                }
                                case REDATELJ -> {
                                    if (!data.isEmpty()) {
                                        List<Person> directors = handlePerson(data);
                                        movie.setDirectors(directors);

                                    }
                                }

                            }
                        }
                    }
                }
            }

        }
        return movies;
    }

    private static void handlePicture(Movie movie, String pictureUrl) {

        try {
            String ext = pictureUrl.substring(pictureUrl.lastIndexOf("."));
            if (ext.length() > 4) {
                ext = EXT;
            }
            String pictureName = UUID.randomUUID() + ext;
            String localPicturePath = DIR + File.separator + pictureName;

            FileUtils.copyFromUrl(pictureUrl, localPicturePath);

            movie.setPoster(localPicturePath);
        } catch (IOException ex) {
            Logger.getLogger(MovieParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static List<Genre> handleGenre(String data) {
        return Arrays.stream(data.split(","))
                .map(String::trim)
                .map(Genre::new)
                .collect(Collectors.toList());
    }

    private static List<Person> handlePerson(String data) {
        return Arrays.stream(data.split(","))
                .map(String::trim)
                .map(Person::new)
                .collect(Collectors.toList());
    }

    private enum TagType {

        ITEM("item"),
        TITLE("title"),
        PUB_DATE("pubDate"),
        ORIGNAZIV("orignaziv"),
        DESCRIPTION("description"),
        TRAJANJE("trajanje"),
        GODINA("godina"),
        PLAKAT("plakat"),
        LINK("link"),
        REZERVACIJA("rezervacija"),
        DATUMPRIKAZIVANJA("datumprikazivanja"),
        PREDSTAVE("predstave"),
        TRAILER("trailer"),
        REDATELJ("redatelj"),
        GLUMCI("glumci"),
        ZANR("zanr");

        private final String name;

        private TagType(String name) {
            this.name = name;
        }

        private static Optional<TagType> from(String name) {
            for (TagType value : values()) {
                if (value.name.equals(name)) {
                    return Optional.of(value);
                }
            }
            return Optional.empty();
        }
    }
}
