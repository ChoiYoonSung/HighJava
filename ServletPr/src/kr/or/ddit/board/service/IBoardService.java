package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardService {

	/**
	 * 게시글 작성
	 * @param bv DB에 insert할 자료가 저장된 BoardVO 객체
	 * @return 성공 : 1, 실패 : 0 반환
	 */
	public int insertBoard(BoardVO bv);
	
	/**
	 * 게시글 수정
	 * @param bv 수정 정보 객체
	 * @return 성공 : 1, 실패 : 0 반환
	 */
	public int updateBoard(BoardVO bv);
	
	/**
	 * 게시글 삭제
	 * @param boardSeq 삭제할 게시글 번호
	 * @return 성공 : 1, 실패 : 0 반환
	 */
	public int deleteBoard(long boardSeq);
	
	/**
	 * 게시글 조회
	 * @param boardSeq 조회할 게시글 번호
	 * @return 조회된 게시글
	 */
	public BoardVO getBoard(long boardSeq);
	
	/**
	 * 전체 게시글 조회
	 * @param smc SQLMapClient 객체
	 * @return 전체 게시글 List
	 */
	public List<BoardVO>getBoardList();
	
	/**
	 * 게시글 검색
	 * @param bv 검색 조건이 담긴 BoardVO 객체
	 * @return 검색된 결과를 담은 List
	 */
	public List<BoardVO>searchBoard(BoardVO bv);
	
	/**
	 * 게시글 조회 시 조회수를 올려주는 메서드
	 * @param boardSeq 조회수를 올릴 게시글 번호
	 * @return 조회수 +1
	 */
	public int countHits(long boardSeq);
	
}
