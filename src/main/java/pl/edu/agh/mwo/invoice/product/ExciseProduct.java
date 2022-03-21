package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public abstract class ExciseProduct extends Product {

    private final BigDecimal excise = BigDecimal.valueOf(5.56);

    protected ExciseProduct(String name, BigDecimal price, BigDecimal tax) {
        super(name, price, tax);
    }

    @Override
    public BigDecimal getTax() {
        return super.getTax().add(excise);
    }
}
