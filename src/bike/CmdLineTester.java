package bike;

import java.util.ArrayList;
import java.util.List;

import basicStuff.LoginAccount;
import basicStuff.Person;

/**
 * Created by gusty on 5/4/17.
 */
public class CmdLineTester {
    public static void main(String[]  args) {
        String[] parts = {"WTB_saddle","1234567890","33.00","25.58",
                          "26inTube","1234567891","7.00","5.58",
                          "seatPost","1234567892","17.00","15.21",
                          "carbonHandleBars42cm","1234567893","47.00","5.58",
                          "700-23SwhalbeTire","1234567894","50.00","40.50",
                          "700-25SwhalbeTire","1234567895","50.00","40.50",
                          "10spRearDerailuer","1234567896","60.00","50.50",
                          "10spFrontDerailuer","1234567897","60.00","50.50",
                          "11spRearDerailuer","1234567898","60.00","50.50",
                          "11spFrontDerailuer","1234567899","60.00","50.50",
                          "mensBibsMedium","1234567900","110.00","99.00"};

        List<BikePart> bpl = new ArrayList<>();
        for (int i = 0; i < parts.length; i+=4)
            bpl.add(new BikePart(parts[i], parts[i+1], parts[i+2], parts[i+3], false));

        BikePart bp = new BikePart("spoke", "123456", "2.00", "1.506", false );
        System.out.println(bp.getSalePrice());
        WareHouseFactory whf = new WareHouseFactory();
        WareHouse mwh = whf.getWareHouse(WareHouseTypes.MAIN_WARE_HOUSE, "MainWareHouse");
        mwh.addPart(bp, 1);
        for (BikePart b : bpl)
            mwh.addPart(b, 3);
        System.out.println(mwh.getWareHouseName() + ": " + mwh.getPart("spoke"));
        System.out.println(mwh.getWareHouseName() + ": " + mwh.getPart("123456"));
        System.out.println(mwh.getWareHouseName() + ": " + mwh.getPart("26inTube"));
        System.out.println(mwh.getWareHouseName() + ": " + mwh.getPart("mensBibsMedium"));

        WareHouse svwh1 = whf.getWareHouse(WareHouseTypes.SALES_VAN_WARE_HOUSE, "SalesVanA");
        svwh1.addPart(bp, 2);
        System.out.println(svwh1.getWareHouseName() + ": " + svwh1.getPart("spoke"));
        System.out.println(svwh1.getWareHouseName() + ": " + svwh1.getPart("123456"));
        bp.setSalePrice("1.0034");
        System.out.println(svwh1.getWareHouseName() + ": " + svwh1.getPart("123456"));

        WareHouse svwh2 = whf.getWareHouse(WareHouseTypes.SALES_VAN_WARE_HOUSE, "SalesVanB");
        svwh2.addPart(bp, 2);
        System.out.println(svwh2.getWareHouseName() + ": " + svwh2.getPart("spoke"));
        System.out.println(svwh2.getWareHouseName() + ": " + svwh2.getPart("123456"));
        bp.setSalePrice("1.0034");
        System.out.println(svwh2.getWareHouseName() + ": " + svwh2.getPart("123456"));

        Person p = new Person("Gusty", "Cooper", "gusty@gusty.bike");
        LoginAccount a = new LoginAccount(p,"gusty", "gusty");
        System.out.println(a.validateLoginAccount("gusty", "gusty"));
        System.out.println(a.validateLoginAccount("gusty", "cooper"));


        System.out.println(whf.getCount(bp));


        OfficeManager om = new OfficeManager("Emily", "Cooper", "emily", "emily", "emily");
        System.out.println(om.validateLoginAccount("emily", "emily"));

        SalesAssociate sa = new SalesAssociate("Coletta", "Cooper", "co", "co", "co", whf, "coVan");
        sa.addPart(bp,5);
        System.out.println(sa.getWareHouse().getWareHouseName() + ": " + sa.getWareHouse().getPart("spoke"));

        System.out.println("Total Count: " + whf.getCount(bp));

        System.out.println("Iterate through WareHouseInventory");
        WareHouseInventory whi = mwh.getWareHouseInventory();
        for (WareHousePart w : whi) {
            System.out.println(w);
        }

        System.out.println("\nPrinting All Inventory");
        whf.printAllInventory();

    }
}
