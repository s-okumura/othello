/**
 * 
 */
package main;

/**
 * 入力された文字の妥当性を判定するクラス
 * @author shogo
 *
 */
public class InputStringValidator {

	/**
	 * 行の名前かどうか判定します
	 * @param character
	 * @return
	 */
	public static boolean isRowName(char character) {
		for (char c : FieldConstants.ROW_NAMES) {
			if (character == c) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 列の名前かどうか判定します
	 * @param character
	 * @return
	 */
	public static boolean isColumnName(char character) {
		for (char c : FieldConstants.COLUMN_NAMES) {
			if (character == c) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 無引数コンストラクタ
	 */
	public InputStringValidator() {
		super();
	}

}
