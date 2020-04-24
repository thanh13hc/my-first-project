package platform.web.springmvc.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sinhvien", catalog = "my_project")
public class Sinhvien implements java.io.Serializable {

	private int maSinhvien;
	private String ho;
	private String ten;
	private Set<Lop> lops = new HashSet<Lop>(0);

	public Sinhvien() {
	}

	public Sinhvien(int maSinhvien, String ho, String ten) {
		this.maSinhvien = maSinhvien;
		this.ho = ho;
		this.ten = ten;
	}

	public Sinhvien(int maSinhvien, String ho, String ten, Set<Lop> lops) {
		this.maSinhvien = maSinhvien;
		this.ho = ho;
		this.ten = ten;
		this.lops = lops;
	}

	@Id

	@Column(name = "ma_sinhvien", unique = true, nullable = false)
	public int getMaSinhvien() {
		return this.maSinhvien;
	}

	public void setMaSinhvien(int maSinhvien) {
		this.maSinhvien = maSinhvien;
	}

	@Column(name = "ho", nullable = false, length = 20)
	public String getHo() {
		return this.ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	@Column(name = "ten", nullable = false, length = 15)
	public String getTen() {
		return this.ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sinhvien_lop", catalog = "my_project", joinColumns = {
			@JoinColumn(name = "ma_sinhvien", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "ma_lop", nullable = false, updatable = false) })
	public Set<Lop> getLops() {
		return this.lops;
	}

	public void setLops(Set<Lop> lops) {
		this.lops = lops;
	}

	@Override
	public String toString() {
		return "Sinhvien [maSinhvien=" + maSinhvien + ", ho=" + ho + ", ten=" + ten + ", lops=" + lops + "]";
	}
	
}
