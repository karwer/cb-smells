// 2 - data class, behaviour in class however data not encapsulated properly

class LeakingInvoice {
    private List<InvoiceItem> items;

    public List<InvoiceItem> getItems() {
        return items;
    }

    public List<InvoiceItem> setItems(List<InvoiceItem> items) {
        this.items = items;
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
