package kr.or.ddit.board.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.common.handler.CommandHandler;

public class GetBoardHandler implements CommandHandler{

	private static final String VIEW_PAGE = "/WEB-INF/view/board/select.jsp";
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		long boardSeq = Long.parseLong(req.getParameter("boardSeq"));

		IBoardService service = BoardServiceImpl.getInstance();
		BoardVO bv = service.getBoard(boardSeq);
		req.setAttribute("boardVO", bv);
		
		service.countHits(boardSeq);
		
		return VIEW_PAGE;
	}

}
