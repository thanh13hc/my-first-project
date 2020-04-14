package vn.my_project.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.bytebuddy.asm.Advice.This;
import vn.my_project.model.Monhoc;
import vn.my_project.service.MonhocService;

@WebServlet(value = "/mon")
public class DanhsachMon extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		MonhocService monhocService = (MonhocService) context.getBean("monhocService",MonhocService.class);

		String page = req.getParameter("page");
		if (page == null)
			// show list mon hoc
			listMonHoc(req, resp, monhocService);
		else {
			switch (page) {
			case "add":
				System.out.println("add");
				break;
			case "form-edit":
				editForm(req, resp, monhocService);
				break;
			case "delete":
				delete(req, resp, monhocService);
				break;
			}
		}
	}

	void editForm(HttpServletRequest req, HttpServletResponse resp, MonhocService monhocService)
			throws ServletException, IOException {
		int id = Integer.valueOf(req.getParameter("id"));
		Monhoc mon = monhocService.readByID(id);
		req.setAttribute("mon", mon);
		req.getRequestDispatcher("ServicePage/MonhocViews/FormEditMon.jsp").include(req, resp);
	}

	void listMonHoc(HttpServletRequest req, HttpServletResponse resp, MonhocService monhocService)
			throws ServletException, IOException {
		int pageNum = Integer.valueOf((req.getParameter("p") == null ? "1" : req.getParameter("p")));
		List<Monhoc> list = monhocService.getLimitList(pageNum);
		int maxPage = monhocService.getNumberOfMonhoc() / 10 + (monhocService.getNumberOfMonhoc() % 10 == 0 ? 0 : 1);
		req.setAttribute("list", list);
		req.setAttribute("maxPage", maxPage);
		req.getRequestDispatcher("ServicePage/MonhocViews/MonService.jsp").include(req, resp);
	}

	void delete(HttpServletRequest req, HttpServletResponse resp, MonhocService monhocService) throws IOException {
		int id = Integer.valueOf(req.getParameter("id"));
		monhocService.delete(id);
		resp.sendRedirect("/QuanLiLop/mon");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		MonhocService monhocService = (MonhocService) context.getBean("monhocService",MonhocService.class);
		String page = req.getParameter("page");

		switch (page) {
		case "add":
			System.out.println("add");
			break;
		case "edit":
			edit(req, resp, monhocService);
			break;
		case "deletes":
			deletes(req, resp, monhocService);
			break;
		}
	}

	void edit(HttpServletRequest req, HttpServletResponse resp, MonhocService monhocService) throws IOException {
		int maMon = Integer.valueOf(req.getParameter("maMon"));
		String tenMonhoc = req.getParameter("tenMon");
		Monhoc mon = new Monhoc(maMon, tenMonhoc);
		monhocService.update(mon);
		resp.sendRedirect("/QuanLiLop/mon");
	}

	private void deletes(HttpServletRequest req, HttpServletResponse resp, MonhocService monhocService)
			throws IOException {
		List<Integer> ids = new ArrayList<Integer>();
		int pageNum = Integer.valueOf((req.getParameter("p") == null ? "1" : req.getParameter("p")));
		List<Monhoc> list = monhocService.getLimitList(pageNum);

		for (Monhoc e : list) {
			if ("on".equals(req.getParameter(e.getMaMonhoc().toString())))
				monhocService.delete(e.getMaMonhoc());
		}

		resp.sendRedirect("/QuanLiLop/mon");
	}
}
