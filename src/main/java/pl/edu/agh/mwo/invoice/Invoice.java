package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {

    private final long invoiceNumber;
    private final Map<Product, Integer> products = new LinkedHashMap<>();

    public Invoice(InvoiceNumberGenerator generator) {
        this.invoiceNumber = generator.getNextNumber();
    }

    public Map<Product, Integer> getProducts() {
        return Collections.unmodifiableMap(products);
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product can`t be null");
        }

        if (!products.containsKey(product)) {
            addProduct(product, 1);
        } else {
            addProduct(product, products.get(product) + 1);
        }
    }

    public void addProduct(Product product, Integer quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException();
        }

        products.put(product, quantity);

    }

    public BigDecimal getNetTotal() {

        BigDecimal subtotal = BigDecimal.ZERO;

        for (Product product : products.keySet()) {

            BigDecimal price = product.getPrice();
            BigDecimal quantity = BigDecimal.valueOf(products.get(product));

            subtotal = subtotal.add(price.multiply(quantity));
        }

        return subtotal;

    }

    public BigDecimal getTaxTotal() {

        BigDecimal taxTotal = BigDecimal.ZERO;

        for (Product product : products.keySet()) {

            BigDecimal tax = product.getTax();
            BigDecimal quantity = BigDecimal.valueOf(products.get(product));

            taxTotal = taxTotal.add(tax.multiply(quantity));
        }

        return taxTotal;
    }

    public BigDecimal getGrossTotal() {

        return getNetTotal().add(getTaxTotal());

    }

    public List<String> getListToPrint() {
        List<String> printList = new ArrayList<>();

        printList.add("Invoice no. " + invoiceNumber);

        for (Product product : products.keySet()) {
            String line = product.getName() + "; "
                    + products.get(product) + "; "
                    + product.getPriceWithTax();

            printList.add(line);
        }

        printList.add("Number of elements: " + products.size());

        return Collections.unmodifiableList(printList);
    }

}
