package pl.edu.agh.mwo.invoice;

public class StaticInvoiceNumberGenerator implements InvoiceNumberGenerator {

    private static long actualNumber = 0;

    @Override
    public long getNextNumber() {
        return ++actualNumber;
    }
}
