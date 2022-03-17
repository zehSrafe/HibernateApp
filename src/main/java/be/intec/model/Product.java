package be.intec.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Product {
    private int id;
    private String productName;
    private int amount;
    private BigDecimal pricePerUnit;

    public Product() {
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) // makes Id autoincrement
    public int getId() {
        return id;
    }

    public Product setId(int id) {
        this.id = id;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public Product setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public Product setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public Product setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
        return this;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", amount=" + amount +
                ", pricePerUnit=" + pricePerUnit +
                '}';
    }
}
