package org.kosta.myproject.service;

import java.util.ArrayList;

import org.kosta.myproject.mapper.BoardMapper;
import org.kosta.myproject.vo.MemberVO;
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
}
