<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Editar Item</title>

<c:import url="../componentes/cabecalho.jsp" />

<div class="p-5 mb-4 bg-light rounded-3">
	<div class="container py-5">
		<h1 class="display-5 fw-bold">Editar Item</h1>
		<p class="col-md-12 fs-4">Preencha o formulário abaixo para
			realizar a alteração do item no sistema.</p>
	</div>
</div>

<main>
	<div class="container">
		<form action="altera" method="POST" class="row g-3">

			<security:csrfInput />

			<!-- ID -->
			<input type="hidden" name="id" value="${item.id}" required>

			<!-- NOME -->
			<div class="form-group">
				<label for="nome" class="col-form-label obrigatorio">Nome</label> <input
					type="text" class="form-control" name="nome" autofocus
					MAXLENGTH="255" value="${item.nome}">
			</div>

			<div class="form-group">
				<label for="qtd" class="col-form-label obrigatorio">Quantidade</label>
				<input type="number" class="form-control" name="qtd" autofocus
					value="${item.qtd}">
			</div>

			
			
			<div class="form-group">
				<label for="tipo" class="col-form-label obrigatorio">Tipo</label> <select
					name="tipo" class="form-select" aria-label="Default select example">
					<option
						${"Permanente".equals(item.tipo) ? "selected" : ""}>Permanente</option>
					<option
						${"Consumo".equals(item.tipo) ? "selected" : ""}>Consumo</option>
				</select>
			</div>


			<div class="text-center">
				<button type="submit" class="btn btn-primary btn-lg">
					<i class="bi bi-arrow-clockwise"></i> Atualizar
				</button>
				<a href="<c:url value="/item/lista" />"
					class="btn btn-secondary btn-lg"> <i class="bi bi-x"></i>
					Cancelar
				</a>
			</div>

		</form>
	</div>
</main>

<c:import url="../componentes/rodape.jsp" />