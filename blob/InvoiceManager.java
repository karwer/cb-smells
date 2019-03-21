// 3 - blob - multiple responsibilities

public class InvoiceManager {
    private final DocumentParser parser;
    private final MailSender mailSender;
    private final PdfCreator pdfCreator;
    private final InvoiceValidator invoiceValidator;

    public InvoiceManager( ... ) { ... }

    public Invoice parseInvoice(Document document) { ... }

    public void sendInvoice(Invoice invoice, String recipient, String subject) { ... }

    public byte[] convertToPDF(Invoice invoice) { ... }

    public validate(Invoice invoice) throws InvalidInvoiceException { ... }
}