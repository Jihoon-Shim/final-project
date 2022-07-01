package org.kosta.myproject.service;

import java.util.List;

import org.kosta.myproject.mapper.ChatMapper;
import org.kosta.myproject.vo.ChattingRoomVO;
import org.kosta.myproject.vo.MemberVO;
import org.kosta.myproject.vo.TradingBoardVO;
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
	@Override
	public List<ChattingRoomVO> findChattingRoomVOListByNickname(String memberNickname) {
		return chatMapper.findChattingRoomVOListByNickname(memberNickname);
	}
	@Override
	public List<TradingBoardVO> getAllPostListNotSoldOutById(String otherId) {
		return chatMapper.getAllPostListNotSoldOutById(otherId);
	}
	@Override
	public String getLastMessage(String myId, String otherMemberId) {
		return chatMapper.getLastMessage(myId, otherMemberId);
	}
	@Override
	public String findChattingTitleByChattingNo(int chattingRoomNo) {
		return chatMapper.findChattingTitleByChattingNo(chattingRoomNo);
	}
	@Override
	public void readOtherChat(String myId, String otherId) {
		chatMapper.readOtherChat(myId, otherId);
	}
	@Override
	public int isReadOtherChat(String myId, String otherMemberId) {
		return chatMapper.isReadOtherChat(myId, otherMemberId);
	}

	
	
	
}
