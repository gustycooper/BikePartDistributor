package basicStuff;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by gusty on 5/4/17.
 */
public class Cost {
    private BigDecimal cost;

    public Cost(String cost) { this.cost = new BigDecimal(cost); }

    public String getCost() { return cost.setScale(2, RoundingMode.HALF_EVEN).toString(); }

    public void setCost(String cost) { this.cost = new BigDecimal(cost); }

    public String toString(){ return cost.setScale(2, RoundingMode.HALF_EVEN).toString(); }

    public boolean equals(Cost c) {
        return cost.setScale(2, RoundingMode.HALF_EVEN).toString().equals(c.getCost());
    }
}
