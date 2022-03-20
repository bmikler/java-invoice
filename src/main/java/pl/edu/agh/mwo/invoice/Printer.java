package pl.edu.agh.mwo.invoice;

import java.util.List;

public class Printer {

    public void printInvoice(Invoice invoice) {

        if (invoice == null) {
            throw new IllegalArgumentException("Invoice can`t be null!");
        }
        List<String> printList = invoice.getListToPrint();

        System.out.println("=====================");
        System.out.println("Invoice no. " + invoice.getInvoiceNumber());

        if (printList.isEmpty()) {
            System.out.println("Invoice is empty");
        } else {
            printProducts(printList);
        }

        System.out.println("=====================");

    }

    private void printProducts(List<String> printList) {
        System.out.println("Name; Quantity; Unit Price with Tax");

        int counter = 0;

        for (String line : printList) {
            System.out.println(line);
            counter++;
        }

        System.out.println();
        System.out.println("Number of elements: " + counter);
    }


}
