public class Book {
    private String name;
    private String publisher;
    private String author;
    private double priceOther;
    private double priceSite;
    private int pageCount;
    private int saleCount;
    private String genre;
    private int point;
    private int votes;

    public Book(String bookName, String bookPublisher, String bookAuthor, double bookPriceOther, double bookPriceSite, int bookPageCount, int bookSaleCount, String bookGenre, int bookPoint, int bookVotes) {
        name = bookName;
        publisher = bookPublisher;
        author = bookAuthor;
        priceOther = bookPriceOther;
        priceSite = bookPriceSite;
        pageCount = bookPageCount;
        saleCount = bookSaleCount;
        genre = bookGenre;
        point = bookPoint;
        votes = bookVotes;
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

    public void setPriceOther(int bookPriceOther) {
        priceOther = bookPriceOther;
    }

    public void setPriceSite(int bookPriceSite) {
        priceSite = bookPriceSite;
    }

    public void setPagecount(int bookPageCount) {
        pageCount = bookPageCount;
    }

    public void setSaleCount(int bookSaleCount) {
        saleCount = bookSaleCount;
    }

    public void setGenre(String bookGenre) {
        genre = bookGenre;
    }

    public void setPoints(int bookPoint) {
        point = bookPoint;
    }

    public void setVotes(int bookVotes) {
        votes = bookVotes;
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

    public double getPriceOther() {
        return priceOther;
    }

    public double getPriceSite() {
        return priceSite;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public String getGenre() {
        return genre;
    }

    public int getPoint() {
        return point;
    }

    public int getVotes() {
        return votes;
    }


    public String toString() {
        return "--------------------------------------------\n"
                + "Name of the book: " + name
                + "\nPublisher: " + publisher
                + "\nAuthor: " + author
                + "\nPrice in other sites: " + priceOther
                + "\nPrice in Kitapyurdu: " + priceSite
                + "\nTotal number of pages: " + pageCount
                + "\nTotal number of sales: " + saleCount
                + "\nGenre of book: " + genre
                + "\nTotal point of users: " + point
                + "\nTotal votes: " + votes
                + "\n--------------------------------------------\n";
    }

    public String toData() {
        if (name.contains("'")) {
            name = name.replaceAll("'","");
        }
        if (publisher.contains("'")) {
            publisher = publisher.replaceAll("'", "");
        }
        if (author.contains("'")) {
            author = author.replaceAll("'", "");
        }
        if (genre.contains("'")) {
            genre = genre.replaceAll("'", "");
        }
        return "'" + name + "','" + publisher + "','" + author + "'," + priceOther + "," + priceSite + "," + pageCount + "," + saleCount + ",'" + genre + "'," + point + "," + votes;
    }

}

