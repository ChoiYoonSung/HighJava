package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardDao {
	public int InsertBoard(SqlMapClient smc, BoardVO board) throws SQLException;
	public int UpdateBoard(SqlMapClient smc, BoardVO board) throws SQLException;
	public int DeleteBoard(SqlMapClient smc, long boardNum) throws SQLException;
	public List<BoardVO> GetAllBoard(SqlMapClient smc) throws SQLException;
	public BoardVO SelectBoard(SqlMapClient smc, long boardNum) throws SQLException;
}
