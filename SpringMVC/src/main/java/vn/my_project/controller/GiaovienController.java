package vn.my_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.my_project.model.Giaovien;
import vn.my_project.model.Lop;
import vn.my_project.service.GiaovienService;
import vn.my_project.service.LopService;

@Controller
@RequestMapping("/giaovien")
public class GiaovienController{
	
	@Autowired
	GiaovienService giaovienService;
	
	@Autowired
	LopService lopService;
	
	@RequestMapping(value="/list-giao-vien",method=RequestMethod.GET)
	String getListGiaovien(Model model,@RequestParam(name="p", required=false) String p) {
		
		int pageNum = Integer.valueOf(p == null ? "1" : p);
		int numbmberOfGiaovien = giaovienService.getNumberOfGiaovien();
		
		List<Giaovien> list = giaovienService.getLimitList(pageNum);
		int maxPage = numbmberOfGiaovien / 10 + (numbmberOfGiaovien % 10 == 0 ? 0 : 1);
		
		model.addAttribute("list", list);
		model.addAttribute("maxPage", maxPage);
		return "ServicePage/GiaovienViews/GiaovienService";
	}
	
	@RequestMapping(value="dang-ki-giang-day",method=RequestMethod.GET)
	String dangKiGiangDayForm(Model model) {
		List<Lop> listLop = lopService.readAll();
		List<Giaovien> listGiaovien = giaovienService.readAll();
		
		model.addAttribute("listLop", listLop);
		model.addAttribute("listGiaovien", listGiaovien);
		
		return "ServicePage/GiaovienViews/DangKiGiangDay";
	}
	
	
	//
	//			 <!-- End Get method -->
	//
	
	
	@RequestMapping(value="dang-ki-giang-day",method=RequestMethod.POST)
	String dangKiGiangDay(
			@RequestParam(name="lophocs") String lophocs,
			@RequestParam(name="giaoviens") String giaoviens) {
		int maLop = Integer.valueOf(lophocs);
		int maGiaovien = Integer.valueOf(giaoviens);
		
		lopService.dangKiGiangDay(maLop, maGiaovien);
		
		return "redirect:list-giao-vien";
	}
}