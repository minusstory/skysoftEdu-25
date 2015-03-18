package egovframework.dev.test.utils;

public class PageUtil {
	private int pageNum; //현재 페이지 번호
	private int startRow; //시작글 번호
	private int endRow; //끝글 번호
	private int totalPageCount; //전체 페이지 개수
	private int	startPageNum; //시작페이지 번호
	private int endPageNum; //끝페이지 번호
	private int rowBlockCount; //한페이지 보여줄 글의 갯수
	private int pageBlockCount ; //한페이지 보여줄 페이지의 갯수
	private int totalRowCount; //전체 글의 갯수

	public PageUtil() {

	}

	public PageUtil(int pageNum, int totalRowCount, int rowBlockCount,
			int pageBlockCount) {
		this.pageNum = pageNum;
		this.totalRowCount = totalRowCount;
		this.rowBlockCount = rowBlockCount;
		this.pageBlockCount = pageBlockCount;

		//시작글 번호
		startRow=(pageNum-1)*rowBlockCount+1;

		//끝글 번호
		endRow = pageNum*rowBlockCount;

		//전체페이지 겟수 totalRowCount는  DB에서 검색한다.
		totalPageCount = (int) Math.ceil((totalRowCount)/(double) rowBlockCount);

		//시작페이지 번호
		startPageNum = (pageNum-1)/pageBlockCount * pageBlockCount+1;

		//끝 페이지 번호
		endPageNum = startPageNum + pageBlockCount -1 ;

		if(totalPageCount<endPageNum){
			endPageNum = totalPageCount;
		}
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getStartPageNum() {
		return startPageNum;
	}

	public void setStartPageNum(int startPageNum) {
		this.startPageNum = startPageNum;
	}

	public int getEndPageNum() {
		return endPageNum;
	}

	public void setEndPageNum(int endPageNum) {
		this.endPageNum = endPageNum;
	}

	public int getRowBlockCount() {
		return rowBlockCount;
	}

	public void setRowBlockCount(int rowBlockCount) {
		this.rowBlockCount = rowBlockCount;
	}

	public int getPageBlockCount() {
		return pageBlockCount;
	}

	public void setPageBlockCount(int pageBlockCount) {
		this.pageBlockCount = pageBlockCount;
	}

	public int getTotalRowCount() {
		return totalRowCount;
	}

	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
	}
}
