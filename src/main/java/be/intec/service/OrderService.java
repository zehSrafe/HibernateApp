package be.intec.service;

import be.intec.dao.OrderRepository;
import be.intec.model.Address;
import be.intec.model.Order;
import be.intec.model.Product;
import be.intec.model.User;

import javax.persistence.NoResultException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class OrderService {
    private OrderRepository orderRepository = new OrderRepository();

    public void getOrderById(int id) {
        try {
            Order order = orderRepository.getOrderByIdFromDb(id);
            // will trigger the nullPointerException if order = null. won't trigger without trying to read a property
            String triggerException = order.getOrderNumber();
            System.out.println(order);
        } catch (NullPointerException e) {
            System.out.println("No order found with id " + id);
        }
    }

    private int getLastOrderNumber() {
        try {
            String fullOrderNumber = orderRepository.getLastOrderNumberFromDB().getOrderNumber();
            String extractedNumber = fullOrderNumber.substring(fullOrderNumber.lastIndexOf('-') + 1);
            return Integer.parseInt(extractedNumber);
        } catch (NoResultException e) {
            // returns 0 if no results were found. will only happen when creating very first order
            return 0;
        }
    }

    private String generateOrderNumber(Date todaysDate) {
        SimpleDateFormat todaysFormatter = new SimpleDateFormat("yyyyMM");
        String todaysFormattedDate = todaysFormatter.format(todaysDate);
        int orderNumber = getLastOrderNumber() + 1;
        return "ORD-" + todaysFormattedDate + "-" + orderNumber;
    }

    private int findAddressID(Address address) {
        Address foundAddress = orderRepository.findAddressIdInDB(address);
        return foundAddress.getId();
    }

    private User determineUser(User user) {
        try {
            int foundAddressID = findAddressID(user.getAddress());
            User matchingUser = orderRepository.findUserInDB(user, foundAddressID);
            return matchingUser;
        } catch (NoResultException e) {
            return user;
        }
    }

    private Product checkProductExistance(Product productToCheck) {
        try {
            return orderRepository.findProductInDB(productToCheck);
        } catch (NoResultException e) {
            return productToCheck;
        }
    }

    private Collection<Product> filterExistingProducts(Collection<Product> productsToFilter) {
        Collection<Product> filteredProducts = new ArrayList<>();
        for (Product product : productsToFilter) {
            filteredProducts.add(checkProductExistance(product));
        }
        return filteredProducts;
    }

    public void makeNewOrder(User user, Collection<Product> products) {
        Date todaysDate = new Date();
        SimpleDateFormat todaysFormatterForDB = new SimpleDateFormat("yyyy-MM-dd");
        String GeneratedOrderNumber = generateOrderNumber(todaysDate);

        // checks whether same user with address is already present in DB and assigns that user if found
        User finalUser = determineUser(user);
        // same as above but for products
        // TODO: 17/03/2022  make commented below work
//        Collection<Product> finalProducts = filterExistingProducts(products);
        Collection<Product> finalProducts = products;

        Order order = new Order().setOrderNumber(GeneratedOrderNumber).setVatFree(false).setSend(false)
                .setOrderDate(todaysFormatterForDB.format(todaysDate)).setUser(finalUser).setProducts(finalProducts);
        orderRepository.saveNewOrderToDB(order);
    }

    public void printAllOrders() {
        Collection<Order> orders = orderRepository.getAllOrdersFromDB();
        for (Order order : orders) {
            getOrderById(order.getId());
        }
    }
}
