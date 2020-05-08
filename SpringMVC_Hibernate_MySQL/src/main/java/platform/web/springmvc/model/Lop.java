package platform.web.springmvc.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "lop", catalog = "my_project")
public class Lop implements java.io.Serializable {

	private Integer maLop;
	private Monhoc monhoc;
	private String tenLop;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	// or use @DateTimeFormat(pattern = DateTimeFormat.ISO.DATE)
	private Date ngayBatdau;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ngayKetthuc;
	private int soGioHoc;
	private Set<Giaovien> giaoviens = new HashSet<Giaovien>(0);
	private Set<Sinhvien> sinhviens = new HashSet<Sinhvien>(0);

	public Lop() {
	}

	public Lop(Monhoc monhoc, String tenLop, Date ngayBatdau, Date ngayKetthuc, int soGioHoc) {
		this.monhoc = monhoc;
		this.tenLop = tenLop;
		this.ngayBatdau = ngayBatdau;
		this.ngayKetthuc = ngayKetthuc;
		this.soGioHoc = soGioHoc;
	}

	public Lop(Monhoc monhoc, String tenLop, Date ngayBatdau, Date ngayKetthuc, int soGioHoc, Set<Giaovien> giaoviens,
			Set<Sinhvien> sinhviens) {
		this.monhoc = monhoc;
		this.tenLop = tenLop;
		this.ngayBatdau = ngayBatdau;
		this.ngayKetthuc = ngayKetthuc;
		this.soGioHoc = soGioHoc;
		this.giaoviens = giaoviens;
		this.sinhviens = sinhviens;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ma_lop", unique = true, nullable = false)
	public Integer getMaLop() {
		return this.maLop;
	}

	public void setMaLop(Integer maLop) {
		this.maLop = maLop;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ma_monhoc", nullable = false)
	public Monhoc getMonhoc() {
		return this.monhoc;
	}

	public void setMonhoc(Monhoc monhoc) {
		this.monhoc = monhoc;
	}

	@Column(name = "ten_lop", nullable = false, length = 45)
	public String getTenLop() {
		return this.tenLop;
	}

	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ngay_batdau", nullable = false, length = 10)
	public Date getNgayBatdau() {
		return this.ngayBatdau;
	}

	public void setNgayBatdau(Date ngayBatdau) {
		this.ngayBatdau = ngayBatdau;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ngay_ketthuc", nullable = false, length = 10)
	public Date getNgayKetthuc() {
		return this.ngayKetthuc;
	}

	public void setNgayKetthuc(Date ngayKetthuc) {
		this.ngayKetthuc = ngayKetthuc;
	}

	@Column(name = "so_gio_hoc", nullable = false)
	public int getSoGioHoc() {
		return this.soGioHoc;
	}

	public void setSoGioHoc(int soGioHoc) {
		this.soGioHoc = soGioHoc;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "giaovien_lop", joinColumns = { @JoinColumn(name = "ma_lop") }, inverseJoinColumns = {
			@JoinColumn(name = "ma_giaovien") })
	public Set<Giaovien> getGiaoviens() {
		return this.giaoviens;
	}

	public void setGiaoviens(Set<Giaovien> giaoviens) {
		this.giaoviens = giaoviens;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sinhvien_lop", joinColumns = { @JoinColumn(name = "ma_lop") }, inverseJoinColumns = {
			@JoinColumn(name = "ma_sinhvien") })
	public Set<Sinhvien> getSinhviens() {
		return this.sinhviens;
	}

	public void setSinhviens(Set<Sinhvien> sinhviens) {
		this.sinhviens = sinhviens;
	}

	@Override
	public String toString() {
		return "Lop [maLop=" + maLop + ", tenLop=" + tenLop + "]";
	}

}
