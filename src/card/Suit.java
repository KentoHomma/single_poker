/*
public enum Suit {
    HEART,
    SPADE,
    CLUB,
    DIAMOND
}
*/

package card;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum Suit {
    SPADE("♠"),
    HEART("♥"),
    DIAMOND("♦"),
    CLUB("♣");


    private final String mark;

	public String getMark() {
		return mark;
	}

}
