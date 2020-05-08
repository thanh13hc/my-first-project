package vn.my_project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.my_project.model.Lop;
import vn.my_project.model.Monhoc;
import vn.my_project.model.Sinhvien;
import vn.my_project.service.LopService;
import vn.my_project.service.MonhocService;
import vn.my_project.service.SinhvienService;

@Controller
@RequestMapping(value = "/sinhvien")
public class SinhvienController {

	@Autowired
	MonhocService monhocService;

	@Autowired
	LopService lopService;

	@Autowired
	SinhvienService sinhvienService;

	@RequestMapping(value = "/list-sinh-vien", method = RequestMethod.GET)
	String getListSinhvien(Model model, @RequestParam(name = "p", required = false) String p) {
		int pageNum = Integer.valueOf(p == null ? "1" : p);
		int numbmberOfSinhvien = sinhvienService.getNumberOfSinhvien();

		List<Sinhvien> list = sinhvienService.getLimitList(pageNum);
		int maxPage = numbmberOfSinhvien / 10 + (numbmberOfSinhvien % 10 == 0 ? 0 : 1);

		model.addAttribute("list", list);
		model.addAttribute("maxPage", maxPage);

		return "ServicePage/SinhvienViews/SinhvienService";
	}

	@RequestMapping(value = "/dang-ki-hoc", method = RequestMethod.GET)
	String dangKiHoc(Model model, @RequestParam(name = "p", required = false) String p) {
		int pageNum = Integer.valueOf(p == null ? "1" : p);
		int numbmberOfMon = monhocService.getNumberOfMonhoc();

		List<Monhoc> list = monhocService.getLimitList(pageNum);
		int maxPage = numbmberOfMon / 10 + (numbmberOfMon % 10 == 0 ? 0 : 1);

		model.addAttribute("list", list);
		model.addAttribute("maxPage", maxPage);

		return "ServicePage/SinhvienViews/DangKiHoc_MonhocView";
	}

	@RequestMapping(value = "/dang-ki-lop", method = RequestMethod.GET)
	String getListSinhven(Model model, @RequestParam(name = "monID") String maMon,
			@RequestParam(name = "p", required = false) String p) {

		int monID = Integer.valueOf(maMon);
		List<Lop> listLop = lopService.getLopByMonhoc(monID);
		List<Sinhvien> listSinhvien = sinhvienService.readAll();

		if (listLop.size() != 0) {
			int pageNum = Integer.valueOf(p == null ? "1" : p);
			int numbmberOfLop = listLop.size();

			int maxPage = numbmberOfLop / 10 + (numbmberOfLop % 10 == 0 ? 0 : 1);

			model.addAttribute("listLop", listLop);
			model.addAttribute("listSinhvien", listSinhvien);
			model.addAttribute("maxPage", maxPage);
		} else {
			model.addAttribute("message", "Không có dữ liệu");
			model.addAttribute("listSinhvien", listSinhvien);
		}

		model.addAttribute("monID", monID);
		return "ServicePage/SinhvienViews/DangKiHoc_LopView";
	}

	//
	// <!-- END GET METHOD -->
	//

	@RequestMapping(value = "/dang-ki-hoc", method = RequestMethod.POST)
	String dangKiHoc(HttpServletRequest req,@RequestParam(name = "maMonhoc") String maMon, @RequestParam(name = "sinhviens") String msv,
			@RequestParam(name = "p", required = false) String p) {

		int page = Integer.valueOf(p == null ? "1" : p);
		int maMonHoc = Integer.valueOf(maMon);
		int maSinhvien = Integer.valueOf(msv);
		List<Lop> list = lopService.getLopByMonhoc(maMonHoc);

		for (int i = 0; i < list.size(); i++) {
			String maLop = list.get(i).getMaLop().toString();
			if (req.getParameter(maLop) != null) {
				lopService.dangKiHoc(Integer.valueOf(maLop), maSinhvien);
			}
		}

		return "redirect:dang-ki-hoc";
	}
}