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

import platform.web.springmvc.dto.LopDTO;
import platform.web.springmvc.model.Lop;
import platform.web.springmvc.service.LopService;
import platform.web.springmvc.service.LopServiceImpl;

@Controller
public class LopController {

	@Autowired
	private LopService lopService;

	private static final Logger logger = LoggerFactory.getLogger(LopController.class);

	@RequestMapping(value = RestURIConstants.DUMMY_LOP, method = RequestMethod.GET)
	public @ResponseBody LopDTO getDummyLop() {
		logger.info("Start getDummyLop");
		LopDTO lop = lopService.getLopByID(01);
		System.out.println("\n\n\n\n>>>>>> Lop: " + lop.getTenLop());
		return lop;
	}

	@RequestMapping(value = RestURIConstants.GET_LOP, method = RequestMethod.GET)
	public @ResponseBody LopDTO getLop(@PathVariable("id") int id) {
		logger.info("Start getLop. ID = " + id);
		LopDTO lop = lopService.getLopByID(id);
		return lop;
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_LOP, method = RequestMethod.GET)
	public @ResponseBody List<LopDTO> getLops() {
		logger.info("Start getAllLops");
		List<LopDTO> list = lopService.getAll();
		return list;
	}

	@RequestMapping(value = RestURIConstants.CREATE_LOP, method = RequestMethod.POST)
	public @ResponseBody Lop createLop(@RequestBody Lop lop) {
		logger.info("Start createLop");
		lopService.insertLop(lop);
		return lop;
	}

	@RequestMapping(value = RestURIConstants.EDIT_LOP, method = RequestMethod.PUT)
	public @ResponseBody Lop editLop(@RequestBody Lop lop) {
		logger.info("Start editeLop. ID = " + lop.getMaLop());
		lopService.editLop(lop);
		return lop;
	}

	@RequestMapping(value = RestURIConstants.DELETE_LOP, method = RequestMethod.DELETE)
	public @ResponseBody Lop deleteLop(@PathVariable("id") int id) {
		logger.info("Start deleteLop. ID = "+id);
		lopService.delete(id);
		return null;
	}
}
