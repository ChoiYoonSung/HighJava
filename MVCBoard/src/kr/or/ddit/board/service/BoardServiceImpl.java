package kr.or.ddit.board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.JDBCUtil;

public class BoardServiceImpl implements IBoardService {
	
//	사용할 DAO의 객체 변수를 선언한다.
//	나중에 implements된 다른 메서드로 편하게 바꾸기 위해 인터페이스 객체 생성함(다형성)
	private IBoardDao boardDao;
//	커넥션 객체 담기위한 메서드 객체 선언
	private Connection conn;
	
	public BoardServiceImpl() {
		boardDao = new BoardDaoImpl();
	}

	@Override
	public int insertBoard(BoardVO bv) {
		int cnt = 0;
		
		try {
			conn = JDBCUtil.getConnection();
			cnt = boardDao.insertBoard(conn, bv);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, null, null, null);
		}
		return cnt;
	}

	@Override
	public boolean checkBoard(Integer boardNo) {
		
		boolean chk = false;
		
		try {
			conn = JDBCUtil.getConnection();
			boardDao.checkBoard(conn, boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, null, null, null);
		}
		return chk;
	}

	@Override
	public List<BoardVO> getAllBoardList() {
		
		List<BoardVO> boardList = new ArrayList<>();
		try {
			conn = JDBCUtil.getConnection();
			boardList = boardDao.getAllBoardList(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, null, null, null);;
		}
		return boardList;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = 0;
		
		try {
			conn = JDBCUtil.getConnection();
			cnt = boardDao.updateBoard(conn, bv);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, null, null, null);
		}
		return cnt;
	}

	@Override
	public int deleteBoard(Integer boardNo) {
		int cnt = 0;
		
		try {
			conn = JDBCUtil.getConnection();
			cnt = boardDao.deleteBoard(conn, boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, null, null, null);
		}
		
		return cnt;
	}
	
	@Override
	public List<BoardVO> getSearchBoard(BoardVO bv) {
		
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			boardList = boardDao.getSearchBoard(conn, bv);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, null, null, null);
		}
			return boardList;
	}

}
