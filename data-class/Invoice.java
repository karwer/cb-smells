// 1 - no data class, data and behaviour encapsulated

class Invoice {
    private final List<InvoiceItem> items;

    public Invoice(final List<InvoiceItem> items) {
        this.items = items;
    }

    public Invoice() {
        this(new ArrayList<>());
    }

    public void addItem(InvoiceItem item) {
        items.add(item);
    }

    public boolean removeItem(InvoiceItem item) {
        return items.remove(item);
    }

    public BigDecimal getTotalPrice() {
        return items.stream()
            .map(InvoiceItem::getPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
