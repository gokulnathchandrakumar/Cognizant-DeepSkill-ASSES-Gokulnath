public abstract class DocumentFactory {
    public abstract Document createDocument();

    public Document getDocument() {
        Document doc = createDocument();
        System.out.println("Factory produced: " + doc.getClass().getSimpleName());
        return doc;
    }
}
