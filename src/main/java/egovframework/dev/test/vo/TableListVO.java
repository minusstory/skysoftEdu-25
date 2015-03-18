package egovframework.dev.test.vo;

import java.util.Date;

public class TableListVO {
	private int seq;
	private String title;
	private String contents;
	private Date regdtm;

	public TableListVO() {
		super();
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getRegdtm() {
		return regdtm;
	}
	public void setRegdtm(Date regdtm) {
		this.regdtm = regdtm;
	}

}
