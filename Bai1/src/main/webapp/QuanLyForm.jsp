<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="entities.NhaCungCap"%>
<%@ page import="entities.DienThoai"%>

<html>
<head>
    <title>Quản Lý Form</title>
    <style type="text/css">
        table {
            border-collapse: collapse;
            width: 100%;
        }

        table, th, td {
            border: 1px solid black;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        #header {
            background-color: #f1f1f1;
            text-align: center;
            padding: 10px;
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            align-items: center;
        }

        #message {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div id="header">
       <h1>Quản lý form</h1>
        <h1>
            <a href="${pageContext.request.contextPath}">Trang Chủ</a>
        </h1>
    </div>

    <table border="1">
        <thead>
            <tr>
                <th>Nhà cung cấp</th>
                <th>Mã điện thoại</th>
                <th>Tên điện thoại</th>
                <th>Năm sản xuất</th>
                <th>Cấu Hình</th>
                <th>Hình Ảnh</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${not empty nhaCungCapDienThoais}">
                <c:forEach var="dt" items="${nhaCungCapDienThoais}">
                    <tr>
                        <td>${dt.nhaCungCap.tenNhaCC}</td>
                        <td>${dt.id}</td>
                        <td>${dt.tenDT}</td>
                        <td>${dt.namSanXuat}</td>
                        <td>${dt.cauHinh}</td>
                        <td><img src="${pageContext.request.contextPath}/resources/images/${dt.hinhAnh}" alt="Hình ảnh điện thoại" width="100" height="100" /></td>
                    	<td>
                    		<form action="QuanLyForm" method="post">
                    			<input type="hidden" name="action" value="delete" />
                    			<input type="hidden" name="id" value="${dt.id}" />
                    			<input type="submit" value="Xóa" onclick="return confirm('Bạn có chắc chắn muốn xóa?')"/>
                    		</form>
                    	</td>
                    </tr>
                </c:forEach>
            </c:if>
        </tbody>
    </table>
</body>
</html>
