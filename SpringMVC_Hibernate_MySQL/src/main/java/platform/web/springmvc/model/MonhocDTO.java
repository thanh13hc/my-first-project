package platform.web.springmvc.model;

public class MonhocDTO {

	private Integer maMonhoc;
	private String tenMonhoc;

	public MonhocDTO() {
	}

	public MonhocDTO(Integer maMon,String tenMonhoc) {
		this.maMonhoc = maMon;
		this.tenMonhoc = tenMonhoc;
	}

	public Integer getMaMonhoc() {
		return this.maMonhoc;
	}

	public void setMaMonhoc(Integer maMonhoc) {
		this.maMonhoc = maMonhoc;
	}

	public String getTenMonhoc() {
		return this.tenMonhoc;
	}

	public void setTenMonhoc(String tenMonhoc) {
		this.tenMonhoc = tenMonhoc;
	}

	@Override
	public String toString() {
		return "Monhoc [maMonhoc=" + maMonhoc + ", tenMonhoc=" + tenMonhoc + "]";
	}

}
