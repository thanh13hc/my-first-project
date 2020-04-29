package platform.web.springmvc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.web.springmvc.model.Monhoc;

public class MonhocController {

	@RequestMapping(value = RestURIConstants.DUMMY_MONHOC, method = RequestMethod.GET)
	public @ResponseBody Monhoc getDummyMonHoc() {
		return null;
	}

	@RequestMapping(value = RestURIConstants.GET_MONHOC, method = RequestMethod.GET)
	public @ResponseBody Monhoc getMonhoc(@PathVariable("id") int id) {
		return null;
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_MONHOC, method = RequestMethod.GET)
	public @ResponseBody List<Monhoc> getMonhocs() {
		return null;
	}

	@RequestMapping(value = RestURIConstants.CREATE_MONHOC, method = RequestMethod.POST)
	public @ResponseBody Monhoc createMonhoc(@RequestBody Monhoc mon) {
		return null;
	}

	@RequestMapping(value = RestURIConstants.EDIT_MONHOC, method = RequestMethod.PUT)
	public int editMonhoc(@PathVariable("id") int id) {
		return 0;
	}

	@RequestMapping(value = RestURIConstants.DELETE_MONHOC, method = RequestMethod.DELETE)
	public String deleteMonhoc(@PathVariable("id") int id) {
		return "";
	}

}
