<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Consulta Estoque</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

    <div class="container border-bottom">
        <header class="d-flex justify-content-between py-3">
            <h1>Metal Flow</h1>
            <ul class="nav nav-pills">
                <li class="nav-item"><a href="/metal-flow/ordens" class="btn link-dark">Dashboard</a></li>
                <li class="nav-item"><a href="/metal-flow/ordem-formulario" class="btn link-dark">Cadastro de OP</a></li>
                <!--<li class="nav-item"><a href="#" class="btn link-dark">Detalhes OP</a></li>-->
                <li class="nav-item"><a href="/metal-flow/estoque" class="btn link-light  bg-dark " aria-current="page">Estoque</a></li>
            </ul>
        </header>
    </div>

    <div class="container mt-1">
        <h2 class="mb-4">Estoque</h2>

        <form method="get" action="/metal-flow/estoque" class="row g-2 mb-4">
            <div class="col-auto">
                <input type="text" name="numero_OP" class="form-control"
                       placeholder="Número da OP" value="${numeroOpFiltro}">
            </div>
            <div class="col-auto">
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownForm" data-bs-toggle="dropdown" aria-expanded="false">
                        <span id="dropdownLabel">${empty codigoSetorFiltro ? 'Selecione o Setor' : codigoSetorFiltro}</span>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownForm">
                        <li><a class="dropdown-item" href="#" onclick="selecionarSetor('')">Todos</a></li>
                        <c:forEach var="setor" items="${setores}">
                            <li><a class="dropdown-item" href="#" onclick="selecionarSetor('${setor.codigo}')">${setor.codigo}</a></li>
                        </c:forEach>
                    </ul>
                    <input type="hidden" name="codigo_setor" id="codigoSetorInput" value="${codigoSetorFiltro}">
                </div>
            </div>
            <div class="col-auto">
                <button type="submit" class="btn btn-dark">Filtrar</button>
            </div>
            <div class="col-auto">
                <a href="/metal-flow/estoque" class="btn btn-outline-secondary">Limpar</a>
            </div>
        </form>

        <c:if test="${not empty erro}">
            <div class="alert alert-danger">${erro}</div>
        </c:if>

        <table class="table table-striped table-hover table-bordered align-middle">
            <thead class="table-dark">
                <tr>
                    <th>Numero da OP</th>
                    <th>Código do produto</th>
                    <th>Setor</th>
                    <th>quantidade</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="estoque" items="${estoques}">
                <tr>
                    <td>${estoque.numero_OP}</td>
                    <td>${estoque.codigo_produto}</td>
                    <td>${estoque.setor}</td>
                    <td>${estoque.quantidade}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function selecionarSetor(codigo) {
            document.getElementById('codigoSetorInput').value = codigo;
            document.getElementById('dropdownLabel').innerText = codigo === '' ? 'Selecione o Setor' : codigo;
        }
    </script>
</body>
</html>