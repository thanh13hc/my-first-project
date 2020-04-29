package platform.web.springmvc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.web.springmvc.model.Giaovien;

public class GiaovienController {
	@RequestMapping(value = RestURIConstants.DUMMY_GIAOVIEN, method = RequestMethod.GET)
	public @ResponseBody Giaovien getDummyGiaovien() {
		return null;
	}

	@RequestMapping(value = RestURIConstants.GET_GIAOVIEN, method = RequestMethod.GET)
	public @ResponseBody Giaovien getGiaovien(@PathVariable("id") int id) {
		return null;
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_GIAOVIEN, method = RequestMethod.GET)
	public @ResponseBody List<Giaovien> getGiaoviens() {
		return null;
	}

	@RequestMapping(value = RestURIConstants.CREATE_GIAOVIEN, method = RequestMethod.POST)
	public @ResponseBody Giaovien createGiaovien(@RequestBody Giaovien giaovien) {
		return null;
	}

	@RequestMapping(value = RestURIConstants.EDIT_GIAOVIEN, method = RequestMethod.PUT)
	public int editGiaovien(@PathVariable("id") int id) {
		return 0;
	}

	@RequestMapping(value = RestURIConstants.DELETE_GIAOVIEN, method = RequestMethod.DELETE)
	public String deleteGiaovien(@PathVariable("id") int id) {
		return "";
	}
}
