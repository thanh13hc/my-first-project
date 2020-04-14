package vn.my_project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.my_project.model.Giaovien;
import vn.my_project.model.Lop;
import vn.my_project.model.Monhoc;
import vn.my_project.model.Sinhvien;
import vn.my_project.service.GiaovienService;
import vn.my_project.service.LopService;
import vn.my_project.service.MonhocService;
import vn.my_project.service.SinhvienService;

@Controller
@RequestMapping(value = "/lop")
public class LopController {

	@Autowired
	MonhocService monhocService;

	@Autowired
	LopService lopService;

	@Autowired
	SinhvienService sinhvienService;

	@Autowired
	GiaovienService giaovienService;

	@RequestMapping(value = "/list-lop", method = RequestMethod.GET)
	String getListLopPage(Model model, @RequestParam(name = "p", required = false) String p) {
		int pageNum = Integer.valueOf(p == null ? "1" : p);
		List<Lop> list = lopService.getLimitList(pageNum);

		int maxPage = lopService.getNumberOfLop() / 10 + (lopService.getNumberOfLop() % 10 == 0 ? 0 : 1);
		model.addAttribute("list", list);
		model.addAttribute("maxPage", maxPage);

		return "ServicePage/LopViews/LopService";
	}

	@RequestMapping(value = "/add-lop", method = RequestMethod.GET)
	String addLopPage(Model model, @RequestParam(name = "p", required = false) String p) {
		List<Monhoc> list = monhocService.readAll();

		model.addAttribute("list", list);
		model.addAttribute("lop", new Lop());

		return "ServicePage/LopViews/TaoLopViews";
	}

	@RequestMapping(value = "/tim-kiem", method = RequestMethod.GET)
	String timKiemForm(Model model, @RequestParam(name = "query", required = false) String query,
			@RequestParam(name = "p", required = false) String p) {
		if (query == null) {
			int pageNum = Integer.valueOf(p == null ? "1" : p);
			List<Lop> list = lopService.getLimitList(pageNum);

			int maxPage = lopService.getNumberOfLop() / 10 + (lopService.getNumberOfLop() % 10 == 0 ? 0 : 1);
			model.addAttribute("list", list);
			model.addAttribute("maxPage", maxPage);
		} else {
			int pageNum = Integer.valueOf(p == null ? "1" : p);
			List<Lop> list = lopService.getLopBySearchQuery(query);

			if (list.size() == 0) {
				model.addAttribute("message", "Không có dữ liệu");
			} else {

				model.addAttribute("list", list);
				model.addAttribute("maxPage", 1);
			}
		}

		return "ServicePage/LopViews/TimKiem";
	}

	@RequestMapping(value = "/tim-kiem-sinhvien", method = RequestMethod.GET)
	String timKiemSinhvien(Model model, @RequestParam(name = "lopID") String lopID,
			@RequestParam(name = "p", required = false) String p) {

		int maLop = Integer.valueOf(lopID);
		int pageNum = Integer.valueOf(p == null ? "1" : p);
		List<Sinhvien> list = sinhvienService.getSVbyLopID(maLop, pageNum);
		int numberOfSv = sinhvienService.getNumberOfSVByLopID(maLop);

		model.addAttribute("maLop", maLop);

		if (numberOfSv == 0) {
			model.addAttribute("message", "Không có dữ liệu");
		} else {
			int maxPage = numberOfSv / 10 + (numberOfSv % 10 == 0 ? 0 : 1);
			model.addAttribute("list", list);
			model.addAttribute("maxPage", maxPage);
		}

		return "ServicePage/LopViews/DanhsachSv";
	}

	@RequestMapping(value = "/tim-kiem-giaovien", method = RequestMethod.GET)
	String timKiemGiaoVien(Model model, @RequestParam(name = "lopID") String lopID,
			@RequestParam(name = "p", required = false) String p) {
		int maLop = Integer.valueOf(lopID);
		int pageNum = Integer.valueOf(p == null ? "1" : p);
		List<Giaovien> list = giaovienService.getGVbyLopID(maLop, pageNum);
		int numberOfGv = giaovienService.getNumberOfGVByLopID(maLop);
		model.addAttribute("maLop", maLop);

		if (numberOfGv == 0) {
			model.addAttribute("message", "Không có dữ liệu");
		} else {
			int maxPage = numberOfGv / 10 + (numberOfGv % 10 == 0 ? 0 : 1);
			model.addAttribute("list", list);
			model.addAttribute("maxPage", maxPage);
		}

		return "ServicePage/LopViews/DanhsachGv";
	}

	@RequestMapping(value = "/them-sv-gv-form", method = RequestMethod.GET)
	String themSvGvForm(Model model, @RequestParam(name = "p", required = false) String p) {
		int pageNum = Integer.valueOf(p == null ? "1" : p);
		List<Lop> list = lopService.getLimitList(pageNum);

		int maxPage = lopService.getNumberOfLop() / 10 + (lopService.getNumberOfLop() % 10 == 0 ? 0 : 1);

		model.addAttribute("list", list);
		model.addAttribute("maxPage", maxPage);

		return "ServicePage/LopViews/DanhsachLopGvSv";
	}

	@RequestMapping(value = "/them-sv-gv", method = RequestMethod.GET)
	String themForm(Model model, @RequestParam(name = "id") String id) {
		int maLop = Integer.valueOf(id);

		Lop lop = lopService.readByID(maLop);
		List<Sinhvien> listSv = sinhvienService.getSinhvienNotInLop(maLop);
		List<Giaovien> listGv = giaovienService.getGiaovienNotInLop(maLop);

		model.addAttribute("lop", lop);
		model.addAttribute("listSv", listSv);
		model.addAttribute("listGv", listGv);

		return "ServicePage/LopViews/Them-giaovien-sinhvien";
	}

	// <!-- End Get method -->

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	String add(@ModelAttribute("lop") Lop lop, @RequestParam("maMonHoc") String maMonhoc) {
		Monhoc mon = monhocService.readByID(Integer.valueOf(maMonhoc));
		lop.setMonhoc(mon);
		lopService.create(lop);
		return "redirect:list-lop";
	}

	@RequestMapping(value = "/tim-kiem", method = RequestMethod.POST)
	String timKiemPost(Model model, @RequestParam(name = "query", required = false) String query,
			@RequestParam(name = "p", required = false) String p) {

		if (query == null) {
			int pageNum = Integer.valueOf(p == null ? "1" : p);
			List<Lop> list = lopService.getLimitList(pageNum);

			int maxPage = lopService.getNumberOfLop() / 10 + (lopService.getNumberOfLop() % 10 == 0 ? 0 : 1);
			model.addAttribute("list", list);
			model.addAttribute("maxPage", maxPage);
		} else {
			int pageNum = Integer.valueOf(p == null ? "1" : p);
			List<Lop> list = lopService.getLopBySearchQuery(query);

			if (list.size() == 0) {
				model.addAttribute("message", "Không có dữ liệu");
			} else {
				model.addAttribute("list", list);
				model.addAttribute("maxPage", 1);
			}
		}

		return "ServicePage/LopViews/TimKiem";
	}

	@RequestMapping(value = "/them-sv-gv", method = RequestMethod.POST)
	String themSvGv(@RequestParam("maLop") String maLop, @RequestParam("sinhviens") String sinhviens,
			@RequestParam("giaoviens") String giaoviens) {
		int lopID = Integer.valueOf(maLop);
		int maSinhvien = Integer.valueOf(sinhviens);
		int maGiaovien = Integer.valueOf(giaoviens);

		lopService.dangKiGiangDay(lopID, maGiaovien);
		lopService.dangKiHoc(lopID, maSinhvien);

		return "redirect:them-sv-gv-form";
	}
}
