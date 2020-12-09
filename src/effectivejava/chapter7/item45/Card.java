package effectivejava.chapter7.item45;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static java.util.stream.Collectors.*;

/**
 * 第45条：坚持使用标准的函数接口
 */
// Generating the Cartesian product of two lists using iteration and streams (Page 209)
// 使用迭代和流生成两个列表的笛卡尔积
public class Card {
    public enum Suit { SPADE, HEART, DIAMOND, CLUB }
    public enum Rank { ACE, DEUCE, THREE, FOUR, FIVE, SIX, SEVEN,
                       EIGHT, NINE, TEN, JACK, QUEEN, KING }

    private final Suit suit;
    private final Rank rank;

    @Override public String toString() {
        return rank + " of " + suit + "S";
    }

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;

    }
    private static final List<Card> NEW_DECK = newDeck();

    // Iterative Cartesian product computation 迭代笛卡尔积计算
    private static List<Card> newDeck() {
        List<Card> result = new ArrayList<>();
        for (Suit suit : Suit.values())
            for (Rank rank : Rank.values())
                result.add(new Card(suit, rank));
        return result;
    }

//    // Stream-based Cartesian product computation 基于流的笛卡尔积计算
//    private static List<Card> newDeck() {
//        return Stream.of(Suit.values())
//                .flatMap(suit ->
//                        Stream.of(Rank.values())
//                                .map(rank -> new Card(suit, rank)))
//                .collect(toList());
//    }

    public static void main(String[] args) {
        System.out.println(NEW_DECK);
    }
}
/* Output:
[ACE of SPADES, DEUCE of SPADES, THREE of SPADES, FOUR of SPADES, FIVE of SPADES, SIX of SPADES, SEVEN of SPADES, EIGHT of SPADES, NINE of SPADES, TEN of SPADES, JACK of SPADES, QUEEN of SPADES, KING of SPADES, ACE of HEARTS, DEUCE of HEARTS, THREE of HEARTS, FOUR of HEARTS, FIVE of HEARTS, SIX of HEARTS, SEVEN of HEARTS, EIGHT of HEARTS, NINE of HEARTS, TEN of HEARTS, JACK of HEARTS, QUEEN of HEARTS, KING of HEARTS, ACE of DIAMONDS, DEUCE of DIAMONDS, THREE of DIAMONDS, FOUR of DIAMONDS, FIVE of DIAMONDS, SIX of DIAMONDS, SEVEN of DIAMONDS, EIGHT of DIAMONDS, NINE of DIAMONDS, TEN of DIAMONDS, JACK of DIAMONDS, QUEEN of DIAMONDS, KING of DIAMONDS, ACE of CLUBS, DEUCE of CLUBS, THREE of CLUBS, FOUR of CLUBS, FIVE of CLUBS, SIX of CLUBS, SEVEN of CLUBS, EIGHT of CLUBS, NINE of CLUBS, TEN of CLUBS, JACK of CLUBS, QUEEN of CLUBS, KING of CLUBS]
 */