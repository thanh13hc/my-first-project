package vn.my_project.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import vn.my_project.model.Giaovien;
import vn.my_project.model.Lop;
import vn.my_project.model.Monhoc;
import vn.my_project.service.GiaovienService;
import vn.my_project.service.LopService;
import vn.my_project.service.MonhocService;
import vn.my_project.utils.DateTimeFomatter;

@WebServlet(value = "/giaovien")
public class DanhsachGiaovien extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		GiaovienService giaovienService = (GiaovienService) context.getBean("giaovienService",GiaovienService.class);
		LopService lopService = (LopService) context.getBean("lopService");
		
		String page = req.getParameter("page");
		if (page == null)
			// show list mon hoc
			listGiaovien(req, resp,giaovienService);
		else {
			switch (page) {
			case "add-lop":
				//addForm(req, resp, monService);
				break;
			case "form-edit":
				//editForm(req, resp, lopService);
				break;
			case "delete":
				//delete(req, resp, lopService);
				break;
			case "dang-ki-giang-day":
				dangKiGiangDayForm(req, resp,lopService, giaovienService);
				break;
			}
		}
	}

	 

	private void dangKiGiangDayForm(HttpServletRequest req, HttpServletResponse resp,LopService lopService, GiaovienService giaovienService) throws ServletException, IOException {
		
		List<Lop> listLop = lopService.readAll();
		List<Giaovien> listGiaovien = giaovienService.readAll();
		
		req.setAttribute("listLop", listLop);
		req.setAttribute("listGiaovien", listGiaovien);
		
		req.getRequestDispatcher("ServicePage/GiaovienViews/DangKiGiangDay.jsp").include(req, resp);
	}



	void addForm(HttpServletRequest req, HttpServletResponse resp, MonhocService monhocService)
			throws ServletException, IOException {
		List<Monhoc> list = monhocService.readAll();
		req.setAttribute("list", list);
		req.getRequestDispatcher("ServicePage/LopViews/TaoLopViews.jsp").include(req, resp);
	}

	void editForm(HttpServletRequest req, HttpServletResponse resp, LopService lopService)
			throws ServletException, IOException {
//		int id = Integer.valueOf(req.getParameter("id"));
//		Monhoc mon = lopService.readByID(id);
//		req.setAttribute("mon", mon);
//		req.getRequestDispatcher("ServicePage/MonhocViews/FormEditMon.jsp").include(req, resp);
	}

	void listGiaovien(HttpServletRequest req, HttpServletResponse resp, GiaovienService giaovienService)
			throws ServletException, IOException {
		
		int pageNum = Integer.valueOf((req.getParameter("p") == null ? "1" : req.getParameter("p")));
		int numbmberOfGiaovien = giaovienService.getNumberOfGiaovien();
		
		List<Giaovien> list = giaovienService.getLimitList(pageNum);
		int maxPage = numbmberOfGiaovien / 10 + (numbmberOfGiaovien % 10 == 0 ? 0 : 1);
		
		req.setAttribute("list", list);
		req.setAttribute("maxPage", maxPage);
		req.getRequestDispatcher("ServicePage/GiaovienViews/GiaovienService.jsp").include(req, resp);
	}

	void delete(HttpServletRequest req, HttpServletResponse resp, LopService lopService) throws IOException {
//		int id = Integer.valueOf(req.getParameter("id"));
//		monhocService.delete(id);
//		resp.sendRedirect("/QuanLiLop/mon");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		LopService lopService = (LopService) context.getBean("lopService");
		MonhocService monService = (MonhocService) context.getBean("monhocService");
		GiaovienService giaovienService = (GiaovienService) context.getBean("giaovienService");
				
		String page = req.getParameter("page");
		
		switch (page) {
		case "add":
			add(req, resp,monService, lopService);
			break;
		case "edit":
			// edit(req, resp, monhocService);
			break;
		case "deletes":
			// deletes(req, resp, monhocService);
			break;
		case "dang-ki-giang-day":
			dangKiGiangDay(req, resp,lopService);
			break;
		}
	}

	private void dangKiGiangDay(HttpServletRequest req, HttpServletResponse resp, LopService lopService) throws IOException {
		
		int maLop = Integer.valueOf(req.getParameter("lophocs"));
		int maGiaovien = Integer.valueOf(req.getParameter("giaoviens"));
		lopService.dangKiGiangDay(maLop, maGiaovien);
		
		resp.sendRedirect("/QuanLiLop/giaovien?p=1");
	}



	void add(HttpServletRequest req, HttpServletResponse resp,MonhocService monService, LopService lopService) throws IOException {
		String lopName = req.getParameter("lopName");
		Monhoc mon = monService.
				readByID(Integer.valueOf(req.getParameter("monhocs")));
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = DateTimeFomatter.
					StringToDate(req.getParameter("startDate"));
			endDate = DateTimeFomatter.
					StringToDate(req.getParameter("endDate"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int giohoc = Integer.valueOf(req.getParameter("giohoc"));
		
		Lop obj = new Lop(mon, lopName, startDate, endDate, giohoc);
		lopService.create(obj);
		
		resp.sendRedirect("/QuanLiLop/lop");
	}

	void edit(HttpServletRequest req, HttpServletResponse resp, MonhocService monhocService) throws IOException {
//		int maMon = Integer.valueOf(req.getParameter("maMon"));
//		String tenMonhoc = req.getParameter("tenMon");
//		Monhoc mon = new Monhoc(maMon, tenMonhoc);
//		monhocService.update(mon);
//		resp.sendRedirect("/QuanLiLop/mon");
	}

	private void deletes(HttpServletRequest req, HttpServletResponse resp, MonhocService monhocService)
			throws IOException {
//		List<Integer> ids = new ArrayList<Integer>();
//		int pageNum = Integer.valueOf((req.getParameter("p") == null ? "1" : req.getParameter("p")));
//		List<Monhoc> list = monhocService.getLimitList(pageNum);
//
//		for (Monhoc e : list) {
//			if ("on".equals(req.getParameter(e.getMaMonhoc().toString())))
//				monhocService.delete(e.getMaMonhoc());
//		}
//
//		resp.sendRedirect("/QuanLiLop/mon");
	}

}
