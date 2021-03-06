package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * JSON : JavaScript Object Notation
 * 
 * - JSON 에서 value값으로 가능한 데이터 타입
 * 	1. String
 *  2. number
 *  3. Object(JSON object)
 *  4. array (JSON Object 안에 배열이 들어가 있는 형태)
 *  5. boolean
 *  6. null
 *  
 */
public class JSONSimpleWriterTest {
	public static void main(String[] args) throws IOException {
		
		// JSON 데이터 설정
		JSONObject jsonObj = new JSONObject();
		
		jsonObj.put("name", "Gil-dong Hong");
		jsonObj.put("job", "Student");
		jsonObj.put("age", 25);
		jsonObj.put("addr", "대전 중구 대흥동");
		
		// JSONArray 데이터 설정
		JSONArray singerList = new JSONArray();
		
		JSONObject singer = new JSONObject();
		singer.put("name", "싸이");
		singer.put("age", 40);
		singer.put("gender", "남자");
		singerList.add(singer);
		
		singer = new JSONObject();
		singer.put("name", "비욘세");
		singer.put("age", 44);
		singer.put("gender", "여자");
		singerList.add(singer);
		
		singer = new JSONObject();
		singer.put("name", "BTS");
		singer.put("age", 30);
		singer.put("gender", "남자");
		singerList.add(singer);
		
		jsonObj.put("singerList", singerList);
		
		FileWriter fw = new FileWriter("D:/D_Other/myJsonFile.txt");
		fw.write(jsonObj.toString());
		fw.flush();
		fw.close();
		
		System.out.println("JSON객체 내용 출력 : " + jsonObj);
		
		
	}
}
