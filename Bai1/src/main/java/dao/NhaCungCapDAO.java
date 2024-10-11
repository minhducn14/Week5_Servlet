package dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import entities.DienThoai;
import entities.NhaCungCap;

public interface NhaCungCapDAO {
	public List<NhaCungCap> getAllNhaCungCap();

	public Optional<NhaCungCap> getNhaCungCap(int maNhaCungCap);

	public boolean addNhaCungCap(NhaCungCap nhaCungCap);

	public boolean updateNhaCungCap(NhaCungCap nhaCungCap);

	public boolean deleteNhaCungCap(int maNhaCungCap);

	List<NhaCungCap> getNhaCungCap(String searchTerm);

}
