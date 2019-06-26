import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

public class FetchBook {
    public static void main(String args[]) throws IOException {

        //URL of the Best seller page on Kitapyurdu without page counter
        String url = "https://www.kitapyurdu.com/index.php?route=product/best_sellers&list_id=1&filter_in_stock=1&filter_in_stock=1&page=";

        //Opening first page to get total page number
        String temp = Jsoup.connect(url + 1).get().select("div.results").text();
        temp = temp.substring(temp.indexOf("(") + 1, temp.lastIndexOf(" "));
        int totalPageCount = Integer.parseInt(temp);

        //Arraylist to store all books
        ArrayList<Book> booklist = new ArrayList<>();

        for (int webPageCount = 1; webPageCount < totalPageCount; webPageCount++) {

            //Connect to page number pageCount
            Document bestSeller = Jsoup.connect(url + webPageCount).get();

            //Each book has itemtype Book
            Elements allBooks = bestSeller.select("div[itemtype=\"http://schema.org/Book\"]");

            //Elements class does not have hasNext() so loop through it with for and size
            for (int index = 0; index < allBooks.size(); index++) {
                //Temporary variables to make code more readable
                String name, publisher, author, genre;
                double priceOther, priceSite;
                int pageCount, saleCount, votes, point;

                Element currentBook = allBooks.get(index);
                String urlOfBook = currentBook.select("div.name").select("a[href]").attr("href");
                //Create new document to get datas from books individual site
                Document bookURL = Jsoup.connect(urlOfBook).get();

                // For an example book Ana sayfa -> Book name
                name = bookURL.select("div.breadcrumb a[href]").eq(1).text();
                author = bookURL.select("span[itemprop=author]").text();
                publisher = bookURL.select("span[itemprop=publisher] span[itemprop=name]").text();

                // Genre is stored on site as Kitap » Diðer » Kiþisel Geliþim 
                String tempForParsing = bookURL.select("div.grid_6.omega.alpha.section a[href]").text();
                genre = tempForParsing.substring(tempForParsing.lastIndexOf("»") + 1);

                //Price is stored such as "27,50"
                tempForParsing = bookURL.select("div.list_price span[class=value]").text();
                tempForParsing = tempForParsing.replaceAll(",", ".");
                if (!tempForParsing.equals(""))
                    priceOther = Double.parseDouble(tempForParsing);
                else
                    priceOther = 0;
                priceSite = Double.parseDouble(bookURL.select("div[itemprop=offers] meta[itemprop=price]").attr("content"));

                //Could only get number of stars
                String pointhelper = bookURL.select("meta[itemprop=ratingValue]").attr("content");
                if (!pointhelper.equals(""))
                    point = Integer.parseInt(pointhelper);
                else
                    point = 0;

                String pageCountHelper = bookURL.select("span[itemprop=numberOfPages]").text();
                if (!pageCountHelper.equals(""))
                    pageCount = Integer.parseInt(pageCountHelper);
                else
                    pageCount = 0;

                tempForParsing = bookURL.select("div.grid_6.omega.alpha.section").text();
                tempForParsing = tempForParsing.replaceAll("\\D+", "");
                if (tempForParsing.length() > 6)
                    tempForParsing = tempForParsing.substring(0, 6);
                saleCount = Integer.parseInt(tempForParsing);

                String votesHelper = bookURL.select("meta[itemprop=ratingCount]").attr("content");
                if (!votesHelper.equals(""))
                    votes = Integer.parseInt(votesHelper);
                else
                    votes = 0;
                Book newest = new Book(name, publisher, author, priceOther, priceSite, pageCount, saleCount, genre, point, votes);
                booklist.add(newest);
                System.out.println(newest);
                System.out.println(newest.toData());

            }//end of fetching books from site 
        }

        BookComparator<Book> comparator = new BookComparator<>();
        booklist.sort(comparator);


    }//end of main method
}