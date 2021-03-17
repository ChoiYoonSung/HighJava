package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.member.vo.MemberVO;

public class MemberIbatisTest {
	public static void main(String[] args) {
		// iBatis를 이용하여 DB자료를 처리하는 작업 순서
		// 1. ibatis의 환경설정파일을 읽어와 실행시킨다.
		try {
			// 1-1. xml 설정문서 읽어오기
			Charset charset = Charset.forName("UTF-8"); //설정파일 인코딩
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			
			// 1-2/. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close();//Reader 객체 닫기
			
			// 2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 직업을 수행한다.
			
			// 2.1 insert작업 연습
			System.out.println("insert 작업 시작");
			
			// 1) 저장할 데이터를 VO에 담는다.
			MemberVO mv = new MemberVO();
			mv.setMemId("B001");;
			mv.setMemName("홍길동");
			mv.setMemTel("010-1111-2222");
			mv.setMemAddr("부산시 해운대구");
			
			// 2) SqlMapClient객체 변수를 이용하여 해당 쿼리문을 실행한다.
			// 형식) smc.insert("namespace값.id값", 파라미터 객체)
			//		반환값 : 성공하면 null이 반환
			Object obj = smc.insert("member.insertMember", mv);
			if(obj == null) {
				System.out.println("insert작업 성공");
			} else {
				System.out.println("insert작업 실패");
			}
			System.out.println("--------------------------------");
			
			// 2-2 update 연습
			System.out.println("update 작업 시작");
			
			MemberVO mv2 = new MemberVO();
			mv2.setMemId("B001");
			mv2.setMemName("name");
			mv2.setMemTel("010-2222-1111");
			mv2.setMemAddr("부산시 사하구");
			
			int cnt = smc.update("member.updateMember",mv2);
			if(cnt > 0) {
				System.out.println("update 작업 성공");
			}else {				
				System.out.println("update 작업 실패");
			}
			System.out.println("--------------------------------");
			
			// 2-3 delete 연습
			System.out.println("delete 작업 시작");
			
			//delete*(메서드의 반환값 : 성공한 레코드 수
			cnt = smc.delete("member.deleteMember", "B001");
			if(cnt > 0) {
				System.out.println("delete 작업 성공");
			} else {
				System.out.println("delete 작업 실패");
			}
			System.out.println("--------------------------------");
			
			//2-4 select연습
			// 1) 응답의 결과가 여러개일 경우
			/*System.out.println("select 연습(결과가 여러개일 경우)");
			List<MemberVO> memList = null;
			
			// 응답 결과가 여러개일 경우에는 queryForList메서드를 사용한다.
			// 이 메서드는 여러개의 레코드를 VO에 담은 후 이 VO데이터를 List에
			// 추가해 주는 작업을 자동으로 수행한다.
			memList = smc.queryForList("member.getMemberAll");
			for (MemberVO mv3 : memList) {
				System.out.println("ID : " + mv3.getMemId());
				System.out.println("NAME : " + mv3.getMemName());
				System.out.println("TEL : " + mv3.getMemTel());
				System.out.println("ADDR : " + mv3.getMemAddr());
				System.out.println("-------------------------");
			}
			System.out.println("출력 완료(결과 여러개)");*/
			
			// 2) 응답이 1개일 경우
			System.out.println("select 연습 시작(결과가 1개일 경우)");
			
			// 응답 결과가 1개가 확실할 경우에는 queryForObject 메서드 사용한다.
			MemberVO mv4 = (MemberVO) smc.queryForObject("member.getMember", "A001");
			
			System.out.println("ID : " + mv4.getMemId());
			System.out.println("NAME : " + mv4.getMemName());
			System.out.println("TEL : " + mv4.getMemTel());
			System.out.println("ADDR : " + mv4.getMemAddr());
			System.out.println("-------------------------");
			System.out.println("출력완료(결과 1개)");
			
			
			
		}catch(IOException e){
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
