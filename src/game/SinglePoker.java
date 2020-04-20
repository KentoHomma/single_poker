package game;

import java.util.Random;

import card.Card;
import card.Deck;
import player.AbstractPlayer;
import player.Hero;
import player.Villain;

public class SinglePoker {

	//ゲームの段階ごとにメッセージを表示するクラス
	Sysouts sysoutMassage = new Sysouts();

	public void game() {

		//ゲーム開始＆説明メッセージの表示
		sysoutMassage.gameDescription();
		sysoutMassage.gameStart();

		//52枚1組のカードデッキを作成する
		Deck deck = new Deck();

		//初期金は引数として受け取り初期化
		AbstractPlayer hero;
		AbstractPlayer villain;

		//Hero,Villainのインスタンスを生成
		hero = new Hero("hero", 10, 1);
		villain = new Villain("villain", 10, 1);

		//初期手札
		hero.initCardList(deck);
		villain.initCardList(deck);


		//どちらかの所持金がなくなるまで(途中に判定あり)繰り返す
		while (true) {

			//先行を決めるランダム
			Random rand = new Random();
			int firstAction = rand.nextInt(2);

			switch (firstAction) {
			case 0:

				//勝負手を選択&変数に代入
				sysoutMassage.chooseBattleCard();
				Card heroBattleCard = hero.chooseBattleCard(deck, false);
				Card villainBattleCard = villain.chooseBattleCard(deck, true);

				//勝負手の強さを変数に入れる
				int heroStrength = heroBattleCard.getPoint();
				int villainStrength = villainBattleCard.getPoint();

				//abstractの中で定義したアクション(fold,call,raise入力形式で分岐)
				//foldしたときに即座に勝敗判定に飛ぶ
				sysoutMassage.chooseAction();
				String heroFoldDone1 = null;
				String villainFoldDone1 = null;
				while (true) {
					System.out.println(" ―――――所持金・賭金情報――――――――");
					System.out.println("|☆Heroの所持金 　：" + hero.getGameMoney() + "　Heroの賭金　 ：" + hero.getBetAmount()+"　 |");
					System.out.println("|★villainの所持金：" + villain.getGameMoney() + "　villainの賭金：" + villain.getBetAmount()+"　 |");
					System.out.println(" ―――――――――――――――――――――");
					heroFoldDone1 = hero.action(villain);
					if (heroFoldDone1.equals("foldDone")) {
						break;
					}
					if (hero.getBetAmount() == villain.getBetAmount()
							&& (hero.getBetAmount() >= 2 && villain.getBetAmount() >= 2)) {
						break;
					}
					System.out.println(" ―――――所持金・賭金情報――――――――");
					System.out.println("|☆Heroの所持金 　：" + hero.getGameMoney() + "　Heroの賭金　 ：" + hero.getBetAmount()+"　 |");
					System.out.println("|★villainの所持金：" + villain.getGameMoney() + "　villainの賭金：" + villain.getBetAmount()+"　 |");
					System.out.println(" ―――――――――――――――――――――");
					villainFoldDone1 = villain.action(hero);
					if (villainFoldDone1.equals("foldDone")) {
						break;
					}

					if (hero.getBetAmount() == villain.getBetAmount()) {
						break;
					}
				}

				//勝敗判定
				boolean heroWin = false;
				boolean villainWin = false;
				boolean evenFlg = false;
				sysoutMassage.winOrLose();
				if (heroFoldDone1.equals("foldDone")) {
					sysoutMassage.villainWin();
					villainWin = true;
				} else if (villainFoldDone1.equals("foldDone")) {
					sysoutMassage.heroWin();
					heroWin = true;
				} else if (heroStrength == 2 && villainStrength == 14) {
					sysoutMassage.heroWin();
					heroWin = true;
				} else if (villainStrength == 2 && heroStrength == 14) {
					sysoutMassage.villainWin();
					villainWin = true;
				} else if (heroStrength > villainStrength) {
					sysoutMassage.heroWin();
					heroWin = true;
				} else if (villainStrength > heroStrength) {
					sysoutMassage.villainWin();
					villainWin = true;
				} else {
					sysoutMassage.even();
					evenFlg = true;
				}
				System.out.println();
				System.out.println("　　　　　❖各人の勝負手❖");
				System.out.println("heroの勝負手 ⇒ " + heroBattleCard + "　　villainの勝負手⇒ " + villainBattleCard);
				System.out.println();

				//金回りの処理(所持金は受け渡してbetは1にリセット)
				if (evenFlg) {
					hero.setGameMoney(hero.getGameMoney());
					villain.setGameMoney(villain.getGameMoney());

					hero.setBetAmount(1);
					villain.setBetAmount(1);
				}

				else if (heroWin) {
					hero.setGameMoney(hero.getGameMoney() + villain.getBetAmount());
					villain.setGameMoney(villain.getGameMoney() - villain.getBetAmount());

					villain.setBetAmount(1);
					hero.setBetAmount(1);
				}

				else if (villainWin) {
					hero.setGameMoney(hero.getGameMoney() - hero.getBetAmount());
					villain.setGameMoney(villain.getGameMoney() + hero.getBetAmount());

					hero.setBetAmount(1);
					villain.setBetAmount(1);

				}
				System.out.println("　　　　　❖各人の現在の所持金❖");
				System.out.println("heroの所持金 ⇒ " + hero.getGameMoney() + "　　villainの所持金⇒ " + villain.getGameMoney());

				//所持金が尽きたらここでゲームを終了させる
				if (villain.getGameMoney() <= 0) {
					sysoutMassage.gameEnd();
					System.out.println("heroの勝ちです！　ゲームを終了します。");
					System.exit(0);
				} else if (hero.getGameMoney() <= 0) {
					sysoutMassage.gameEnd();
					System.out.println("villainの勝ちです！　ゲームを終了します。");
					System.exit(0);
				}

				//勝負に使った一枚を廃棄する
				hero.getCardList().remove(heroBattleCard);
				villain.getCardList().remove(villainBattleCard);

				//廃棄した分をそれぞれ一枚補充
				//もしデッキのカードが切れたらその時点で勝敗判定
				sysoutMassage.drawNewCard();
				try {
					villain.draw(deck, true);
					hero.draw(deck);
				} catch (Exception e) {
					sysoutMassage.gameEnd();
					System.out.println("デッキが切れました。現在の所持金で勝敗を判定します。");
					System.out.println("heroの所持金：" + hero.getGameMoney() + "　　villainの所持金：" + villain.getGameMoney());
					if (villain.getGameMoney() < hero.getGameMoney()) {
						System.out.println("heroの勝ちです！　ゲームを終了します。");
						System.exit(0);
					} else if (hero.getGameMoney() < villain.getGameMoney()) {
						System.out.println("villainの勝ちです！　ゲームを終了します。");
						System.exit(0);
					} else {
						System.out.println("引き分けです！　ゲームを終了します。");
						System.exit(0);
					}
				}

				break;








			case 1:
				//abstractの中で定義したメソッドで勝負手を選択
				sysoutMassage.chooseBattleCard();
				Card villainBattleCard1 = villain.chooseBattleCard(deck, true);
				Card heroBattleCard1 = hero.chooseBattleCard(deck, false);

				//勝負手の強さを変数に入れる
				int heroStrength1 = heroBattleCard1.getPoint();
				int villainStrength1 = villainBattleCard1.getPoint();

				//abstractの中で定義したアクション(fold,call,raiseを入力形式で分岐)
				sysoutMassage.chooseAction();
				String villainFoldDone2 = null;
				String heroFoldDone2 = null;
				while (true) {
					System.out.println(" ―――――所持金・賭金情報――――――――");
					System.out.println("|☆Heroの所持金 　：" + hero.getGameMoney() + "　Heroの賭金　 ：" + hero.getBetAmount()+"　 |");
					System.out.println("|★villainの所持金：" + villain.getGameMoney() + "　villainの賭金：" + villain.getBetAmount()+"　 |");
					System.out.println(" ―――――――――――――――――――――");
					villainFoldDone2 = villain.action(hero);
					if (villainFoldDone2.equals("foldDone")) {
						break;
					}
					if (hero.getBetAmount() == villain.getBetAmount()
							&& (hero.getBetAmount() >= 2 && villain.getBetAmount() >= 2)) {
						break;
					}
					System.out.println(" ―――――所持金・賭金情報――――――――");
					System.out.println("|☆Heroの所持金 　：" + hero.getGameMoney() + "　Heroの賭金　 ：" + hero.getBetAmount()+"　 |");
					System.out.println("|★villainの所持金：" + villain.getGameMoney() + "　villainの賭金：" + villain.getBetAmount()+"　 |");
					System.out.println(" ―――――――――――――――――――――");
					heroFoldDone2 = hero.action(villain);
					if (heroFoldDone2.equals("foldDone")) {
						break;
					}
					if (hero.getBetAmount() == villain.getBetAmount()) {
						break;
					}
				}

				//勝敗判定
				sysoutMassage.winOrLose();
				boolean heroWin1 = false;
				boolean villainWin1 = false;
				boolean evenFlg1 = false;
				if (villainFoldDone2.equals("foldDone")) {
					sysoutMassage.heroWin();
					heroWin1 = true;
				} else if (heroFoldDone2.equals("foldDone")) {
					sysoutMassage.villainWin();
					villainWin1 = true;
				} else if (heroStrength1 == 2 && villainStrength1 == 14) {
					sysoutMassage.heroWin();
					heroWin1 = true;
				} else if (villainStrength1 == 2 && heroStrength1 == 14) {
					sysoutMassage.villainWin();
					villainWin1 = true;
				} else if (heroStrength1 > villainStrength1) {
					sysoutMassage.heroWin();
					heroWin1 = true;
				} else if (villainStrength1 > heroStrength1) {
					sysoutMassage.villainWin();
					villainWin1 = true;
				} else {
					sysoutMassage.even();
					evenFlg1 = true;
				}
				System.out.println();
				System.out.println("　　　　　❖各人の勝負手❖");
				System.out.println("heroの勝負手 ⇒ " + heroBattleCard1 + "　　villainの勝負手⇒ " + villainBattleCard1);
				System.out.println();

				//金回りの処理
				//金回りの処理(所持金は受け渡してbetは1にリセット)
				if (heroWin1) {
					hero.setGameMoney(hero.getGameMoney() + villain.getBetAmount());
					villain.setGameMoney(villain.getGameMoney() - villain.getBetAmount());

					villain.setBetAmount(1);
					hero.setBetAmount(1);
				}

				else if (villainWin1) {
					hero.setGameMoney(hero.getGameMoney() - hero.getBetAmount());
					villain.setGameMoney(villain.getGameMoney() + hero.getBetAmount());

					hero.setBetAmount(1);
					villain.setBetAmount(1);

				}

				else if (evenFlg1) {
					hero.setGameMoney(hero.getGameMoney());
					villain.setGameMoney(villain.getGameMoney());

					hero.setBetAmount(1);
					villain.setBetAmount(1);
				}
				System.out.println("　　　　　❖各人の現在の所持金❖");
				System.out.println("heroの所持金 ⇒ " + hero.getGameMoney() + "　　villainの所持金⇒ " + villain.getGameMoney());

				//所持金が尽きたらここでゲームを終了させる
				if (villain.getGameMoney() <= 0) {
					sysoutMassage.gameEnd();
					System.out.println("heroの勝ちです！　ゲームを終了します。");
					System.exit(0);
				} else if (hero.getGameMoney() <= 0) {
					sysoutMassage.gameEnd();
					System.out.println("villainの勝ちです！　ゲームを終了します。");
					System.exit(0);
				}

				//勝負に使った一枚を捨てる
				villain.getCardList().remove(villainBattleCard1);
				hero.getCardList().remove(heroBattleCard1);

				//勝負が終わったらそれぞれ一枚追加
				//もしデッキのカードが切れたらその時点で勝敗判定
				sysoutMassage.drawNewCard();
				try {
					villain.draw(deck, true);
					hero.draw(deck);
				} catch (Exception e) {

					sysoutMassage.gameEnd();
					System.out.println("デッキが切れました。現在の所持金で勝敗を判定します。");
					System.out.println("heroの所持金：" + hero.getGameMoney() + "　　villainの所持金：" + villain.getGameMoney());
					if (villain.getGameMoney() < hero.getGameMoney()) {
						System.out.println("heroの勝ちです！　ゲームを終了します。");
						System.exit(0);
					} else if (hero.getGameMoney() < villain.getGameMoney()) {
						System.out.println("villainの勝ちです！　ゲームを終了します。");
						System.exit(0);
					} else {
						System.out.println("引き分けです！　ゲームを終了します。");
						System.exit(0);
					}
				}

				break;

			}

		}
	}
}
