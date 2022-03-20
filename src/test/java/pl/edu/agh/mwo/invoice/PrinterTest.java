package pl.edu.agh.mwo.invoice;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class PrinterTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private Printer printer;

    @Before
    public void setUp() {
        printer = new Printer();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void printInvoiceWithManyLines() {

        List<String> expectedPrintList = new ArrayList<>();
        expectedPrintList.add("Kubek; 2; 5");
        expectedPrintList.add("Kozi Serek; 3; 10.80");

        Invoice invoice = mock(Invoice.class);
        when(invoice.getInvoiceNumber()).thenReturn(1L);
        when(invoice.getListToPrint()).thenReturn(expectedPrintList);

        printer.printInvoice(invoice);

        String expected = "=====================\n" +
                "Invoice no. 1\n" +
                "Name; Quantity; Unit Price with Tax\n" +
                "Kubek; 2; 5\n" +
                "Kozi Serek; 3; 10.80\n" +
                "\n" +
                "Number of elements: 2\n" +
                "=====================";

        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
    }

    @Test
    public void printEmptyInvoice() {

        Invoice invoice = mock(Invoice.class);
        when(invoice.getInvoiceNumber()).thenReturn(1L);
        when(invoice.getListToPrint()).thenReturn(new ArrayList<>());

        String expected = "=====================\n" +
                "Invoice no. 1\n" +
                "Invoice is empty\n" +
                "=====================";
        printer.printInvoice(invoice);

        assertEquals(expected, outputStreamCaptor.toString()
                .trim());

    }

    @Test(expected = IllegalArgumentException.class)
    public void printNullInvoice() {
        printer.printInvoice(null);
    }
}