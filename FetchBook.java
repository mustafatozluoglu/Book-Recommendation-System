import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class FetchBook {
    public static void main(String args[]) throws IOException {

        String url = "https://www.kitapyurdu.com/index.php?route=product/best_sellers&list_id=1&filter_in_stock=1&filter_in_stock=1&page=1";
        String temp = Jsoup.connect("https://www.kitapyurdu.com/index.php?route=product/best_sellers&list_id=1&filter_in_stock=1&filter_in_stock=1&page=1").get().select("div.results").text();
        temp = temp.substring(temp.indexOf("(")+1,temp.lastIndexOf(" "));
        int totalPageCount = Integer.parseInt(temp);
        //Arraylist to store all books
        ArrayList<Book> booklist = new ArrayList<>();

        for (int pageCount = 1; pageCount < totalPageCount; pageCount++) {


            //First page of Best Seller on kitapyurdu.com
            Document bestSeller = Jsoup.connect("https://www.kitapyurdu.com/index.php?route=product/best_sellers&list_id=1&filter_in_stock=1&filter_in_stock=1&page=" + pageCount).get();

            //Each book has itemtype Book
            Elements allBooks = bestSeller.select("div[itemtype=\"http://schema.org/Book\"]");


            for (int index = 0; index < allBooks.size(); index++) {
                //Temporary variables to make code more readable
                String name, publisher, author, details;
                double priceOther, priceSite;

                //Assign values of them
                name = allBooks.get(index).child(4).text();
                if(!name.contains("Dergi") && allBooks.get(index).children().size()==16){
                    publisher = allBooks.get(index).child(5).child(0).text();
                    author = allBooks.get(index).child(7).text();
                    details = allBooks.get(index).child(8).text();

                    //Child(9) indicates prices, Child(1) indicates price in other site with text "Liste fiyat?" Child(1) of that is only price
                    //Same hierarchy exist for Kitapyurdu price
                    String temporary = allBooks.get(index).child(9).child(0).child(1).text();
                    temporary = temporary.replace(',', '.'); //Prices include comma to seperate penny
                    priceOther = Double.parseDouble(temporary); //Needs validaiton
                    temporary = allBooks.get(index).child(9).child(1).child(1).text();
                    temporary = temporary.replace(',', '.');
                    priceSite = Double.parseDouble(temporary); //Needs valdiation as well

                    //System.out.println(name); //Name of the book
                    //System.out.println(publisher); //Publisher of the book
                    //System.out.println(author); //Author of the book
                    //System.out.println(details); //Book details

                    //System.out.println(priceOther); //Price of the book in other sites
                    //System.out.println(priceSite); //Price of the book in Kitapyurdu

                    //Create temporary book object to write these data to booklist
                    Book newest = new Book(name, publisher, author, details, priceOther, priceSite);
                    booklist.add(newest);
                }

            }//end of fetching books from site
        }



        System.out.println(booklist.size()); // To see if book is added correctly

    }//end of main method
}