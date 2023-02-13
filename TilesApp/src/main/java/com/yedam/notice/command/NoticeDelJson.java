package com.yedam.notice.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;

public class NoticeDelJson implements Command {


		public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//댓글번호 삭제.
			String nid = req.getParameter("nid");
			String json = "";	
			System.out.println(nid);
			
			NoticeService service = new NoticeServiceImpl();
			if(service.reNotice(Integer.parseInt(nid))>0) {
				//{"retCode":"Success"}
				json = "{\"retCode\":\"Success\"}";
			}else {
				//{"retCode":"Fail"}
				json = "{\"retCode\":\"Fail\"}";
			}
			
			
			return json+".json";
		}
	

}
