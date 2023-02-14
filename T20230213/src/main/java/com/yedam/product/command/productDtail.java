package com.yedam.product.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.product.service.ProductService;
import com.yedam.product.service.ProductServiceMybatis;

public class productDtail implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pid = req.getParameter("pid");

		ProductService service = new ProductServiceMybatis();

		req.setAttribute("vo", service.getProduct(pid));
		System.out.println(service.getProduct(pid));
		
		req.setAttribute("list",service.relateList());
		
		return "product/product.tiles";
	}

}
