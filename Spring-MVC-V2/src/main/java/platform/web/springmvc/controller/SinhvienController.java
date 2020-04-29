package platform.web.springmvc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.web.springmvc.model.Sinhvien;

public class SinhvienController {
	@RequestMapping(value = RestURIConstants.DUMMY_SINHVIEN, method = RequestMethod.GET)
	public @ResponseBody Sinhvien getDummySinhvien() {
		return null;
	}

	@RequestMapping(value = RestURIConstants.GET_SINHVIEN, method = RequestMethod.GET)
	public @ResponseBody Sinhvien getSinhvien(@PathVariable("id") int id) {
		return null;
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_SINHVIEN, method = RequestMethod.GET)
	public @ResponseBody List<Sinhvien> getSinhviens() {
		return null;
	}

	@RequestMapping(value = RestURIConstants.CREATE_SINHVIEN, method = RequestMethod.POST)
	public @ResponseBody Sinhvien createSinhvien(@RequestBody Sinhvien sinhvien) {
		return null;
	}

	@RequestMapping(value = RestURIConstants.EDIT_SINHVIEN, method = RequestMethod.PUT)
	public int editSinhvien(@PathVariable("id") int id) {
		return 0;
	}

	@RequestMapping(value = RestURIConstants.DELETE_SINHVIEN, method = RequestMethod.DELETE)
	public String deleteSinhvien(@PathVariable("id") int id) {
		return "";
	}
}
