package kr.or.ddit.board.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.common.handler.CommandHandler;

public class GetBoardListHandler implements CommandHandler{
	
	private static final String VIEW_PAGE = "/WEB-INF/view/board/list.jsp";
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		IBoardService service = BoardServiceImpl.getInstance();
		List<BoardVO> list = service.getBoardList();
		req.setAttribute("list", list);
		
		return VIEW_PAGE;
	}

}
