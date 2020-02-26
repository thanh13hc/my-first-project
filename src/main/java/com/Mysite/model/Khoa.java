package com.Mysite.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "khoa", catalog = "dhxd")
public class Khoa implements Serializable {

	@Id
	@Column(name = "makhoa", unique = true, nullable = false)
	private int maKhoa;

	@Column(name = "tenkhoa", nullable = false, length = 45)
	private String tenKhoa;

	@OneToMany(mappedBy = "khoaLop", fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Lop> lop;

	@OneToMany(mappedBy = "khoaSv", fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Sinhvien> sinhVien;

	public Khoa() {

	}
	
	public Khoa(int maKhoa) {
		super();
		this.maKhoa = maKhoa;
	}

	public Khoa(int maKhoa, String tenKhoa, List<Lop> lop, List<Sinhvien> sinhvien) {
		super();
		this.maKhoa = maKhoa;
		this.tenKhoa = tenKhoa;
		this.lop = lop;
		this.sinhVien = sinhvien;
	}

	public int getMaKhoa() {
		return maKhoa;
	}

	public void setMaKhoa(int maKhoa) {
		this.maKhoa = maKhoa;
	}

	public String getTenKhoa() {
		return tenKhoa;
	}

	public void setTenKhoa(String tenKhoa) {
		this.tenKhoa = tenKhoa;
	}

	public List<Lop> getLop() {
		return lop;
	}

	public void setLop(List<Lop> lop) {
		this.lop = lop;
	}

	public List<Sinhvien> getSinhvien() {
		return sinhVien;
	}

	public void setSinhvien(List<Sinhvien> sinhvien) {
		this.sinhVien = sinhvien;
	}

}
