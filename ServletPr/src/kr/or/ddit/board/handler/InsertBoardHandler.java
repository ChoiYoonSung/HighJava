package kr.or.ddit.board.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.common.handler.CommandHandler;
import kr.or.ddit.common.service.AtchFileServiceImpl;
import kr.or.ddit.common.service.IAtchFileService;
import kr.or.ddit.common.vo.AtchFileVO;
import kr.or.ddit.util.FileUploadRequestWrapper;

public class InsertBoardHandler implements CommandHandler{

	private static final String VIEW_PAGE = "/WEB-INF/view/board/insert.jsp";
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		if(req.getMethod().equals("GET")) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(req.getMethod().equals("GET"))	{
			return VIEW_PAGE;
		}else {
			
			FileItem item = ((FileUploadRequestWrapper)req).getFileItem("atchFileId")
					== null?null:((FileUploadRequestWrapper)req).getFileItem("atchFileId");
			
			AtchFileVO atchFileVO = new AtchFileVO();
			if(item != null) {
				IAtchFileService fileService = AtchFileServiceImpl.getInstance();
				atchFileVO = fileService.saveAtchFile(item);
			}
			
			IBoardService boardService = BoardServiceImpl.getInstance();
			BoardVO bv = new BoardVO();
			
			bv.setBoardTitle(req.getParameter("boardTitle"));
			bv.setBoardWriter(req.getParameter("boardWriter"));
			bv.setBoardContent(req.getParameter("boardContent"));
			bv.setAtchFileId(atchFileVO.getAtchFileId());

			boardService.insertBoard(bv);
			
			String redirectUrl = req.getContextPath() + "/list.do";
			
			return redirectUrl;
		}
	}
}
