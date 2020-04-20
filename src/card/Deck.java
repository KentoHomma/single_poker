/*
package card;

import java.util.Collections;
import java.util.List;


public class Deck {
    private List<Card> oneDeck;
    {
    	//リストに1~52のカードを入れる
        Collections.shuffle(oneDeck);
    }

    public Card draw(){
        Card card = oneDeck.get(0);
        oneDeck.remove(0);
        return card;
    }
}
*/

package card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Deck {
    private List<Card> oneDeck;
    {
        oneDeck = Arrays.stream(Suit.values()).flatMap(s -> IntStream.rangeClosed(1,13).mapToObj(i -> new Card(s,i))).collect(Collectors.toList());
        Collections.shuffle(oneDeck);
    }

    public Card draw(){
        Card card = oneDeck.get(0);
        oneDeck.remove(0);
        return card;
    }

}