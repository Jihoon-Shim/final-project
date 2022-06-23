package org.kosta.myproject.service;

import org.kosta.myproject.vo.ChattingRoomVO;
import org.kosta.myproject.vo.MemberVO;

public interface ChatService {

	void createChattingRoom(String myNick, String yourNick);

	int checkChattingRoomNo(String myNick, String yourNick);

	ChattingRoomVO findChattingRoom(String myNick, String yourNick);

	void recordChatting(MemberVO memberVO, ChattingRoomVO chattingRoomVO, String msg);


	
}
