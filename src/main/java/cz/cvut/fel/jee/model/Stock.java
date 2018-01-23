package cz.cvut.fel.jee.model;

/**
 * @author Vaclav Rechtberger
 */
public class Stock extends RestObject {
    private String product;                 //product ID
    private int amount;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
