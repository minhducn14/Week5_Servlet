package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import dao.NhaCungCapDAO;
import entities.NhaCungCap;

public class NhaCungCapDAOImpl implements NhaCungCapDAO {

	private DataSource dataSource;

	public NhaCungCapDAOImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public List<NhaCungCap> getAllNhaCungCap() {

		// TODO Auto-generated method stub
		String sql = "Select * from NhaCungCap";
		List<NhaCungCap> nhaCungCaps = new ArrayList<NhaCungCap>();
		try (Connection con = this.dataSource.getConnection();
				PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();) {
			while (rs.next()) {
				NhaCungCap nhaCungCap = new NhaCungCap();

				int maNhaCungCap = rs.getInt("MANCC");
				String tenNhaCungCap = rs.getString("TENNHACC");
				String diaChi = rs.getString("DIACHI");
				String soDienThoai = rs.getString("SODIENTHOAI");
				nhaCungCap.setDiaChi(diaChi);
				nhaCungCap.setId(maNhaCungCap);
				nhaCungCap.setSoDienThoai(soDienThoai);
				nhaCungCap.setTenNhaCC(tenNhaCungCap);
				nhaCungCaps.add(nhaCungCap);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return nhaCungCaps;
	}

	@Override
	public Optional<NhaCungCap> getNhaCungCap(int maNhaCungCap) {
		// TODO Auto-generated method stub
		String sql = "Select * from NhaCungCap where MANCC=?";

		try (Connection con = this.dataSource.getConnection();
				PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);) {
			ps.setInt(1, maNhaCungCap);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					NhaCungCap nhaCungCap = new NhaCungCap();
					String tenNhaCungCap = rs.getString("TENNHACC");
					String diaChi = rs.getString("DIACHI");
					String soDienThoai = rs.getString("SODIENTHOAI");
					nhaCungCap.setDiaChi(diaChi);
					nhaCungCap.setId(maNhaCungCap);
					nhaCungCap.setSoDienThoai(soDienThoai);
					nhaCungCap.setTenNhaCC(tenNhaCungCap);
					return Optional.of(nhaCungCap);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public boolean addNhaCungCap(NhaCungCap nhaCungCap) {
		String sql = "INSERT INTO NhaCungCap (MANCC,TENNHACC, DIACHI, SODIENTHOAI) VALUES (?,?, ?, ?)";
		try (Connection con = this.dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, nhaCungCap.getId());
			ps.setString(2, nhaCungCap.getTenNhaCC());
			ps.setString(3, nhaCungCap.getDiaChi());
			ps.setString(4, nhaCungCap.getSoDienThoai());

			int rowsInserted = ps.executeUpdate();
			return rowsInserted > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateNhaCungCap(NhaCungCap nhaCungCap) {
		String sql = "UPDATE NhaCungCap SET TENNHACC=?, DIACHI=?, SODIENTHOAI=? WHERE MANCC=?";
		try (Connection con = this.dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, nhaCungCap.getTenNhaCC());
			ps.setString(2, nhaCungCap.getDiaChi());
			ps.setString(3, nhaCungCap.getSoDienThoai());
			ps.setInt(4, nhaCungCap.getId());

			int rowsUpdated = ps.executeUpdate();
			return rowsUpdated > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteNhaCungCap(int maNhaCungCap) {
		String sql = "DELETE FROM NhaCungCap WHERE MANCC=?";
		try (Connection con = this.dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setLong(1, maNhaCungCap);

			int rowsDeleted = ps.executeUpdate();
			return rowsDeleted > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<NhaCungCap> getNhaCungCap(String searchTerm) {
		String sql = "SELECT * FROM NhaCungCap WHERE MANCC = ? OR TENNHACC LIKE ? OR DIACHI LIKE ? OR SODIENTHOAI LIKE ?";
		List<NhaCungCap> nhaCungCaps = new ArrayList<>();

		try (Connection con = this.dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			if (searchTerm.matches("\\d+")) {
				ps.setInt(1, Integer.parseInt(searchTerm));
			} else {
				ps.setNull(1, java.sql.Types.INTEGER);
			}

			String likePattern = "%" + searchTerm + "%";
			ps.setString(2, likePattern);
			ps.setString(3, likePattern);
			ps.setString(4, likePattern);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					NhaCungCap nhaCungCap = new NhaCungCap();
					nhaCungCap.setId(rs.getInt("MANCC"));
					nhaCungCap.setTenNhaCC(rs.getString("TENNHACC"));
					nhaCungCap.setDiaChi(rs.getString("DIACHI"));
					nhaCungCap.setSoDienThoai(rs.getString("SODIENTHOAI"));
					nhaCungCaps.add(nhaCungCap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nhaCungCaps;
	}

}
