package pl.edu.agh.mwo.invoice;

import java.util.List;

public class Printer {
    public void printInvoice(Invoice invoice) {

        if (invoice == null) {
            throw new IllegalArgumentException("Invoice can`t be null");
        }

        System.out.println("Invoice no. " + invoice.getInvoiceNumber());

        if (invoice.getListToPrint().isEmpty()) {
            System.out.println("Invoice is empty");
        } else {
            printProducts(invoice.getListToPrint());
        }
    }

    private void printProducts(List<String> products) {
        for (String line : products) {
            System.out.println(line);
        }
        System.out.println("Number of elements: " + products.size());
    }
}
