package vn.my_project.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vn.my_project.model.Monhoc;
import vn.my_project.service.MonhocService;

@Controller
@RequestMapping(value = "/mon")
public class MonController {

	@Autowired
	MonhocService monhocService;

	@RequestMapping(value = "/list-mon-hoc", method = RequestMethod.GET)
	String getListMonPage(Model model, @RequestParam(name = "p", required = false) String p) {
		int pageNum = Integer.valueOf(p == null ? "1" : p);
		List<Monhoc> list = monhocService.getLimitList(pageNum);
		int numberOfMon = monhocService.getNumberOfMonhoc();
		int maxPage = numberOfMon / 10 + (numberOfMon % 10 == 0 ? 0 : 1);

		model.addAttribute("list", list);
		model.addAttribute("maxPage", maxPage);

		return "ServicePage/MonhocViews/MonService";
	}

	@RequestMapping(value = { "/form-edit" }, method = RequestMethod.GET)
	String getEditPage(Model model, @RequestParam(name = "id") String id) {
		int idMon = Integer.valueOf(id);
		Monhoc mon = monhocService.readByID(idMon);
		model.addAttribute("mon", mon);
		return "ServicePage/MonhocViews/FormEditMon";
	}

	@RequestMapping(value = { "/delete" }, method = RequestMethod.GET)
	String deletePage(@RequestParam(name = "id") String id) {
		int idMon = Integer.valueOf(id);
		monhocService.delete(idMon);
		return "ServicePage/MonhocViews/MonService";
	}

	@RequestMapping(value = { "/edit" }, method = RequestMethod.POST)
	String edit(@ModelAttribute("mon") Monhoc mon) {

		monhocService.update(mon);

		return "redirect:list-mon-hoc";
	}

	@RequestMapping(value = { "/deletes" }, method = RequestMethod.POST)
	String deletes(HttpServletRequest req, @RequestParam(name = "p") String p) {
		List<Integer> ids = new ArrayList<Integer>();
		int pageNum = Integer.valueOf(p == null ? "1" : p);
		List<Monhoc> list = monhocService.getLimitList(pageNum);

		for (Monhoc e : list) {
			if ("on".equals(req.getParameter(e.getMaMonhoc().toString())))
				monhocService.delete(e.getMaMonhoc());
		}

		return "redirect:list-mon-hoc";
	}
}
