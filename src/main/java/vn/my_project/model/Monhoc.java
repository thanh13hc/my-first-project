package vn.my_project.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "monhoc", catalog = "my_project")
public class Monhoc implements java.io.Serializable {

	private Integer maMonhoc;
	private String tenMonhoc;
	private Set<Lop> lops = new HashSet<Lop>(0);

	public Monhoc() {
	}

	public Monhoc(Integer maMon,String tenMonhoc) {
		this.maMonhoc = maMon;
		this.tenMonhoc = tenMonhoc;
	}
	
	public Monhoc(String tenMonhoc) {
		this.tenMonhoc = tenMonhoc;
	}

	public Monhoc(String tenMonhoc, Set<Lop> lops) {
		this.tenMonhoc = tenMonhoc;
		this.lops = lops;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ma_monhoc", unique = true, nullable = false)
	public Integer getMaMonhoc() {
		return this.maMonhoc;
	}

	public void setMaMonhoc(Integer maMonhoc) {
		this.maMonhoc = maMonhoc;
	}

	@Column(name = "ten_monhoc", nullable = false, length = 45)
	public String getTenMonhoc() {
		return this.tenMonhoc;
	}

	public void setTenMonhoc(String tenMonhoc) {
		this.tenMonhoc = tenMonhoc;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "monhoc")
	public Set<Lop> getLops() {
		return this.lops;
	}

	public void setLops(Set<Lop> lops) {
		this.lops = lops;
	}

}
