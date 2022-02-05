package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Collection<Product> products;

    public Invoice() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        // TODO: implement
        products.add(product);
    }

    public void addProduct(Product product, Integer quantity) {
        // TODO: implement
        if (quantity <= 0)
            throw new IllegalArgumentException();

        for (int i = 0; i < quantity; i++){
            products.add(product);
        }

    }

    public BigDecimal getSubtotal() {

        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTax() {

        return products.stream()
                .map(Product::getTax)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotal() {

        return products.stream()
                .map(Product::getPriceWithTax)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
