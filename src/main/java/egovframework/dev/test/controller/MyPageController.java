package egovframework.dev.test.controller;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import egovframework.dev.test.service.LoginService;
import egovframework.dev.test.service.MyPageService;
import egovframework.dev.test.vo.MemberVO;
import egovframework.dev.test.vo.SessionVO;
import egovframework.framework.annotation.PageTitle;
import egovframework.rte.fdl.property.EgovPropertyService;

@Controller
public class MyPageController {

	/** Log Info */
	protected Log log = LogFactory.getLog(this.getClass());

	@Resource(name = "fileuploadPropService")
	private EgovPropertyService fileuploadPropService;

	@Resource(name="myPageService")
	private MyPageService myPageService;

	@Resource(name="loginService")
	private LoginService loginService;

	// jsp / js 쪽 예외처리 좀더 해야함

	@PageTitle("회원정보수정 버튼 클릭")
	@RequestMapping("/mypage/memInfo.do")
	public ModelAndView memInfo(HttpSession session , ModelMap model) throws Exception{
		MemberVO memberVO = new MemberVO();

		memberVO = changeSetting(session,memberVO); // 수정하기 위해 정보를 가져오는 함수

		model.addAttribute("memberVO",memberVO);
		return new ModelAndView("/mypage/memInfo");
	}


	@PageTitle("정보수정")
	@RequestMapping("/mypage/updateInfo.do")
	public ModelAndView updateInfo(@RequestParam(value="updateYN")String updateYN,  @ModelAttribute("MemberVO") MemberVO memberVO, ModelMap model) throws Exception {

		//파일명은 아이디+프로필명
		if(updateYN.equals("Y")){ //기존 프로파일 이미지가 있는 경우
			String storedPath = fileuploadPropService.getString("fileupload.global.profile-image");//저장경로

			File profileImg = new File(storedPath , memberVO.getId()+memberVO.getProfile());

			MultipartFile file = memberVO.getProfileImg();
			profileImg.delete(); //파일 삭제

			memberVO.setProfile(file.getOriginalFilename());
			File newImg = new File(storedPath , memberVO.getId()+memberVO.getProfile());

			if(!file.isEmpty())
			{
				file.transferTo(newImg); //파일 쓰기
			}
		}
		else{
			if(memberVO.getProfile().equals("")){
				memberVO.setProfile(null);
			}
		}
		myPageService.updateInfo(memberVO);

		// 변경완료 메시지 추가 message 로
		model.addAttribute("memberVO", memberVO);
		model.addAttribute("message", "수정을 완료 하였습니다");
		return new ModelAndView("/mypage/memInfo");
	}

	@PageTitle("비밀번호변경 버튼 클릭")
	@RequestMapping("/mypage/memPwdChange.do")
	public ModelAndView memPwdChange(HttpSession session , ModelMap model)throws Exception {
		SessionVO sessionVO = (SessionVO)session.getAttribute("sessionVO");

		String id = sessionVO.getMemberId();
		System.out.println("id : " + id);
		model.addAttribute("id",id);
		return new ModelAndView("/mypage/memPwdChange");
	}

	//회원정보 , 비밀번호 변경 등 정보수정을 하기 위하여 회원정보 추출 메소드
	private MemberVO changeSetting(HttpSession session, MemberVO vo) throws Exception{
		SessionVO sessionVO = (SessionVO) session.getAttribute("sessionVO");
		System.out.println("sessionVO.id "  + sessionVO.getMemberId());
		String id = sessionVO.getMemberId(); //세션에서 아이디 추출
		vo.setId(id);
		List<MemberVO> list = loginService.getMember(vo);
		vo = list.get(0);
		LoginController.tokenizeMemInfo(vo);
		return vo;
	}

	@PageTitle("비밀번호변경")
	@RequestMapping("/mypage/changePW.do")
	public ModelAndView changePw(HttpSession session ,ModelMap model , @ModelAttribute("MemberVO") MemberVO memberVO) throws Exception{
		MemberVO tmVO = new MemberVO();
		tmVO = changeSetting(session, tmVO);
		if(tmVO.getPassword().equals(memberVO.getPassword())){ // 기존비밀번호가 같을 경우
			tmVO.setPassword(memberVO.getChangePw()); //비밀번호 변경
			myPageService.updateInfo(tmVO); // 비밀번호 변경
			return new ModelAndView("forward:/logout.do");
		}
		else{
			model.addAttribute("message","비밀번호가 부정확하여 변경 실패하였습니다.");
			model.addAttribute("id",tmVO.getId());
			return new ModelAndView("/mypage/memPwdChange");
		}
	}


}
