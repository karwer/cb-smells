// 3 - data class - even if it was immutable with final properties, lack of getTotalPrice() (or similar) method can be wrong

public class InvoiceItem {
    private int numberOfUnits;
    private BigDecimal unitPrice;
    private Unit unit;
    private TaxRate taxRate;

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public BigDecimal getUnitPrice() {
        return numberOfUnits;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.numberOfUnits = numberOfUnits;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public TaxRate getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(TaxRate taxRate) {
        this.taxRate = taxRate;
    }
}

/* better */

public class InvoiceItem {
    private final int numberOfUnits;
    private final BigDecimal unitPrice;
    private final Unit unit;
    private final TaxRate taxRate;

    public InvoiceItem(int numberOfUnits, BigDecimal unitPrice, Unit unit, TaxRate taxRate) {
        this.numberOfUnits = numberOfUnits;
        this.unitPrice = unitPrice;
        this.unit = unit;
        this.taxRate = taxRate;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public BigDecimal getUnitPrice() {
        return numberOfUnits;
    }

    public Unit getUnit() {
        return unit;
    }

    public TaxRate getTaxRate() {
        return taxRate;
    }

    public BigDecimal getNetPrice() {
        return unitPrice.multiply(BigDecimal.valueOf(numberOfUnits));
    }

    public BigDecimal getGrossPrice() {
        BigDecimal netPrice = getNetPrice();
        BigDecimal tax = taxRate.getTax(netPrice);
        return netPrice.add(tax);
    }

}
