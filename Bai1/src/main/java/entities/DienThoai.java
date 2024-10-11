package entities;

public class DienThoai {
	private long id;
	private String tenDT;
	private int namSanXuat;
	private String cauHinh;
	private String hinhAnh;

	private NhaCungCap nhaCungCap;

	public DienThoai(long id, String tenDT, int namSanXuat, String cauHinh, String hinhAnh, NhaCungCap nhaCungCap) {
		super();
		this.id = id;
		this.tenDT = tenDT;
		this.namSanXuat = namSanXuat;
		this.cauHinh = cauHinh;
		this.hinhAnh = hinhAnh;
		this.nhaCungCap = nhaCungCap;
	}

	public DienThoai() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTenDT() {
		return tenDT;
	}

	public void setTenDT(String tenDT) {
		this.tenDT = tenDT;
	}

	public int getNamSanXuat() {
		return namSanXuat;
	}

	public void setNamSanXuat(int namSanXuat) {
		this.namSanXuat = namSanXuat;
	}

	public String getCauHinh() {
		return cauHinh;
	}

	public void setCauHinh(String cauHinh) {
		this.cauHinh = cauHinh;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}

	@Override
	public String toString() {
		return "DienThoai [id=" + id + ", tenDT=" + tenDT + ", namSanXuat=" + namSanXuat + ", cauHinh=" + cauHinh
				+ ", hinhAnh=" + hinhAnh + ", nhaCungCap=" + nhaCungCap + "]";
	}

}
