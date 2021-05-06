package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.SqlMapClientUtil;

public interface IBoardService {
	public int InsertBoard( BoardVO board);
	public int UpdateBoard(BoardVO board);
	public int DeleteBoard(long boardNum);
	public List<BoardVO> InsertBoard();
	public BoardVO SelectBoard(long boardNum);
}
