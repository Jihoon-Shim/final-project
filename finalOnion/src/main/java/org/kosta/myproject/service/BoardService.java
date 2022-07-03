package org.kosta.myproject.service;

import java.util.ArrayList;

import org.kosta.myproject.vo.MemberVO;
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
	
	int currentNo();

	int getTotalPostCountBySearch(String searchword);

	ArrayList<TradingBoardVO> orderByTempsearch(Pagination pagination);

	ArrayList<TradingBoardVO> orderByPricesearch(Pagination pagination);

	ArrayList<TradingBoardVO> orderByDate1search(Pagination pagination);

	int getTotalSalePostCountBySearch(String searchword);

	ArrayList<TradingBoardVO> orderBySaleTempsearch(Pagination pagination);

	ArrayList<TradingBoardVO> orderBySalePricesearch(Pagination pagination);

	ArrayList<TradingBoardVO> orderBySaleDatesearch(Pagination pagination);
}
