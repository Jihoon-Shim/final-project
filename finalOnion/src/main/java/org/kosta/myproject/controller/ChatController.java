package org.kosta.myproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.kosta.myproject.service.ChatService;
import org.kosta.myproject.service.MemberService;
import org.kosta.myproject.vo.ChattingRoomVO;
import org.kosta.myproject.vo.ChattingVO;
import org.kosta.myproject.vo.MemberVO;
import org.kosta.myproject.vo.TradingBoardVO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatController{
	private final ChatService chatService;
	private final MemberService memberService;
	
	@RequestMapping("/goChat")
	public String getMemberId(@AuthenticationPrincipal MemberVO myMemberVO, String otherId, Model model) {
		String myId = myMemberVO.getMemberId();
		ChattingRoomVO chattingRoomVO = new ChattingRoomVO();
		chattingRoomVO = chatService.findChattingRoom(myId, otherId);
		if(chattingRoomVO==null) {
			chatService.createChattingRoom(myId, otherId);
			chattingRoomVO = chatService.findChattingRoom(myId, otherId);
		}
		model.addAttribute("chattingRoomVO", chattingRoomVO);
		List<String> chattingList = chatService.getChattingList(myId, otherId);
		List<TradingBoardVO> postVOList = chatService.getAllPostListNotSoldOutById(otherId);
		model.addAttribute("postVOList", postVOList);
		model.addAttribute("chattingList", chattingList);
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
		List<ChattingRoomVO> chattingRoomVOList = chatService.findChattingRoomVOListByNickname(memberVO.getMemberId());
		List<ChattingVO> chattingVOList = new ArrayList<ChattingVO>();
		ChattingVO chattingVO = null;
		MemberVO otherMemberVO = null;
		for(ChattingRoomVO chattingRoomVO : chattingRoomVOList) {
			chattingVO = new ChattingVO();
			chattingVO.setChattingRoomVO(chattingRoomVO);
			int chattingRoomNo = chattingRoomVO.getChattingRoomNo();
			String ChattingTitle = chatService.findChattingTitleByChattingNo(chattingRoomNo);
			String otherMemberId= ChattingTitle.replace(" and ", "").replace(memberVO.getMemberId(), "");
			otherMemberVO = new MemberVO();
			otherMemberVO = memberService.findMemberById(otherMemberId);
			chattingVO.setMemberVO(otherMemberVO);
			String lastMessage = chatService.getLastMessage(otherMemberId);
			if(lastMessage==null) {
				lastMessage="상대방의 채팅이 없습니다.";
			}
			chattingVO.setChatting(lastMessage);
			chattingVOList.add(chattingVO);
		}
		model.addAttribute("chattingVOList",chattingVOList);
		return "chat/chattingList";
	}
	
	
}

