package bike;

import basicStuff.LoginAccount;
import basicStuff.Person;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by gusty on 5/5/17.
 */
public class WareHouseManager extends LoginAccount {
    private String whn;
    private WareHouse wh;
    private WareHouseFactory whf;

    public WareHouseManager(String fn, String ln, String em, String un, String pw, WareHouseFactory whf, String whn) {
        super(new Person(fn, ln, em), un, pw);
        this.whf = whf;
        this.whn = whn;
        this.wh = whf.getWareHouse(WareHouseTypes.MAIN_WARE_HOUSE, whn);
    }

    public String getWarehouseName() { return whn; }

    public WareHouse getWarehouse() { return wh; }
    /**
     * Load Main Warehouse with parts described in inventory delivery file
     * File format: WTB_saddle,1234567890,33.00,25.58,false,25
     * @param fileName
     * @return
     */
    public boolean loadMainWareHouse(String fileName) {
        File file = new File(fileName);
        try {
            Scanner read = new Scanner(file);
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String regExp = "\\s*(\\s|,)\\s*";
                String[] bpi = line.split(regExp);
                if (Main.DEBUG)
                    System.out.println(Arrays.toString(bpi));
                BikePart bp = new BikePart(bpi[0], bpi[1], bpi[2], bpi[3], bpi[4].equals("true") ? true : false);
                wh.addPart(bp,Integer.parseInt(bpi[5]));
            }
        }
        catch (FileNotFoundException e) {
            return false;
            // System.out.println("Inventory delivery file not found");
            // e.printStackTrace();
        }
        return true;
    }
}
