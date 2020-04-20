package player;

import java.util.Scanner;

import card.Card;
import card.Deck;
import game.Sysouts;

public class Hero extends AbstractPlayer {
	Sysouts sysoutMassage = new Sysouts();

	public Hero(String name, int gameMoney, int betAmount) {
		super(name, gameMoney, betAmount);
	}

	@Override
	public void initCardList(Deck deck) {
		draw(deck);
		draw(deck);
	}

	@Override
	public Card chooseBattleCard(Deck deck, boolean isHidden) {

		Card battleCard = null;

		while (true) {
			System.out.println();
			System.out.println("heroのターンです。勝負手を選んでください。");
			System.out.println(
					"　　⇒　【left： " + getCardList().get(0) + "】" + "　or　" + "【right：　" + getCardList().get(1) + "】");

			Scanner scan = new Scanner(System.in);
			String choose = scan.nextLine();

			if (choose.equals("left")) {
				battleCard = getCardList().get(0);
				/*System.out.println(battleCard.toString() + "で勝負します！");*/
				break;

			} else if (choose.equals("right")) {
				battleCard = getCardList().get(1);
				/*System.out.println(battleCard.toString() + "で勝負します！");*/
				break;
			} else {
				System.err.println("※入力値が不正です。入力し直してください。");
			}

		}

		return battleCard;
	}

	@Override
	public String action(AbstractPlayer ap) {

		while (true) {
			System.out.println();
			System.out.println("heroのアクションを選択してください。");
			System.out.println("⇒ 【f: FOLD】　 【c: CALL】　 【r: RAISE】");
			Scanner scan = new Scanner(System.in);
			String choose = scan.nextLine();

			if (choose.equals("f")) {
				fold();
				return "foldDone";

			} else if (choose.equals("c")) {
				call(ap);
				return "callDone";
			} else if (choose.equals("r")) {
				raise(ap);
				sysoutMassage.zawazawa();
				return "callDone";
			} else {
				System.err.println("※入力値が不正です。入力し直してください。");
			}

		}
	}

	@Override
	public void fold() {
		this.setGameMoney(this.getGameMoney());
		System.out.println("heroのアクション　⇒　FOLD");
		System.out.println();
	}

	@Override
	public void call(AbstractPlayer ap) {
		if (this.getGameMoney() <= ap.getBetAmount()) {
			this.setBetAmount(this.getGameMoney());
		} else if (this.getGameMoney() > ap.getBetAmount()) {
			this.setBetAmount(ap.getBetAmount());
		}
		System.out.println("heroのアクション　⇒　CALL");
		System.out.println();

	}

	@Override
	public void raise(AbstractPlayer ap) {

		System.out.println("⇒賭金をいくらにRAISEしますか？　※半角数字で入力");
		Scanner scan = new Scanner(System.in);
		String raiseAmount = scan.nextLine();
		int raiseAmountInt = Integer.valueOf(raiseAmount);

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
		System.out.println("heroのアクション　⇒　" + "賭金を" + raiseAmountInt + "にレイズ");
		System.out.println();

	}
}