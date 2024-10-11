package entities;

public class NhaCungCap {
	private int id;
	private String tenNhaCC;
	private String diaChi;
	private String soDienThoai;

	public NhaCungCap(int id, String tenNhaCC, String diaChi, String soDienThoai) {
		super();
		this.id = id;
		this.tenNhaCC = tenNhaCC;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
	}

	public NhaCungCap() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenNhaCC() {
		return tenNhaCC;
	}

	public void setTenNhaCC(String tenNhaCC) {
		this.tenNhaCC = tenNhaCC;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	@Override
	public String toString() {
		return "NhaCungCap [id=" + id + ", tenNhaCC=" + tenNhaCC + ", diaChi=" + diaChi + ", soDienThoai=" + soDienThoai
				+ "]";
	}

}
