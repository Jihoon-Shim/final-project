package org.kosta.myproject.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kosta.myproject.service.BoardService;
import org.kosta.myproject.vo.AdminBoardVO;
import org.kosta.myproject.service.TagService;
import org.kosta.myproject.vo.FileVO;
import org.kosta.myproject.vo.MemberVO;
import org.kosta.myproject.vo.Pagination;
import org.kosta.myproject.vo.TagVO;
import org.kosta.myproject.vo.TradingBoardVO;
import org.kosta.myproject.vo.UpLoadFileUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	private final TagService tagService;
	
	@RequestMapping("/board/buylist")
	public String buyList(Model model) {
		ArrayList<TradingBoardVO> list = new ArrayList<TradingBoardVO>();
		//클라이언트로부터 페이지번호를 전달받는다. Pagination(dao.getTotalPostCount(),nowPage);
		String pageNo =(String) model.getAttribute("pageNo");
		Pagination pagination = null;
		if(pageNo==null) {
			pagination = new Pagination(boardService.getTotalPostCount());
		}else {
			pagination=new Pagination(boardService.getTotalPostCount(),Integer.parseInt(pageNo));
		}
		//list.jsp에서 페이징처리를 하기위해 Pagination객체를 공유한다.
		list = boardService.orderByDate1(pagination);
		model.addAttribute("pagination",pagination);
		model.addAttribute("list",list);
		return "board/buylist";
	}
	
	@RequestMapping("/board/orderbuyList")
	public String orderbuyList(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(false);
		String sort = request.getParameter("sort1");
		String sorton = request.getParameter("sorton");
		String searchword = request.getParameter("searchword1");
		if(session.getAttribute("sorting")==null) {
			if(sorton!=null) {
				session.setAttribute("sorting", sort);
			}else{
				session.setAttribute("sorting", "date");
			}
		}
		if(sorton!=null) {
			session.setAttribute("sorting", sort);
		}
		String sort1 =(String) session.getAttribute("sorting");
		ArrayList<TradingBoardVO> list = new ArrayList<TradingBoardVO>();
		//클라이언트로부터 페이지번호를 전달받는다. Pagination(dao.getTotalPostCount(),nowPage);
		String pageNo = request.getParameter("pageNo");
		Pagination pagination = null;
		if(searchword==null) {
			//검색어 미포함
			if(pageNo==null) {
				pagination = new Pagination(boardService.getTotalPostCount());
			}else {
				pagination=new Pagination(boardService.getTotalPostCount(),Integer.parseInt(pageNo));
			}	
			if(sort1.equals("temp")) {
				list = boardService.orderByTemp(pagination);
			}else if(sort1.equals("price")) {
				list = boardService.orderByPrice(pagination);
			}else{
				list = boardService.orderByDate1(pagination);
			}
			request.setAttribute("pagination", pagination);
			request.setAttribute("list", list);
			return "board/buylist";
		}else {
			//검색어를 포함한다.
			if(pageNo==null) {
				pagination = new Pagination(boardService.getTotalPostCountBySearch(searchword),searchword);
			}else {
				pagination=new Pagination(boardService.getTotalPostCountBySearch(searchword),Integer.parseInt(pageNo),searchword);
			}
			if(sort1.equals("temp")) {
				list = boardService.orderByTempsearch(pagination);
			}else if(sort1.equals("price")) {
				list = boardService.orderByPricesearch(pagination);
			}else{
				list = boardService.orderByDate1search(pagination);
			}
			request.setAttribute("pagination", pagination);
			request.setAttribute("list", list);
			return "board/buylist";
		}
		
		//list.jsp에서 페이징처리를 하기위해 Pagination객체를 공유한다.
		
	}
	
	@RequestMapping("/board/salelist")
	public String salelist(Model model) {
		ArrayList<TradingBoardVO> list = new ArrayList<TradingBoardVO>();
		//클라이언트로부터 페이지번호를 전달받는다. Pagination(dao.getTotalPostCount(),nowPage);
		String pageNo =(String) model.getAttribute("pageNo");
		Pagination pagination = null;
		if(pageNo==null) {
			pagination = new Pagination(boardService.getTotalSalePostCount());
		}else {
			pagination=new Pagination(boardService.getTotalSalePostCount(),Integer.parseInt(pageNo));
		}
		//list.jsp에서 페이징처리를 하기위해 Pagination객체를 공유한다.

		list = boardService.orderBySaleDate(pagination);

		model.addAttribute("pagination",pagination);
		model.addAttribute("list",list);
		return "board/salelist";

	}
	
	@RequestMapping("/board/ordersaleList")
	public String ordersaleList(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(false);
		String sort = request.getParameter("sort1");
		String sorton = request.getParameter("sorton");
		String searchword = request.getParameter("searchword1");
		if(session.getAttribute("sorting")==null) {
			if(sorton!=null) {
				session.setAttribute("sorting", sort);
			}else{
				session.setAttribute("sorting", "date");
			}
		}
		if(sorton!=null) {
			session.setAttribute("sorting", sort);
		}
		String sort1 =(String) session.getAttribute("sorting");
		ArrayList<TradingBoardVO> list = new ArrayList<TradingBoardVO>();
		String pageNo = request.getParameter("pageNo");
		Pagination pagination = null;
		if(searchword==null) {
			//검색어 미포함
			if(pageNo==null) {
				pagination = new Pagination(boardService.getTotalSalePostCount());
			}else {
				pagination=new Pagination(boardService.getTotalSalePostCount(),Integer.parseInt(pageNo));
			}
			//list.jsp에서 페이징처리를 하기위해 Pagination객체를 공유한다.
			if(sort1.equals("temp")) {
				list = boardService.orderBySaleTemp(pagination);
			}else if(sort1.equals("price")) {
				list = boardService.orderBySalePrice(pagination);
			}else{
				list = boardService.orderBySaleDate(pagination);
			}
			request.setAttribute("pagination", pagination);
			request.setAttribute("list", list);
			return "board/salelist";
		}else {
			//검색어를 포함한다.
			if(pageNo==null) {
				pagination = new Pagination(boardService.getTotalSalePostCountBySearch(searchword),searchword);
			}else {
				pagination = new Pagination(boardService.getTotalSalePostCountBySearch(searchword),Integer.parseInt(pageNo),searchword);
			}
			//list.jsp에서 페이징처리를 하기위해 Pagination객체를 공유한다.
			if(sort1.equals("temp")) {
				list = boardService.orderBySaleTempsearch(pagination);
			}else if(sort1.equals("price")) {
				list = boardService.orderBySalePricesearch(pagination);
			}else{
				list = boardService.orderBySaleDatesearch(pagination);
			}
			request.setAttribute("pagination", pagination);
			request.setAttribute("list", list);
			return "board/salelist";
		}
	}
	@Transactional
	@PostMapping("/board/PostBuy")	
	public String PostBuy(@AuthenticationPrincipal MemberVO memberVO,TradingBoardVO tradingBoardVO,HttpServletRequest request,@RequestParam(value="file") MultipartFile[] file) 
			throws Exception {
		File profile = new File(".");
        String rootPath = profile.getAbsolutePath().substring(0,profile.getAbsolutePath().length()-2);
		String imgUploadPath = rootPath + File.separator +"src"+ File.separator +"main"+ File.separator +"resources"+ File.separator + "static" + File.separator + "myweb" + File.separator + "images" + File.separator + "board";  
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

		  tradingBoardVO.setProductPicture( fileName);//imgUploadPath+ File.separator +
		  // gdsThumbImg에 썸네일 파일 경로 + 썸네일 파일명 저장
		 // vo.setGdsThumbImg(File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);		  
		 } else {  // 첨부된 파일이 없으면
		  fileName =  "iu.jpg";	  //imgUploadPath +File.separator +
		  tradingBoardVO.setProductPicture(fileName);
		  //vo.setGdsThumbImg(fileName);
		 }
		tradingBoardVO.setMemberVO(memberVO);
		boardService.posting(tradingBoardVO);
		for(int i=0;i<5;i++) {
			String tag = request.getParameter(Integer.toString(i));
			if(tag!=null) {
				if(tagService.tagCheck(tag).equals("ok")) {
					tagService.registTag(tag);
				}else {
					tagService.hitsTag(tag);
				}
				int currentNo = boardService.currentNo();
				int tagNo = tagService.findTagNoByTag(tag);
				tagService.relateTag(tagNo,currentNo);
			}
		}
		return "board/PostBuy";
	}

	@RequestMapping("/board/updatePostForm")
	public String updatePostForm(int boardNo, Model model) {
		TradingBoardVO tvo = new TradingBoardVO();
		tvo = boardService.findtradingboardbyno(boardNo);
		model.addAttribute("tvo",tvo);
		return "board/updatePostForm";
	}
	@Transactional
	@PostMapping("/board/updatePost")	
	public String updatePost(@AuthenticationPrincipal MemberVO memberVO,TradingBoardVO tradingBoardVO,@RequestParam(value="updatefile") MultipartFile[] newfile) throws Exception {
		tradingBoardVO.setMemberVO(memberVO);
		File profile = new File(".");
        String rootPath = profile.getAbsolutePath().substring(0,profile.getAbsolutePath().length()-2);
		String imgUploadPath = rootPath + File.separator +"src"+ File.separator +"main"+ File.separator +"resources"+ File.separator + "static" + File.separator + "myweb" + File.separator + "images" + File.separator + "board";
		File file=new File(imgUploadPath+File.separator+tradingBoardVO.getProductPicture());
		File thumbfile=new File(imgUploadPath+File.separator+"s"+File.separator+tradingBoardVO.getProductPicture());
		String fileName=null;
		if(newfile[0]!=null) {
			file.delete();
			thumbfile.delete();
			FileVO fvo = new FileVO();
			fvo.setContentType(newfile[0].getContentType());
			fvo.setFileName(newfile[0].getOriginalFilename());
			boardService.postpicture(fvo);
			int seq = boardService.currentseq();
			String newfileName = File.separator+seq+"_"+fvo.getFileName();
			fileName =  UpLoadFileUtils.fileUpload(imgUploadPath, newfileName, newfile[0].getBytes());
			fileName = fileName.substring(1,fileName.length());
			tradingBoardVO.setProductPicture( fileName);
		}		
		boardService.updating(tradingBoardVO);
		return "board/updatePost";
	}
	@RequestMapping("/board/deletePost")
	public String deletePost(int boardNo) {
		boardService.deletePost(boardNo);
		return"board/deletePost";
	}
	@RequestMapping("/board/chatlist")
	public String chatlist() {
		return"board/chatlist";
	}
	
	@RequestMapping("/board/postBuyForm")
	public String postBuyForm() {
		return"board/postBuyForm";
	}
	@RequestMapping("/board/postdetail")
	public String postdetail(int boardNo, Model model) {
		TradingBoardVO tvo = new TradingBoardVO();  
		tvo = boardService.postdetail(boardNo);
		
		String avo01 = new MemberVO().getMemberId();
		avo01 = boardService.findMemberIdByNo(boardNo);
		model.addAttribute("avo01" , avo01);
		
		List<TagVO> tlist = new ArrayList<TagVO>(); 
		tlist = tagService.findTagByBoardNo(boardNo);
		model.addAttribute("tvo",tvo);
		model.addAttribute("tlist",tlist);
		return"board/postdetail";
	}
	@RequestMapping("/board/contact")
	public String contact(Model model) {
		ArrayList<AdminBoardVO> list0 = new ArrayList<AdminBoardVO>();
		ArrayList<AdminBoardVO> adminList = new ArrayList<AdminBoardVO>();
		//클라이언트로부터 페이지번호를 전달받는다. Pagination(dao.getTotalPostCount(),nowPage);
		String pageNo =(String) model.getAttribute("pageNo");
		Pagination pagination = null;
		if(pageNo==null) {
			pagination = new Pagination(boardService.getTotalAdminCount());
		}else {
			pagination=new Pagination(boardService.getTotalAdminCount(),Integer.parseInt(pageNo));
		}
		//list.jsp에서 페이징처리를 하기위해 Pagination객체를 공유한다.
		list0 = boardService.orderByDate1Contact(pagination);
		adminList = boardService.orderAdminList(list0);
		
		
		model.addAttribute("pagination",pagination);
		model.addAttribute("adminList" , adminList);
		return "board/contact";
	}
	@RequestMapping("/board/postContactForm")
	public String postContactForm(Model model , @AuthenticationPrincipal MemberVO memberVO) {
		model.addAttribute("id" , memberVO.getMemberId());
		return "board/postContactForm";
	}
	@RequestMapping("/board/postAdmin")
	public String postContact(AdminBoardVO adminVO , Model model , @AuthenticationPrincipal MemberVO memberVO) {
		adminVO.setMemberVO(memberVO);
		boardService.posting2(adminVO);
		ArrayList<AdminBoardVO> list0 = new ArrayList<AdminBoardVO>();
		ArrayList<AdminBoardVO> adminList = new ArrayList<AdminBoardVO>();
		//클라이언트로부터 페이지번호를 전달받는다. Pagination(dao.getTotalPostCount(),nowPage);
		String pageNo =(String) model.getAttribute("pageNo");
		Pagination pagination = null;
		if(pageNo==null) {
			pagination = new Pagination(boardService.getTotalAdminCount());
		}else {
			pagination=new Pagination(boardService.getTotalAdminCount(),Integer.parseInt(pageNo));
		}
		//list.jsp에서 페이징처리를 하기위해 Pagination객체를 공유한다.
		list0 = boardService.orderByDate1Contact(pagination);
		adminList = boardService.orderAdminList(list0);
		
		
		model.addAttribute("pagination",pagination);
		model.addAttribute("adminList",adminList);
		return "/board/contact";
	}
	@RequestMapping("/board/adminDetail")
	public String adminDetail(Model model , @AuthenticationPrincipal MemberVO memberVO , int adminBoardNo) {
		model.addAttribute("loginId" , memberVO.getMemberId());
		
		
		AdminBoardVO avo00 = new AdminBoardVO();
		avo00 = boardService.findAdminBoardByNo(adminBoardNo);
		model.addAttribute("avo00" , avo00);
		
		String avo01 = new MemberVO().getMemberId();
		avo01 = boardService.findMemberIdByNo(adminBoardNo);
		model.addAttribute("avo01" , avo01);
		
		String adminManager = new MemberVO().getMemberId();
		adminManager = boardService.findManagerId();
		model.addAttribute("adminManager" , adminManager);
		
		return "board/adminDetail";
	}
	@RequestMapping("/board/deleteAdmin")
	public String deleteAdmin(int adminBoardNo , Model model) {
		boardService.deleteAdmin(adminBoardNo);
		ArrayList<AdminBoardVO> list0 = new ArrayList<AdminBoardVO>();
		ArrayList<AdminBoardVO> adminList = new ArrayList<AdminBoardVO>();
		//클라이언트로부터 페이지번호를 전달받는다. Pagination(dao.getTotalPostCount(),nowPage);
		String pageNo =(String) model.getAttribute("pageNo");
		Pagination pagination = null;
		if(pageNo==null) {
			pagination = new Pagination(boardService.getTotalAdminCount());
		}else {
			pagination=new Pagination(boardService.getTotalAdminCount(),Integer.parseInt(pageNo));
		}
		//list.jsp에서 페이징처리를 하기위해 Pagination객체를 공유한다.
		list0 = boardService.orderByDate1Contact(pagination);
		adminList = boardService.orderAdminList(list0);
		
		model.addAttribute("pagination",pagination);
		model.addAttribute("adminList",adminList);
		return "/board/contact";
	}
	@RequestMapping("/board/orderAdmin")
	public String orderAdmin(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(false);
		String sort = request.getParameter("sort1");
		String sorton = request.getParameter("sorton");
		if(session.getAttribute("sorting")==null) {
			if(sorton!=null) {
				session.setAttribute("sorting", sort);
			}else{
				session.setAttribute("sorting", "date");
			}
		}
		if(sorton!=null) {
			session.setAttribute("sorting", sort);
		}
		String sort1 =(String) session.getAttribute("sorting");
		
		/*
		 * ArrayList<AdminBoardVO> list0 = new ArrayList<AdminBoardVO>(); //클라이언트로부터
		 * 페이지번호를 전달받는다. Pagination(dao.getTotalPostCount(),nowPage); String pageNo
		 * =(String) model.getAttribute("pageNo"); Pagination pagination = null;
		 * if(pageNo==null) { pagination = new
		 * Pagination(boardService.getTotalPostCount()); }else { pagination=new
		 * Pagination(boardService.getTotalPostCount(),Integer.parseInt(pageNo)); }
		 * //list.jsp에서 페이징처리를 하기위해 Pagination객체를 공유한다. list0 =
		 * boardService.orderByDate1Contact(pagination);
		 * model.addAttribute("pagination",pagination);
		 * model.addAttribute("list0",list0);
		 */
		
		ArrayList<AdminBoardVO> list0 = new ArrayList<AdminBoardVO>();
		ArrayList<AdminBoardVO> adminList = new ArrayList<AdminBoardVO>();
		//클라이언트로부터 페이지번호를 전달받는다. Pagination(dao.getTotalPostCount(),nowPage);
		String pageNo = request.getParameter("pageNo");
		Pagination pagination = null;
		if(pageNo==null) {
			pagination = new Pagination(boardService.getTotalAdminCount());
		}else {
			pagination=new Pagination(boardService.getTotalAdminCount(),Integer.parseInt(pageNo));
		}		
		
		//list.jsp에서 페이징처리를 하기위해 Pagination객체를 공유한다.
		
		if(sort1.equals("id")) {
			list0 = boardService.orderAdminById(pagination);
		}else if(sort1.equals("title")) {
			list0 = boardService.orderAdminByTitle(pagination);
		}else if(sort1.equals("date")){
			list0 = boardService.orderAdminByDate(pagination);
		}
		
		list0 = boardService.orderByDate1Contact(pagination);
		adminList = boardService.orderAdminList(list0);
		
		request.setAttribute("pagination", pagination);
		request.setAttribute("adminList", adminList);
		return "/board/contact";
	}
	@RequestMapping("/board/updateAdminForm")
	public String updateAdminForm( int adminBoardNo, Model model) {
		AdminBoardVO avo = new AdminBoardVO();
		avo = boardService.findAdminBoardByNo(adminBoardNo);
		model.addAttribute("avo",avo);
		
		String avo01 = new MemberVO().getMemberId();
		avo01 = boardService.findMemberIdByNo(adminBoardNo);
		model.addAttribute("avo01" , avo01);
		
		return "board/updateAdminForm";
	}
	@PostMapping("/board/updateAdmin")	
	public String updateAdmin(AdminBoardVO adminBoardVO) {
		adminBoardVO.setAdminBoardNo(adminBoardVO.getAdminBoardNo());
		adminBoardVO.setAdminBoardTitle(adminBoardVO.getAdminBoardTitle());
		adminBoardVO.setAdminBoardContent(adminBoardVO.getAdminBoardContent());
		adminBoardVO.setAdminBoardKind(adminBoardVO.getAdminBoardKind());
		boardService.updateAdmin(adminBoardVO);
		return "redirect:/resultAdminUpdate";
	}
	@GetMapping("/resultAdminUpdate")
	public String resultAdminUpdate(Model model , HttpServletRequest request){
		
		HttpSession session = request.getSession(false);
		String sort = request.getParameter("sort1");
		String sorton = request.getParameter("sorton");
		if(session.getAttribute("sorting")==null) {
			if(sorton!=null) {
				session.setAttribute("sorting", sort);
			}else{
				session.setAttribute("sorting", "date");
			}
		}
		if(sorton!=null) {
			session.setAttribute("sorting", sort);
		}
		String sort1 =(String) session.getAttribute("sorting");
		ArrayList<AdminBoardVO> list0 = new ArrayList<AdminBoardVO>();
		ArrayList<AdminBoardVO> adminList = new ArrayList<AdminBoardVO>();
		//클라이언트로부터 페이지번호를 전달받는다. Pagination(dao.getTotalPostCount(),nowPage);
		String pageNo = request.getParameter("pageNo");
		Pagination pagination = null;
		if(pageNo==null) {
			pagination = new Pagination(boardService.getTotalAdminCount());
		}else {
			pagination=new Pagination(boardService.getTotalAdminCount(),Integer.parseInt(pageNo));
		}		
		//list.jsp에서 페이징처리를 하기위해 Pagination객체를 공유한다.
		if(sort1.equals("id")) {
			list0 = boardService.orderAdminById(pagination);
		}else if(sort1.equals("title")) {
			list0 = boardService.orderAdminByTitle(pagination);
		}else if(sort1.equals("date")){
			list0 = boardService.orderAdminByDate(pagination);
		}
		
		list0 = boardService.orderByDate1Contact(pagination);
		adminList = boardService.orderAdminList(list0);
		
		request.setAttribute("pagination", pagination);
		request.setAttribute("adminList", adminList);
		 
		return "/board/contact";
	}
}
