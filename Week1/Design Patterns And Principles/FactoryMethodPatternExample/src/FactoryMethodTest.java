public class FactoryMethodTest {
    public static void main(String[] args) {
        System.out.println("---- Creating a Word Document ----");
        DocumentFactory wordFactory = new WordDocumentFactory();
        Document wordDoc = wordFactory.getDocument();
        wordDoc.open();
        wordDoc.save();

        System.out.println("\n---- Creating a PDF Document ----");
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        Document pdfDoc = pdfFactory.getDocument();
        pdfDoc.open();
        pdfDoc.save();

        System.out.println("\n---- Creating an Excel Document ----");
        DocumentFactory excelFactory = new ExcelDocumentFactory();
        Document excelDoc = excelFactory.getDocument();
        excelDoc.open();
        excelDoc.save();

        System.out.println("\n---- Demonstrating polymorphism with an array ----");
        DocumentFactory[] factories = {
            new WordDocumentFactory(),
            new PdfDocumentFactory(),
            new ExcelDocumentFactory()
        };

        for (DocumentFactory factory : factories) {
            Document doc = factory.getDocument();
            doc.open();
        }
    }
}
