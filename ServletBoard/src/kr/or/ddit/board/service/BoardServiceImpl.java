package kr.or.ddit.board.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.SqlMapClientUtil;

public class BoardServiceImpl implements IBoardService{
	
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
	public int InsertBoard(BoardVO board) {
		int cnt = 0;
		try {
			cnt = boardDao.InsertBoard(smc, board);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int UpdateBoard(BoardVO board) {
		int cnt = 0;
		try {
			cnt = boardDao.UpdateBoard(smc, board);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int DeleteBoard(long boardNum) {
		int cnt = 0;
		try {
			cnt = boardDao.DeleteBoard(smc, boardNum);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<BoardVO> InsertBoard() {
		List<BoardVO> boardList = new ArrayList<>();
		try {
			boardList = boardDao.GetAllBoard(smc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardList;
	}

	@Override
	public BoardVO SelectBoard(long boardNum) {
		BoardVO boardVO = new BoardVO();
		try {
			boardVO = boardDao.SelectBoard(smc, boardNum);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardVO;
	}
}
