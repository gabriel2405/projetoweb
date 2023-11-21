<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Exibe os dados da Solicitação</title>
<c:import url="../componentes/cabecalho.jsp" />

<main class="center-v">
	<div class="container">
		<div class="card border-light mb-3">
			<div class="card-header">Exibe os dados da Solicitação</div>
			<!-- Table -->
			<div class="card-body">
				<div class="table-responsive">
					<table
						class="table table-striped  table-bordered dt-responsive nowrap">
						<tr>
							<th colspan="5" style="text-align: center; font-size: 20px;">Solicitação</th>
						</tr>

						<tr>
							<th width="30%" colspan="2">ID</th>
							<td colspan="3">${itemSolicitacao[0].solicitacao.id}</td>
						</tr>
						<tr>
							<th colspan="2">Servidor</th>
							<td colspan="3" style="font-weight: bold; color: blue;">${itemSolicitacao[0].solicitacao.servidor.nome}</td>
						</tr>
						<tr>
							<th colspan="2">Data</th>
							<td colspan="3" style="font-weight: bold; color: blue;">${itemSolicitacao[0].solicitacao.data}</td>
						</tr>
						<tr>
							<th colspan="2">Status</th>
							<td colspan="3" style="font-weight: bold; color: blue;">${itemSolicitacao[0].solicitacao.status}</td>
						</tr>


					</table>

					<table
						class="table table-striped datatable table-bordered dt-responsive nowrap"
						id="datatable">
						<h4 style="text-align: center">Itens da Solicitação</h4>
						<thead>
							<tr>
								<th scope="col">ID</th>
								<th scope="col">Nome</th>
								<th scope="col">Quantidade</th>
								<th scope="col">Tipo</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="itemSolicitacao" items="${itemSolicitacao}">
								<tr>
									<td scope="row">${itemSolicitacao.item.id}</td>
									<td>${itemSolicitacao.item.nome}</td>
									<td>${itemSolicitacao.qtd}</td>
									<td>${itemSolicitacao.item.tipo}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<button class="btn btn-success" onclick="window.history.back()">
					<i class="bi bi-chevron-left"></i> Voltar
				</button>
			</div>
		</div>
	</div>
</main>
<c:import url="../componentes/rodape.jsp" />