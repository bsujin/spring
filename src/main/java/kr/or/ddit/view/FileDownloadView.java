package kr.or.ddit.view;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

// controller에서 개발자가 model 객체에 realfilename = 실제 디스크에 저장된 경로와 파일명을,
// 								  filename = 업로드 당시의 파일명을 속성으로 저장한다 
public class FileDownloadView extends AbstractView{

	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// realFilename의 값 - d:\\upload\\sally.png 
		String realFilename = (String) model.get("realfilename");
		String filename = (String) model.get("filename");

		// header의 값을 가져온다 
		response.setHeader("Content-Disposition", "attachement; filename=" + filename);
		
		ServletOutputStream sos =  response.getOutputStream();
		
		FileInputStream fis = new FileInputStream(new File(realFilename));
		// 읽을 단위를 설정
		byte[] buf = new byte[512];
		// 끝이 아닐때 까지 읽는다 
		while(fis.read(buf) != -1) {
			sos.write(buf);
		}
		//사용한 자원닫기 
		fis.close();
		sos.close();
	}
	

}
