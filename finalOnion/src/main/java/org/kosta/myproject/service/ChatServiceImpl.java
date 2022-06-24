package org.kosta.myproject.service;

import java.util.List;

import org.kosta.myproject.mapper.ChatMapper;
import org.kosta.myproject.vo.ChattingRoomVO;
import org.kosta.myproject.vo.MemberVO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
	private final ChatMapper chatMapper;
	
	@Override
	public int checkChattingRoomNo(String myNick, String yourNick) {
		return chatMapper.checkChattingRoomNo(myNick, yourNick);
		
	}
	@Override
	public void createChattingRoom(String myNick, String yourNick) {
		chatMapper.createChattingRoom(myNick, yourNick);
	}
	@Override
	public ChattingRoomVO findChattingRoom(String myNick, String yourNick) {
		return chatMapper.findChattingRoom(myNick, yourNick);
	}
	@Override
	public void recordChatting(MemberVO memberVO, ChattingRoomVO chattingRoomVO, String msg) {
		chatMapper.recordChatting(memberVO, chattingRoomVO, msg);
	}
	@Override
	public List<String> getChattingList(String myNick, String otherNick) {
		return chatMapper.getChattingList(myNick, otherNick);
	}

	
	
	
}
