package main;

/**
 * 文字列の共通処理を行うクラス
 * @author shogo
 *
 */
public class StringUtil {

	/**
	 * 文字列を座標に変換します
	 * 
	 * @param str
	 * @return
	 */
	public static int[] changeStringToCoordinates(String str) throws IllegalStateException {
		if (!isValidString(str)) {
			throw new IllegalStateException("入力した文字列が不正です。");
		}
		int[] coordinates = new int[2];
		coordinates[0] = Character.getNumericValue((str.charAt(0)));
		char targetChar = str.charAt(1);
		if (targetChar == 'A') {
			coordinates[1] = 1;
		} else if (targetChar == 'B') {
			coordinates[1] = 2;
		} else if (targetChar == 'C') {
			coordinates[1] = 3;
		} else if (targetChar == 'D') {
			coordinates[1] = 4;
		} else if (targetChar == 'E') {
			coordinates[1] = 5;
		} else if (targetChar == 'F') {
			coordinates[1] = 6;
		} else if (targetChar == 'G') {
			coordinates[1] = 7;
		} else if (targetChar == 'H') {
			coordinates[1] = 8;
		} else {
			throw new IllegalStateException("列名が不正です。");
		}
		return coordinates;
	}

	/**
	 * 入力文字列チェック (座標として適切な文字列かどうか判定する)
	 * 
	 * @param str
	 * @return true:
	 */
	private static boolean isValidString(String str) {
		if (str == null) {
			return false;
		}
		if (str.length() != 2) {
			return false;
		}
		if (!InputStringValidator.isRowName(str.charAt(0))) {
			return false;
		}
		if (!InputStringValidator.isColumnName(str.charAt(1))) {
			return false;
		}
		return true;
	}

	
}
