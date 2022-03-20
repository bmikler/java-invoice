package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.*;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {

    private Map<Product, Integer> products= new HashMap<>();
    
    public void addProduct(Product product) {
        // TODO: implement
        if (!products.containsKey(product)){
            products.put(product, 1);
        } else {
            products.put(product, products.get(product) + 1);
        }
    }

    public void addProduct(Product product, Integer quantity) {
        // TODO: implement
        if (quantity <= 0)
            throw new IllegalArgumentException();

        for (int i = 0; i < quantity; i++){
            addProduct(product);
        }

    }

    public BigDecimal getSubtotal() {

        BigDecimal subtotal = BigDecimal.ZERO;

        for (Product product : products.keySet()){

            BigDecimal price = product.getPrice();
            BigDecimal quantity = BigDecimal.valueOf(products.get(product));

            subtotal = subtotal.add(price.multiply(quantity));
        }

        return subtotal;

    }

    public BigDecimal getTax() {

        BigDecimal taxTotal = BigDecimal.ZERO;

        for (Product product : products.keySet()){

            BigDecimal tax = product.getTax();
            BigDecimal quantity = BigDecimal.valueOf(products.get(product));

            taxTotal = taxTotal.add(tax.multiply(quantity));
        }

        return taxTotal;
    }

    public BigDecimal getTotal() {

        return getSubtotal().add(getTax());

    }
}
