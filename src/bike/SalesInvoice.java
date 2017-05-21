package bike;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * TODO - Implement Customer class
 */
public class SalesInvoice {
    Date dateOfSale;
    String customer;
    List<WareHousePart> wareHouseParts;
    String receivedBy;
    double totalCostOfInvoice;

    public SalesInvoice(Date dateOfSale, String customer) {
        if (dateOfSale == null)
            this.dateOfSale = new Date();
        else
            this.dateOfSale = dateOfSale;
        this.customer = customer;
        this.wareHouseParts = new ArrayList<>();
        this.totalCostOfInvoice = 0;
    }

    public void addInvoice(WareHousePart whp) {
        wareHouseParts.add(whp);
        totalCostOfInvoice += Double.parseDouble(whp.getBikePart().getSalePrice());
    }

    public void addReceivedBy(String name) { receivedBy = name; }

    public Date getDateOfSale() { return dateOfSale; }

    public String toString() {
        String sa = "Invoice: " + customer + " Date: " + dateOfSale + "\n";
        for (WareHousePart whp : wareHouseParts)
            sa += whp.toString() + "\n";
        sa += totalCostOfInvoice + "\n";
        return sa;
    }
}
