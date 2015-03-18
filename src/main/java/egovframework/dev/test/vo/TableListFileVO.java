package egovframework.dev.test.vo;

import java.util.Date;

public class TableListFileVO {
	private int orgseq;
	private String useyn;
	private String savefilenm;
	private String realfilenm;
	private Date regdtm;

	public int getOrgseq() {
		return orgseq;
	}
	public void setOrgseq(int orgseq) {
		this.orgseq = orgseq;
	}

	public String getUseyn() {
		return useyn;
	}
	public void setUseyn(String useyn) {
		this.useyn = useyn;
	}
	public String getSavefilenm() {
		return savefilenm;
	}
	public void setSavefilenm(String savefilenm) {
		this.savefilenm = savefilenm;
	}
	public String getRealfilenm() {
		return realfilenm;
	}
	public void setRealfilenm(String realfilenm) {
		this.realfilenm = realfilenm;
	}
	public Date getRegdtm() {
		return regdtm;
	}
	public void setRegdtm(Date regdtm) {
		this.regdtm = regdtm;
	}


}