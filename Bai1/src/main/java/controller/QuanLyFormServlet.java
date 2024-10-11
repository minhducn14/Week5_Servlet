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

/**
 * Servlet implementation class QuanLyFormServlet
 */
public class QuanLyFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/quanlydienthoai")
	private DataSource dataSource;
	private DienThoaiDAO dienThoaiDAO;
	private NhaCungCapDAO nhaCungCapDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuanLyFormServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		dienThoaiDAO = new DienThoaiDAOImpl(this.dataSource);
		nhaCungCapDAO = new NhaCungCapDAOImpl(this.dataSource);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<DienThoai> nhaCungCapDienThoais = dienThoaiDAO.getTatCaDienThoaiNhomTheoNhaCungCap();
		request.setAttribute("nhaCungCapDienThoais", nhaCungCapDienThoais);
		request.getRequestDispatcher("/QuanLyForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String maDienThoai = request.getParameter("id");
		if (maDienThoai != null) {
			dienThoaiDAO.deleteDienThoai(Long.parseLong(maDienThoai));
		}
		doGet(request, response);
	}

}
