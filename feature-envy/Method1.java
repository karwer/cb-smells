public BigDecimal getGrossPrice() {
    BigDecimal netPrice = getNetPrice();
    BigDecimal tax = taxRate.getTax(netPrice);
    return netPrice.add(tax);
}
