package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import pl.edu.agh.mwo.invoice.product.Product;

import static org.junit.Assert.*;

public class ProductTest {
    @Test
    public void testProductNameIsCorrect() {
        Product product = new OtherProduct("buty", new BigDecimal("100.0"));
        assertEquals("buty", product.getName());
    }

    @Test
    public void testProductPriceAndTaxWithDefaultTax() {
        Product product = new OtherProduct("Ogorki", new BigDecimal("100.0"));
        assertThat(new BigDecimal("100"), Matchers.comparesEqualTo(product.getPrice()));
        assertThat(new BigDecimal("0.23"), Matchers.comparesEqualTo(product.getTaxPercent()));
    }

    @Test
    public void testProductPriceAndTaxWithDairyProduct() {
        Product product = new DairyProduct("Szarlotka", new BigDecimal("100.0"));
        assertThat(new BigDecimal("100"), Matchers.comparesEqualTo(product.getPrice()));
        assertThat(new BigDecimal("0.08"), Matchers.comparesEqualTo(product.getTaxPercent()));
    }

    @Test
    public void testPriceWithTax() {
        Product product = new DairyProduct("Oscypek", new BigDecimal("100.0"));
        assertThat(new BigDecimal("108"), Matchers.comparesEqualTo(product.getPriceWithTax()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithNullName() {
        new OtherProduct(null, new BigDecimal("100.0"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithEmptyName() {
        new TaxFreeProduct("", new BigDecimal("100.0"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithNullPrice() {
        new DairyProduct("Banany", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithNegativePrice() {
        new TaxFreeProduct("Mandarynki", new BigDecimal("-1.00"));
    }

    @Test
    public void testAlcoholProductTotalPrice() {
        Product product = new AlcoholProduct("Wine",  new BigDecimal(30));
        BigDecimal priceExpected = BigDecimal.valueOf(42.46);
        BigDecimal priceActual = product.getPriceWithTax();

        assertEquals(priceExpected, priceActual);

    }

    @Test
    public void testAlcoholProductTax() {
        Product product = new AlcoholProduct("Wine",  new BigDecimal(30));
        BigDecimal taxExpected = BigDecimal.valueOf(12.46);
        BigDecimal taxActual = product.getTax();

        assertEquals(taxExpected, taxActual);

    }

    @Test
    public void testFuelProductTotalPrice() {
        Product product = new FuelProduct("Gasoline", new BigDecimal(5));
        BigDecimal priceExpected = BigDecimal.valueOf(10.56);
        BigDecimal priceActual = product.getPriceWithTax();

        assertEquals(priceExpected, priceActual);

    }

    @Test
    public void testFuelProductTax() {
        Product product = new FuelProduct("Gasoline", new BigDecimal(5));
        BigDecimal taxExpected = BigDecimal.valueOf(5.56);
        BigDecimal taxActual =product.getTax();

        assertEquals(taxExpected, taxActual);
    }

}
