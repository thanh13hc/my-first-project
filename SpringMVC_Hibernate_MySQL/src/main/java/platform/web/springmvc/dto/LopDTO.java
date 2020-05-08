package platform.web.springmvc.dto;

import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;

import platform.web.springmvc.model.Monhoc;

public class LopDTO {

	private Integer maLop;
	private String tenLop;
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
	private Date ngayBatdau;
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
	private Date ngayKetthuc;
	private int soGioHoc;

	public LopDTO() {
	}

	public LopDTO(Integer maLop, String tenLop, Date ngayBatdau, Date ngayKetthuc, int soGioHoc) {
		this.maLop = maLop;
		this.tenLop = tenLop;
		this.ngayBatdau = ngayBatdau;
		this.ngayKetthuc = ngayKetthuc;
		this.soGioHoc = soGioHoc;
	}

	public Integer getMaLop() {
		return this.maLop;
	}

	public void setMaLop(Integer maLop) {
		this.maLop = maLop;
	}

	public String getTenLop() {
		return this.tenLop;
	}

	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}

	public Date getNgayBatdau() {
		return this.ngayBatdau;
	}

	public void setNgayBatdau(Date ngayBatdau) {
		this.ngayBatdau = ngayBatdau;
	}

	public Date getNgayKetthuc() {
		return this.ngayKetthuc;
	}

	public void setNgayKetthuc(Date ngayKetthuc) {
		this.ngayKetthuc = ngayKetthuc;
	}

	public int getSoGioHoc() {
		return this.soGioHoc;
	}

	public void setSoGioHoc(int soGioHoc) {
		this.soGioHoc = soGioHoc;
	}
	
	@Override
	public String toString() {
		return "Lop [maLop=" + maLop + ", tenLop=" + tenLop + "]";
	}

}
