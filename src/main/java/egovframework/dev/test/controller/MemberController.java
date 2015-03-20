package egovframework.dev.test.controller;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import egovframework.dev.test.service.MemberService;
import egovframework.dev.test.utils.TestMail;
import egovframework.dev.test.vo.MemberVO;
import egovframework.framework.annotation.PageTitle;
import egovframework.rte.fdl.property.EgovPropertyService;

@Controller
public class MemberController {

	/** Log Info */
	protected Log log = LogFactory.getLog(this.getClass());

	@Resource(name = "fileuploadPropService")
	private EgovPropertyService fileuploadPropService;

	@Resource(name = "memberService")
	private MemberService memberService;

	@RequestMapping("/member/beforeMemChk.do")
	public ModelAndView beforeMemCheck(){
		return new ModelAndView("/member/memChk");
	}

	@PageTitle("회원가입 여부")
	@RequestMapping("/member/memChk.do")
	public ModelAndView memCheck(@ModelAttribute("MemberVO")MemberVO memberVO, ModelMap model) throws Exception{
		// Y N Null
		List<MemberVO> list = memberService.isMemberCheck(memberVO);

		if(list.isEmpty())
			memberVO.setUse_yn("N");
		else{
			memberVO = list.get(0);
		}
		model.addAttribute("memberVO",memberVO);
		return new ModelAndView("/member/memChk");
	}

	@RequestMapping("/member/beforeMemJoin.do")
	public ModelAndView beforeMemJoin(@ModelAttribute("MemberVO")MemberVO memberVO  ,ModelMap model){

		System.out.println("memberVO name : " + memberVO.getName());
		System.out.println("memberVO.rrnumber : " + memberVO.getRrnumber1());
		model.addAttribute("memberVO",memberVO);
		return new ModelAndView("/member/memJoin");
	}

	@RequestMapping("/member/idCheck.do")
	public ModelAndView idCheck(@ModelAttribute("MemberVO") MemberVO memberVO , ModelMap model) throws Exception{
		int result = memberService.searchMemberById(memberVO);
		String idCheckResult ;
		if(result == 1 )
			idCheckResult="Y"; //중복이면 Y
		else
			idCheckResult="N"; //중복이 아니면 N


		model.addAttribute("memberVO",memberVO);
		model.addAttribute("idCheckResult",idCheckResult);

		return new ModelAndView("/member/memJoin");
	}

	@PageTitle("회원가입")
	@RequestMapping("/member/memJoin.do")
	public ModelAndView memJoin(@RequestParam("registProfileYN")String registProfileYN , @ModelAttribute("MemberVO")MemberVO memberVO, ModelMap model) throws Exception{
		if(registProfileYN.equals("Y")){ // 프로파일 등록
			System.out.println("file name : " +memberVO.getProfileImg().getOriginalFilename());
			String storedPath = fileuploadPropService.getString("fileupload.global.profile-image");//저장경로

			MultipartFile file = memberVO.getProfileImg();

			String filename = file.getOriginalFilename();
			File targetFile = new File(storedPath, memberVO.getId()+filename);
			file.transferTo(targetFile); //파일 쓰기
			memberVO.setProfile(filename);
		}
		memberService.addMember(memberVO);
		return new ModelAndView("redirect:/index.do");
	}

	@PageTitle("ID/PW찾기 버튼 클릭")
	@RequestMapping("/member/memFindID.do")
	public String memFindId() throws Exception{
		return "/member/memFindID";
	}

	@PageTitle("ID찾기")
	@RequestMapping("/member/memFindIDResult.do")
	public ModelAndView memFindIdResult(@ModelAttribute("memberVO")MemberVO memberVO, ModelMap model) throws Exception{
		List<MemberVO> list = memberService.isMemberCheck(memberVO);

		if(list.isEmpty()){
			model.addAttribute("message","회원 가입이 되어 있지 않습니다.");
			return new ModelAndView("/member/memFindID");
		}
		else{
			memberVO = list.get(0);
			model.addAttribute("memberVO",memberVO);
			return new ModelAndView("/member/memFindIDResult");
		}
	}

	@PageTitle("PW찾기 버튼 클릭")
	@RequestMapping("/member/memFindPW.do")
	public String memFindPW() throws Exception{
		return "/member/memFindPW";
	}

	@PageTitle("비밀번호 찾기 버튼 클릭")
	@RequestMapping("/member/memFindPWResult.do")
	public ModelAndView memFindPWResult(@ModelAttribute("memberVO")MemberVO memberVO, ModelMap model) throws Exception{
		List<MemberVO> list = memberService.isMemberCheck(memberVO);
		System.out.println("memFindPWResult in");
		if(list.isEmpty()){ // 회원 조회하여 결과가 없을 경우
			System.out.println("memFindPWResult  empty in");
			model.addAttribute("message","입력하신 정보가 잘못되었습니다 다시확인하여 주세요");
			return new ModelAndView("/member/memFindPW");
		}
		else{ //회원 조회하여 결과가 있을 경우
			System.out.println("memFindPWResult result in");
			MemberVO tempVO = list.get(0);
			System.out.println("memberVO.getEmail() = "+memberVO.getEmail()+"/ tempVO.getEmail() = "+tempVO.getEmail());
			if(tempVO.getEmail().equals(memberVO.getEmail())){ //문의한 메일과 동일한 메일주소가 있을 경우 메일 발송
				System.out.println("memFindPWResult ok");
				TestMail mail  = new TestMail();
				mail.setForm(tempVO.getEmail(),tempVO.getId(),tempVO.getPassword(),tempVO.getName());
				mail.sendMail();
				model.addAttribute("message","메일 발송 완료");
				model.addAttribute("memberVO",memberVO);
				return new ModelAndView("/member/memFindPW");
			}
			else{ //메일 주소와 가입한 메일 주소가 다를 경우 리턴
				System.out.println("memFindPWResult non ok");
				model.addAttribute("message","입력하신 메일 주소가 잘못되었습니다 다시확인하여 주세요");
				return new ModelAndView("/member/memFindPW");
			}
		}
	}


}
