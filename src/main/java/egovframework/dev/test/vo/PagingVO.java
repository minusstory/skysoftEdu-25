package egovframework.dev.test.vo;

public class PagingVO {
	private int startrow;
	private int endrow;
	private int recordCountPerPage;
	private String itemText;
	private String searchItem;

	public String getItemText() {
		return itemText;
	}
	public void setItemText(String itemText) {
		this.itemText = itemText;
	}
	public String getSearchItem() {
		return searchItem;
	}
	public void setSearchItem(String searchItem) {
		this.searchItem = searchItem;
	}
	public int getStartrow() {
		return startrow;
	}
	public void setStartrow(int startrow) {
		this.startrow = startrow;
	}
	public int getEndrow() {
		return endrow;
	}
	public void setEndrow(int endrow) {
		this.endrow = endrow;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

}
