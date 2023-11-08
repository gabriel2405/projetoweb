<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Servidor/Responsavel</title>

<c:import url="../componentes/cabecalho.jsp" />

<div class="p-5 mb-4 bg-light rounded-3">
	<div class="container py-5">
		<h1 class="display-5 fw-bold">Editar Servidor/Responsavel</h1>
		<p class="col-md-12 fs-4">Preencha o formulário abaixo para
			realizar a alteração do Servidor/Responsavel no sistema.</p>
	</div>
</div>

<main>
	<div class="container">
		<form action="altera" method="POST" class="row g-3">

			<security:csrfInput />

			<input type="hidden" name="id" value="${servidor.id}" required>

			<div class="form-group">
				<label for="nome" class="col-form-label obrigatorio">Nome</label> <input
					type="text" class="form-control" name="nome" autofocus
					MAXLENGTH="255" value="${servidor.nome}" required>
			</div>

			<div class="form-group">
				<label for="email" class="col-form-label obrigatorio">Email</label>
				<input type="text" class="form-control" name="email" MAXLENGTH="255"
					value="${servidor.email}" required>
			</div>


			<div class="form-group">
				<label for="tell" class="col-form-label">Telefone</label> <input
					type="number" class="form-control" name="tell"
					value="${servidor.tell}">
			</div>

			<div class="form-group">
				<label for="tipo" class="col-form-label obrigatorio">Tipo</label> <select
					name="tipo" class="form-select" aria-label="Default select example">
					<option
						${"Servidor".equals(servidor.tipo) ? "selected" : ""}>Servidor</option>
					<option
						${"Responsavel".equals(servidor.tipo) ? "selected" : ""}>Responsavel</option>
				</select>
			</div>



			<div class="text-center">
				<button type="submit" class="btn btn-primary btn-lg">
					<i class="bi bi-arrow-clockwise"></i> Atualizar
				</button>
				<a href="<c:url value="/servidor/lista" />"
					class="btn btn-secondary btn-lg"> <i class="bi bi-x"></i>
					Cancelar
				</a>
			</div>

		</form>
	</div>
</main>

<c:import url="../componentes/rodape.jsp" />