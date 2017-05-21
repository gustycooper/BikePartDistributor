package bike;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by gusty on 5/4/17.
 */
public class WareHouseFactory {
    List<WareHouse> allWareHouses;

    public WareHouseFactory() { allWareHouses = new ArrayList<>(); }

    public WareHouse getWareHouse(WareHouseTypes wht, String wareHouseName) {
        WareHouse w;
        switch (wht) {
            case MAIN_WARE_HOUSE:
                w = new MainWareHouse(wareHouseName);
                allWareHouses.add(w);
                return w;
            case SALES_VAN_WARE_HOUSE:
                w = new SalesVanWareHouse(wareHouseName);
                allWareHouses.add(w);
                return w;
        }
        return null;
    }

    public WareHousePart getWareHousePart(String nameOrNum) {
        for (WareHouse wh : allWareHouses) {
            if (wh instanceof MainWareHouse) {
                return wh.getPart(nameOrNum);
            }
        }
        return null;
    }

    public int getCount(BikePart bp) {
        int totalCount = 0;
        for (WareHouse w : allWareHouses) {
            if (Main.DEBUG) {
                System.out.print("WareHouse Name: " + w.getWareHouseName());
            }
            WareHouseInventory wi = w.getWareHouseInventory();
            for (WareHousePart wp : wi) {
                if (bp.equals(wp.getBikePart())) {
                    if (Main.DEBUG)
                        System.out.println(" Count: " + wp.getCount());
                    totalCount += wp.getCount();
                }
            }
        }
        return totalCount;
    }


    private WareHousePart findByName(WareHouse wh, String partName) {
        for (WareHousePart whp : wh.getWareHouseInventory()) {
            if (whp.getBikePart().getName().equals(partName))
                return whp;
        }
        return null;
    }
    private void movePart(String partName, int count, WareHouse fromWh, WareHouse toWh) {
        WareHousePart fromWhp = findByName(fromWh, partName);
        WareHousePart toWhp = findByName(toWh, partName);
        if (fromWhp == null)
            return;
        if (toWhp == null) {
            BikePart bp = new BikePart(fromWhp.getBikePart().getName(),
                    fromWhp.getBikePart().getNumber(),
                    fromWhp.getBikePart().getPrice(),
                    fromWhp.getBikePart().getSalePrice(),
                    fromWhp.getBikePart().onSale());
            toWh.addPart(bp, 0);
            toWhp = toWh.getPart(partName);
        }
        fromWhp.updateCount(count, "subtract");
        toWhp.updateCount(count, "add");

    }

    public boolean moveInventory(String fromWhName, String toWhName, String fileName) {
        WareHouse fromWh = null, toWh = null;
        for (WareHouse wh : allWareHouses) {
            if (wh.getWareHouseName().equals(fromWhName))
                fromWh = wh;
            if (wh.getWareHouseName().equals(toWhName))
                toWh = wh;
        }
        if (fromWh == null || toWh == null)
            return false;
        File file = new File(fileName);
        try {
            Scanner read = new Scanner(file);
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String regExp = "\\s*(\\s|,)\\s*";
                String[] pv = line.split(regExp);
                System.out.println(Arrays.toString(pv));
                String partName = pv[0];
                int number = Integer.parseInt(pv[1]);
                movePart(partName, number, fromWh, toWh);
            }
        }
        catch (FileNotFoundException e) {
            return false;
            // System.out.println("Move Inventory file not found");
            // e.printStackTrace();
        }
        return true;
    }

    public String[] getWareHouseNames() {
        String[] retArray = new String[allWareHouses.size()];
        int i = 0;
        for (WareHouse w : allWareHouses) {
            retArray[i] = w.getWareHouseName();
            i++;
        }
        return retArray;
    }

    public WareHouse getWareHouseByName(String whn) {
        for (WareHouse w : allWareHouses) {
            if (w.getWareHouseName().equals(whn))
                return w;
        }
        return null;
    }

    public void printAllInventory() {
        for (WareHouse wh : allWareHouses) {
            for (WareHousePart whp : wh.getWareHouseInventory()) {
                System.out.println(wh.getWareHouseName() + ": " + whp);
            }
        }
    }
}
