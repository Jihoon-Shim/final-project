package org.kosta.myproject.controller;

import org.kosta.myproject.service.ChatService;
import org.kosta.myproject.vo.ChattingRoomVO;
import org.kosta.myproject.vo.MemberVO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatController{
	private final ChatService chatService;
	
	@RequestMapping("/chatRecord")
	@ResponseBody
	public String recordChatting(@AuthenticationPrincipal MemberVO memberVO, ChattingRoomVO chattingRoomVO, String msg) {
		chatService.recordChatting(memberVO, chattingRoomVO, msg);
		return "";
	}
	
	@GetMapping("/chat")
    public String chatGET(@AuthenticationPrincipal MemberVO memberVO, String yourNick, Model model){
		String myNick = memberVO.getMemberNickname();
		yourNick = "손흥민";
		ChattingRoomVO chattingRoomVO = chatService.findChattingRoom(myNick, yourNick);
		if(chattingRoomVO==null) {
			chatService.createChattingRoom(myNick, yourNick);
			chattingRoomVO = chatService.findChattingRoom(myNick, yourNick);
		}
		model.addAttribute("chattingRoomVO", chattingRoomVO);
        return "member/chat";
    }
	
}

