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
import com.Mysite.service.LopService;
import com.Mysite.service.SinhvienService;


@WebServlet("/lop")
public class LopController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		ApplicationContext context = (ApplicationContext) req.getAttribute("App_Context");
		LopService lopService = (LopService) context.getBean("lopService");
		String page = req.getParameter("page");
		if (page == null)
			showList(req, resp, lopService);
		else {
			switch (page) {
			case "danh-sach-lop":
				showList(req, resp, lopService);
				break;
			case "edit":
				edit(req, resp, lopService);
				break;
			default:
				showList(req, resp, lopService);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		ApplicationContext context = (ApplicationContext) req.getAttribute("App_Context");
		LopService lopService = (LopService) context.getBean("lopService");
		String page = req.getParameter("page");

		switch (page) {
		case "edit":
			save(req, resp, lopService);
			break;
		case "delete":
			delete(req, resp, lopService);
			break;
		}
	}

	void showList(HttpServletRequest req, HttpServletResponse resp, LopService lopService)
			throws ServletException, IOException {
		List<Lop> lop = lopService.getAll();
		req.setAttribute("lop", lop);
		req.getRequestDispatcher("lop/danh-sach-lop.jsp").include(req, resp);
	}

	void edit(HttpServletRequest req, HttpServletResponse resp, LopService lopService)
			throws ServletException, IOException {
		int id = Integer.valueOf(req.getParameter("id"));
		Lop lop = lopService.findByID(id);
		req.setAttribute("lop", lop);
		req.getRequestDispatcher("lop/edit.jsp").include(req, resp);
	}

	void save(HttpServletRequest req, HttpServletResponse resp, LopService lopService) throws IOException {
		int maLop = Integer.valueOf(req.getParameter("maLop"));
		String tenLop = req.getParameter("tenLop");
		System.out.println("Tên Lớp"+tenLop);
		int maKhoa = Integer.valueOf(req.getParameter("khoaLop"));
		List<Sinhvien> sv = lopService.getSinhvienByLopID(maLop);
		
		Lop lop = new Lop(maLop, tenLop, new Khoa(maKhoa), sv);
		lopService.update(lop);
		resp.sendRedirect("/MySpringCoreSite/lop");
	}

	void delete(HttpServletRequest req, HttpServletResponse resp, LopService lopService) throws IOException {
		List<Lop> allLop = (List<Lop>) lopService.getAll();
		List<Integer> listID = new ArrayList<Integer>();

		for (Lop lop : allLop) {
			if (req.getParameter("lop" + lop.getMaLop()) == null)
				listID.add(lop.getMaLop());
		}
		lopService.delete(listID);
		resp.sendRedirect("/MySpringCoreSite/lop");
	}
}
