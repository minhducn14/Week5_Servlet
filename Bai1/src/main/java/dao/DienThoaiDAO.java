package dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import entities.DienThoai;
import entities.NhaCungCap;

public interface DienThoaiDAO {
	public boolean addDienThoai(DienThoai tt);

	public boolean updateDienThoai(DienThoai tt);

	public boolean deleteDienThoai(long maDienThoai);

	public Optional<DienThoai> getDienThoai(long maDienThoai);

	public List<DienThoai> getAllDienThoai();

	public List<DienThoai> getDienThoaiByMaNhaCungCap(int maNhaCungCap);

	public List<DienThoai> getTatCaDienThoaiNhomTheoNhaCungCap();

	public List<DienThoai> timKiemDienThoaiTheoNhaCungCap(String thongTinTimKiem);

}
