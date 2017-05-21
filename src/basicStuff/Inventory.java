package basicStuff;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic Inventory
 */
public class Inventory<PART> {
    List<PART> db;

    public Inventory() { db = new ArrayList<>(); }
}
