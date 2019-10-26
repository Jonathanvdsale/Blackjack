
//Implementation of Deck received from https://stackoverflow.com/questions/15942050/deck-of-cards-java/25092220

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private final ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>();
        // populate deck with cards
        for (Suit suit : Suit.values()) {
            for (Face face : Face.values()) {
                cards.add(new Card(face, suit));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        return cards.remove(0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cards.size(); i++) {
            sb.append(i + 1);
            sb.append('/');
            sb.append(cards.size());
            sb.append(' ');
            sb.append(cards.get(i));
            sb.append('\n');
        }
        return sb.toString();
    }

}