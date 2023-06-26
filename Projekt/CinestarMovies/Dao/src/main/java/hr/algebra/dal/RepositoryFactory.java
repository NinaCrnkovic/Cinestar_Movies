/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nina
 */
public final class RepositoryFactory {

    private static final Properties properties = new Properties();
    private static final String PATH = "/config/repository.properties";
    private static final Map<RepoType, Repository> repositories = new HashMap<>();
    private static final Map<SqlRepoType, SQLServiceRepository> sqlRepositories = new HashMap<>();

    static {
        try (InputStream is = RepositoryFactory.class.getResourceAsStream(PATH)) {
            properties.load(is);

            for (RepoType type : RepoType.values()) {
                String className = properties.getProperty(type.name());
                Repository repository = (Repository) Class
                        .forName(className)
                        .getDeclaredConstructor()
                        .newInstance();
                repositories.put(type, repository);
            }

            for (SqlRepoType type : SqlRepoType.values()) {
                String className = properties.getProperty(type.name());
                SQLServiceRepository sqlRepository = (SQLServiceRepository) Class
                        .forName(className)
                        .getDeclaredConstructor()
                        .newInstance();
                sqlRepositories.put(type, sqlRepository);
            }

        } catch (Exception ex) {
            Logger.getLogger(RepositoryFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Repository getRepository(RepoType type) {
        return repositories.get(type);
    }

    public static SQLServiceRepository getSqlRepository(SqlRepoType type) {
        return sqlRepositories.get(type);
    }

    public enum RepoType {
        ACCOUNT_TYPE,
        FAVORITE_MOVIE,
        GENRE,
        MOVIE_GENRE,
        MOVIE_PERSON_ROLE,
        MOVIE,
        PERSON,
        ROLE,
        USER
    }

    public enum SqlRepoType {
        DATABASE
        
    }
}
