package egovframework.dev.test.controller;

import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import egovframework.dev.test.service.LoginService;
import egovframework.dev.test.vo.MemberVO;
import egovframework.dev.test.vo.SessionVO;
import egovframework.framework.annotation.PageTitle;
import egovframework.rte.fdl.property.EgovPropertyService;


@Controller
public class LoginController {

	/** Log Info */
	protected Log log = LogFactory.getLog(this.getClass());

	@Resource(name = "fileuploadPropService")
	private EgovPropertyService fileuploadPropService;

	@Resource(name = "loginService")
	private LoginService loginService;

	@PageTitle("로그인")
	@RequestMapping("/login.do")
	public ModelAndView login(HttpSession session , @ModelAttribute("MemberVO") MemberVO memberVO, ModelMap model) throws Exception {

		List<MemberVO> list = loginService.getMember(memberVO);
		if (list.size() == 0) // 아이디가 없거나 비밀번호가 틀리거나 아이디가 틀릴 경우
		{
			model.addAttribute("message","아이디와 비밀번호를 잘못 입력하셨습니다");
			return new ModelAndView("forward:/index.do");
		} else {
			memberVO = list.get(0);

			tokenizeMemInfo(memberVO); //데이터 세분화 함수 호출

			model.addAttribute("memberVO", memberVO);

			//세션 VO 생성
			SessionVO sessionVO = new SessionVO();
			sessionVO.setMemberId(memberVO.getId());
			sessionVO.setMemberName(memberVO.getName());

			//세션에 맵핑
			session.setAttribute("sessionVO", sessionVO);
			return new ModelAndView("/index");
		}
	}

	//데이터 세분화 함수 호출
	protected static void tokenizeMemInfo(MemberVO memberVO){
		String rrnumber = memberVO.getRrnumber();
		String tel = memberVO.getTel();
		String hp = memberVO.getHp();
		String email = memberVO.getEmail();

		StringTokenizer stRrnumber = new StringTokenizer(rrnumber, "-");
		memberVO.setRrnumber1(stRrnumber.nextToken());
		memberVO.setRrnumber2(stRrnumber.nextToken());

		System.out.println("tel : " + tel);
		if(tel!=null){
			StringTokenizer stTel = new StringTokenizer(tel, "-");
			memberVO.setTelFir(stTel.nextToken());
			memberVO.setTelSec(stTel.nextToken());
			memberVO.setTelThr(stTel.nextToken());
		}

		System.out.println("hp : " + hp);
		if(hp!=null){
			StringTokenizer stHp = new StringTokenizer(hp, "-");
			memberVO.setHpFir(stHp.nextToken());
			memberVO.setHpSec(stHp.nextToken());
			memberVO.setHpThr(stHp.nextToken());
		}

		StringTokenizer stEmail = new StringTokenizer(email, "@");
		memberVO.setEmailFir(stEmail.nextToken());
		memberVO.setEmailSec(stEmail.nextToken());
	}


	@PageTitle("로그아웃")
	@RequestMapping("/logout.do")
	public String logout(HttpSession session){
		session.invalidate();
		return "/index";
	}

}
