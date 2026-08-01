<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ordens</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

    <div class="container border-bottom">
        <header class="d-flex justify-content-between py-3">
            <h1>Metal Flow</h1>
            <ul class="nav nav-pills">
                <li class="nav-item"><a href="/metal-flow/ordens" class="btn link-light  bg-dark " aria-current="page">Dashboard</a></li>
                <li class="nav-item"><a href="/metal-flow/ordem-formulario" class="btn link-dark">Cadastro de OP</a></li>
                <!--<li class="nav-item"><a href="#" class="btn link-dark">Detalhes OP</a></li>-->
                <li class="nav-item"><a href="/metal-flow/estoque" class="btn link-dark">Estoque</a></li>
            </ul>
        </header>
    </div>

    <div class="container mt-1">

        <h2 class="mb-4">Informações Gerias</h2>

        <table class="table table-striped table-hover table-bordered align-middle">
            <thead class="table-dark">
                <tr>
                    <th>Abertas</th>
                    <th>Em produção</th>
                    <th>Concluídas</th>
                    <th>Saldo Total</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${abertas}</td>
                    <td>${emProducao}</td>
                    <td>${concluidas}</td>
                    <td>${saldoTotal}</td>
                </tr>
            </tbody>
        </table>

        <h2 class="mb-4">Ordens de Produção</h2>

        <c:if test="${not empty sucesso}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                ${sucesso}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>

        <c:if test="${not empty erro}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                ${erro}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>

        <table class="table table-striped table-hover table-bordered align-middle">
            <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Número</th>
                <th>Produto</th>
                <th>Saldo</th>
                <th>Status</th>
                <th>Ações</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="ordem" items="${ordens}">
                <tr>
                    <td>${ordem.id}</td>
                    <td>${ordem.numeroOp}</td>
                    <td>${ordem.codigoProduto}</td>
                    <td>${ordem.saldoOp}</td>
                    <td>
                        <c:choose>
                            <c:when test="${ordem.statusOp == 'ABERTA'}">
                                <span class="badge bg-secondary">${ordem.statusOp}</span>
                            </c:when>
                            <c:when test="${ordem.statusOp == 'EM_PRODUCAO'}">
                                <span class="badge bg-warning text-dark">${ordem.statusOp}</span>
                            </c:when>
                            <c:when test="${ordem.statusOp == 'CONCLUIDA'}">
                                <span class="badge bg-success">${ordem.statusOp}</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge bg-light text-dark">${ordem.statusOp}</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="/metal-flow/detalhe-ordem?id=${ordem.id}" class="btn btn-secondary">Ver/Apontar</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>