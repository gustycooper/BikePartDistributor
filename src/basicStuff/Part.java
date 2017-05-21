package basicStuff;

/** Defines a plan part, which can be extended
 * BigDecimal modelVal = new BigDecimal("24.455");
 * BigDecimal displayVal = modelVal.setScale(2, RoundingMode.HALF_EVEN);
 */
public class Part {
    protected String name;
    protected String number;
    protected Cost price;
    public Part() { }

    public Part(String name, String number, String cost) {
        this.name = name;
        this.number = number;
        this.price = new Cost(cost);
    }

    public String getName() { return name; }
    public String getNumber() { return number; }
    public String getPrice() { return price.toString(); }
    public void setPrice(String price) { this.price = new Cost(price); }
}
