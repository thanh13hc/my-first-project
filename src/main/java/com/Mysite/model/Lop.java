package com.Mysite.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "lop", catalog = "dhxd")
public class Lop implements Serializable {

	@Id
	@Column(name = "malop", unique = true, nullable = false)
	private int maLop;

	@Column(name = "tenlop", nullable = false, length = 45)
	private String tenLop;

	@ManyToOne
	@JoinColumn(name = "makhoa")
	private Khoa khoaLop;

	@OneToMany(mappedBy = "lopSv", fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Sinhvien> sinhVien;

	public Lop() {

	}

	public Lop(int maLop) {
		super();
		this.maLop = maLop;
	}
	public Lop(int maLop, String tenLop, Khoa khoaLop, List<Sinhvien> sinhVien) {
		super();
		this.maLop = maLop;
		this.tenLop = tenLop;
		this.khoaLop = khoaLop;
		this.sinhVien = sinhVien;
	}

	public int getMaLop() {
		return maLop;
	}

	public void setMaLop(int maLop) {
		this.maLop = maLop;
	}

	public String getTenLop() {
		return tenLop;
	}

	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}

	public Khoa getKhoaLop() {
		return khoaLop;
	}

	public void setKhoaLop(Khoa khoaLop) {
		this.khoaLop = khoaLop;
	}

	public List<Sinhvien> getSinhVien() {
		return sinhVien;
	}

	public void setSinhVien(List<Sinhvien> sinhVien) {
		this.sinhVien = sinhVien;
	}

}
