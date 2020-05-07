package platform.web.springmvc.model;

public class GiaovienDTO {

	private Integer maGiaovien;
	private String ho;
	private String ten;

	public GiaovienDTO() {
	}

	public GiaovienDTO(Integer maGiaovien, String ho, String ten) {
		this.maGiaovien = maGiaovien;
		this.ho = ho;
		this.ten = ten;
	}

	public Integer getMaGiaovien() {
		return this.maGiaovien;
	}

	public void setMaGiaovien(Integer maGiaovien) {
		this.maGiaovien = maGiaovien;
	}

	public String getHo() {
		return this.ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return this.ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	@Override
	public String toString() {
		return "Giaovien [maGiaovien=" + maGiaovien + ", ho=" + ho + ", ten=" + ten + "]";
	}
	
}
