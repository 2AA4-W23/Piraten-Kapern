package pk.game.cards.deck;

import pk.game.cards.Card;
import pk.game.count.Counter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {

    private final List<Card> cards;
    private final Counter counter;

    public CardDeck(Card... cards) {
        this.cards = new ArrayList<>();
        this.cards.addAll(List.of(cards));
        this.shuffle();
        this.counter = new Counter();
    }

    /**
     *
     * Shuffles the cards in the deck
     */
    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    /**
     *
     * @return The card at the top of the deck
     */
    public Card draw() {
        Card card = this.cards.get(this.counter.getCount());
        this.counter.add(1);

        if(this.counter.getCount()%this.cards.size() == 0) // Did the card deck use up all the cards?
            this.counter.reset(); // Reset the cards in the same order

        return card;
    }
}
