package kr.or.ddit.board.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.SqlMapClientUtil;

public class BoardServiceImpl implements IBoardService {
	
	private IBoardDao boardDao;
	private SqlMapClient smc;
	
	private static IBoardService boardService;
	
	private BoardServiceImpl() {
		boardDao = BoardDaoImpl.getInstance();
		smc = SqlMapClientUtil.getInstance();
	}
	
	public static IBoardService getInstance() {
		if(boardService == null) {
			boardService = new BoardServiceImpl();
		}
		return boardService;
	}
	
	@Override
	public int insertBoard(BoardVO bv) {
		int cnt = 0;
		try {
			cnt = boardDao.insertBoard(smc, bv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = 0;
		try {
			cnt = boardDao.updateBoard(smc, bv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteBoard(long boardSeq) {
		int cnt = 0;
		try {
			cnt = boardDao.deleteBoard(smc, boardSeq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public BoardVO getBoard(long boardSeq) {
		BoardVO bv = null;
		try {
			bv = boardDao.getBoard(smc, boardSeq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bv;
	}

	@Override
	public List<BoardVO> getBoardList() {
		List<BoardVO> list = new ArrayList<>();
		try {
			list = boardDao.getBoardList(smc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BoardVO> searchBoard(BoardVO bv) {
		List<BoardVO> list = new ArrayList<>();
		try {
			list = boardDao.searchBoard(smc, bv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int countHits(long boardSeq) {
		int cnt = 0;
		try {
			cnt = boardDao.countHits(smc, boardSeq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

}
