package platform.web.springmvc.dto;

public class SinhvienDTO{

	private int maSinhvien;
	private String ho;
	private String ten;

	public SinhvienDTO() {
	}

	public SinhvienDTO(int maSinhvien, String ho, String ten) {
		this.maSinhvien = maSinhvien;
		this.ho = ho;
		this.ten = ten;
	}

	public int getMaSinhvien() {
		return this.maSinhvien;
	}

	public void setMaSinhvien(int maSinhvien) {
		this.maSinhvien = maSinhvien;
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
		return "SinhvienDTO [maSinhvien=" + maSinhvien + ", ho=" + ho + ", ten=" + ten + "]";
	}

}
