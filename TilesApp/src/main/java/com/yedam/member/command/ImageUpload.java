package com.yedam.member.command;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Command;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceMybatis;
import com.yedam.member.vo.MemberVO;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;
import com.yedam.notice.vo.NoticeVO;

public class ImageUpload implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String savePath = req.getServletContext().getRealPath("/upload");
		int maxSize = (1024*1024*10);
		String encoding = "utf-8";
		
		try {
			MultipartRequest multi = //
			new MultipartRequest(req,savePath,maxSize,encoding,new DefaultFileRenamePolicy());
			String id = multi.getParameter("id");
			String fileName = "";		
			
					Enumeration<?>files = multi.getFileNames();
			while(files.hasMoreElements()) {
				String file =(String) files.nextElement();
				System.out.println(file);
				fileName = multi.getFilesystemName(file); //바뀐 파일이름을 읽을때
			}
			
			MemberVO vo = new MemberVO();
			vo.setMemberId(id);
			vo.setImage(fileName);
			MemberService service = new MemberServiceMybatis();
			service.modifyMember(vo);
			
			System.out.println(vo);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		//{"retCode":"Success"}
		return "{\"retCode\":\"Success\"}";
	
	}

}
