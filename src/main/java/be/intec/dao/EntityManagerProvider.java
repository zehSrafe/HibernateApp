package be.intec.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProvider {
    // shouldn't be static but easier for now
    // create emf using name declared in resources/META-INF/persistance.xml
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("datasource");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
