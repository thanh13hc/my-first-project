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

import com.Mysite.model.Khoa;
import com.Mysite.model.Lop;
import com.Mysite.model.Sinhvien;
import com.Mysite.service.KhoaService;

@WebServlet(urlPatterns="/khoa")
public class KhoaController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		ApplicationContext context = (ApplicationContext) req.getAttribute("App_Context");
		KhoaService khoaService = (KhoaService) context.getBean("khoaService");
		String page = req.getParameter("page");
		if (page == null)
			showList(req, resp, khoaService);
		else {
			switch (page) {
			case "danh-sach-khoa":
				showList(req, resp, khoaService);
				break;
			case "edit":
				edit(req, resp, khoaService);
				break;
			default:
				showList(req, resp, khoaService);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		ApplicationContext context = (ApplicationContext) req.getAttribute("App_Context");
		KhoaService khoaService = (KhoaService) context.getBean("khoaService");
		String page = req.getParameter("page");

		switch (page) {
		case "edit":
			save(req, resp, khoaService);
			break;
		case "delete":
			delete(req, resp, khoaService);
			break;
		}
	}

	void showList(HttpServletRequest req, HttpServletResponse resp, KhoaService khoaService)
			throws ServletException, IOException {
		List<Khoa> khoa = khoaService.getAll();
		req.setAttribute("khoa", khoa);
		req.getRequestDispatcher("khoa/danh-sach-khoa.jsp").include(req, resp);
	}

	void edit(HttpServletRequest req, HttpServletResponse resp, KhoaService khoaService)
			throws ServletException, IOException {
		int id = Integer.valueOf(req.getParameter("id"));
		Khoa khoa = khoaService.findByID(id);
		req.setAttribute("khoa", khoa);
		req.getRequestDispatcher("khoa/edit.jsp").include(req, resp);
	}

	void save(HttpServletRequest req, HttpServletResponse resp, KhoaService khoaService) throws IOException {
		int maKhoa = Integer.valueOf(req.getParameter("maKhoa"));
		String tenKhoa = req.getParameter("tenKhoa");
		
		List<Lop> lop = khoaService.getLopByKhoaID(maKhoa);
		List<Sinhvien> sv = khoaService.getSinhvienByKhoaID(maKhoa);
		
		Khoa khoa = new Khoa(maKhoa, tenKhoa, lop, sv);
		khoaService.update(khoa);;
		resp.sendRedirect("/MySpringCoreSite/khoa");
	}

	void delete(HttpServletRequest req, HttpServletResponse resp, KhoaService khoaService) throws IOException {
		List<Khoa> allKhoa = (List<Khoa>) khoaService.getAll();
		List<Integer> listID = new ArrayList<Integer>();

		for (Khoa khoa : allKhoa) {
			if (req.getParameter("lop" + khoa.getMaKhoa()) == null)
				listID.add(khoa.getMaKhoa());
		}
		khoaService.delete(listID);
		resp.sendRedirect("/MySpringCoreSite/khoa");
	}
}
