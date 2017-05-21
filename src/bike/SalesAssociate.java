package bike;

import basicStuff.LoginAccount;
import basicStuff.Person;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class SalesAssociate extends LoginAccount {
    private String whn;
    private WareHouse wh;
    private WareHouseFactory whf;
    private List<SalesInvoice> sales;

    public SalesAssociate(String fn, String ln, String em, String un, String pw, WareHouseFactory whf, String whn) {
        super(new Person(fn, ln, em), un, pw);
        this.whf = whf;
        this.whn = whn;
        this.wh = whf.getWareHouse(WareHouseTypes.SALES_VAN_WARE_HOUSE, whn);
        this.sales = new ArrayList<>();
    }

    public SalesVanWareHouse getWareHouse() { return (SalesVanWareHouse)wh; }

    public void addPart(BikePart bp, int count) { wh.addPart(bp, count); }

    public void addSalesInvoice(SalesInvoice s) { sales.add(s); }

    public List<SalesInvoice> getSales(Date startDate, Date endDate) {
        List<SalesInvoice> retList = new ArrayList<>();
        for (SalesInvoice si : sales) {
            if (startDate == null) {
                retList.add(si);
            }
            else {
                // TODO - fix getSales to compare dates
                //if (startDate.compareTo(si.getDateOfSale()) <= 0 && endDate.compareTo(si.getDateOfSale()) >= 0)
                retList.add(si);
            }
        }
        return retList;
    }

    public void loadSalesVanWareHouse(String fromWhName, String fileName) {
        whf.moveInventory(fromWhName, whn, fileName);
    }
}
