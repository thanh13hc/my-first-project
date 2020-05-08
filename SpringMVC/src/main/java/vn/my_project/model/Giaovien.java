package vn.my_project.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "giaovien", catalog = "my_project")
public class Giaovien implements java.io.Serializable {

	private Integer maGiaovien;
	private String ho;
	private String ten;
	private Set<Lop> lops = new HashSet<Lop>(0);

	public Giaovien() {
	}

	public Giaovien(Integer maGiaovien) {
		this.maGiaovien = maGiaovien;
	}

	public Giaovien(String ho, String ten) {
		this.ho = ho;
		this.ten = ten;
	}

	public Giaovien(String ho, String ten, Set<Lop> lops) {
		this.ho = ho;
		this.ten = ten;
		this.lops = lops;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ma_giaovien", unique = true, nullable = false)
	public Integer getMaGiaovien() {
		return this.maGiaovien;
	}

	public void setMaGiaovien(Integer maGiaovien) {
		this.maGiaovien = maGiaovien;
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
	@JoinTable(name = "giaovien_lop", catalog = "my_project", joinColumns = {
			@JoinColumn(name = "ma_giaovien", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "ma_lop", nullable = false, updatable = false) })
	public Set<Lop> getLops() {
		return this.lops;
	}

	public void setLops(Set<Lop> lops) {
		this.lops = lops;
	}

	@Override
	public String toString() {
		return "Giaovien [maGiaovien=" + maGiaovien + ", ho=" + ho + ", ten=" + ten + "]";
	}
	
	

}
