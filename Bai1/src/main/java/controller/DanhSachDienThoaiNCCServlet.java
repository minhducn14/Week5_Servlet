package controller;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import javax.sql.DataSource;

import dao.DienThoaiDAO;
import dao.NhaCungCapDAO;
import daoImpl.DienThoaiDAOImpl;
import daoImpl.NhaCungCapDAOImpl;
import entities.DienThoai;
import entities.NhaCungCap;

@WebServlet("/DanhSachDienThoaiNCCServlet")
public class DanhSachDienThoaiNCCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/quanlydienthoai")
	private DataSource dataSource;
	private DienThoaiDAO dienThoaiDAO;
	private NhaCungCapDAO nhaCungCapDAO;

	public DanhSachDienThoaiNCCServlet() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dienThoaiDAO = new DienThoaiDAOImpl(this.dataSource);
		nhaCungCapDAO = new NhaCungCapDAOImpl(this.dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String thongTinTimKiem = request.getParameter("thongTinTimKiem");
		List<DienThoai> nhaCungCapDienThoais;

		if (thongTinTimKiem != null && !thongTinTimKiem.isEmpty()) {
			nhaCungCapDienThoais = dienThoaiDAO.timKiemDienThoaiTheoNhaCungCap(thongTinTimKiem);
		} else {
			nhaCungCapDienThoais = dienThoaiDAO.getTatCaDienThoaiNhomTheoNhaCungCap();
		}

		if (nhaCungCapDienThoais != null && !nhaCungCapDienThoais.isEmpty()) {
			request.setAttribute("nhaCungCapDienThoais", nhaCungCapDienThoais);
		} else {
			request.setAttribute("message", "Không tìm thấy điện thoại nào.");
		}

		request.getRequestDispatcher("/DanhSachDienThoaiNCC.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
