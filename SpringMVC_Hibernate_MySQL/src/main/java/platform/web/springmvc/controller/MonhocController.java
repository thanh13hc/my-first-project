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

import platform.web.springmvc.model.Monhoc;
import platform.web.springmvc.service.MonhocService;

@Controller
public class MonhocController {

	@Autowired
	private MonhocService monhocService;

	private static final Logger logger = LoggerFactory.getLogger(MonhocController.class);

	@RequestMapping(value = RestURIConstants.DUMMY_MONHOC, method = RequestMethod.GET)
	public @ResponseBody Monhoc getDummyMonHoc() {
		logger.info("Start getDummyMonHoc");
		Monhoc mon = monhocService.getMonhocByID(01);
		System.out.println("\n\n\n\n>>>>>> Monhoc: " + mon.getTenMonhoc());
		return mon;
	}

	@RequestMapping(value = RestURIConstants.GET_MONHOC, method = RequestMethod.GET)
	public @ResponseBody Monhoc getMonhoc(@PathVariable("id") int id) {
		logger.info("Start getMonhoc. ID = " + id);
		Monhoc mon = monhocService.getMonhocByID(id);
		return mon;
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_MONHOC, method = RequestMethod.GET)
	public @ResponseBody List<Monhoc> getMonhocs() {
		logger.info("Start getAllMonhocs");
		List<Monhoc> list = monhocService.getAll();
		return list;
	}

	@RequestMapping(value = RestURIConstants.CREATE_MONHOC, method = RequestMethod.POST)
	public @ResponseBody Monhoc createMonhoc(@RequestBody Monhoc mon) {
		logger.info("Start createMonhoc");
		monhocService.insertMonhoc(mon);
		return mon;
	}

	@RequestMapping(value = RestURIConstants.EDIT_MONHOC, method = RequestMethod.PUT)
	public @ResponseBody Monhoc editMonhoc(@RequestBody Monhoc mon) {
		logger.info("Start editMonhoc. ID = " + mon.getMaMonhoc());
		monhocService.editMonhoc(mon);
		return mon;
	}

	@RequestMapping(value = RestURIConstants.DELETE_MONHOC, method = RequestMethod.DELETE)
	public @ResponseBody Monhoc deleteMonhoc(@PathVariable("id") int id) {
		logger.info("Start deleteMonhoc. ID = " + id);
		monhocService.delete(id);
		return null;
	}

}
