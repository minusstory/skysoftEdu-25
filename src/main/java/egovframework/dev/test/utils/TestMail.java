package egovframework.dev.test.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class TestMail {
	private String to_address; // 수신자
	private String contents; //이메일 내용

	public TestMail() {
		super();
	}

	//name 이름 , pw 패스워드 , id 이름
	public void setForm(String to_address,  String id,  String pw, String name){
		this.to_address = to_address;
		this.contents = name + "님 문의하신 "+ "아이디 : " +id + "와 비밀번호 : " + pw + "입니다. ";
	}

	public void sendMail() throws Exception {
		String mshSubj = "비밀번호 찾기관련 문의메일입니다 "; // 메일제목

		Properties p = System.getProperties();
		p.put("mail.smtp.starttls.enable", "true"); // gmail은 무조건 true 고정
		p.put("mail.smtp.host", "smtp.gmail.com"); // smtp 서버 주소
		p.put("mail.smtp.auth", "true"); // gmail은 무조건 true 고정
		p.put("mail.smtp.port", "587"); // gmail 포트

		Authenticator auth = new MyAuthentication();


		Session session = Session.getDefaultInstance(p, auth);
		MimeMessage msg = new MimeMessage(session);

		try {
			msg.setSentDate(new Date()); // 보낸시간

			//이메일 발신자
			InternetAddress from = new InternetAddress("jhw0110<jhw0110@gmail.com>");
			msg.setFrom(from);

			//이메일 수신자
			InternetAddress to = new InternetAddress(to_address);
			msg.setRecipient(Message.RecipientType.TO, to);

			//이메일 제목
			msg.setSubject(mshSubj,"UTF-8");

			//이메일 내용
			msg.setText(contents,"UTF-8");

			//이메일 헤더
			//msg.setHeader("content-Type","text-html");

			Transport.send(msg);

			System.out.println("Mail send success");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	class MyAuthentication extends Authenticator {

		PasswordAuthentication pa;

		public MyAuthentication() {
			String id = "jhw0110@gmail.com"; // 구글 ID fullpath
			String pw = "a641800a"; // 구글 비밀번호
			// ID와 비밀번호를 입력한다.
			pa = new PasswordAuthentication(id, pw);
		}

		// 시스템에서 사용하는 인증정보
		public PasswordAuthentication getPasswordAuthentication() {
			return pa;
		}
	}
}

