    public BigDecimal getTotalPrice() {
        return items.stream()
            .map(item -> item.getNetPrice() + item.getTaxRate().getRate() * item.getNetPrice())
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    