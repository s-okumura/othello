/**
 * 
 */
package main;

/**
 * 盤面表示用の定数クラス
 * @author shogo
 *
 */
public class FieldConstants {

	public static final String COLUMN_NAME_FOR_DISPLAY = "　　Ａ　Ｂ　Ｃ　Ｄ　Ｅ　Ｆ　Ｇ　Ｈ";

	public static final String[] ROW_NAME_FOR_DISPLAY = { "　", "１", "２", "３", "４", "５",
			"６", "７", "８" };

	public static final char[] COLUMN_NAMES = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

	public static final char[] ROW_NAMES = {'1', '2', '3', '4', '5', '6', '7', '8'};

	public static final String HORIZONTAL_LINE = "　＋ー＋ー＋ー＋ー＋ー＋ー＋ー＋ー＋";

	public static final String VERTICAL_LINE = "｜";

	public static final String SPACE = "　";

	public static final String FIVE_SPACES = "　　　　　";

	public static final String COLON = "：";

	public static final int MIN_ROW = 1;

	public static final int MAX_ROW = 8;

	public static final int MIN_COLUMN = 1;

	public static final int MAX_COLUMN = 8;

	/**
	 * 無引数コンストラクタ
	 */
	public FieldConstants() {
		super();
	}

}
