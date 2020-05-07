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

import platform.web.springmvc.model.Giaovien;
import platform.web.springmvc.model.GiaovienDTO;
import platform.web.springmvc.service.GiaovienService;

@Controller
public class GiaovienController {

	@Autowired
	private GiaovienService giaovienService;

	private static final Logger logger = LoggerFactory.getLogger(GiaovienController.class);

	@RequestMapping(value = RestURIConstants.DUMMY_GIAOVIEN, method = RequestMethod.GET)
	public @ResponseBody GiaovienDTO getDummyGiaovien() {
		logger.info("Start getDummyGiaovien");
		GiaovienDTO giaovien = giaovienService.getGiaovienByID(01);
		System.out.println("\n\n\n\n>>>>>> Giaovien: " + giaovien.getHo() + " " + giaovien.getTen());
		return giaovien;
	}

	@RequestMapping(value = RestURIConstants.GET_GIAOVIEN, method = RequestMethod.GET)
	public @ResponseBody GiaovienDTO getGiaovien(@PathVariable("id") int id) {
		logger.info("Start getGiaovien. ID = " + id);
		GiaovienDTO giaovien = giaovienService.getGiaovienByID(id);
		return giaovien;
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_GIAOVIEN, method = RequestMethod.GET)
	public @ResponseBody List<GiaovienDTO> getGiaoviens() {
		logger.info("Start getAllGiaoviens");
		List<GiaovienDTO> list = giaovienService.getAll();
		return list;
	}

	@RequestMapping(value = RestURIConstants.CREATE_GIAOVIEN, method = RequestMethod.POST)
	public @ResponseBody Giaovien createGiaovien(@RequestBody Giaovien giaovien) {
		logger.info("Start create Giaovien");
		giaovienService.insertGiaovien(giaovien);
		return giaovien;
	}

	@RequestMapping(value = RestURIConstants.EDIT_GIAOVIEN, method = RequestMethod.PUT)
	public @ResponseBody Giaovien editGiaovien(@RequestBody Giaovien giaovien) {
		logger.info("Start editGiaovien. ID = " + giaovien.getMaGiaovien());
		giaovienService.editGiaovien(giaovien);
		return giaovien;
	}

	@RequestMapping(value = RestURIConstants.DELETE_GIAOVIEN, method = RequestMethod.DELETE)
	public @ResponseBody Giaovien deleteGiaovien(@PathVariable("id") int id) {
		logger.info("Start deleteGiaovien. ID = " + id);
		giaovienService.delete(id);
		return null;
	}
}
