package org.kosta.myproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.kosta.myproject.service.ChatService;
import org.kosta.myproject.vo.ChattingRoomVO;
import org.kosta.myproject.vo.ChattingVO;
import org.kosta.myproject.vo.MemberVO;
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
	
	@PostMapping("profile")
	public String getMemberId(@AuthenticationPrincipal MemberVO myMemberVO, String otherNick, Model model) {
		String myNick = myMemberVO.getMemberNickname();
		ChattingRoomVO chattingRoomVO = chatService.findChattingRoom(myNick, otherNick);
		if(chattingRoomVO==null) {
			chatService.createChattingRoom(myNick, otherNick);
			chattingRoomVO = chatService.findChattingRoom(myNick, otherNick);
		}
		model.addAttribute("chattingRoomVO", chattingRoomVO);
		List<String> list = chatService.getChattingList(myNick, otherNick);
		model.addAttribute("chattingList", list);
		return "member/chat";
	}
	
	@RequestMapping("/chatRecord")
	@ResponseBody
	public String recordChatting(@AuthenticationPrincipal MemberVO memberVO, ChattingRoomVO chattingRoomVO, String msg) {
		chatService.recordChatting(memberVO, chattingRoomVO, msg);
		return "";
	}
	
	@RequestMapping("/chattingRoomList")
	public String chattingRoom(@AuthenticationPrincipal MemberVO memberVO, Model model) {
		List<ChattingRoomVO> chattingRoomVOList = chatService.findChattingRoomNoById(memberVO.getMemberId());
		List<ChattingVO> chattingVOList = new ArrayList<ChattingVO>();
		ChattingVO chattingVO = null;
		for(ChattingRoomVO chattingRoomVO : chattingRoomVOList) {
			chattingVO = new ChattingVO();
			chattingVO.setChattingRoomVO(chattingRoomVO);
			String otherNick= chattingRoomVO.getChattingRoomTitle().replace(memberVO.getMemberNickname(), "").replace(" and ", "");
			MemberVO otherMemberVO = new MemberVO();
			otherMemberVO.setMemberNickname(otherNick);
			chattingVO.setMemberVO(otherMemberVO);
			chattingVOList.add(chattingVO);
		}
		model.addAttribute("chattingVOList",chattingVOList);
		return "chat/chattingList";
	}
	
	
}

