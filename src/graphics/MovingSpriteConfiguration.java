package graphics;

public class MovingSpriteConfiguration {
	private int rowCount, colCount;
	private int changeTime;

	// Spritesheet Indexes
	private int leftIndex;
	private int rightIndex;
	private int upIndex;
	private int downIndex;

	private int spritesPerAction;

	public MovingSpriteConfiguration(int rowCount, int colCount, int changeTime, int leftIndex, int rightIndex,
			int upIndex, int downIndex, int spritesPerAction) {
		this.setRowCount(rowCount);
		this.setColCount(colCount);
		this.setChangeTime(changeTime);
		this.setLeftIndex(leftIndex);
		this.setRightIndex(rightIndex);
		this.setUpIndex(upIndex);
		this.setDownIndex(downIndex);
		this.setSpritesPerAction(spritesPerAction);
	}

	/**
	 * @return the rowCount
	 */
	public int getRowCount() {
		return rowCount;
	}

	/**
	 * @param rowCount
	 *            the rowCount to set
	 */
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	/**
	 * @return the colCount
	 */
	public int getColCount() {
		return colCount;
	}

	/**
	 * @param colCount
	 *            the colCount to set
	 */
	public void setColCount(int colCount) {
		this.colCount = colCount;
	}

	/**
	 * @return the changeTime
	 */
	public int getChangeTime() {
		return changeTime;
	}

	/**
	 * @param changeTime
	 *            the changeTime to set
	 */
	public void setChangeTime(int changeTime) {
		this.changeTime = changeTime;
	}

	/**
	 * @return the leftIndex
	 */
	public int getLeftIndex() {
		return leftIndex;
	}

	/**
	 * @param leftIndex
	 *            the leftIndex to set
	 */
	public void setLeftIndex(int leftIndex) {
		this.leftIndex = leftIndex;
	}

	/**
	 * @return the rightIndex
	 */
	public int getRightIndex() {
		return rightIndex;
	}

	/**
	 * @param rightIndex
	 *            the rightIndex to set
	 */
	public void setRightIndex(int rightIndex) {
		this.rightIndex = rightIndex;
	}

	/**
	 * @return the upIndex
	 */
	public int getUpIndex() {
		return upIndex;
	}

	/**
	 * @param upIndex
	 *            the upIndex to set
	 */
	public void setUpIndex(int upIndex) {
		this.upIndex = upIndex;
	}

	/**
	 * @return the downIndex
	 */
	public int getDownIndex() {
		return downIndex;
	}

	/**
	 * @param downIndex
	 *            the downIndex to set
	 */
	public void setDownIndex(int downIndex) {
		this.downIndex = downIndex;
	}

	/**
	 * @return the spritesPerAction
	 */
	public int getSpritesPerAction() {
		return spritesPerAction;
	}

	/**
	 * @param spritesPerAction
	 *            the spritesPerAction to set
	 */
	public void setSpritesPerAction(int spritesPerAction) {
		this.spritesPerAction = spritesPerAction;
	}
}