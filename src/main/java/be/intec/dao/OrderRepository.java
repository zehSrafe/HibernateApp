package be.intec.dao;

import be.intec.model.Address;
import be.intec.model.Order;
import be.intec.model.Product;
import be.intec.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Collection;

public class OrderRepository {
    public Order getOrderByIdFromDb(int id) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        return em.find(Order.class, id);
    }

    public void saveNewOrderToDB(Order order) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        em.merge(order);
        em.getTransaction().begin();
        em.getTransaction().commit();
    }

    public Order getLastOrderNumberFromDB() throws NoResultException {
        EntityManager em = EntityManagerProvider.getEntityManager();
        Query queryOrder = em.createQuery("SELECT o FROM Order o ORDER BY o.id DESC", Order.class).setMaxResults(1);
        return (Order) queryOrder.getSingleResult();
    }

    public Collection<Order> getAllOrdersFromDB() {
        EntityManager em = EntityManagerProvider.getEntityManager();
        Query queryAllOrders = em.createQuery("SELECT o FROM Order o", Order.class);
        return queryAllOrders.getResultList();
    }

    public User findUserInDB(User userToFind, int addressID) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        Query query = em.createNativeQuery("SELECT u.* FROM User u WHERE u.firstName = :findFirstName " +
                "AND u.lastName = :findLastName AND u.address_id = :addressId", User.class).setMaxResults(1);
        query.setParameter("findFirstName", userToFind.getFirstName());
        query.setParameter("findLastName", userToFind.getLastName());
        query.setParameter("addressId", addressID);
        return (User) query.getSingleResult();
    }

    public Address findAddressIdInDB(Address address) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        Query query = em.createNativeQuery("SELECT a.* FROM Address a WHERE a.streetName = :findStreetName " +
                "AND a.addressNumber = :findAddressNumber AND a.city = :findCity AND a.postCode = :findPostCode"
                , Address.class).setMaxResults(1);
        query.setParameter("findStreetName", address.getStreetName());
        query.setParameter("findAddressNumber", address.getAddressNumber());
        query.setParameter("findCity", address.getCity());
        query.setParameter("findPostCode", address.getPostCode());
        return (Address) query.getSingleResult();
    }

    public Product findProductInDB(Product productToCheck) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        Query query = em.createNativeQuery("SELECT p.* FROM Product p WHERE p.amount = :amount " +
                        "AND p.pricePerUnit = :price AND p.productName = :name", Product.class).setMaxResults(1);
        query.setParameter("amount", productToCheck.getAmount());
        query.setParameter("price", productToCheck.getPricePerUnit());
        query.setParameter("name", productToCheck.getProductName());
        return (Product) query.getSingleResult();
    }
}
