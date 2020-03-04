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

import net.bytebuddy.asm.Advice.This;
import vn.my_project.model.Giaovien;
import vn.my_project.model.Lop;
import vn.my_project.model.Monhoc;
import vn.my_project.model.Sinhvien;
import vn.my_project.service.GiaovienService;
import vn.my_project.service.LopService;
import vn.my_project.service.MonhocService;
import vn.my_project.service.SinhvienService;
import vn.my_project.utils.DateTimeFomatter;

@WebServlet(value = "/lop")
public class DanhsachLop extends HttpServlet {

	static Logger log = Logger.getLogger(DanhsachLop.class.getName());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		LopService lopService = (LopService) context.getBean("lopService");
		MonhocService monService = (MonhocService) context.getBean("monhocService");
		SinhvienService sinhvienService = (SinhvienService) context.getBean("sinhvienService");
		GiaovienService giaovienService = (GiaovienService) context.getBean("giaovienService");

		String page = req.getParameter("page");
		if (page == null)
			// show list mon hoc
			listLop(req, resp, lopService);
		else {
			switch (page) {
			case "add-lop":
				addForm(req, resp, monService);
				break;
			case "form-edit":
				editForm(req, resp, lopService);
				break;
			case "delete":
				delete(req, resp, lopService);
				break;
			case "tim-kiem":
				timkiemGet(req, resp, lopService);
				break;
			case "tim-kiem-sinhvien":
				timkiemSV(req, resp, sinhvienService);
				break;
			case "tim-kiem-giaovien":
				timkiemGV(req, resp, giaovienService);
				break;
			case "them-sv-gv-form":
				themSvGvForm(req, resp,lopService);
				break;
			case "them-sv-gv":
				themForm(req, resp,lopService,sinhvienService,giaovienService);
			}
		}
	}

	void themForm(HttpServletRequest req, HttpServletResponse resp, LopService lopService, SinhvienService sinhvienService,
			GiaovienService giaovienService) throws ServletException, IOException {
		log.info("Trang đăng kí học - giảng dạy chưa trong lớp ...");
		int maLop = Integer.valueOf(req.getParameter("id"));
		
		Lop lop = lopService.readByID(maLop);
		List<Sinhvien> listSv = sinhvienService.getSinhvienNotInLop(maLop);
		List<Giaovien> listGv = giaovienService.getGiaovienNotInLop(maLop);
		
		req.setAttribute("lop", lop);
		req.setAttribute("listSv", listSv);
		req.setAttribute("listGv", listGv);
		
		req.getRequestDispatcher("ServicePage/LopViews/Them-giaovien-sinhvien.jsp").include(req, resp);
	}

	void themSvGvForm(HttpServletRequest req, HttpServletResponse resp, LopService lopService) throws ServletException, IOException {
			log.info("Trang danh sách Lop de dang ki học - giang dạy ...");
		
			int pageNum = Integer.valueOf((req.getParameter("p") == null ? "1" : req.getParameter("p")));
			List<Lop> list = lopService.getLimitList(pageNum);

			int maxPage = lopService.getNumberOfLop() / 10 + (lopService.getNumberOfLop() % 10 == 0 ? 0 : 1);
			req.setAttribute("list", list);
			req.setAttribute("maxPage", maxPage);
			req.getRequestDispatcher("ServicePage/LopViews/DanhsachLopGvSv.jsp").include(req, resp);
	}

	private void timkiemGV(HttpServletRequest req, HttpServletResponse resp, GiaovienService giaovienService)
			throws ServletException, IOException {
		log.info("Trang tìm kiếm -> danh sách giáo viên");
		
		int maLop = Integer.valueOf(req.getParameter("lopID"));
		int pageNum = Integer.valueOf((req.getParameter("p") == null ? "1" : req.getParameter("p")));
		List<Giaovien> list = giaovienService.getGVbyLopID(maLop, pageNum);
		int numberOfGv = giaovienService.getNumberOfGVByLopID(maLop);
		req.setAttribute("maLop", maLop);

		if (numberOfGv == 0) {
			req.setAttribute("message", "Không có dữ liệu");
		} else {
			int maxPage = numberOfGv / 10 + (numberOfGv % 10 == 0 ? 0 : 1);
			req.setAttribute("list", list);
			req.setAttribute("maxPage", maxPage);
		}

		req.getRequestDispatcher("ServicePage/LopViews/DanhsachGv.jsp").include(req, resp);
	}

	private void timkiemSV(HttpServletRequest req, HttpServletResponse resp, SinhvienService sinhvienService)
			throws ServletException, IOException {
		log.info("Trang tìm kiếm -> danh sách sinh viên");
		
		int maLop = Integer.valueOf(req.getParameter("lopID"));
		int pageNum = Integer.valueOf((req.getParameter("p") == null ? "1" : req.getParameter("p")));
		List<Sinhvien> list = sinhvienService.getSVbyLopID(maLop, pageNum);
		int numberOfSv = sinhvienService.getNumberOfSVByLopID(maLop);
		req.setAttribute("maLop", maLop);

		if (numberOfSv == 0) {
			req.setAttribute("message", "Không có dữ liệu");
		} else {
			int maxPage = numberOfSv / 10 + (numberOfSv % 10 == 0 ? 0 : 1);
			req.setAttribute("list", list);
			req.setAttribute("maxPage", maxPage);
		}

		req.getRequestDispatcher("ServicePage/LopViews/DanhsachSv.jsp").include(req, resp);
	}

	void timkiemGet(HttpServletRequest req, HttpServletResponse resp, LopService lopService)
			throws ServletException, IOException {
		
		log.info("Tìm kiếm lớp học không có query ...");
		
		String query = req.getParameter("query");
		if (query == null) {
			int pageNum = Integer.valueOf((req.getParameter("p") == null ? "1" : req.getParameter("p")));
			List<Lop> list = lopService.getLimitList(pageNum);

			int maxPage = lopService.getNumberOfLop() / 10 + (lopService.getNumberOfLop() % 10 == 0 ? 0 : 1);
			req.setAttribute("list", list);
			req.setAttribute("maxPage", maxPage);
			req.getRequestDispatcher("ServicePage/LopViews/TimKiem.jsp").include(req, resp);
		} else {
			int pageNum = Integer.valueOf((req.getParameter("p") == null ? "1" : req.getParameter("p")));
			List<Lop> list = lopService.getLopBySearchQuery(query);

			if (list.size() == 0) {
				req.setAttribute("message", "Không có dữ liệu");
			} else {
				// int numbmberOfLop = list.size();

				// int maxPage = numbmberOfLop / 10 + (numbmberOfLop % 10 == 0 ? 0 : 1);

				req.setAttribute("list", list);
				req.setAttribute("maxPage", 1);
			}

			req.getRequestDispatcher("ServicePage/LopViews/TimKiem.jsp").include(req, resp);
		}
	}

	void addForm(HttpServletRequest req, HttpServletResponse resp, MonhocService monhocService)
			throws ServletException, IOException {
		log.info("Tạo lớp form ...");

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

	void listLop(HttpServletRequest req, HttpServletResponse resp, LopService lopService)
			throws ServletException, IOException {
		log.info("Danh sach Lop ...");
		int pageNum = Integer.valueOf((req.getParameter("p") == null ? "1" : req.getParameter("p")));
		List<Lop> list = lopService.getLimitList(pageNum);

		int maxPage = lopService.getNumberOfLop() / 10 + (lopService.getNumberOfLop() % 10 == 0 ? 0 : 1);
		req.setAttribute("list", list);
		req.setAttribute("maxPage", maxPage);
		req.getRequestDispatcher("ServicePage/LopViews/LopService.jsp").include(req, resp);
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
		String page = req.getParameter("page");

		switch (page) {
		case "add":
			add(req, resp, monService, lopService);
			break;
		case "edit":
			// edit(req, resp, monhocService);
			break;
		case "deletes":
			// deletes(req, resp, monhocService);
			break;
		case "tim-kiem":
			timkiemPost(req, resp, lopService);
			break;
		case "them-sv-gv":
			themSvGv(req, resp,lopService);
		}
	}

	 void themSvGv(HttpServletRequest req, HttpServletResponse resp, LopService lopService) throws IOException {
		log.info("Đăng kí học - giảng dạy ...");
		 int maLop = Integer.valueOf(req.getParameter("maLop"));
		int maSinhvien = Integer.valueOf(req.getParameter("sinhviens"));
		int maGiaovien = Integer.valueOf(req.getParameter("giaoviens"));
		
		lopService.dangKiGiangDay(maLop, maGiaovien);
		lopService.dangKiHoc(maLop, maSinhvien);
		
		resp.sendRedirect("/QuanLiLop/lop?page=them-sv-gv-form");
	}

	void timkiemPost(HttpServletRequest req, HttpServletResponse resp, LopService lopService)
			throws ServletException, IOException {
		log.info("Trang tìm kiếm có query: "+req.getParameter("query")+" ...");
		
		String query = req.getParameter("query");
		if (query == null) {
			int pageNum = Integer.valueOf((req.getParameter("p") == null ? "1" : req.getParameter("p")));
			List<Lop> list = lopService.getLimitList(pageNum);

			int maxPage = lopService.getNumberOfLop() / 10 + (lopService.getNumberOfLop() % 10 == 0 ? 0 : 1);
			req.setAttribute("list", list);
			req.setAttribute("maxPage", maxPage);
			req.getRequestDispatcher("ServicePage/LopViews/TimKiem.jsp").include(req, resp);
		} else {
			int pageNum = Integer.valueOf((req.getParameter("p") == null ? "1" : req.getParameter("p")));
			List<Lop> list = lopService.getLopBySearchQuery(query);

			if (list.size() == 0) {
				req.setAttribute("message", "Không có dữ liệu");
			} else {
				// int numbmberOfLop = list.size();

				// int maxPage = numbmberOfLop / 10 + (numbmberOfLop % 10 == 0 ? 0 : 1);

				req.setAttribute("list", list);
				req.setAttribute("maxPage", 1);
			}

			req.getRequestDispatcher("ServicePage/LopViews/TimKiem.jsp").include(req, resp);
		}

	}

	void add(HttpServletRequest req, HttpServletResponse resp, MonhocService monService, LopService lopService)
			throws IOException {
		log.info("Thêm lớp ...");
		
		String lopName = req.getParameter("lopName");
		Monhoc mon = monService.readByID(Integer.valueOf(req.getParameter("monhocs")));
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = DateTimeFomatter.StringToDate(req.getParameter("startDate"));
			endDate = DateTimeFomatter.StringToDate(req.getParameter("endDate"));
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
