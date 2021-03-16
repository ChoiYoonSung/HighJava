package kr.or.ddit.board;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.vo.BoardVO;

/*
	회원정보를 관리하는 프로그램을 작성하는데 
	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
	(DB의 MYBoard테이블을 이용하여 작업한다.)
	
	* 자료 삭제는 회원ID를 입력 받아서 삭제한다.
	
	예시메뉴)
	----------------------
		== 작업 선택 ==
		1. 자료 입력			---> insert
		2. 자료 삭제			---> delete
		3. 자료 수정			---> update
		4. 전체 자료 출력	---> select
		5. 작업 끝.
	----------------------
	 
	   
// 회원관리 프로그램 테이블 생성 스크립트 
create table myBoard(
    board_title varchar2(8) not null,  -- 회원ID
    board_writer varchar2(100) not null, -- 이름
    board_date varchar2(50) not null, -- 전화번호
    board_content varchar2(128),    -- 주소
    CONSTRAINT MYBoard_PK PRIMARY KEY (board_title)
);

*/
public class BoardMain {
	
	// 서비스객체 멤버변수 선언
	private IBoardService boardService;
	
	public BoardMain() {
		boardService = new BoardServiceImpl();
	}
	
	private Scanner scan = new Scanner(System.in); 
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 자료 검색");
		System.out.println("  5. 전체 자료 출력");
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 자료 입력
					insertBoard();
					break;
				case 2 :  // 자료 삭제
					deleteBoard();
					break;
				case 3 :  // 자료 수정
					updateBoard();
					break;
				case 4:   //자료 검색
					searchBoard();
					break;
				case 5 :  // 전체 자료 출력
					displayAll();
					break;
				case 6 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=6);
	}
	
	private void searchBoard() {
		scan.nextLine(); //입력버퍼 지우기
		System.out.println();
		System.out.println("게시물 정보를 입력하세요.");
		System.out.print("제목 >>");
		String boardTitle = scan.nextLine().trim(); //trim()문장 맨 좌,우측 공백 제거
		
		System.out.print("작성자 >>");
		String boardWriter = scan.nextLine().trim(); 
		
		System.out.print("날짜 >>");
		String boardDate = scan.nextLine().trim(); 
		
		System.out.print("내용 >>");
		String boardContent = scan.nextLine().trim();
		
		BoardVO bv = new BoardVO();
		bv.setBoardTitle(boardTitle);
		bv.setBoardWriter(boardWriter);
		bv.setBoardDate(boardDate);
		bv.setBoardContent(boardContent);
		
		List<BoardVO> boardList = boardService.getSearchBoard(bv);
		
		System.out.println();
		System.out.println("----------------------------------------");
		System.out.println(" No\t제  목\t작성자\t내  용\ts날짜");
		System.out.println("----------------------------------------");
		
		for(BoardVO bv2: boardList) {
			System.out.println(bv2.getBoardNo() +"\t" +bv2.getBoardTitle() + "\t" + bv2.getBoardWriter() 
							+ "\t" + bv2.getBoardContent() + "\t" + bv2.getBoardDate());		
			System.out.println("----------------------------------------");
		}
		System.out.println("=========================================");
	}

	/*
	 * 회원정보를 삭제하는 메서드 (입력받은 회원ID를 이용하여 삭제한다.)
	 */
	private void deleteBoard() {
		scan.nextLine(); //입력버퍼 지우기
		System.out.println();
		System.out.println("삭제할 게시물 번호를 입력하세요.");
		System.out.print("번호 >> ");
		int boardNo = scan.nextInt();
		
		int cnt = boardService.deleteBoard(boardNo);
	
		if(cnt > 0) {
			System.out.println(boardNo + "번 게시글의 정보 삭제 작업 성공");
		}else {
			System.out.println("게시글 정보 삭제 작업 실패");
		}
	}
	

	/*
	 * 회원정보를 수정하는 메서드
	 */
	private void updateBoard() {
		scan.nextLine(); //입력버퍼 지우기
		boolean chk = false;
		int boardNo;
		
		do {
			System.out.println();
			System.out.println("수정할 게시물 정보를 입력하세요.");
			System.out.print("게시물 번호>> ");
			boardNo = scan.nextInt();
			
			chk = boardService.checkBoard(boardNo);
			
			if(chk == false) {
				System.out.println(boardNo + "번 게시물이 없습니다.");
				System.out.println("다시 입력해 주세요.");
			}
		}while(chk == false);
		
		System.out.print("제목 >>");
		String boardTitle = scan.next();
		
		System.out.print("내용 >>");
		String boardContent = scan.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setBoardTitle(boardTitle);
		bv.setBoardContent(boardContent);
		bv.setBoardNo(boardNo);
		
		int cnt = boardService.updateBoard(bv);
			
		if(cnt > 0) {
			System.out.println(boardNo + "번 게시물 수정 작업 성공");
		}else {
			System.out.println("게시물 수정 작업 실패");
		}
	}		
		
	

	/*
	 * 전체 회원을 출력하는 메서드
	 */
	private void displayAll() {
		System.out.println();
		System.out.println("----------------------------------------");
		System.out.println(" No\t제목\t작성자\t작성일");
		System.out.println("----------------------------------------");
		
		List<BoardVO> boardList = boardService.getAllBoardList();
		
		for(BoardVO bv: boardList) {
			System.out.println(bv.getBoardNo() + "\t" + bv.getBoardTitle() + "\t" + bv.getBoardWriter() + "\t" 
							 + bv.getBoardDate());		
		}
		System.out.println("----------------------------------------");
	}
			
		
	

	/*
	 * 회원을 추가하기 위한 메서드
	 */
	private void insertBoard() {
		scan.nextLine(); //입력버퍼 지우기
		
		System.out.println();
		System.out.println("게시글을 추가합니다..");
		System.out.print("제목 >> ");
		String boardTitle = scan.next();
			
		System.out.print("내용 >>");
		String boardContent = scan.next();
		
		System.out.print("작성자 >>");
		String boardWriter = scan.next();
		
		BoardVO bv = new BoardVO();
		bv.setBoardTitle(boardTitle);
		bv.setBoardContent(boardContent);
		bv.setBoardWriter(boardWriter);
		
		int cnt = boardService.insertBoard(bv);
			
		if(cnt > 0) {
			System.out.println("게시물이 추가되었습니다.");
		}else {
			System.out.println("게시물 추가에 실패하셨습니다.");
		}
	}

	public static void main(String[] args) {
		new BoardMain().start(); 
	}
}






