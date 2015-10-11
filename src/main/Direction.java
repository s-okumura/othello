package main;

/**
 * 方向
 * @author shogo
 *
 */
public enum Direction {

	UPPER("上", -1, 0),
	UPPERRIGHT("右上", -1, 1),
	RIGHT("右", 0, 1),
	LOWERRIGHT("右下", 1, 1),
	LOWER("下", 1, 0),
	LOWERLEFT("左下", 1, -1),
	LEFT("左", 0, -1),
	UPPERLEFT("左上", -1, -1);
	
	private String directionName;

	private int rowParameter;

	private int columnParameter;

	private Direction(String name, int rowParam, int columnParam) {
		directionName = name;
		rowParameter = rowParam;
		columnParameter = columnParam;
	}

	/**
	 * @return the directionName
	 */
	public String getDirectionName() {
		return directionName;
	}

	/**
	 * @param directionName the directionName to set
	 */
	public void setDirectionName(String directionName) {
		this.directionName = directionName;
	}

	/**
	 * @return the rowParameter
	 */
	public int getRowParameter() {
		return rowParameter;
	}

	/**
	 * @param rowParameter the rowParameter to set
	 */
	public void setRowParameter(int rowParameter) {
		this.rowParameter = rowParameter;
	}

	/**
	 * @return the columnParameter
	 */
	public int getColumnParameter() {
		return columnParameter;
	}

	/**
	 * @param columnParameter the columnParameter to set
	 */
	public void setColumnParameter(int columnParameter) {
		this.columnParameter = columnParameter;
	}

}
