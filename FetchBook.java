
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FetchBook {
    public static void main(String args[]) throws IOException {

        Document bestSeller = Jsoup.connect("https://www.kitapyurdu.com/cok-satan-kitaplar/haftalik/1.html").get();
        Elements allBooks = bestSeller.select("div.name.ellipsis");

        int count = 0;

        while (allBooks.hasText()){

            System.out.println(count + " " + allBooks.get(count).text());
            count++;
        }
    }
}
