package org.kosta.myproject.service;

import java.util.ArrayList;

import org.kosta.myproject.vo.Pagination;
import org.kosta.myproject.vo.TradingBoardVO;

public interface BoardService {

	int getTotalPostCount();

	ArrayList<TradingBoardVO> orderByTemp(Pagination pagination);

	ArrayList<TradingBoardVO> orderByDate1(Pagination pagination);

	ArrayList<TradingBoardVO> orderByPrice(Pagination pagination);

	ArrayList<TradingBoardVO> orderByDate2(Pagination pagination);

	ArrayList<TradingBoardVO> orderByDate3(Pagination pagination);

	ArrayList<TradingBoardVO> orderByDate4(Pagination pagination);

}
