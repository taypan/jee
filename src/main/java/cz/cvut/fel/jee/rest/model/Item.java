package cz.cvut.fel.jee.rest.model;

/**
 * @author Vaclav Rechtberger
 */
public class Item {
    private String product;                    //ID ref
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
