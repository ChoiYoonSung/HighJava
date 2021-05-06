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
	public int insertBoard(SqlMapClient smc, BoardVO bv) throws SQLException {
		int cnt = 0;
		
		Object obj = smc.insert("board.insertBoard", bv);
		
		if(obj == null) {
			cnt = 1;
		}
		return cnt;
	}

	@Override
	public int updateBoard(SqlMapClient smc, BoardVO bv) throws SQLException {
		return (int) smc.update("board.updateBoard", bv);
	}

	@Override
	public int deleteBoard(SqlMapClient smc, long boardSeq) throws SQLException {
		return (int) smc.delete("board.deleteBoard", boardSeq);
	}

	@Override
	public BoardVO getBoard(SqlMapClient smc, long boardSeq) throws SQLException {
		return (BoardVO) smc.queryForObject("board.getBoard", boardSeq);
	}

	@Override
	public List<BoardVO> getBoardList(SqlMapClient smc) throws SQLException {
		List<BoardVO> list = smc.queryForList("board.getBoardList");
		return list;
	}

	@Override
	public List<BoardVO> searchBoard(SqlMapClient smc, BoardVO bv) throws SQLException {
		List<BoardVO> list = smc.queryForList("board.searchBoard", bv);
		return list;
	}

	@Override
	public int countHits(SqlMapClient smc, long boardSeq) throws SQLException {
		return (int) smc.update("board.countHits", boardSeq);
	}

}
