/*package card;



public class Card {
    int value;
    Suit suite;
    public Card(int value, Suit suite) {
        this.value = value;
        this.suite = suite;
    }
    public int getValue() {
        return value;
    }
    public Suit getSuite() {
        return suite;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public void setSuit(Suit suite) {
        this.suite = suite;
    }
    @Override
    public String toString() {
        return "Card{" +
                "value=" + value +
                ", suite=" + suite +
                '}';
    }
}
*/

package card;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Card {

    private final Suit suit;
    private final int rank;

    private String toDisplayValue() {
        switch (this.rank) {
            case 1:
                return "A";
            case 11:
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
            default:
                return String.valueOf(this.rank);
        }
    }
    public int getPoint() {
    	int rank = this.rank;
    	if(rank==1) {
    		rank=14;
    	}
        return rank;
    }
    @Override
    public String toString() {
        return this.suit.getMark() + this.toDisplayValue();
    }

}