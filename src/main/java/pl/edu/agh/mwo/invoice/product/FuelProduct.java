package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class FuelProduct extends ExciseProduct {
    protected FuelProduct(String name, BigDecimal price) {
        super(name, price, BigDecimal.ZERO);
    }
}
