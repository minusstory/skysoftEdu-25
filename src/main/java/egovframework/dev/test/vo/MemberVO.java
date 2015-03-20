package egovframework.dev.test.vo;

import org.springframework.web.multipart.MultipartFile;

public class MemberVO {
	private String name;

	private String id;
	private String password;
	private String repassword;

	private String rrnumber; //|DB에 저장한 정보를 읽어오기 위함
	private String tel;
	private String hp;
	private String email;

	private String rrnumber1; // 주민등록번호 입력을 받기 위한 연락처 세분하
	private String rrnumber2;

	private String telFir; //연락처 입력을 위한 연락처 세분화
	private String telSec;
	private String telThr;

	private String hpFir; // 휴대폰번호 입력을 위한 휴대폰번호 세분화
	private String hpSec;
	private String hpThr;

	private String emailFir; // 이메일 입력을 위한 이메일번호 세분화
	private String emailSec;
	private String emailThr;

	private String zipcode;
	private String address;
	private String d_address;
	private String use_yn;

	private String profile;   // 프로파일명
	private MultipartFile profileImg; //업로드한 프로파일 이미지 파일

	private String changePw; //비밀번호 변경 관련 프로퍼티


	public String getChangePw() {
		return changePw;
	}

	public void setChangePw(String changePw) {
		this.changePw = changePw;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public MultipartFile getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(MultipartFile profileImg) {
		this.profileImg = profileImg;
	}



	public String getRrnumber() {
		return rrnumber;
	}

	public void setRrnumber(String rrnumber) {
		this.rrnumber = rrnumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRrnumber1() {
		return rrnumber1;
	}
	public void setRrnumber1(String rrnumber1) {
		this.rrnumber1 = rrnumber1;
	}
	public String getRrnumber2() {
		return rrnumber2;
	}
	public void setRrnumber2(String rrnumber2) {
		this.rrnumber2 = rrnumber2;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	public String getTelFir() {
		return telFir;
	}
	public void setTelFir(String telFir) {
		this.telFir = telFir;
	}
	public String getTelSec() {
		return telSec;
	}
	public void setTelSec(String telSec) {
		this.telSec = telSec;
	}
	public String getTelThr() {
		return telThr;
	}
	public void setTelThr(String telThr) {
		this.telThr = telThr;
	}
	public String getHpFir() {
		return hpFir;
	}
	public void setHpFir(String hpFir) {
		this.hpFir = hpFir;
	}
	public String getHpSec() {
		return hpSec;
	}
	public void setHpSec(String hpSec) {
		this.hpSec = hpSec;
	}
	public String getHpThr() {
		return hpThr;
	}
	public void setHpThr(String hpThr) {
		this.hpThr = hpThr;
	}
	public String getEmailFir() {
		return emailFir;
	}
	public void setEmailFir(String emailFir) {
		this.emailFir = emailFir;
	}
	public String getEmailSec() {
		return emailSec;
	}
	public void setEmailSec(String emailSec) {
		this.emailSec = emailSec;
	}
	public String getEmailThr() {
		return emailThr;
	}
	public void setEmailThr(String emailThr) {
		this.emailThr = emailThr;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getD_address() {
		return d_address;
	}
	public void setD_address(String d_address) {
		this.d_address = d_address;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return  "name : " + name+"/ id : "+id+"/ password : "+  password+"/ rnnumber" + rrnumber+"/ hp : "+  hp+"\n/ email : "+  email+"/"+
					rrnumber1+"/"+  rrnumber2+"/"+  telFir+"/"+	  telSec+"/"+	  telThr+"/"+	  hpFir+"/"+	  hpSec+"/"+
					hpThr+"/"+	 emailFir+"/"+	  emailSec+"/"+  emailThr+"/"+	  zipcode+"/"+	  address+"/"+
					d_address;
	}
}
