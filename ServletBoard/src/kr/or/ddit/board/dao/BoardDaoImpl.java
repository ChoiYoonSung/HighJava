package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao{

	private static IBoardDao boardDao;
	
	private BoardDaoImpl() {
	}
	
	public static IBoardDao getInstance() {
		if(boardDao == null) {
			boardDao = new BoardDaoImpl();
		}
		return boardDao;
	}
	
	@Override
	public int InsertBoard(SqlMapClient smc, BoardVO board) throws SQLException {
		int cnt = 0;
		Object obj = smc.insert("board.InsertBoard", board);
		if(obj == null) {
			cnt = 1;
		}
		return cnt;
	}

	@Override
	public int UpdateBoard(SqlMapClient smc, BoardVO board) throws SQLException {
		int cnt = 0;
		Object obj = smc.insert("board.UpdateBoard", board);
		if(obj == null) {
			cnt = 1;
		}
		return cnt;
	}

	@Override
	public int DeleteBoard(SqlMapClient smc, long boardNum) throws SQLException {
		int cnt = 0;
		Object obj = smc.insert("board.DeleteBoard", boardNum);
		if(obj == null) {
			cnt = 1;
		}
		return cnt;
	}

	@Override
	public List<BoardVO> GetAllBoard(SqlMapClient smc) throws SQLException {
		List<BoardVO> boardList = smc.queryForList("board.GetAllBoard");
		return boardList;
	}

	@Override
	public BoardVO SelectBoard(SqlMapClient smc, long boardNum) throws SQLException {
		BoardVO boardVO = (BoardVO) smc.queryForObject("board.SelectBoard");
		return boardVO;
	}
	
}
