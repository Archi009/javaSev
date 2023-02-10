package com.yedam.member.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Command;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceMybatis;

public class DelMember implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mid = req.getParameter("id");
		
		MemberService service = new MemberServiceMybatis();
		int r = service.removeMember(mid);
		Map<String,Object> resultMap = new HashMap<>();
		Gson gson = new GsonBuilder().create();	
		
			if (r > 0) {
				resultMap.put("retCode", "Success");
			} else {
				resultMap.put("retCode", "Fail");	
			}
		
		return gson.toJson(resultMap) + ".json";
	}

}
