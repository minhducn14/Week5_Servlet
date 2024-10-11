package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import dao.DienThoaiDAO;
import dao.NhaCungCapDAO;
import entities.DienThoai;
import entities.NhaCungCap;

public class DienThoaiDAOImpl implements DienThoaiDAO {
	private DataSource dataSource;
	private NhaCungCapDAO nhaCungCapDAO;

	public DienThoaiDAOImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
		nhaCungCapDAO = new NhaCungCapDAOImpl(this.dataSource);
	}

	@Override
	public boolean addDienThoai(DienThoai dt) {
		String sql = "INSERT INTO DIENTHOAI (MADT, TENDT, NAMSANXUAT, CAUHINH, MANCC, HINHANH) VALUES (?, ?, ?, ?, ?,?)";
		try (Connection con = this.dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, dt.getId());
			ps.setString(2, dt.getTenDT());
			ps.setInt(3, dt.getNamSanXuat());
			ps.setString(4, dt.getCauHinh());
			ps.setInt(5, dt.getNhaCungCap().getId());
			ps.setString(6, dt.getHinhAnh());

			int rowsInserted = ps.executeUpdate();
			return rowsInserted > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateDienThoai(DienThoai dt) {
		String sql = "UPDATE DIENTHOAI SET TENDT=?, NAMSANXUAT=?, CAUHINH=?, MANCC=?, HINHANH=? WHERE MADT=?";
		try (Connection con = this.dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, dt.getTenDT());
			ps.setInt(2, dt.getNamSanXuat());
			ps.setString(3, dt.getCauHinh());
			ps.setInt(4, dt.getNhaCungCap().getId());
			ps.setString(5, dt.getHinhAnh());
			ps.setLong(6, dt.getId());

			int rowsUpdated = ps.executeUpdate();
			return rowsUpdated > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteDienThoai(long maDienThoai) {
		String sql = "DELETE FROM DIENTHOAI WHERE MADT=?";
		try (Connection con = this.dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, maDienThoai);

			int rowsDeleted = ps.executeUpdate();
			return rowsDeleted > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Optional<DienThoai> getDienThoai(long maDienThoai) {
		String sql = "SELECT * FROM DIENTHOAI WHERE MADT=?";
		try (Connection con = this.dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, maDienThoai);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					DienThoai dienThoai = new DienThoai();
					dienThoai.setId(rs.getLong("MADT"));
					dienThoai.setTenDT(rs.getString("TENDT"));
					dienThoai.setNamSanXuat(rs.getInt("NAMSANXUAT"));
					dienThoai.setCauHinh(rs.getString("CAUHINH"));

					int maNCC = rs.getInt("MANCC");
					NhaCungCap nhaCungCap = nhaCungCapDAO.getNhaCungCap(maNCC).get();
					dienThoai.setNhaCungCap(nhaCungCap);
					dienThoai.setHinhAnh(rs.getString("HINHANH"));
					return Optional.of(dienThoai);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public List<DienThoai> getAllDienThoai() {
		String sql = "SELECT * FROM DIENTHOAI";
		List<DienThoai> dienThoais = new ArrayList<>();
		try (Connection con = this.dataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				DienThoai dienThoai = new DienThoai();
				dienThoai.setId(rs.getLong("MADT"));
				dienThoai.setTenDT(rs.getString("TENDT"));
				dienThoai.setNamSanXuat(rs.getInt("NAMSANXUAT"));
				dienThoai.setCauHinh(rs.getString("CAUHINH"));
				int maNCC = rs.getInt("MANCC");
				NhaCungCap nhaCungCap = nhaCungCapDAO.getNhaCungCap(maNCC).get();
				dienThoai.setNhaCungCap(nhaCungCap);
				dienThoai.setHinhAnh(rs.getString("HINHANH"));
				dienThoais.add(dienThoai);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dienThoais;
	}

	@Override
	public List<DienThoai> getDienThoaiByMaNhaCungCap(int maNhaCungCap) {
		String sql = "SELECT * FROM DIENTHOAI WHERE MANCC=?  GROUP BY MANCC;" + "";
		List<DienThoai> dienThoais = new ArrayList<>();
		try (Connection con = this.dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, maNhaCungCap);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					DienThoai dienThoai = new DienThoai();
					dienThoai.setId(rs.getLong("MADT"));
					dienThoai.setTenDT(rs.getString("TENDT"));
					dienThoai.setNamSanXuat(rs.getInt("NAMSANXUAT"));
					dienThoai.setCauHinh(rs.getString("CAUHINH"));
					int maNCC = rs.getInt("MANCC");
					NhaCungCap nhaCungCap = nhaCungCapDAO.getNhaCungCap(maNCC).get();
					dienThoai.setNhaCungCap(nhaCungCap);
					dienThoai.setHinhAnh(rs.getString("HINHANH"));
					dienThoais.add(dienThoai);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dienThoais;
	}

	@Override
	public List<DienThoai> getTatCaDienThoaiNhomTheoNhaCungCap() {
		String sql = "SELECT dt.MANCC,dt.MADT, dt.TENDT,dt.NAMSANXUAT,dt.CAUHINH,dt.HINHANH FROM dienthoai dt Order By MANCC";
		List<DienThoai> dienThoais = new ArrayList<>();
		try (Connection con = this.dataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				DienThoai dienThoai = new DienThoai();
				dienThoai.setId(rs.getLong("MADT"));
				dienThoai.setTenDT(rs.getString("TENDT"));
				dienThoai.setNamSanXuat(rs.getInt("NAMSANXUAT"));
				dienThoai.setCauHinh(rs.getString("CAUHINH"));
				int maNCC = rs.getInt("MANCC");
				NhaCungCap nhaCungCap = nhaCungCapDAO.getNhaCungCap(maNCC).get();
				dienThoai.setNhaCungCap(nhaCungCap);
				dienThoai.setHinhAnh(rs.getString("HINHANH"));
				dienThoais.add(dienThoai);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dienThoais;
	}

	public List<DienThoai> timKiemDienThoaiTheoNhaCungCap(String thongTinTimKiem) {
		List<DienThoai> dienThoais = new ArrayList<>();
		String sql = "SELECT * FROM DienThoai dt JOIN NhaCungCap ncc ON dt.MANCC = ncc.MANCC "
				+ "WHERE ncc.MANCC = ? OR ncc.TENNHACC LIKE ? OR ncc.DIACHI LIKE ? OR ncc.SODIENTHOAI LIKE ?";

		try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			if (thongTinTimKiem.matches("\\d+")) {
				ps.setInt(1, Integer.parseInt(thongTinTimKiem));
			} else {
				ps.setNull(1, java.sql.Types.INTEGER);
			}

			String likePattern = "%" + thongTinTimKiem + "%";
			ps.setString(2, likePattern);
			ps.setString(3, likePattern);
			ps.setString(4, likePattern);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					DienThoai dt = new DienThoai();
					dt.setId(rs.getLong("MADT"));
					dt.setTenDT(rs.getString("TENDT"));
					dt.setNamSanXuat(rs.getInt("NAMSANXUAT"));
					dt.setCauHinh(rs.getString("CAUHINH"));
					dt.setHinhAnh(rs.getString("HINHANH"));
					NhaCungCap ncc = nhaCungCapDAO.getNhaCungCap(rs.getInt("MANCC")).get();
					dt.setNhaCungCap(ncc);
					dienThoais.add(dt);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dienThoais;
	}

}
