package org.kosta.myproject.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosta.myproject.vo.ChattingRoomVO;
import org.kosta.myproject.vo.MemberVO;

@Mapper
public interface ChatMapper {

	void createChattingRoom(String myNick, String yourNick);

	int checkChattingRoomNo(String myNick, String yourNick);

	ChattingRoomVO findChattingRoom(String myNick, String yourNick);

	void recordChatting(MemberVO memberVO, ChattingRoomVO chattingRoomVO, String msg);


}
