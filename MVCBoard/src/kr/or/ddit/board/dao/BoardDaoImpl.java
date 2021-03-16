package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.JDBCUtil;

public class BoardDaoImpl implements IBoardDao{

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Override
	public int insertBoard(Connection conn, BoardVO bv) throws SQLException {
		int cnt = 0;
		try {			
			String sql = "insert into jdbc_board (board_no, board_title, board_content, board_writer, board_date)"
					+"values (board_seq.nextVal,?,?,?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getBoardTitle());
			pstmt.setString(2, bv.getBoardContent());
			pstmt.setString(3, bv.getBoardWriter());
			
			cnt = pstmt.executeUpdate();
		} finally {
			JDBCUtil.disConnect(null, null, pstmt, null);
		}
		
		return cnt;
	}

	@Override
	public boolean checkBoard(Connection conn, Integer boardNo) throws SQLException {
		
		boolean chk = false;
		
		try {
			String sql ="select count(*) cnt from jdbc_board where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			while(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			if(cnt > 0) {
				chk = true;
			}			
		} finally {
			JDBCUtil.disConnect(null, null, pstmt, rs);
		}
		return chk;
	}

	@Override
	public List<BoardVO> getAllBoardList(Connection conn) throws SQLException {
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try {
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("select * from jdbc_board");
			
			while(rs.next()) {
				BoardVO bv = new BoardVO();
				
				int boardNo = rs.getInt("board_no");
				String boardTitle = rs.getString("board_title");
				String boardContent = rs.getString("board_content");
				String boardWriter = rs.getString("board_writer");
				String boardDate = rs.getString("board_date");	
				
				bv.setBoardNo(boardNo);
				bv.setBoardTitle(boardTitle);
				bv.setBoardContent(boardContent);
				bv.setBoardWriter(boardWriter);
				bv.setBoardDate(boardDate);
				
				boardList.add(bv);
			}
			
		} finally {
			JDBCUtil.disConnect(null, stmt, null, rs);
		}
		return boardList;
	}

	@Override
	public int updateBoard(Connection conn, BoardVO bv) throws SQLException {
		int cnt = 0; 
		
		try {
			
			String sql = "update jdbc_board" 
					+ " set board_title = ?"
					+ "	   ,board_content = ?" 
					+ "    ,board_date= sysdate" 
					+ "where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getBoardTitle());
			pstmt.setString(2, bv.getBoardContent());
			pstmt.setInt(3, bv.getBoardNo());
			
			
			cnt = pstmt.executeUpdate();
		} finally {
			JDBCUtil.disConnect(null, null, pstmt, null);
		}
		
		return cnt;
	}

	@Override
	public int deleteBoard(Connection conn, Integer boardNo) throws SQLException {
		int cnt = 0;
		
		try {	
			String sql = "delete from jdbc_board where board_no = ?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			cnt = pstmt.executeUpdate();
		} finally {
			JDBCUtil.disConnect(null, null, pstmt, null);
		}
		
		return cnt;
	}
	
	@Override
	public List<BoardVO> getSearchBoard(Connection conn, BoardVO bv) throws SQLException {
		
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			String sql = "select * from jdbc_board where 1=1 ";
			//각 정보에 대해 존재 유무에 따른 쿼리 추가(ID, 이름, 전화번호, 주소)
			//Dynamic Query
			
			if(bv.getBoardTitle() != null && !bv.getBoardTitle().equals("")) {
				sql += "and board_title = like '%' || ? || '%' ";
			}
			if(bv.getBoardContent() != null && !bv.getBoardContent().equals("")) {
				sql += "and board_content = like '%' || ? || '%' ";
			}
			if(bv.getBoardWriter() != null && !bv.getBoardWriter().equals("")) {
				sql += "and board_writer = like '%' || ? || '%' ";
			}
			if(bv.getBoardDate() != null && !bv.getBoardDate().equals("")) {
				sql += "and board_date like '%' || ? || '%' ";
			}
			pstmt = conn.prepareStatement(sql);
			
			int index = 1;
			if(bv.getBoardTitle() != null && !bv.getBoardTitle().equals("")) {
				pstmt.setString(index++, bv.getBoardTitle());
			}
			if(bv.getBoardContent() != null && !bv.getBoardContent().equals("")) {
				pstmt.setString(index++, bv.getBoardContent());
			}
			if(bv.getBoardWriter() != null && !bv.getBoardWriter().equals("")) {
				pstmt.setString(index++, bv.getBoardWriter());
			}
			if(bv.getBoardDate() != null && !bv.getBoardDate().equals("")) {
				pstmt.setString(index++, bv.getBoardDate());
			}
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO bv2 = new BoardVO();				
				bv2.setBoardNo(rs.getInt("board_no"));
				bv2.setBoardTitle(rs.getString("board_title"));
				bv2.setBoardContent(rs.getString("board_content"));
				bv2.setBoardWriter(rs.getString("board_writer"));
				bv2.setBoardDate(rs.getString("board_date"));
				
				boardList.add(bv2);
			}
		} finally {
			JDBCUtil.disConnect(null, null, pstmt, rs);
		}
		
		return boardList;
	}

}
