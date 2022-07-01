package org.kosta.myproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.kosta.myproject.vo.ChattingRoomVO;
import org.kosta.myproject.vo.MemberVO;
import org.kosta.myproject.vo.TradingBoardVO;

@Mapper
public interface ChatMapper {

	void createChattingRoom(String myNick, String yourNick);

	int checkChattingRoomNo(String myNick, String yourNick);

	ChattingRoomVO findChattingRoom(String myNick, String yourNick);

	void recordChatting(MemberVO memberVO, ChattingRoomVO chattingRoomVO, String msg);

	List<String> getChattingList(String myNick, String otherNick);

	List<ChattingRoomVO> findChattingRoomVOListByNickname(String memberNickname);

	List<TradingBoardVO> getAllPostListNotSoldOutById(String otherId);

	String getLastMessage(String myId, String otherMemberId);

	String findChattingTitleByChattingNo(int chattingRoomNo);

	void readOtherChat(String myId, String otherId);

	int isReadOtherChat(String myId, String otherMemberId);


}
