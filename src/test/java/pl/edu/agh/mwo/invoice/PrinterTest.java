package pl.edu.agh.mwo.invoice;

import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.mwo.invoice.Invoice;
import pl.edu.agh.mwo.invoice.Printer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

        String expected = "Invoice no. 1\n" +
                "Kubek; 2; 5\n" +
                "Kozi Serek; 3; 10.80\n" +
                "Number of elements: 2";

        assertEquals(expected, outputStreamCaptor.toString()
                .trim());
    }

    @Test
    public void printEmptyInvoice() {

        Invoice invoice = mock(Invoice.class);
        when(invoice.getInvoiceNumber()).thenReturn(1L);
        when(invoice.getListToPrint()).thenReturn(new ArrayList<>());

        String expected = "Invoice no. 1\n" +
                "Invoice is empty";
        printer.printInvoice(invoice);

        assertEquals(expected, outputStreamCaptor.toString()
                .trim());

    }

    @Test(expected = IllegalArgumentException.class)
    public void printNullInvoice() {
        printer.printInvoice(null);
    }

}