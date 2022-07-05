package org.kosta.myproject.service;

import java.util.ArrayList;
import java.util.List;

import org.kosta.myproject.vo.MemberVO;
import org.kosta.myproject.vo.AdminBoardVO;
import org.kosta.myproject.vo.FileVO;
import org.kosta.myproject.vo.Pagination;
import org.kosta.myproject.vo.TradingBoardVO;

public interface BoardService {

	int getTotalPostCount();

	ArrayList<TradingBoardVO> orderByTemp(Pagination pagination);

	ArrayList<TradingBoardVO> orderByDate1(Pagination pagination);

	ArrayList<TradingBoardVO> orderByPrice(Pagination pagination);

	ArrayList<TradingBoardVO> orderByDate3(Pagination pagination);

	ArrayList<TradingBoardVO> orderByDate4(Pagination pagination);

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
	
	int currentNo();

	int getTotalPostCountBySearch(String searchword);

	ArrayList<TradingBoardVO> orderByTempsearch(Pagination pagination);

	ArrayList<TradingBoardVO> orderByPricesearch(Pagination pagination);

	ArrayList<TradingBoardVO> orderByDate1search(Pagination pagination);

	int getTotalSalePostCountBySearch(String searchword);

	ArrayList<TradingBoardVO> orderBySaleTempsearch(Pagination pagination);

	ArrayList<TradingBoardVO> orderBySalePricesearch(Pagination pagination);

	ArrayList<TradingBoardVO> orderBySaleDatesearch(Pagination pagination);

	ArrayList<TradingBoardVO> orderByDate001(String memberId);
	ArrayList<TradingBoardVO> orderByDate002(String memberId);
	ArrayList<TradingBoardVO> orderByDate003(String memberId);
	ArrayList<TradingBoardVO> orderByDate004(String memberId); // 자기 아이디로 게시판 묶음 갖오기 , 개수 제한

	int getTotalAdminCount();

	ArrayList<AdminBoardVO> orderAdminList(ArrayList<AdminBoardVO> list0);
	
	ArrayList<AdminBoardVO> fAQ();

	AdminBoardVO adminpostdetail(int adminBoardNo);

	List<TradingBoardVO> findTradingBoardByMemberId(String myId);

	void updatePostSoldOutByBoardNo(int boardNo);

	void raisehits(int boardNo);

	void setAuthorityThatReview(int boardNo, String otherId);

	List<TradingBoardVO> getReviewablePosts(String myId);

	void reviewedPost(int boardNo);
}
