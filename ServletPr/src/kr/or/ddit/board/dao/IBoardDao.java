package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardDao {
	
	/**
	 * 게시글 작성
	 * @param smc SQLMapClient 객체
	 * @param bv DB에 insert할 자료가 저장된 BoardVO 객체
	 * @return 성공 : 1, 실패 : 0 반환
	 * @throws SQLException JDBC 예외 발생 시 처리
	 */
	public int insertBoard(SqlMapClient smc, BoardVO bv) throws SQLException;
	
	/**
	 * 게시글 수정
	 * @param smc SQLMapClient 객체
	 * @param bv 수정 정보 객체
	 * @return 성공 : 1, 실패 : 0 반환
	 * @throws SQLException JDBC 예외 발생 시 처리
	 */
	public int updateBoard(SqlMapClient smc, BoardVO bv) throws SQLException;
	
	/**
	 * 게시글 삭제
	 * @param smc SQLMapClient 객체
	 * @param boardSeq 삭제할 게시글 번호
	 * @return 성공 : 1, 실패 : 0 반환
	 * @throws SQLException JDBC 예외 발생 시 처리
	 */
	public int deleteBoard(SqlMapClient smc, long boardSeq) throws SQLException;
	
	/**
	 * 게시글 조회
	 * @param smc SQLMapClient 객체
	 * @param boardSeq 조회할 게시글 번호
	 * @return 조회된 게시글
	 * @throws SQLException JDBC 예외 발생 시 처리
	 */
	public BoardVO getBoard(SqlMapClient smc, long boardSeq) throws SQLException;
	
	/**
	 * 전체 게시글 조회
	 * @param smc SQLMapClient 객체
	 * @return 전체 게시글 List
	 * @throws SQLException JDBC 예외 발생 시 처리
	 */
	public List<BoardVO>getBoardList(SqlMapClient smc) throws SQLException;
	
	/**
	 * 게시글 검색
	 * @param smc SQLMapClient 객체
	 * @param bv 검색 조건이 담긴 BoardVO 객체
	 * @return 검색된 결과를 담은 List
	 * @throws SQLException JDBC 예외 발생 시 처리
	 */
	public List<BoardVO>searchBoard(SqlMapClient smc, BoardVO bv) throws SQLException;
	
	/**
	 * 게시글 조회 시 조회수를 올려주는 메서드
	 * @param smc SQLMapClient 객체
	 * @param boardSeq 조회수를 올릴 게시글 번호
	 * @return 조회수 +1
	 * @throws SQLException JDBC 예외 발생 시 처리
	 */
	public int countHits(SqlMapClient smc, long boardSeq) throws SQLException;
	
}
