<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm Điện Thoại</title>

<script type="text/javascript">
	function testValid() {
		var maDienThoai = document.getElementsByName("maDienThoai")[0].value;
		var tenDienThoai = document.getElementsByName("tenDienThoai")[0].value;
		var cauHinh = document.getElementsByName("cauHinh")[0].value;
		var namSanXuat = document.getElementsByName("namSanXuat")[0].value;
		var hinhAnh = document.getElementsByName("hinhAnh")[0].value;

		// Kiểm tra mã điện thoại và tên điện thoại
		if (maDienThoai.trim() == "" || tenDienThoai.trim() == "" || cauHinh.trim() == "" || namSanXuat.trim() == "" || hinhAnh.trim == "") {
			alert("Vui lòng nhập đầy đủ");
			return false;
		}

		// Kiểm tra thông tin cấu hình không quá 255 ký tự
		if (cauHinh.length > 255) {
			alert("Thông tin cấu hình không quá 255 ký tự.");
			return false;
		}

		// Kiểm tra năm sản xuất (phải là số nguyên gồm 4 chữ số)
		var namSanXuatPattern = /^\d{4}$/;
		if (!namSanXuatPattern.test(namSanXuat)) {
			alert("Năm sản xuất phải là số nguyên gồm 4 chữ số.");
			return false;
		}

		// Kiểm tra định dạng file hình ảnh (chỉ chấp nhận png, jpg, jpeg)
		var imagePattern = /\.(png|jpg|jpeg)$/i;
		if (!imagePattern.test(hinhAnh)) {
			alert("Chỉ chấp nhận tệp hình ảnh có định dạng .png, .jpg, .jpeg.");
			return false;
		}

		return true;
	}
</script>
</head>
<body>
	<h1>Thêm Điện Thoại</h1>
	<form action="DienThoaiForm" method="post"
		enctype="multipart/form-data" onsubmit="return testValid();">

		<input type="hidden" name="action" value="add" />
		<table>
			<tr>
				<td>Mã điện thoại</td>
				<td><input type="text" name="maDienThoai" required="required" /></td>
			</tr>
			<tr>
				<td>Tên điện thoại</td>
				<td><input type="text" name="tenDienThoai" required="required" /></td>
			</tr>
			<tr>
				<td>Cấu hình</td>
				<td><input type="text" name="cauHinh" required="required" /></td>
			<tr>
				<td>Năm Sản Xuất</td>
				<td><input type="text" name="namSanXuat" required="required" /></td>
			</tr>
			<tr>
				<td>Hình Ảnh</td>
				<td><input type="file" name="hinhAnh" accept=".png,.jpg,.jpeg"
					required="required" /></td>
			</tr>
			<tr>
				<td>Nhà cung cấp</td>
				<td><select name="nhaCungCap">
						<c:forEach items="${nhaCungCaps}" var="nhaCungCap">
							<option value="${nhaCungCap.id}">${nhaCungCap.tenNhaCC}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Thêm" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
