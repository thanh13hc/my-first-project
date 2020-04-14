
	<div class="row" style="background-color: #435D7D;">
		<div class="col-sm-8 mt-3 mb-3">
			<h4 style="color: whitesmoke;">
				Quản lí <span style="font-weight: bolder;">môn học </span>
			</h4>
		</div>
		<div class="col-sm-4 mt-3 mb-3">
			<button class="btn btn-success">Thêm mới</button>
		</div>
	</div>
	<form action="/QuanLiLopMVC/mon/deletes?p=${param.p}" method="post">
		<table class="table">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">Mã môn học</th>
					<th scope="col">Tên môn học</th>
					<th scope="col">Actions</th>
				</tr>
			</thead>
			<c:forEach var="mon" items="${list}">
				<tbody>
					<tr>
						<th scope="row"><input type="checkbox" name="${mon.maMonhoc}">
						<td>${mon.maMonhoc}</td>
						<td>${mon.tenMonhoc}</td>
						<td><a
							href="/QuanLiLopMVC/mon/form-edit?id=${mon.maMonhoc}"> <i
								class="far fa-edit"></i>
						</a> <a href="/QuanLiLopMVC/mon/delete?id=${mon.maMonhoc}"><i
								class="fas fa-trash"></i></a></td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
		<input type="submit" class="btn btn-danger" value="Xóa">
	</form>
	<div class="row mt-3">
		<%
		Integer p = Integer.valueOf((request.getParameter("p") == null ? "1" : request.getParameter("p")));
			Integer maxPage = (Integer) request.getAttribute("maxPage");
		%>
		<c:choose>
			<c:when test="<%=p > 1%>">
				<a href="/QuanLiLopMVC/mon/list-mon-hoc?p=<%=p - 1%>">Previous</a>
			</c:when>
			<c:otherwise>
				<a href="/QuanLiLopMVC/mon/list-mon-hoc?p=1">Previous</a>
			</c:otherwise>
		</c:choose>

		<span style="margin: 0px 1.5rem; color: #333; letter-spacing: 10px">
			<span><%=p == null ? 1 : p%></span>
			/ ${maxPage}
		</span>

		<c:choose>
			<c:when test="<%=p < maxPage%>">
				<a href="/QuanLiLopMVC/mon/list-mon-hoc?p=<%=p + 1%>">Next</a>
			</c:when>
			<c:otherwise>
				<a href="/QuanLiLopMVC/mon/list-mon-hoc?p=${maxPage}">Next</a>
			</c:otherwise>
		</c:choose>
	</div>
