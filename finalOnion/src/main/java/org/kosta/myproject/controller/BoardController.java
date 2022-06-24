package org.kosta.myproject.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kosta.myproject.service.BoardService;
import org.kosta.myproject.vo.MemberVO;
import org.kosta.myproject.vo.Pagination;
import org.kosta.myproject.vo.TradingBoardVO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	
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
		list = boardService.orderByDate(pagination);
		model.addAttribute("pagination",pagination);
		model.addAttribute("list",list);
		return "board/buylist";
	}
	
	@RequestMapping("/board/orderbuyList")
	public String orderbuyList(HttpServletRequest request) throws Exception {
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
		ArrayList<TradingBoardVO> list = new ArrayList<TradingBoardVO>();
		//클라이언트로부터 페이지번호를 전달받는다. Pagination(dao.getTotalPostCount(),nowPage);
		String pageNo = request.getParameter("pageNo");
		Pagination pagination = null;
		if(pageNo==null) {
			pagination = new Pagination(boardService.getTotalPostCount());
		}else {
			pagination=new Pagination(boardService.getTotalPostCount(),Integer.parseInt(pageNo));
		}
		//list.jsp에서 페이징처리를 하기위해 Pagination객체를 공유한다.

		if(sort1.equals("temp")) {
			list = boardService.orderByTemp(pagination);
		}else if(sort1.equals("price")) {
			list = boardService.orderByPrice(pagination);
		}else{
			list = boardService.orderByDate(pagination);
		}
		request.setAttribute("pagination", pagination);
		request.setAttribute("list", list);
		return "board/buylist";
	}
	
	@RequestMapping("/board/salelist")
	public String salelist(Model model) {
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
		list = boardService.orderByDate(pagination);
		model.addAttribute("pagination",pagination);
		model.addAttribute("list",list);
		return "board/buylist";
	}
	
	@RequestMapping("/board/ordersaleList")
	public String ordersaleList(HttpServletRequest request) throws Exception {
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
		ArrayList<TradingBoardVO> list = new ArrayList<TradingBoardVO>();
		String pageNo = request.getParameter("pageNo");
		Pagination pagination = null;
		if(pageNo==null) {
			pagination = new Pagination(boardService.getTotalPostCount());
		}else {
			pagination=new Pagination(boardService.getTotalPostCount(),Integer.parseInt(pageNo));
		}
		//list.jsp에서 페이징처리를 하기위해 Pagination객체를 공유한다.
		
		if(sort1.equals("temp")) {
			list = boardService.orderByTemp(pagination);
		}else if(sort1.equals("price")) {
			list = boardService.orderByPrice(pagination);
		}else{
			list = boardService.orderByDate(pagination);
		}
		request.setAttribute("pagination", pagination);
		request.setAttribute("list", list);
		return "board/buylist";
	}
	
	@PostMapping("/board/PostBuy")	
	public String PostBuy(@AuthenticationPrincipal MemberVO memberVO,TradingBoardVO tradingBoardVO) throws Exception {
		tradingBoardVO.setMemberVO(memberVO);
		System.out.println(tradingBoardVO);
		boardService.posting(tradingBoardVO);
		return "board/PostBuy";
	}
	
	@RequestMapping("/board/chatlist")
	public String chatlist() {
		return"board/chatlist";
	}
	
	@RequestMapping("/board/postBuyForm")
	public String postBuyForm() {
		System.out.println("hello");
		return"board/postBuyForm";
	}
}
