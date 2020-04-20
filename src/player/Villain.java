package player;

import java.util.Random;

import card.Card;
import card.Deck;

public class Villain extends AbstractPlayer {

	public Villain(String name, int gameMoney, int betAmount) {
		super(name, gameMoney, betAmount);
	}

	@Override
	public void initCardList(Deck deck) {
		draw(deck, true); //表示させないためのboolean、true falseの分岐表示はabsで処理済み
		draw(deck, true); //表示させないためのboolean、true falseの分岐表示はabsで処理済み
	}

	@Override
	public Card chooseBattleCard(Deck deck, boolean isHidden) {

		System.out.println("villainのターンです。以下から勝負手を選びます。");
		Card battleCard = null;
		isHidden = true;

		if (getCardList().get(0).getPoint() <= 7 && getCardList().get(1).getPoint() <= 7) {
			System.out.println("　　　　　　　 (DOWN)　or　(DOWN)");
		} else if (getCardList().get(0).getPoint() >= 8 && getCardList().get(1).getPoint() >= 8) {
			System.out.println("　　　　　　　 (UP)　or　(UP)");
		} else {
			System.out.println("　　　　　　　 (UP)　or　(DOWN)");
		}
		System.out.println();
		Random randChoose = new Random();
		int choose = randChoose.nextInt(2);

		if (choose == 0) {
			battleCard = getCardList().get(0);

		} else if (choose == 1) {
			battleCard = getCardList().get(1);
		}

		return battleCard;

	}

	@Override
	public String action(AbstractPlayer ap) {

		/*テスト用入力形式
		Scanner scan = new Scanner(System.in);
		String choose = scan.nextLine();*/

		Random randAction = new Random();
		int choose = randAction.nextInt(3);

		//両者の賭金が等しいターンではfoldしない。
		if(ap.getBetAmount() == this.getBetAmount()) {
			Random randNoFold = new Random();
			int chooseNoFold = randNoFold.nextInt(2)+1;
			choose = chooseNoFold;
		}

		//相手がオールインしてきたターンでは額でレイズしない
		else if(ap.getGameMoney() == ap.getBetAmount()) {
			Random randNoRaise = new Random();
			int chooseNoRaise = randNoRaise.nextInt(2);
			choose = chooseNoRaise;

		}

		switch (choose) {
		case 0:
			fold();
			return "foldDone";

		case 1:
			call(ap);
			return "callDone";

		case 2:
			raise(ap);
			return "raiseDone";
		}
		return null;
	}

	@Override
	public void fold() {
		this.setGameMoney(this.getGameMoney());
		System.out.println();
		System.out.println("villainのアクション　⇒　fold");
		System.out.println();
	}

	@Override
	public void call(AbstractPlayer ap) {
		if (this.getGameMoney() <= ap.getBetAmount()) {
		 this.setBetAmount(this.getGameMoney());
		}
		else if(this.getGameMoney() > ap.getBetAmount()) {
			this.setBetAmount(ap.getBetAmount());
		}
		 System.out.println();
		System.out.println("villainのアクション　⇒　call");
		System.out.println();

	}

	@Override
	public void raise(AbstractPlayer ap) {

		/*金銭処理テスト用の入力形式
		Scanner scan = new Scanner(System.in);
		String raiseAmount = scan.nextLine();
		int raiseAmountInt = Integer.valueOf(raiseAmount);*/

		Random rand = new Random();
		int raiseAmountInt = rand.nextInt(ap.getBetAmount())+3;


		if (raiseAmountInt <= ap.getBetAmount()) {
			raiseAmountInt = ap.getBetAmount() + 1;
		}

		else if (this.getGameMoney()<ap.getGameMoney()) {
			if (raiseAmountInt > this.getGameMoney()) {
				raiseAmountInt = this.getGameMoney();
			}
		}

		else if (ap.getGameMoney()<this.getGameMoney()) {
			if (raiseAmountInt > ap.getGameMoney()) {
				raiseAmountInt = ap.getGameMoney();
			}
		}

		this.setBetAmount(raiseAmountInt);
		System.out.println();
		System.out.println("villainのアクション　⇒　賭金を" + raiseAmountInt + "にレイズ");
		System.out.println();
	}

}
