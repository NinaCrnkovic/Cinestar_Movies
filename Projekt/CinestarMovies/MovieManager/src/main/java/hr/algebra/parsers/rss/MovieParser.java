/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.parsers.rss;
import hr.algebra.factory.ParserFactory;
import hr.algebra.factory.UrlConnectionFactory;
import hr.algebra.model.Movie;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author Nina
 */
public class MovieParser {
    private static final String RSS_URL = "https://www.blitz-cinestar-bh.ba/rss.aspx?id=2682";
    private static final String ATTRIBUTE_URL = "url";
    private static final String EXT = ".jpg";
    private static final String DIR = "assets";
    
        public static List<Movie> parse() throws IOException, XMLStreamException {
        List<Movie> articles = new ArrayList<>();
//        HttpURLConnection con = UrlConnectionFactory.getHttpUrlConnection(RSS_URL);
//        try (InputStream is = con.getInputStream()) { // stream will close the connection
//            XMLEventReader reader = ParserFactory.createStaxParser(is);

//            Optional<TagType> tagType = Optional.empty();
//            Article article = null;
//            StartElement startElement = null;
//            while (reader.hasNext()) {
//                XMLEvent event = reader.nextEvent();
//                switch (event.getEventType()) {
//                    case XMLStreamConstants.START_ELEMENT -> {
//                        startElement = event.asStartElement();
//                        String qName = startElement.getName().getLocalPart();
//                        tagType = TagType.from(qName);
//                        // put breakpoint here
//                        if (tagType.isPresent() && tagType.get().equals(TagType.ITEM)) {
//                            article = new Article();
//                            articles.add(article);
//                        }
//                    }
//                    case XMLStreamConstants.CHARACTERS -> {
//                        if (tagType.isPresent() && article != null) {
//                            Characters characters = event.asCharacters();
//                            String data = characters.getData().trim();
//                            switch (tagType.get()) {
//                                case TITLE -> {
//                                    if (!data.isEmpty()) {
//                                        article.setTitle(data);
//                                    }
//                                }
//                                case LINK -> {
//                                    if (!data.isEmpty()) {
//                                        article.setLink(data);
//                                    }
//                                }
//                                case DESCRIPTION -> {
//                                    if (!data.isEmpty()) {
//                                        article.setDescription(data);
//                                    }
//                                }
//                                case ENCLOSURE -> {
//                                    if (startElement != null && article.getPicturePath() == null) {
//                                        Attribute urlAttribute = startElement.getAttributeByName(new QName(ATTRIBUTE_URL));
//                                        if (urlAttribute != null) {
//                                            handlePicture(article, urlAttribute.getValue());
//                                        }
//                                    }
//                                }
//                                case PUB_DATE -> {
//                                    if (!data.isEmpty()) {
//                                        LocalDateTime publishedDate = LocalDateTime.parse(data, DateTimeFormatter.RFC_1123_DATE_TIME);
//                                        article.setPublishedDate(publishedDate);
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//        }
       return articles;
    }

        
        private enum TagType {

        ITEM("item"),
        TITLE("title"),
        LINK("link"),
        DESCRIPTION("description"),
        ENCLOSURE("enclosure"),
        PUB_DATE("pubDate");

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
