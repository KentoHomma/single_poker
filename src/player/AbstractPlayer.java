package player;

import java.util.ArrayList;
import java.util.List;

import card.Card;
import card.Deck;

public abstract class AbstractPlayer {

	private String name;
	private int gameMoney;
	private List<Card> cardList = new ArrayList<>();
	private int betAmount;

	public AbstractPlayer(String name, int gameMoney, int betAmount) {
		this.name = name;
		this.gameMoney = gameMoney;
		this.betAmount = betAmount;
	}

	public String getName() {
		return name;
	}

	public int getGameMoney() {
		return gameMoney;
	}

	public void setGameMoney(int firstMoney) {
		this.gameMoney = firstMoney;
	}

	public List<Card> getCardList() {
		return cardList;
	}

	public int getBetAmount() {
		return betAmount;
	}

	public void setBetAmount(int betAmount) {
		this.betAmount = betAmount;
	}

	//下部に定義あり。引いたカードを、heroのは見えてvillainのは見えない感じで表示。
	public void draw(Deck deck) {
		draw(deck, false);
	}

	//hero,villainごとに勝負手を決める抽象メソッド
	public abstract Card chooseBattleCard(Deck deck, boolean isHidden);

	//actionはhero,villainそれぞれでオーバーライドして定義
	public abstract String action(AbstractPlayer ap);

	public abstract void fold();

	public abstract void call(AbstractPlayer ap);

	public abstract void raise(AbstractPlayer ap);

	//public abstract int getBetAmount();

	//山札からカードを引く
	public void draw(Deck deck, boolean isHidden) {
		Card card = deck.draw();
		cardList.add(card);
		if (isHidden) {
			if (card.getPoint() >= 8) {
				System.out.println(this.name + "が引いたカード⇒　(UP)");
			} else if (card.getPoint() <= 7) {
				System.out.println(this.name + "が引いたカード⇒　(DOWN)");
			}
		} else if (!isHidden) {
			System.out.println(this.name + "が引いたカード 　⇒　" + card.toString());
		}
	}

	//hero,villainごとに初期手札を設定するための抽象メソッド
	public abstract void initCardList(Deck deck);

}
