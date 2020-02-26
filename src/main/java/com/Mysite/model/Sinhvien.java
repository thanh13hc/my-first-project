package com.Mysite.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sinhvien", catalog = "dhxd")
public class Sinhvien implements Serializable {

	@Id
	@Column(name = "msv", unique = true, nullable = false)
	private int msv;

	@Column(name = "ho", nullable = false, length = 20)
	private String ho;

	@Column(name = "ten", nullable = false, length = 15)
	private String ten;

	@ManyToOne
	@JoinColumn(name = "maKhoa")
	private Khoa khoaSv;

	@ManyToOne
	@JoinColumn(name = "maLop")
	private Lop lopSv;

	public Sinhvien() {

	}

	public Sinhvien(int msv, String ho, String ten, Khoa khoaSv, Lop lopSv) {
		super();
		this.msv = msv;
		this.ho = ho;
		this.ten = ten;
		this.khoaSv = khoaSv;
		this.lopSv = lopSv;
	}

	public int getMsv() {
		return msv;
	}

	public void setMsv(int msv) {
		this.msv = msv;
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public Khoa getKhoaSv() {
		return khoaSv;
	}

	public void setKhoaSv(Khoa khoaSv) {
		this.khoaSv = khoaSv;
	}

	public Lop getLopSv() {
		return lopSv;
	}

	public void setLopSv(Lop lopSv) {
		this.lopSv = lopSv;
	}

}
