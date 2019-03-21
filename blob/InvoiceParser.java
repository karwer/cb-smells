// 2 - little blob - reading/writing and parsing together

public class InvoiceParser {
    private final DocumentReader reader;
    private final InvoiceWriter writer;

    public DocumentParser(final DocumentReader reader, final InvoiceWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public void parse(String inputFilename, String outputFilename) {
        Document document = reader.read(inputFilename);
        Invoice invoice = parse(document);
        writer.write(outputFilename, invoice);
    }

    private Invoice parse(Document document) {
        XMLParser parser = new XMLParser(document.getContent());
        List<InvoiceItem> items = parser
            .getElements('invoiceitem')
            .stream()
            .map(XMLElement::getText)
            .map(InvoiceItem::new);
        return new Invoice(items);
    }
}

/* better */

public class InvoiceFileExtractor {
    private final DocumentReader reader;
    private final InvoiceWriter writer;

    public DocumentParser(final DocumentReader reader, final InvoiceWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }
    
    public void extract(String inputFilename, String outputFilename) {
        Document document = reader.read(inputFilename);        
        writer.write(outputFilename, document.parse());
    }
}

public class Document {
    public Invoice parse() {
        XMLParser parser = new XMLParser(content);
        List<InvoiceItem> items = parser
            .getElements('invoiceitem')
            .stream()
            .map(XMLElement::getText)
            .map(InvoiceItem::new);
        return new Invoice(items);   
    }
}