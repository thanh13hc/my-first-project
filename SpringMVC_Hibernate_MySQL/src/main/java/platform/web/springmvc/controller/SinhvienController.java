package platform.web.springmvc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.web.springmvc.model.Sinhvien;
import platform.web.springmvc.service.SinhvienService;

@Controller
public class SinhvienController {

	@Autowired
	private SinhvienService sinhvienService;

	private static final Logger logger = LoggerFactory.getLogger(SinhvienController.class);

	@RequestMapping(value = RestURIConstants.DUMMY_SINHVIEN, method = RequestMethod.GET)
	public @ResponseBody Sinhvien getDummyMonHoc() {
		logger.info("Start getDummyMonHoc");
		Sinhvien sinhvien = sinhvienService.getSinhvienByID(01);
		System.out.println("\n\n\n\n>>>>>> Sinhvien: " + sinhvien.getHo() + " " + sinhvien.getTen());
		return sinhvien;
	}

	@RequestMapping(value = RestURIConstants.GET_SINHVIEN, method = RequestMethod.GET)
	public @ResponseBody Sinhvien getSinhvien(@PathVariable("id") int id) {
		logger.info("Start getSinhvien. ID = " + id);
		Sinhvien sinhvien = sinhvienService.getSinhvienByID(id);
		return sinhvien;
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_SINHVIEN, method = RequestMethod.GET)
	public @ResponseBody List<Sinhvien> getSinhviens() {
		logger.info("Start getAllSinhviens");
		List<Sinhvien> list = sinhvienService.getAll();
		return list;
	}

	@RequestMapping(value = RestURIConstants.CREATE_SINHVIEN, method = RequestMethod.POST)
	public @ResponseBody Sinhvien createSinhvien(@RequestBody Sinhvien sinhvien) {
		logger.info("Start createSinhvien");
		sinhvienService.insertSinhvien(sinhvien);
		return sinhvien;
	}

	@RequestMapping(value = RestURIConstants.EDIT_SINHVIEN, method = RequestMethod.PUT)
	public @ResponseBody Sinhvien editSinhvien(@RequestBody Sinhvien sinhvien) {
		logger.info("Start editSinhvien. ID = " + sinhvien.getMaSinhvien());
		sinhvienService.editSinhvien(sinhvien);
		return sinhvien;
	}

	@RequestMapping(value = RestURIConstants.DELETE_SINHVIEN, method = RequestMethod.DELETE)
	public @ResponseBody Sinhvien deleteSinhvien(@PathVariable("id") int id) {
		logger.info("Start deleteSinhvien. ID = " + id);
		sinhvienService.delete(id);
		return null;
	}

}
