package be.intec.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Orders")   // will use this table for orders class. usefull for creating tables of reserved keywords
public class Order {
    private int id;
    private String orderNumber;
    private boolean vatFree;
    private boolean send;
    private String orderDate;
    private User user;
    private Collection<Product> products;

    // rank is a SQL reserved keyword and thus will fail when trying to create column named "rank"
    // @Column(name = "the name") will set the name you wish as the name of the column
//    @Column(name = "rank_column")
//    private int rank;

    public Order() {
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) // makes Id autoincrement
    public int getId() {
        return id;
    }

    public Order setId(int id) {
        this.id = id;
        return this;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public Order setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public boolean isVatFree() {
        return vatFree;
    }

    public Order setVatFree(boolean vatFree) {
        this.vatFree = vatFree;
        return this;
    }

    public boolean isSend() {
        return send;
    }

    public Order setSend(boolean send) {
        this.send = send;
        return this;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public Order setOrderDate(String orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public User getUser() {
        return user;
    }

    public Order setUser(User user) {
        this.user = user;
        return this;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Collection<Product> getProducts() {
        return products;
    }

    public Order setProducts(Collection<Product> products) {
        this.products = products;
        return this;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", vatFree=" + vatFree +
                ", send=" + send +
                ", orderDate='" + orderDate + '\'' +
                ", user=" + user +
                ", products=" + products +
                '}';
    }
}
