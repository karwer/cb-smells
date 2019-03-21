public void handleInvoice(Invoice invoice) {
    switch (invoice.getType()) {
        case PAID:
            BigDecimal totalPrice = invoice.getItems().stream()
                .map(item -> item.getNetPrice() + item.getTaxRate().getRate() * item.getNetPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
            storeIncome(totalPrice);
            break;
        case UNPAID:
            sendReminder(invoice.getRecipient());
            break;
    }
}