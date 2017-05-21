package bike;

/**
 * Created by gusty on 5/4/17.
 */
public class WareHousePart {
    private BikePart bp;
    private int count;

    public WareHousePart(BikePart bp, int count) {
        this.bp = bp; // TODO - clone bp to fix mutability
        this.count = count;
    }

    public BikePart getBikePart() { return this.bp; }

    public int getCount() { return count; }

    public void updateCount(int value, String addSub) {
        if (addSub.equals("add"))
            this.count += value;
        else
            this.count -= value;
    }

    public String toString() {
        return this.bp + ", Count: " + count;
    }
}
