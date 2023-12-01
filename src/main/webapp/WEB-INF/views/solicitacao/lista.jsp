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
							<c:if test="${not empty solicitacoes[0].responsavel}">
								<th scope="col">Responsavel</th>
							</c:if>
							<th scope="col">Data-Hora</th>
							<th scope="col">Status</th>
							<th scope="col">Ações</th>
						</tr>
					</thead>
					<tbody>
						<!-- percorre cursos montando as linhas da tabela -->
						<c:forEach var="solicitacao" items="${solicitacoes}">
							<tr>
								<td scope="row">${solicitacao.id}</td>
								<td><a href="" data-toggle="tooltip"
									data-bs-placement="bottom" title="Exibir"
									data-bs-toggle="modal"
									data-bs-target="#modal${solicitacao.servidor.id}">${solicitacao.servidor.nome}</a>



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
									</div></td>
								<c:if test="${not empty solicitacoes[0].responsavel}">
									<td>${solicitacao.responsavel.nome}</td>
								</c:if>
								<td>${solicitacao.data}</td>
								<td>${solicitacao.status}</td>
								<td><c:choose>
										<c:when test="${status eq 'pendente' && !responsavel}">
											<a
												href="<c:url value="/solicitacao/lista_item?id=${solicitacao.id}" />"
												class="btn btn-warning btn-sm" data-toggle="tooltip"
												data-bs-placement="bottom" title="Editar"> <i
												class="bi bi-pencil-square"></i>
											</a>

											<a
												href="<c:url value="/solicitacao/exibe?id=${solicitacao.id}"/>"
												class="btn btn-info btn-sm" data-toggle="tooltip"
												data-bs-placement="bottom" title="Exibir"> <i
												class="bi bi-eye"></i>
											</a>
										</c:when>
										<c:when test="${responsavel}">
											<a href="#" title="Confirmar" class="btn btn-success btn-sm"
												role="button"> <i class="bi bi-check-lg"></i>
											</a>

											<!-- Botão de Cancelamento com Ícone -->
											<a href="#" title="Cancelar" class="btn btn-danger btn-sm"
												role="button"><i class="bi bi-x-lg"></i></a>

										</c:when>
										<c:otherwise>
											<a
												href="<c:url value="/solicitacao/edita?id=${solicitacao.id}" />"
												class="btn btn-warning btn-sm" data-toggle="tooltip"
												data-bs-placement="bottom" title="Editar"> <i
												class="bi bi-pencil-square"></i>
											</a>
											<button type="button" class="btn btn-danger btn-sm"
												data-toggle="tooltip" data-bs-placement="bottom"
												title="Excluir" data-bs-toggle="modal"
												data-bs-target="#modal${solicitacao.id}">
												<i class="bi bi-trash"></i>
											</button>
											<div class="modal fade" id="modal${solicitacao.id}"
												tabindex="-1">
												<div class="modal-dialog" role="document">
													<div class="modal-content">
														<div class="modal-header">
															<h5 class="modal-title">Exclusão da Solicitação</h5>
															<button type="button" class="btn-close"
																data-bs-dismiss="modal" aria-label="Close">
																<span aria-hidden="true"></span>
															</button>
														</div>
														<div class="modal-body">
															<p>
																Deseja realmente excluir a Solicitação? <br>ID da
																Solicitação: ${solicitacao.id}

															</p>
														</div>
														<div class="modal-footer">
															<a
																href="<c:url value="/solicitacao/remove?id=${solicitacao.id}" />"
																class="btn btn-danger"> <i class="bi bi-trash"></i>
																Excluir
															</a>
															<button type="button" class="btn btn-secondary"
																data-bs-dismiss="modal">
																<i class="bi bi-x"></i> Fechar
															</button>
														</div>
													</div>
												</div>
											</div>
											<a
												href="<c:url value="/solicitacao/lista_item?id=${solicitacao.id}" />"
												class="btn btn-success"> Continuar </a>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<c:if test="${!responsavel}">
			<div align="center">
				<a href="<c:url value="/solicitacao/novo" />"
					class="btn btn-primary btn-lg"> <i class="bi bi-plus-circle"></i>
					Cadastrar
				</a>
			</div>

		</c:if>

	</div>
</main>
<c:import url="../componentes/rodape.jsp" />