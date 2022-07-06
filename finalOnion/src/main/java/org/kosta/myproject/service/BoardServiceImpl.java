package org.kosta.myproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kosta.myproject.mapper.BoardMapper;
import org.kosta.myproject.vo.MemberVO;
import org.kosta.myproject.vo.AdminBoardVO;
import org.kosta.myproject.vo.FileVO;
import org.kosta.myproject.vo.Pagination;
import org.kosta.myproject.vo.TradingBoardVO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	private final BoardMapper boardmapper;

	@Override
	public int getTotalPostCount() {
		int result = boardmapper.getTotalPostCount();
		return result;
	}

	@Override
	public ArrayList<TradingBoardVO> orderByTemp(Pagination pagination) {
		ArrayList<TradingBoardVO> list = new ArrayList<TradingBoardVO>();
		list = boardmapper.orderByTemp(pagination);
		return list;
	}

	@Override
	public ArrayList<TradingBoardVO> orderByDate1(Pagination pagination) {
		ArrayList<TradingBoardVO> list1 = new ArrayList<TradingBoardVO>();
		list1 = boardmapper.orderByDate1(pagination);
		return list1;
	}

	@Override
	public ArrayList<TradingBoardVO> orderByPrice(Pagination pagination) {
		ArrayList<TradingBoardVO> list = new ArrayList<TradingBoardVO>();
		list = boardmapper.orderByPrice(pagination);
		return list;
	}

	@Override
	public ArrayList<TradingBoardVO> orderByDate3(Pagination pagination) {
		ArrayList<TradingBoardVO> list3 = new ArrayList<TradingBoardVO>();
		list3 = boardmapper.orderByDate3(pagination);
		return list3;
	}

	@Override
	public ArrayList<TradingBoardVO> orderByDate4(Pagination pagination) {
		ArrayList<TradingBoardVO> list4 = new ArrayList<TradingBoardVO>();
		list4 = boardmapper.orderByDate4(pagination);
		return list4;
	}

	public void posting(TradingBoardVO tradingBoardVO) {
		boardmapper.posting(tradingBoardVO);
	}

	@Override
	public int getTotalSalePostCount() {
		int result = boardmapper.getTotalSalePostCount();
		return result;
	}

	@Override
	public ArrayList<TradingBoardVO> orderBySaleDate(Pagination pagination) {
		ArrayList<TradingBoardVO> list2 = new ArrayList<TradingBoardVO>();
		list2 = boardmapper.orderBySaleDate(pagination);
		return list2;
	}

	@Override
	public ArrayList<TradingBoardVO> orderBySaleTemp(Pagination pagination) {
		ArrayList<TradingBoardVO> list = new ArrayList<TradingBoardVO>();
		list = boardmapper.orderBySaleTemp(pagination);
		return list;
	}

	@Override
	public ArrayList<TradingBoardVO> orderBySalePrice(Pagination pagination) {
		ArrayList<TradingBoardVO> list = new ArrayList<TradingBoardVO>();
		list = boardmapper.orderBySalePrice(pagination);
		return list;
	}

	@Override
	public TradingBoardVO postdetail(int boardNo) {
		TradingBoardVO tvo = new TradingBoardVO();
		tvo = boardmapper.postdetail(boardNo);
		return tvo;
	}
	
	@Override
	public TradingBoardVO findtradingboardbyno(int boardNo) {
		TradingBoardVO tvo = new TradingBoardVO();
		tvo = boardmapper.findtradingboardbyno(boardNo);
		return tvo;
	}
	
	@Override
	public void updating(TradingBoardVO tradingBoardVO) {
		boardmapper.updating(tradingBoardVO);
	}

	@Override
	public void deletePost(int boardNo) {
		boardmapper.deletePost(boardNo);
		
	}
	
	@Override
	public ArrayList<MemberVO> findAllList() {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		list = boardmapper.findAllList();
		return list;
	}

	@Override
	public ArrayList<MemberVO> findAllBlackList() {
		ArrayList<MemberVO> blackList = new ArrayList<MemberVO>();
		blackList = boardmapper.findAllBlackList();
		return blackList;
	}

	@Override
	public void blackListMember(String memberId) {
		boardmapper.blackListMember(memberId);
	}

	@Override
	public void restoreMember(String memberId) {
		boardmapper.restoreMember(memberId);
	}

	@Override
	public void postpicture(FileVO fvo) {
		boardmapper.postpicture(fvo);		
	}

	@Override
	public int currentseq() {
		int seq = boardmapper.currentseq();
		return seq;
	}

	@Override
	public void posting2(AdminBoardVO adminVO) {
		boardmapper.posting2(adminVO);
	}

	@Override
	public AdminBoardVO adminDetailByNo(int adminBoardNo) {
		AdminBoardVO avo = new AdminBoardVO();
		avo = boardmapper.adminDetailByNo(adminBoardNo);
		return avo;
	}

	@Override
	public void deleteAdmin(int adminBoardNo) {
		boardmapper.deleteAdmin(adminBoardNo);
		
	}

	@Override
	public ArrayList<AdminBoardVO> orderAdminById(Pagination pagination) {
		ArrayList<AdminBoardVO> adminList = new ArrayList<AdminBoardVO>();
		adminList = boardmapper.orderAdminById(pagination);
		return adminList;
	}

	@Override
	public ArrayList<AdminBoardVO> orderAdminByTitle(Pagination pagination) {
		ArrayList<AdminBoardVO> adminList2 = new ArrayList<AdminBoardVO>();
		adminList2 = boardmapper.orderAdminByTitle(pagination);
		return adminList2;
	}

	@Override
	public ArrayList<AdminBoardVO> orderAdminByDate(Pagination pagination) {
		ArrayList<AdminBoardVO> adminList3 = new ArrayList<AdminBoardVO>();
		adminList3 = boardmapper.orderAdminByDate(pagination);
		return adminList3;
	}

	@Override
	public AdminBoardVO findAdminBoardByNo(int adminBoardNo) {
		AdminBoardVO avo = new AdminBoardVO();
		avo = boardmapper.findAdminBoardByNo(adminBoardNo);
		return avo;
	}

	@Override
	public void updateAdmin(AdminBoardVO adminBoardVO) {
		boardmapper.updateAdmin(adminBoardVO);
	}

	@Override
	public String findMemberIdByNo(int adminBoardNo) {
		String avo01 = boardmapper.findMemberIdByNo(adminBoardNo);
		return avo01;
	}

	@Override
	public String findManagerId() {
		String adminManager = boardmapper.findManagerId();
		return adminManager;
	}
	
	@Override
	public int currentNo() {
		return boardmapper.currentNo();
	}

	@Override
	public int getTotalPostCountBySearch(String searchword) {
		int result = boardmapper.getTotalPostCountBySearch(searchword);
		return result;
	}

	@Override
	public ArrayList<TradingBoardVO> orderByTempsearch(Pagination pagination) {
		ArrayList<TradingBoardVO> list = new ArrayList<TradingBoardVO>();
		list = boardmapper.orderByTempsearch(pagination);
		return list;
	}

	@Override
	public ArrayList<TradingBoardVO> orderByPricesearch(Pagination pagination) {
		ArrayList<TradingBoardVO> list = new ArrayList<TradingBoardVO>();
		list = boardmapper.orderByPricesearch(pagination);
		return list;
	}

	@Override
	public ArrayList<TradingBoardVO> orderByDate1search(Pagination pagination) {
		ArrayList<TradingBoardVO> list = new ArrayList<TradingBoardVO>();
		list = boardmapper.orderByDate1search(pagination);
		return list;
	}

	@Override
	public int getTotalSalePostCountBySearch(String searchword) {
		int result = boardmapper.getTotalSalePostCountBySearch(searchword);
		return result;
	}

	@Override
	public ArrayList<TradingBoardVO> orderBySaleTempsearch(Pagination pagination) {
		ArrayList<TradingBoardVO> list = new ArrayList<TradingBoardVO>();
		list = boardmapper.orderBySaleTempsearch(pagination);
		return list;
	}

	@Override
	public ArrayList<TradingBoardVO> orderBySalePricesearch(Pagination pagination) {
		ArrayList<TradingBoardVO> list = new ArrayList<TradingBoardVO>();
		list = boardmapper.orderBySalePricesearch(pagination);
		return list;
	}

	@Override
	public ArrayList<TradingBoardVO> orderBySaleDatesearch(Pagination pagination) {
		ArrayList<TradingBoardVO> list = new ArrayList<TradingBoardVO>();
		list = boardmapper.orderBySaleDatesearch(pagination);
		return list;
	}

	@Override
	public ArrayList<TradingBoardVO> orderByDate001(String memberId) {
		ArrayList<TradingBoardVO> list1 = new ArrayList<TradingBoardVO>();
		list1 = boardmapper.orderByDate001(memberId);
		return list1;
	}
	@Override
	public ArrayList<TradingBoardVO> orderByDate002(String memberId) {
		ArrayList<TradingBoardVO> list2 = new ArrayList<TradingBoardVO>();
		list2 = boardmapper.orderByDate002(memberId);
		return list2;
	}
	@Override
	public ArrayList<TradingBoardVO> orderByDate003(String memberId) {
		ArrayList<TradingBoardVO> list3 = new ArrayList<TradingBoardVO>();
		list3 = boardmapper.orderByDate003(memberId);
		return list3;
	}
	@Override
	public ArrayList<TradingBoardVO> orderByDate004(String memberId) {
		ArrayList<TradingBoardVO> list4 = new ArrayList<TradingBoardVO>();
		list4 = boardmapper.orderByDate004(memberId);
		return list4;
	}
	
	@Override
	public ArrayList<AdminBoardVO> fAQ() {
		ArrayList<AdminBoardVO> avolist = boardmapper.fAQ();
		return avolist;
	}

	@Override
	public AdminBoardVO adminpostdetail(int adminBoardNo) {
		return boardmapper.adminpostdetail(adminBoardNo);
	}

	@Override
	public List<TradingBoardVO> findTradingBoardByMemberId(String myId) {
		return boardmapper.findTradingBoardByMemberId(myId);
	}

	@Override
	public void updatePostSoldOutByBoardNo(int boardNo) {
		boardmapper.updatePostSoldOutByBoardNo(boardNo);
	}

	@Override
	public void raisehits(int boardNo) {
		boardmapper.raisehits(boardNo);

	}

	@Override
	public void setAuthorityThatReview(int boardNo, String otherId) {
		boardmapper.setAuthorityThatReview(boardNo, otherId);
	}

	@Override
	public List<TradingBoardVO> getReviewablePosts(String myId) {
		return boardmapper.getReviewablePosts(myId);
	}

	@Override
	public void reviewedPost(int boardNo) {
		boardmapper.reviewedPost(boardNo);
	}
	@Override
	public void deleteBoardTag(int boardNo) {
		boardmapper.deleteBoardTag(boardNo);

	}

	/*
	 * @Override public ArrayList<AdminBoardVO> orderByDate1Contact(Map<String,
	 * Object> map) { ArrayList<AdminBoardVO> adminList = new
	 * ArrayList<AdminBoardVO>(); adminList = boardmapper.orderByDate1Contact(map);
	 * return adminList; }
	 */

	@Override
	public ArrayList<AdminBoardVO> orderByDate2Contact(String memberId) {
		ArrayList<AdminBoardVO> adminList = new ArrayList<AdminBoardVO>();
		adminList = boardmapper.orderByDate2Contact(memberId);
		return adminList;
	}

	@Override
	public int getTotalAdminCountById(String memberId) {
		int result = boardmapper.getTotalAdminCountById(memberId);
		return result;
	}

	@Override
	public ArrayList<AdminBoardVO> orderByDate1Contact(Pagination pagination) {
		ArrayList<AdminBoardVO> adminList = new ArrayList<AdminBoardVO>();
		adminList = boardmapper.orderByDate1Contact(pagination);
		return adminList;
	}


	
}
