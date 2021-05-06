package kr.or.ddit.board.handler;

import java.util.List;

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

public class UpdateBoardHandler implements CommandHandler{

	private static final String VIEW_PAGE = "/WEB-INF/view/board/update.jsp";
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		if(req.getMethod().equals("GET")){
			return false;
		}else {
			return true;
		}
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(req.getMethod().equals("GET")) {
			long boardSeq = Long.parseLong(req.getParameter("boardSeq"));
			
			IBoardService boardService = BoardServiceImpl.getInstance();
			BoardVO bv = boardService.getBoard(boardSeq);
			
			if(bv.getAtchFileId() > 0) {
				AtchFileVO fileVO = new AtchFileVO();
				fileVO.setAtchFileId(bv.getAtchFileId());
				
				IAtchFileService atchFileService= AtchFileServiceImpl.getInstance();
				List<AtchFileVO> atchFileList = atchFileService.getAtchFileList(fileVO);
				
				req.setAttribute("atchFileList", atchFileList);
			}
			req.setAttribute("boardVO", bv);
			
			return VIEW_PAGE;
		} else {
			FileItem item = ((FileUploadRequestWrapper)req).getFileItem("atchFileId");
			AtchFileVO atchFileVO = new AtchFileVO();
			
			atchFileVO.setAtchFileId(req.getParameter
					("atchFileId") == null ? -1: Long.parseLong(req.getParameter("atchFileId")));
			
			if(item != null && !item.getName().equals("")) {
				IAtchFileService fileService = AtchFileServiceImpl.getInstance();
				atchFileVO = fileService.saveAtchFile(item); 
			}
			
			IBoardService boardService = BoardServiceImpl.getInstance();
			long boardSeq = Long.parseLong(req.getParameter("boardSeq"));
			
			BoardVO bv = new BoardVO();
			bv.setBoardSeq(boardSeq);
			bv.setBoardTitle(req.getParameter("boardTitle"));
			bv.setBoardContent(req.getParameter("boardContent"));
			bv.setAtchFileId(atchFileVO.getAtchFileId());
			
			boardService.updateBoard(bv);
			
			String redirectUrl = req.getContextPath() + "/select.do?boardSeq=" + boardSeq;
			
			return redirectUrl;
		}
	}
}
