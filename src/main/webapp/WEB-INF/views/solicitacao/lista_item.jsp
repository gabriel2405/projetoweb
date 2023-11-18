<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Selecionar Itens</title>

<c:import url="../componentes/cabecalho.jsp" />
<main class="center-v">
	<div class="container center-v">

		<div class="card border-light mb-3">
			<div class="card-header">Listagem de Itens</div>

			<!-- Table -->
			<div class="card-body">
				<table class="table datatable" id="datatable">
					<thead>
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Nome</th>
							<th scope="col">Quantidade</th>
							<th scope="col">Tipo</th>
							<th scope="col">Ações</th>
						</tr>
					</thead>
					<tbody>
						<!-- percorre cursos montando as linhas da tabela -->
						<c:forEach var="item" items="${itens}">
							<tr>
								<td scope="row">${item.id}</td>
								<td>${item.nome}</td>
								<td>${item.qtd}</td>
								<td>${item.tipo}</td>
								<td>
									<button type="button" class="btn btn-success"
										data-toggle="tooltip" data-bs-placement="bottom"
										title="Selecionar" data-bs-toggle="modal"
										data-bs-target="#modal${item.id}">Selecionar</button>
									<div class="modal fade" id="modal${item.id}" tabindex="-1">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title">Escolha a quantidade do item</h5>
													<button type="button" class="btn-close"
														data-bs-dismiss="modal" aria-label="Close">
														<span aria-hidden="true"></span>
													</button>
												</div>
												<div class="modal-body">
													<p>
														ID (${item.id}) -> ${item.nome}<br> Quantidade Atual:
														${item.qtd}<br> <label for="quantity">Quantidade:</label>
														<input type="number" id="quantity" name="quantity" min="1"
															max="${item.qtd}" required>
													</p>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-success"
														data-bs-dismiss="modal"
														onclick="selectItem(${item.id}, '${item.nome}', ${item.qtd})">Concluir</button>
													<button type="button" class="btn btn-secondary"
														data-bs-dismiss="modal">
														<i class="bi bi-x"></i> Fechar
													</button>
												</div>
											</div>
										</div>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>


			</div>
		</div>
		<table class="table table-hover" id="selectedItemsTable">
			<thead>
				<tr class="table-dark">
					<th scope="col">ID</th>
					<th scope="col">Nome</th>
					<th scope="col">Quantidade Selecionada</th>
					 <th scope="col">Ações</th>
				</tr>
			</thead>
			<tbody id="selectedItemsBody">

			</tbody>
		</table>

	</div>
</main>
<script>
function selectItem(itemId, itemName, maxQuantity) {
    var quantity = document.getElementById("quantity").value;
    if (quantity === null || quantity === "" || isNaN(quantity) || parseInt(quantity) < 1 || parseInt(quantity) > maxQuantity) {
        alert("Por favor, insira uma quantidade válida.");
        return;
    }

    var newRow = "<tr><td>" + itemId + "</td><td>" + itemName + "</td><td>" + quantity + "</td><td><button type='button' class='btn btn-danger btn-sm' onclick='deleteRow(this)'>Excluir</button></td></tr>";
    document.getElementById("selectedItemsBody").innerHTML += newRow;
}

function deleteRow(button) {
    var row = button.parentNode.parentNode;
    row.parentNode.removeChild(row);
}
</script>
<c:import url="../componentes/rodape.jsp" />