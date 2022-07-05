package org.kosta.myproject.controller;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kosta.myproject.service.BoardService;
import org.kosta.myproject.service.MemberService;
import org.kosta.myproject.vo.FileVO;
import org.kosta.myproject.vo.MemberVO;
import org.kosta.myproject.vo.TradingBoardVO;
import org.kosta.myproject.vo.UpLoadFileUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	private final BoardService boardService;
	//비인증 상태에서도 접근 가능하도록 /guest/ 이하로 url 등록 
	//org.kosta.myproject.config.security.WebSecurityConfig 설정되어 있음 
	@RequestMapping("guest/findMemberById")
	public String findMemberById(String memberId,Model model) {		
		MemberVO vo = memberService.findMemberById(memberId);
		if (vo == null)
			return "member/findMemberById_fail";
		else {
			model.addAttribute("memberVO",vo);
			return "member/findMemberById_success" ;
		}
	}
	@RequestMapping("guest/findIdForm")
	//@AuthenticationPrincipal : Spring Security를 통해 로그인한 인증정보를 받아오는 어노테이션 
	public String findIdForm(@AuthenticationPrincipal MemberVO memberVO,Model model) {
		model.addAttribute("member", memberVO);
		return "member/findIdForm";
	}
	@RequestMapping("guest/findIdByTel")
	public String findIdByTel(String memberTel,Model model) {		
		String id = memberService.findIdByTel(memberTel);
		if (id == null)
			return "member/findIdByTel_fail";
		else {
			model.addAttribute("memberId",id);
			return "member/findIdByTel_success" ;
		}
	}
	@RequestMapping("guest/findPasswordForm")
	//@AuthenticationPrincipal : Spring Security를 통해 로그인한 인증정보를 받아오는 어노테이션 
	public String findPasswordForm(@AuthenticationPrincipal MemberVO memberVO,Model model) {
		model.addAttribute("member", memberVO);
		return "member/findPasswordForm";
	}
	@RequestMapping("guest/findPasswordByIdTel")
	public String findPasswordByIdTel(String memberTel,String memberId,Model model) {
		MemberVO vo = new MemberVO();
		vo.setMemberId(memberId);
		vo.setMemberTel(memberTel);
		int result = memberService.findPasswordByIdTel(vo);
		if (result == 0)
			return "member/findPasswordByIdTel_fail";
		else {
			model.addAttribute("memberTel",memberTel);
			model.addAttribute("memberId",memberId);
			return "member/findPasswordByIdTel_success" ;
		}
	}
	@PostMapping("guest/updatePassword")
	public String updatePassword(MemberVO memberVO) {
		memberService.updatePassword(memberVO);//변경시 service에서 비밀번호를 암호화 한다 
		return "redirect:/guest/updatePasswordView?memberId=" + memberVO.getMemberId();
	}

	@RequestMapping("guest/updatePasswordView")
	public ModelAndView updatePasswordView(String memberId) {
		MemberVO memberVO = memberService.findMemberById(memberId);
		return new ModelAndView("member/updatePasswordView-result", "memberVO", memberVO);
	}
	//WebSecurityConfig에 등록되어 있음 ( failureUrl("/login_fail") )
	@RequestMapping("login_fail")
	public String loginFail() {
		return "member/login_fail";
	}
    //spring security에서 로그인 , 로그아웃 처리를 하므로 login , logout 관련 메서드는 필요없다  
	//guest/ 가 아닌 모든 컨트롤러는 인증이 되어야 한다. 비인증 상태에서 접근할 경우 로그인 폼이 있는 home으로 redirect 됨 
	@RequestMapping("enterCafe")
	public String enterCafe() {
		return "member/ajax-cafe";
	}
	@RequestMapping("mypage")
	public String mypage(@AuthenticationPrincipal MemberVO memberVO , Model model) {
		ArrayList<TradingBoardVO> list1 = new ArrayList<TradingBoardVO>();
		ArrayList<TradingBoardVO> list2 = new ArrayList<TradingBoardVO>();
		ArrayList<TradingBoardVO> list3 = new ArrayList<TradingBoardVO>();
		ArrayList<TradingBoardVO> list4 = new ArrayList<TradingBoardVO>();
		
		list1 = boardService.orderByDate001(memberVO.getMemberId());
		list2 = boardService.orderByDate002(memberVO.getMemberId());
		list3 = boardService.orderByDate003(memberVO.getMemberId());
		list4 = boardService.orderByDate004(memberVO.getMemberId());
		
		model.addAttribute("list1",list1);
		model.addAttribute("list2",list2);
		model.addAttribute("list3",list3);
		model.addAttribute("list4",list4);
		
		String id = memberVO.getMemberId();
		float temp = (float)memberService.findTempById(id);
		
		
		model.addAttribute("temp",temp);
		
		return "member/mypage.html";
	}
	
	@RequestMapping("guest/idcheckAjax")
	@ResponseBody
	public String idcheckAjax(String memberId) {
		return memberService.idcheck(memberId);
	}
	@GetMapping("getMemberTotalCount")	
	@ResponseBody
	public int getMemberTotalCount() {
		return memberService.getMemberCount();
	}
	@PostMapping("postAjaxTest")
	@ResponseBody
	public String postAjaxTest(String message) {
		//log.debug("post ajax 는 csrf token 이 필요합니다 {}",message);
		return message+" ajax 요청에 대한 응답입니다";
	}	
	
	@RequestMapping("updateForm")
	//@AuthenticationPrincipal : Spring Security를 통해 로그인한 인증정보를 받아오는 어노테이션 
	public String updateForm(@AuthenticationPrincipal MemberVO memberVO,Model model) {
		model.addAttribute("member", memberVO);
		return "member/updateForm";
	}

	@PostMapping("updateMemberAction")
	//첫번째 매개변수 Authentication : Spring Security 인증 정보 , 두번째 매개변수 memberVO : 수정폼에서 전달받는 데이터 
	public String updateMemberAction(Authentication authentication, MemberVO memberVO) {
		MemberVO vo = (MemberVO)authentication.getPrincipal();			
		memberService.updateMember(memberVO);//service에서 변경될 비밀번호를 암호화한다 
		// 수정한 회원정보로 Spring Security 회원정보를 업데이트한다
		vo.setMemberPassword(memberVO.getMemberPassword());
		vo.setMemberName(memberVO.getMemberName());
		vo.setMemberAddress(memberVO.getMemberAddress());
		vo.setMemberNickname(memberVO.getMemberNickname());
		vo.setMemberTel(memberVO.getMemberTel());
		return "redirect:updateResult";
	}
	  @PostMapping("deleteMemberAction") 
	  public String deleteMemberAction(@AuthenticationPrincipal MemberVO memberVO , HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		memberService.deleteMember(memberVO.getMemberId());
		session.invalidate();
		return "redirect:index.html";
	  }
	 
	@GetMapping("updateResult")
	public String updateResult(){
		return "member/update_result";
	}
	@RequestMapping("guest/registerForm")
	public String registerForm() {
		return "member/registerForm";
	}
	@Transactional
	@PostMapping("guest/registerMember")
	public String register(MemberVO memberVO, @RequestParam(value="file") MultipartFile[] file) throws IOException, Exception {
		File profile = new File(".");
        String rootPath = profile.getAbsolutePath().substring(0,profile.getAbsolutePath().length()-2);
		String imgUploadPath = rootPath + File.separator +"src"+ File.separator +"main"+ File.separator +"resources"+ File.separator + "static" + File.separator + "myweb" + File.separator + "images" + File.separator + "member";  
		// 이미지를 업로드할 폴더를 설정 = /uploadPath/imgUpload
		 String fileName = null;  // 기본 경로와 별개로 작성되는 경로 + 파일이름
		 if(file[0].getOriginalFilename() != null && !file[0].getOriginalFilename().equals("")) {
			 FileVO fvo = new FileVO();
			fvo.setContentType(file[0].getContentType());
			fvo.setFileName(file[0].getOriginalFilename());
			boardService.postpicture(fvo);
			int seq = boardService.currentseq();
			String newfileName = File.separator+seq+"_"+fvo.getFileName();
			fileName =  UpLoadFileUtils.fileUpload(imgUploadPath, newfileName, file[0].getBytes());
			fileName = fileName.substring(1,fileName.length());
			memberVO.setMemberPicture(fileName);//imgUploadPath+ File.separator +
			 // gdsThumbImg에 썸네일 파일 경로 + 썸네일 파일명 저장
			 // vo.setGdsThumbImg(File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);		  
			} else {  // 첨부된 파일이 없으면
			fileName = "미등록프로필.jpg"; //imgUploadPath +File.separator +
			memberVO.setMemberPicture(fileName);
			//vo.setGdsThumbImg(fileName);
			}
		memberService.registerMember(memberVO);//등록시 service에서 비밀번호를 암호화 한다 
		memberService.registerMemberTemp(memberVO);
		return "redirect:/guest/registerResultView?memberId=" + memberVO.getMemberId();
	}

	@RequestMapping("guest/registerResultView")
	public ModelAndView registerResultView(String memberId) {
		MemberVO memberVO = memberService.findMemberById(memberId);
		return new ModelAndView("member/register_result", "memberVO", memberVO);
	}
	


	
}
