package org.kosta.myproject.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kosta.myproject.service.BoardService;
import org.kosta.myproject.service.ChatService;
import org.kosta.myproject.service.MemberService;
import org.kosta.myproject.vo.ChattingRoomVO;
import org.kosta.myproject.vo.ChattingVO;
import org.kosta.myproject.vo.MemberVO;
import org.kosta.myproject.vo.TradingBoardVO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatController{
	private final ChatService chatService;
	private final MemberService memberService;
	private final BoardService boardService;
	
	@RequestMapping("/goChat")
	public String getMemberId(@AuthenticationPrincipal MemberVO myMemberVO, String otherId, Model model, HttpServletRequest request) {
		String myId = myMemberVO.getMemberId();
		ChattingRoomVO chattingRoomVO = new ChattingRoomVO();
		chattingRoomVO = chatService.findChattingRoom(myId, otherId);
		if(chattingRoomVO==null) {
			chatService.createChattingRoom(myId, otherId);
			chattingRoomVO = chatService.findChattingRoom(myId, otherId);
		}
		model.addAttribute("chattingRoomVO", chattingRoomVO);
		//상대 채팅 읽음 처리
		chatService.readOtherChat(myId,otherId);
		List<String> chattingList = chatService.getChattingList(myId, otherId);
		List<TradingBoardVO> postVOList = chatService.getAllPostListNotSoldOutById(otherId);
		MemberVO otherMemberVO = memberService.findMemberById(otherId);
		float temp = memberService.findTempById(otherId);
		model.addAttribute("otherMemberVO", otherMemberVO);
		model.addAttribute("temp", temp);
		model.addAttribute("postVOList", postVOList);
		model.addAttribute("chattingList", chattingList);
		//채팅 알림
		if(myMemberVO!=null) {
			int reception = chatService.isReadChattingRoom(myMemberVO.getMemberId());
			HttpSession session = request.getSession();
			session.setAttribute("reception", reception);
		}
		//판매 완료가 아닌 내 글 가져오기
		List<TradingBoardVO> List = boardService.findTradingBoardByMemberId(myId);
		List<TradingBoardVO> boardList = new ArrayList<TradingBoardVO>();
		for(TradingBoardVO tradingBoardVO : List) {
			if(tradingBoardVO.getTradeStatus()==1) {
				boardList.add(tradingBoardVO);
			}
		}
		model.addAttribute("boardList",boardList);
		
		return "chat/chat";
	}
	@RequestMapping("/chatRecord")
	@ResponseBody
	public String recordChatting(@AuthenticationPrincipal MemberVO memberVO, ChattingRoomVO chattingRoomVO, String msg) {
		chatService.recordChatting(memberVO, chattingRoomVO, msg);
		return "";
	}
	@RequestMapping("/chattingRoomList")
	public String chattingRoom(@AuthenticationPrincipal MemberVO memberVO, Model model) {
		String myId = memberVO.getMemberId();
		List<ChattingRoomVO> chattingRoomVOList = chatService.findChattingRoomVOListByNickname(memberVO.getMemberId());
		List<ChattingVO> chattingVOList = new ArrayList<ChattingVO>();
		ChattingVO chattingVO = null;
		MemberVO otherMemberVO = null;
		for(ChattingRoomVO chattingRoomVO : chattingRoomVOList) {
			chattingVO = new ChattingVO();
			chattingVO.setChattingRoomVO(chattingRoomVO);
			int chattingRoomNo = chattingRoomVO.getChattingRoomNo();
			String ChattingTitle = chatService.findChattingTitleByChattingNo(chattingRoomNo);
			String otherMemberId= ChattingTitle.replace(memberVO.getMemberId()+" and ", "").replace(" and "+memberVO.getMemberId(), "");
			otherMemberVO = new MemberVO();
			otherMemberVO = memberService.findMemberById(otherMemberId);
			chattingVO.setMemberVO(otherMemberVO);
			System.out.println(otherMemberVO);
			//마지막 채팅
			String lastMessage = chatService.getLastMessage(myId, otherMemberId);
			if(lastMessage==null) {
				lastMessage="상대방의 채팅이 없습니다.";
			}
			chattingVO.setChatting(lastMessage);
			//상대 채팅 읽음처리 확인
			int reception = chatService.isReadOtherChat(myId, otherMemberId);
			chattingVO.setReception(reception);
			chattingVOList.add(chattingVO);
		}
		model.addAttribute("chattingVOList",chattingVOList);
		return "chat/chattingList";
	}
	@PostMapping("postSoldOut")
	@ResponseBody
	public String postSoldOut(int boardNo) {
		boardService.updatePostSoldOutByBoardNo(boardNo);
		return "거래완료 처리되었습니다.";
	}
}

