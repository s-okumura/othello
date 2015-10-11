/**
 * 
 */
package main;

/**
 * オセロの石
 * @author shogo
 *
 */
public enum Stone {
	WHITE("白", "○"),
	BLACK("黒", "●");

	private final String name;

	private final String symbol;

	private Stone(final String name, final String symbol) {
		this.name = name;
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public String getSymbol() {
		return symbol;
	}

	public Stone getOtherStone() {
		if (this.equals(WHITE)) {
			return BLACK;
		}
		return WHITE;
	}
}
