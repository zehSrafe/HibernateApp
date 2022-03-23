package be.intec.view;

import be.intec.model.Address;
import be.intec.model.Product;
import be.intec.model.User;
import be.intec.service.OrderService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        List<Product> products = new ArrayList<>();

//        Address myAddress = new Address().setStreetName("notreat street").setAddressNumber("420")
//                .setCity("Brussels").setPostCode("1000");
//        Address alternateAddress = new Address().setStreetName("No idea").setAddressNumber("69")
//                .setCity("Liege").setPostCode("5426");
//        Address thirdAddress = new Address().setStreetName("this is the third address").setAddressNumber("444469")
//                .setCity("Louvain").setPostCode("1300");
//        Product product1 = new Product().setProductName("Clipper").setAmount(2).setPricePerUnit(BigDecimal.valueOf(2.99));
//        Product product2 = new Product().setProductName("Keyboard").setAmount(500).setPricePerUnit(BigDecimal.valueOf(144));
//        products.add(product1);
//        products.add(product2);
//        User myUser = new User().setFirstName("Loïc").setLastName("Deketelaere").setAddress(myAddress);
//
//        orderService.makeNewOrder(myUser, products);
//        orderService.getOrderById(1);

//        orderService.findAddressID(alternateAddress);

        orderService.printAllOrders();
    }
}
