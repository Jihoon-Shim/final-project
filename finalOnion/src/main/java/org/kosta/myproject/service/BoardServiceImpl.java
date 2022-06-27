package org.kosta.myproject.service;

import java.util.ArrayList;

import org.kosta.myproject.mapper.BoardMapper;
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
	public ArrayList<TradingBoardVO> orderByDate(Pagination pagination) {
		ArrayList<TradingBoardVO> list = new ArrayList<TradingBoardVO>();
		list = boardmapper.orderByDate(pagination);
		return list;
	}

	@Override
	public ArrayList<TradingBoardVO> orderByPrice(Pagination pagination) {
		ArrayList<TradingBoardVO> list = new ArrayList<TradingBoardVO>();
		list = boardmapper.orderByPrice(pagination);
		return list;
	}

	@Override
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
		ArrayList<TradingBoardVO> list = new ArrayList<TradingBoardVO>();
		list = boardmapper.orderBySaleDate(pagination);
		return list;
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
}
