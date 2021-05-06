package kr.or.ddit.board.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.common.handler.CommandHandler;

public class DeleteBoardHandler implements CommandHandler {

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return true;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		IBoardService service = BoardServiceImpl.getInstance();
		service.deleteBoard(Long.parseLong(req.getParameter("boardSeq")));
	
		return req.getContextPath() + "/list.do";
	}
}
