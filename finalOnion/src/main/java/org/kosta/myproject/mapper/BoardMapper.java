package org.kosta.myproject.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.kosta.myproject.vo.Pagination;
import org.kosta.myproject.vo.TradingBoardVO;

@Mapper
public interface BoardMapper {

	int getTotalPostCount();

	ArrayList<TradingBoardVO> orderByTemp(Pagination pagination);
	
	ArrayList<TradingBoardVO> orderByDate(Pagination pagination);

	ArrayList<TradingBoardVO> orderByPrice(Pagination pagination);

	void posting(TradingBoardVO tradingBoardVO);

	int getTotalSalePostCount();

	ArrayList<TradingBoardVO> orderBySaleDate(Pagination pagination);

	ArrayList<TradingBoardVO> orderBySaleTemp(Pagination pagination);

	ArrayList<TradingBoardVO> orderBySalePrice(Pagination pagination);

	TradingBoardVO postdetail(int boardNo);
	
	TradingBoardVO findtradingboardbyno(int boardNo);
	
	void updating(TradingBoardVO tradingBoardVO);

}
