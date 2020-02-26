package com.Mysite.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import com.Mysite.model.*;
import com.Mysite.service.SinhvienService;

@WebServlet(urlPatterns = "/sinhvien")
public class SinhvienController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		ApplicationContext context = (ApplicationContext) req.getAttribute("App_Context");
		SinhvienService sinhvienService = (SinhvienService) context.getBean("sinhvienService");
		String page = req.getParameter("page");
		if (page == null)
			showList(req, resp, sinhvienService);
		else {
			switch (page) {
			case "danh-sach-sv":
				showList(req, resp, sinhvienService);
				break;
			case "add":
				add(req, resp);
				break;
			case "edit":
				edit(req, resp, sinhvienService);
				break;
			default:
				showList(req, resp, sinhvienService);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		ApplicationContext context = (ApplicationContext) req.getAttribute("App_Context");
		SinhvienService sinhvienService = (SinhvienService) context.getBean("sinhvienService");
		String page = req.getParameter("page");

		switch (page) {
		case "add":
			add(req, resp, sinhvienService);
			break;
		case "edit":
			save(req, resp, sinhvienService);
			break;
		case "delete":
			delete(req, resp, sinhvienService);
			break;
		}
	}

	void showList(HttpServletRequest req, HttpServletResponse resp, SinhvienService sinhvienService)
			throws ServletException, IOException {
		List<Sinhvien> std = sinhvienService.getAll();
		req.setAttribute("std", std);
		req.getRequestDispatcher("sinhvien/danh-sach-sv.jsp").include(req, resp);
	}

	void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("sinhvien/addSv.jsp").include(req, resp);
	}

	void edit(HttpServletRequest req, HttpServletResponse resp, SinhvienService sinhvienService)
			throws ServletException, IOException {
		int id = Integer.valueOf(req.getParameter("id"));
		Sinhvien sv = sinhvienService.findByID(id);
		if (sv == null)
			System.out.println("null");
		req.setAttribute("sv", sv);
		req.getRequestDispatcher("sinhvien/editSv.jsp").include(req, resp);
	}

	private void add(HttpServletRequest req, HttpServletResponse resp, SinhvienService sinhvienService) throws IOException {
		int msv = Integer.valueOf(req.getParameter("msv"));
		String ho = req.getParameter("ho");
		String ten = req.getParameter("ten");
		int maKhoa = Integer.valueOf(req.getParameter("maKhoa"));
		int maLop = Integer.valueOf(req.getParameter("maLop"));
		Sinhvien sv = new Sinhvien(msv, ho, ten, new Khoa(maKhoa), new Lop(maLop));
		sinhvienService.add(sv);
		resp.sendRedirect("/MySpringCoreSite/sinhvien");
	}

	
	void save(HttpServletRequest req, HttpServletResponse resp, SinhvienService sinhvienService) throws IOException {
		int msv = Integer.valueOf(req.getParameter("msv"));
		String ho = req.getParameter("ho");
		String ten = req.getParameter("ten");
		int maKhoa = Integer.valueOf(req.getParameter("khoaSv"));
		int maLop = Integer.valueOf(req.getParameter("lopSv"));

		Sinhvien sv = new Sinhvien(msv, ho, ten, new Khoa(maKhoa), new Lop(maLop));
		sinhvienService.update(sv);
		resp.sendRedirect("/MySpringCoreSite/sinhvien");
	}

	void delete(HttpServletRequest req, HttpServletResponse resp, SinhvienService sinhvienService) throws IOException {
		List<Sinhvien> allSV = (List<Sinhvien>) sinhvienService.getAll();
		List<Integer> listID = new ArrayList<Integer>();

		for (Sinhvien sv : allSV) {
			if (req.getParameter("sv" + sv.getMsv()) == null)
				listID.add(sv.getMsv());
		}
		sinhvienService.delete(listID);
		resp.sendRedirect("/MySpringCoreSite/sinhvien");
	}
}
