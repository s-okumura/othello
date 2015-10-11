/**
 * 
 */
package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * メインクラス
 * @author shogo
 * 
 */
public class Main {

	/**
	 * 現在の石の配置
	 */
	private static Stone[][] currentStones = new Stone[FieldConstants.MAX_ROW + 2][FieldConstants.MAX_COLUMN + 2];

	/**
	 * 黒と白、どちらのターンか (最初は黒)
	 */
	private static Stone whoseTurn = Stone.BLACK;

	/**
	 * メインメソッド
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		initializeStones();
		disp(true);
		for (;;) {
			System.out.println("どこに石を置きますか？　※半角英数で入力してください。例：「3B」");
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			String inputString;
			try {
				inputString = br.readLine();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				inputString = "";
			}
			try {
				int[] coordinates = StringUtil.changeStringToCoordinates(inputString);
			    putStone(coordinates[0], coordinates[1], currentStones);
		        changeTurn();
	            if (!canPutStone(currentStones)) {
                    changeTurn();
                    if (!canPutStone(currentStones)) {
                        displayCurrentField();
                        judgeWinOrLose();
                        break;
                    } else {
                        System.out.println("置く場所がないのでターンを交代します。");
                    }
	            }
			    displayCurrentField();
			} catch (IllegalStateException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * どちらが勝ったか判定します。
	 */
	private static void judgeWinOrLose() {
	    int blackStoneNum = countStones(Stone.BLACK);
	    int whiteStoneNum = countStones(Stone.WHITE);
	    if (blackStoneNum > whiteStoneNum) {
	        System.out.println("黒の勝ち！");
	    } else if (blackStoneNum == whiteStoneNum) {
	        System.out.println("引き分け！");
	    } else {
	        System.out.println("白の勝ち！");
	    }
        
    }

    /**
	 * 次の番のプレーヤが石を置けるか判定する
	 * @return
	 */
	private static boolean canPutStone(Stone[][] stones) {
	    // 現在の石の配置を取得する
        Stone[][] tmpStones = new Stone[stones.length][stones[0].length];
        int i;
        int j;
        for (i = 0; i < stones.length; i++) {
            for (j = 0; j < stones[0].length; j++) {
                tmpStones[i][j] = stones[i][j];
            }
        }
        int k;
        int l;
        for (k = 1; k <= FieldConstants.MAX_ROW; k++) {
            for (l = 1; l <= FieldConstants.MAX_COLUMN; l++) {
                if (isAnyStoneAround(k, l, tmpStones) && putStone(whoseTurn, k, l, tmpStones)) {
                    return true;
                }
            }
        }
		return false;
	}

	/**
	 * 周囲に石があるかどうかを判定する
	 * 石がある：true／石がない：false
	 * @param k 行
	 * @param l 列
	 * @param stones 石の配置
	 * @return
	 */
    private static boolean isAnyStoneAround(int k, int l, Stone[][] stones) {
        if (stones[k - 1][l - 1] != null) {
            return true;
        }
        if (stones[k - 1][l] != null) {
            return true;
        }
        if (stones[k - 1][l + 1] != null) {
            return true;
        }
        if (stones[k][l - 1] != null) {
            return true;
        }
        if (stones[k][l + 1] != null) {
            return true;
        }
        if (stones[k + 1][l - 1] != null) {
            return true;
        }
        if (stones[k + 1][l] != null) {
            return true;
        }
        if (stones[k + 1][l + 1] != null) {
            return true;
        }
        return false;
    }

    /**
	 * 現在のフィールドを表示します
	 */
	private static void displayCurrentField() {
		disp(false);
	}

	/**
	 * 現在のフィールドを表示します
	 * 
	 * @param isInitial 初期状態かどうか
	 */
	private static void disp(boolean isInitial) {
		System.out.println(FieldConstants.COLUMN_NAME_FOR_DISPLAY);
		int i;
		int j;
		for (i = FieldConstants.MIN_ROW; i <= FieldConstants.MAX_ROW; i++) {
			System.out.println(FieldConstants.HORIZONTAL_LINE);
			System.out.print(FieldConstants.ROW_NAME_FOR_DISPLAY[i]);
			for (j = FieldConstants.MIN_COLUMN; j <= FieldConstants.MAX_COLUMN; j++) {
				System.out.print(FieldConstants.VERTICAL_LINE);
				if (currentStones[i][j] != null) {
					System.out.print(currentStones[i][j].getSymbol());
				} else {
					System.out.print(FieldConstants.SPACE);
				}
			}
			System.out.print(FieldConstants.VERTICAL_LINE);
			if (i == 3) {
				System.out.print(FieldConstants.FIVE_SPACES);
				System.out.print(Stone.BLACK.getName());
				System.out.print(FieldConstants.COLON);
				System.out.print(countStones(Stone.BLACK));
			}
			if (i == 4) {
				System.out.print(FieldConstants.FIVE_SPACES);
				System.out.print(Stone.WHITE.getName());
				System.out.print(FieldConstants.COLON);
				System.out.print(countStones(Stone.WHITE));
			}
			if (i == 5) {
				System.out.print("　　　　　");
				String initialOrNext;
				if (isInitial) {
					initialOrNext = "最初は";
				} else {
					initialOrNext = "次は";
				}
				System.out.print(initialOrNext);
				System.out.print(whoseTurn.getName());
				System.out.print("の番です。");
			}
			System.out.println("");
		}
		System.out.println(FieldConstants.HORIZONTAL_LINE);
		System.out.println("");
	}

	/**
	 * 現在フィール上にある石の数を数えます
	 * 
	 * @param stone 数える石の種類
	 * @return 石の数
	 */
	private static int countStones(Stone stone) {
		int counter = 0;
		int i;
		int j;
		for (i = FieldConstants.MIN_ROW; i <= FieldConstants.MAX_ROW; i++) {
			for (j = FieldConstants.MIN_COLUMN; j <= FieldConstants.MAX_COLUMN; j++) {
				if (currentStones[i][j] != null
						&& currentStones[i][j].equals(stone)) {
					counter++;
				}
			}
		}
		return counter;
	}

	/**
	 * 石を初期状態にします
	 */
	private static void initializeStones() {
		int i;
		int j;
		for (i = 1; i <= 8; i++) {
			for (j = 1; j <= 8; j++) {
				currentStones[i][j] = null;
			}
		}
		currentStones[4][4] = Stone.WHITE;
		currentStones[4][5] = Stone.BLACK;
		currentStones[5][4] = Stone.BLACK;
		currentStones[5][5] = Stone.WHITE;
	}

	/**
	 * フィールド上に石を置きます (置く石の種類はどちらのターンかで決まる)
	 * 
	 * @param row 行
	 * @param column 列
	 */
	private static void putStone(int row, int column, Stone[][] stones) throws IllegalStateException {
		if (putStone(whoseTurn, row, column, stones)) {
			stones[row][column] = whoseTurn;
		} else {
			throw new IllegalStateException("そこには置けません。");
		}
	}

	/**
	 * フィールド上に石を置きます
	 * 
	 * @param stone
	 * @param row
	 * @param column
	 * @return true:成功／false:失敗
	 */
	private static boolean putStone(Stone stone, int row, int column, Stone[][] stones) {
		if (stones[row][column] == null) {
			if (turnOver(stone, row, column, stones)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * ターンを交代します (白から黒、または黒から白)
	 */
	private static void changeTurn() {
		whoseTurn = whoseTurn.getOtherStone();
	}

	/**
	 * 石をひっくり返します
	 * 
	 * @param stone
	 * @param row
	 * @param column
	 * @return true:ひっくり返せる／false:ひっくり返せない
	 */
	private static boolean turnOver(Stone stone, int row, int column, Stone[][] stones) {
		return turnStoneByDirection(stone, row, column, stones);
	}

	/**
	 * 特定の方向にある石をひっくり返します
	 * @param stone
	 * @param row
	 * @param column
	 * @return
	 */
	private static boolean turnStoneByDirection(Stone stone, int row, int column, Stone[][] stones) {
		int margin = 0;
		boolean canTurnOverFlg = false;
		for (Direction d : Direction.values()) {
			switch (d) {
			case UPPER:
				margin = row - FieldConstants.MIN_ROW;
				break;
			case UPPERRIGHT:
				margin = Math.min(row - FieldConstants.MIN_ROW,
						FieldConstants.MAX_COLUMN - column);
				break;
			case RIGHT:
				margin = FieldConstants.MAX_COLUMN - column;
				break;
			case LOWERRIGHT:
				margin = Math.min(FieldConstants.MAX_ROW - row,
						FieldConstants.MAX_COLUMN - column);
				break;
			case LOWER:
				margin = FieldConstants.MAX_ROW - row;
				break;
			case LOWERLEFT:
				margin = Math.min(FieldConstants.MAX_ROW - row, column
						- FieldConstants.MIN_ROW);
				break;
			case LEFT:
				margin = column - FieldConstants.MIN_COLUMN;
				break;
			case UPPERLEFT:
				margin = Math.min(row - FieldConstants.MIN_ROW, column
						- FieldConstants.MIN_ROW);
				break;
			default:
				throw new IllegalStateException("向きが不正です。");
			}
			int i;
			int j;
			for (i = 1; i <= margin; i++) {
				Stone targetStone = stones[row + i
						* (d.getRowParameter())][column + i
						* (d.getColumnParameter())];
				if (targetStone == null) {
					break;
				}
				if (i == 1 && targetStone.equals(stone)) {
					break;
				}
				if (i > 1 && targetStone != null && targetStone.equals(stone)) {
					canTurnOverFlg = true;
					for (j = 1; j < i; j++) {
						stones[row + j * (d.getRowParameter())]
								[column + j * (d.getColumnParameter())]
										= stones[row + j * (d.getRowParameter())]
												[column + j * (d.getColumnParameter())].getOtherStone();

					}
					break;
				}
			}
		}
		return canTurnOverFlg;
	}
}
