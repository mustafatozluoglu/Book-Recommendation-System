import java.util.Comparator;

public class BookComparator<E> implements Comparator<E> {

    public int compare(E e1, E e2) {
        Book b1 = (Book) e1;
        Book b2 = (Book) e2;
        if (b1.getPoint() > b2.getPoint()) // Compare number of stars
            return 1;
        if (b1.getPoint() == b2.getPoint()) { // If stars are equal compare total number of votes
            if (b1.getVotes() > b2.getVotes())
                return 1;
            if (b1.getVotes() == b2.getVotes()) // If some miracle situtation occurs then they are equally likely to buy
                return 0;
            return -1;
        }
        return -1;
    }
}
