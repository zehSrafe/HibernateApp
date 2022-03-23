package be.intec.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityManagerProvider {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("datasource", fillEMF());

    private static Map<String, String> fillEMF() {
        Path credentialsFile = Paths.get("D:\\_School\\Cloud\\OneDrive - INTEC BRUSSEL vzw\\Intec\\HibernateApp\\src\\main\\resources\\credentials");
        List<String> readCredentials = null;
        try {
            readCredentials = Files.readAllLines(credentialsFile);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        Map<String, String> credentials = new HashMap<>();
        credentials.put("javax.persistence.jdbc.url", readCredentials.get(0));
        credentials.put("javax.persistence.jdbc.user", readCredentials.get(1));
        credentials.put("javax.persistence.jdbc.password", readCredentials.get(2));

        return credentials;
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
