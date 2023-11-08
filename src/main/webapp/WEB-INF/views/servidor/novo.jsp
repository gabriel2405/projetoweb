<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Cadastrar Servidor/Responsavel</title>

<c:import url="../componentes/cabecalho.jsp" />

<div class="p-5 mb-4 bg-light rounded-3">
	<div class="container py-5">
		<h1 class="display-5 fw-bold">Cadastrar Servidor/Responsavel</h1>
		<p class="col-md-12 fs-4">Preencha o formulário abaixo para
			realizar o cadastro de Servidor/Responsavel no sistema.</p>
	</div>
</div>

<main>
	<div class="container">
		<form action="adiciona" method="POST" class="row g-3">

			<security:csrfInput />

			<!-- NOME -->
			<div class="form-group">
				<label for="nome" class="col-form-label obrigatorio">Nome</label> <input
					type="text" class="form-control" name="nome" autofocus
					MAXLENGTH="255" required>
			</div>
			
			<div class="form-group">
				<label for="email" class="col-form-label obrigatorio">Email</label> <input
					type="text" class="form-control" name="email" 
					MAXLENGTH="255" required>
			</div>
			

			<div class="form-group">
				<label for="tell" class="col-form-label">Telefone</label>
				<input type="number" class="form-control" name="tell" 
					>
			</div>

			<div class="form-group">
			<label for="tipo" class="col-form-label obrigatorio">Tipo</label>
				<select name="tipo" class="form-select" aria-label="Default select example">
					<option selected>Servidor</option>
					<option  value="Responsavel">Responsavel</option>
					
				</select>
			</div>


			<div class="text-center">
				<button type="submit" class="btn btn-primary btn-lg">
					<i class="bi bi-plus-circle"></i> Cadastrar
				</button>
				<button type="reset" class="btn btn-secondary btn-lg">
					<i class="bi bi-trash"></i> Limpar
				</button>
			</div>

		</form>
	</div>
</main>

<c:import url="../componentes/rodape.jsp" />