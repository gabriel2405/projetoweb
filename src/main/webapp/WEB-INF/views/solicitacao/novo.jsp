<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Cadastrar Solicitação</title>

<c:import url="../componentes/cabecalho.jsp" />

<div class="p-5 mb-4 bg-light rounded-3">
	<div class="container py-5">
		<h1 class="display-5 fw-bold">Cadastrar Solicitação</h1>
		<p class="col-md-12 fs-4">Selecione um servidor para
			realizar o cadastro de Solicitação no sistema.</p>
	</div>
</div>

<main>
	<div class="container">
		<form action="adiciona" method="POST" onsubmit="return validarRadio()" class="row g-3">

			<security:csrfInput />
			
			<input name="status" value="Pendente" hidden>

			<!-- NOME -->
			<div class="form-group">
				<label for="servidor.id" class="col-form-label obrigatorio">Servidor</label> 
					<table class="table datatable" id="datatable">
					<thead>
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Nome</th>
							<th scope="col">Email</th>
							<th scope="col">Selecionar</th>

						</tr>
					</thead>
					<tbody>
						<!-- percorre cursos montando as linhas da tabela -->
						<c:forEach var="servidor" items="${servidores}">
							<tr>
								<td scope="row">${servidor.id}</td>
								<td>${servidor.nome}</td>
								<td >${servidor.email}</td>
								<td><input type="radio"  name="servidor.id" value="${servidor.id}"></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
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

    <script>
    function validarRadio() {
        var radios = document.querySelectorAll('input[type="radio"]');
        var peloMenosUmSelecionado = Array.from(radios).some(function(radio) {
            return radio.checked;
        });

        if (!peloMenosUmSelecionado) {
            alert('Selecione pelo menos um Servidor!');
            return false;
        }

        return true;
    }
</script>
 

<c:import url="../componentes/rodape.jsp" />