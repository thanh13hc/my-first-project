package platform.web.springmvc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.web.springmvc.model.Lop;

public class LopController {
	@RequestMapping(value = RestURIConstants.DUMMY_LOP, method = RequestMethod.GET)
	public @ResponseBody Lop getDummyLop() {
		return null;
	}

	@RequestMapping(value = RestURIConstants.GET_LOP, method = RequestMethod.GET)
	public @ResponseBody Lop getLop(@PathVariable("id") int id) {
		return null;
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_LOP, method = RequestMethod.GET)
	public @ResponseBody List<Lop> getLops() {
		return null;
	}

	@RequestMapping(value = RestURIConstants.CREATE_LOP, method = RequestMethod.POST)
	public @ResponseBody Lop createLop(@RequestBody Lop lop) {
		return null;
	}

	@RequestMapping(value = RestURIConstants.EDIT_LOP, method = RequestMethod.PUT)
	public int editLop(@PathVariable("id") int id) {
		return 0;
	}

	@RequestMapping(value = RestURIConstants.DELETE_LOP, method = RequestMethod.DELETE)
	public String deleteLop(@PathVariable("id") int id) {
		return "";
	}
}
