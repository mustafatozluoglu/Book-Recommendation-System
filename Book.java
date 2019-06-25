import java.util.StringTokenizer;

public class Book {
    private String name;
    private String publisher;
    private String author;
    private String details;
    private double priceOther;
    private double priceSite;
    private int pageCount;

    public Book(String bookName, String bookPublisher, String bookAuthor, String bookDetails, double bookPriceOther, double bookPriceSite) {
        name = bookName;
        publisher = bookPublisher;
        author = bookAuthor;
        details = bookDetails;
        priceOther = bookPriceOther;
        priceSite = bookPriceSite;
        parseDetails();
    }

    public void setName(String bookName) {
        name = bookName;
    }

    public void setPublisher(String bookPublisher) {
        publisher = bookPublisher;
    }

    public void setauthor(String bookAuthor) {
        author = bookAuthor;
    }

    public void setDetails(String bookDetails) {
        details = bookDetails;
    }

    public void setPriceOther(int bookPriceOther) {
        priceOther = bookPriceOther;
    }

    public void setPriceSite(int bookPriceSite) {
        priceSite = bookPriceSite;
    }

    public void setPagecount(int bookPageCount) {
        pageCount = bookPageCount;
    }

    public String getName() {
        return name;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getAuthor() {
        return author;
    }

    public String getDetails() {
        return details;
    }

    public double getPriceOther() {
        return priceOther;
    }

    public double getPriceSite() {
        return priceSite;
    }

    public int getPageCount() {
        return pageCount;
    }

    //Method to parse details into meaningful sentence
    public String parseDetails() {
        //Example format of details
        // 6053609902 | 18. Bask� | T�RK�E | 136 | Karton Kapak | Kitap Ka��d� 2019-05-24
        //System.out.println(details);
        StringBuilder sb = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(details, "|");
        String token = tokenizer.nextToken();
        if (!Character.isDigit(token.charAt(1))) //For detail that doesnt fit example format
            token = tokenizer.nextToken(); //Ignore that token
        //System.out.println(token);
        sb.append("ID of book: " + token);
        token = tokenizer.nextToken();
        //System.out.println(token);
        sb.append("\nEdition number: " + token);
        token = tokenizer.nextToken();
        //System.out.println(token);
        sb.append("\nLanguage: " + token);
        token = tokenizer.nextToken();

        if (!Character.isDigit(token.charAt(1)))
            pageCount = 0;
        else {
            token = token.substring(1, token.length() - 1);
            pageCount = Integer.parseInt(token); //Needs validation
        }
        //System.out.println(token);
        sb.append("\nPage count: " + pageCount);
        token = tokenizer.nextToken();
        //System.out.println(token);
        sb.append("\nCover: " + token);
        token = tokenizer.nextToken();
        //System.out.println(token);
        sb.append("\nPage Quality and publish date: " + token);
        return sb.toString();
    }

    public String toString() {
        return "--------------------------------------------\n"
                + "Name of the book: " + name
                + "\nPublisher: " + publisher
                + "\nAuthor: " + author
                + "\n" + parseDetails()
                + "\nPrice in other sites: " + priceOther
                + "\nPrice in Kitapyurdu: " + priceSite
                + "\n--------------------------------------------\n";
    }
}

