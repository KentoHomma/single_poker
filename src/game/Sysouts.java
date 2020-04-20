package game;

import java.util.Scanner;

public class Sysouts {


	public void gameDescription() {
		stopAtEachSection();
		System.out.println();
		System.out.println("===============================================");
		System.out.println("                 ゲーム説明");
		System.out.println("===============================================");
		System.out.println();

		System.out.println("これからシングルポーカーを行います。");
		System.out.println("シングルポーカーは、手札二枚のうち一枚を選択し、");
		System.out.println("その一枚をもとにお互いの所持金を賭け、");
		System.out.println("片方の所持金が無くなるまで奪い合うゲームです。");
		System.out.println();
	}

	public void gameStart() {
		stopAtEachSection();
		System.out.println();
		System.out.println("==============================================");
		System.out.println("                 ゲーム開始");
		System.out.println("==============================================");
		System.out.println();
	}

	public void chooseBattleCard() {
		stopAtEachSection();
		System.out.println();
		System.out.println("==============================================");
		System.out.println("                 勝負手選択");
		System.out.println("==============================================");
		System.out.println();
	}

	public void chooseAction() {
		stopAtEachSection();
		System.out.println();
		System.out.println("==============================================");
		System.out.println("                アクション選択");
		System.out.println("==============================================");
		System.out.println();
	}

	public void winOrLose() {
		stopAtEachSection();
		System.out.println("==============================================");
		System.out.println("                 勝敗判定");
		System.out.println("==============================================");
		System.out.println();
	}

	public void drawNewCard() {
		stopAtEachSection();
		System.out.println();
		System.out.println("==============================================");
		System.out.println("                 カード補充");
		System.out.println("==============================================");
		System.out.println();
	}

	public void gameEnd() {
		stopAtEachSection();
		System.out.println();
		System.out.println("==============================================");
		System.out.println("                 ゲーム終了");
		System.out.println("==============================================");
		System.out.println();
	}

	public void heroWin() {
		System.out.println("☆☆☆☆☆☆☆☆heroの勝ちです☆☆☆☆☆☆☆☆");
	}

	public void villainWin() {
		System.out.println("★★★★★★★villainの勝ちです★★★★★★★");

	}

	public void even() {
		System.out.println("◇◇◇◇◇◇◇◇◇引き分けです◇◇◇◇◇◇◇◇◇");
	}

	public void zawazawa() {
		System.err.println("ざわ・・・　　　　　");
		System.err.println("　　　　　　　　　　　　　ざわ・・・");
		System.err.println("　　　　　ざわ・・・");
		System.out.println();
	}

	public void stopAtEachSection() {
		Scanner scan = new Scanner(System.in);
		String stopper = scan.nextLine();
		while(true) {
		if(stopper.equals("") || stopper.matches(".*")) {
			break;
		}
		}
}
}
