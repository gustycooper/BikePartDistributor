package basicStuff;

/**
 * Generic WareHousePart
 */
public class WareHousePart<PART> {
    private PART part;
    private int count;

    public WareHousePart(PART part, int count) {
        this.part = part;
        this.count = count;
    }

    public PART getPart() { return this.part; }

    public int getCount() { return this.count; }

    public int addCount(int val) { count += val; return count; }
}
