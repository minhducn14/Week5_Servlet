package controller;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.io.File;

import javax.sql.DataSource;

import dao.DienThoaiDAO;
import dao.NhaCungCapDAO;
import daoImpl.DienThoaiDAOImpl;
import daoImpl.NhaCungCapDAOImpl;
import entities.DienThoai;
import entities.NhaCungCap;

/**
 * Servlet implementation class DienThoaiFormServlet
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class DienThoaiFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/quanlydienthoai")
	private DataSource dataSource;
	private DienThoaiDAO dienThoaiDAO;
	private NhaCungCapDAO nhaCungCapDAO;

	public DienThoaiFormServlet() {
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
		List<NhaCungCap> nhaCungCaps = nhaCungCapDAO.getAllNhaCungCap();
		request.setAttribute("nhaCungCaps", nhaCungCaps);
		request.getRequestDispatcher("/DienThoaiForm.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			Part filePart = request.getPart("hinhAnh");

			if (filePart != null && filePart.getSize() > 0 && filePart.getSubmittedFileName() != null
					&& !filePart.getSubmittedFileName().isEmpty()) {
				String fileName = filePart.getSubmittedFileName();

				if (!fileName.endsWith(".png") && !fileName.endsWith(".jpg") && !fileName.endsWith(".jpeg")) {
					request.setAttribute("message", "Chỉ chấp nhận tệp hình ảnh định dạng .png, .jpg, .jpeg");
					request.getRequestDispatcher("/DienThoaiForm.jsp").forward(request, response);
					return;
				}

				String relativeWebPath = "/resources/images";
				String absoluteDiskPath = getServletContext().getRealPath(relativeWebPath);

				File uploadDir = new File(absoluteDiskPath);
				if (!uploadDir.exists()) {
					uploadDir.mkdirs();
				}

				filePart.write(absoluteDiskPath + File.separator + fileName);

				String maDienThoai = request.getParameter("maDienThoai");
				String tenDienThoai = request.getParameter("tenDienThoai");
				String namSanXuat = request.getParameter("namSanXuat");
				String nhaCungCapId = request.getParameter("nhaCungCap");
				String cauHinh = request.getParameter("cauHinh");

				int maNhaCungCap = Integer.parseInt(nhaCungCapId);
				NhaCungCap nhaCungCap = nhaCungCapDAO.getNhaCungCap(maNhaCungCap).get();
				DienThoai dt = new DienThoai();
				dt.setTenDT(tenDienThoai);
				dt.setNamSanXuat(Integer.parseInt(namSanXuat));
				dt.setCauHinh(cauHinh);
				dt.setNhaCungCap(nhaCungCap);
				dt.setHinhAnh(fileName);
				int maDT = Integer.parseInt(maDienThoai);
				dt.setId(maDT);
				dienThoaiDAO.addDienThoai(dt);

				response.sendRedirect(request.getContextPath() + "/DanhSachDienThoaiNCC");

			} else {
				request.setAttribute("message", "Vui lòng chọn một file hợp lệ.");
				request.getRequestDispatcher("/DienThoaiForm.jsp").forward(request, response);
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
