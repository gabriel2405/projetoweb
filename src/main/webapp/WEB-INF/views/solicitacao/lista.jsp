<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Cadastrar Item</title>

<c:import url="../componentes/cabecalho.jsp" />
<main class="center-v">
	<div class="container center-v">

		<div class="card border-light mb-3">
			<div class="card-header">Listagem de Solicitações</div>

			<!-- Table -->
			<div class="card-body">
				<table class="table datatable" id="datatable">
					<thead>
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Servidor</th>
							<th scope="col">Responsavel</th>
							<th scope="col">Data-Hora</th>
							<th scope="col">Status</th>
						</tr>
					</thead>
					<tbody>
						<!-- percorre cursos montando as linhas da tabela -->
						<c:forEach var="solicitacao" items="${solicitacoes}">
							<tr>
								<td scope="row">${solicitacao.id}</td>
								<td >${solicitacao.servidor.nome}
									<button type="button" class="btn btn-info btn-sm" 
										data-toggle="tooltip" data-bs-placement="bottom"
										title="Exibir" data-bs-toggle="modal"
										data-bs-target="#modal${solicitacao.servidor.id}">
										<i class="bi bi-eye"></i>
									</button>
									<div class="modal fade" id="modal${solicitacao.servidor.id}"
										tabindex="-1">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title">Dados do Servidor</h5>
													<button type="button" class="btn-close"
														data-bs-dismiss="modal" aria-label="Close">
														<span aria-hidden="true"></span>
													</button>
												</div>
												<div class="modal-body">
													<table class="table datatable" id="datatable">
														<thead>
															<tr>
																<th scope="col">ID</th>
																<th scope="col">Nome</th>
																<th scope="col">Email</th>
																<th scope="col">Telefone</th>
															</tr>
														</thead>
														<tbody>

															<tr>
																<td scope="row">${solicitacao.servidor.id}</td>
																<td>${solicitacao.servidor.nome}</td>
																<td>${solicitacao.servidor.email}</td>
																<td>${solicitacao.servidor.tell}</td>
															</tr>
														</tbody>
													</table>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary"
														data-bs-dismiss="modal">
														<i class="bi bi-x"></i> Fechar
													</button>
												</div>
											</div>
										</div>
									</div>
								</td>
								<td>${solicitacao.responsavel.nome}</td>
								<td>${solicitacao.data}</td>
								<td>${solicitacao.status}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<div align="center">
			<a href="<c:url value="/solicitacao/novo" />"
				class="btn btn-primary btn-lg"> <i class="bi bi-plus-circle"></i>
				Cadastrar
			</a>
		</div>
	</div>
</main>
<c:import url="../componentes/rodape.jsp" />