package org.kosta.myproject.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.kosta.myproject.vo.MemberVO;
import org.kosta.myproject.vo.AdminBoardVO;
import org.kosta.myproject.vo.FileVO;
import org.kosta.myproject.vo.Pagination;
import org.kosta.myproject.vo.TradingBoardVO;

@Mapper
public interface BoardMapper {

	int getTotalPostCount();

	ArrayList<TradingBoardVO> orderByTemp(Pagination pagination);
	
	ArrayList<TradingBoardVO> orderByDate1(Pagination pagination);

	ArrayList<TradingBoardVO> orderByPrice(Pagination pagination);



	ArrayList<TradingBoardVO> orderByDate4(Pagination pagination);

	ArrayList<TradingBoardVO> orderByDate3(Pagination pagination);
	
	void posting(TradingBoardVO tradingBoardVO);

	int getTotalSalePostCount();

	ArrayList<TradingBoardVO> orderBySaleDate(Pagination pagination);

	ArrayList<TradingBoardVO> orderBySaleTemp(Pagination pagination);

	ArrayList<TradingBoardVO> orderBySalePrice(Pagination pagination);

	TradingBoardVO postdetail(int boardNo);
	
	TradingBoardVO findtradingboardbyno(int boardNo);
	
	void updating(TradingBoardVO tradingBoardVO);

	void deletePost(int boardNo);

	ArrayList<MemberVO> findAllList();

	ArrayList<MemberVO> findAllBlackList();

	void blackListMember(String memberId);

	void restoreMember(String memberId);
	
	void postpicture(FileVO fvo);

	int currentseq();

	ArrayList<AdminBoardVO> orderByDate1Contact(Pagination pagination);

	void posting2(AdminBoardVO adminVO);

	AdminBoardVO adminDetailByNo(int adminBoardNo);

	void deleteAdmin(int adminBoardNo);

	ArrayList<AdminBoardVO> orderAdminById(Pagination pagination);

	ArrayList<AdminBoardVO> orderAdminByTitle(Pagination pagination);

	ArrayList<AdminBoardVO> orderAdminByDate(Pagination pagination);

	AdminBoardVO findAdminBoardByNo(int adminBoardNo);

	void updateAdmin(AdminBoardVO adminBoardVO);

	String findMemberIdByNo(int adminBoardNo);

	String findManagerId();

}
