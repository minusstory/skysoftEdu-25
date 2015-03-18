package egovframework.dev.test.vo;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class TableListVO extends PagingVO {
	private int seq;
	private String title;
	private String contents;
	private Date regdtm;
	private String realfilenm;
	private String savefilenm;
	private MultipartFile attachFile;

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
	public String getRealfilenm() {
		return realfilenm;
	}
	public void setRealfilenm(String realfilenm) {
		this.realfilenm = realfilenm;
	}
	public MultipartFile getAttachFile() {
		return attachFile;
	}
	public void setAttachFile(MultipartFile attachFile) {
		this.attachFile = attachFile;
	}
	public String getSavefilenm() {
		return savefilenm;
	}
	public void setSavefilenm(String savefilenm) {
		this.savefilenm = savefilenm;
	}

}
