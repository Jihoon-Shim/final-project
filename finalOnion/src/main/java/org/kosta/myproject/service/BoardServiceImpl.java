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
	public ArrayList<TradingBoardVO> orderByDate2(Pagination pagination) {
		ArrayList<TradingBoardVO> list2 = new ArrayList<TradingBoardVO>();
		list2 = boardmapper.orderByDate2(pagination);
		return list2;
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

	
}
