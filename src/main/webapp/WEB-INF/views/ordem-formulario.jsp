<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <title>Document</title>
    </head>

    <body>

    <div class="container border-bottom">
        <header class="d-flex justify-content-between py-3">
            <h1>Metal Flow</h1>
            <ul class="nav nav-pills">
                <li class="nav-item"><a href="/metal-flow/ordens" class="btn link-dark">Dashboard</a></li>
                <li class="nav-item"><a href="/metal-flow/ordem-formulario" class="btn link-light bg-dark">Cadastro de OP</a></li>
                <!--<li class="nav-item"><a href="#" class="btn link-dark">Detalhes OP</a></li>-->
                <li class="nav-item"><a href="/metal-flow/estoque" class="btn link-dark " aria-current="page">Estoque</a></li>
            </ul>
        </header>
    </div>

    <div class="container mt-2">
        <h2>Cadastrar Ordem de Produção</h2>

            <form method="POST" action="/metal-flow/ordem-formulario"class="p-4 border rounded bg-light">
                <div class="row">
                    <div class="col-md-6">
                        <label for="numero_OP">Numero da OP</label>
                        <input type="text" name="numero_OP" class="form-control" id="numero_OP"
                               placeholder="Ex: 4501" required>
                    </div>
                    <div class="col-md-6">
                        <label for="codigo_produto">Codigo do Produto</label>
                        <input type="text" name="codigo_produto" class="form-control" id="codigo_produto"
                               placeholder="Ex: SUP-MET-001" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <label for="descricao">Descrição</label>
                        <input type="text" name="descricao" class="form-control" id="descricao"
                            placeholder="Ex: Suporte metálico zincado" required>
                    </div>
                    <div class="col-md-6">
                        <label for="quantidade">Quantidade</label>
                        <input type="number" name="quantidade" class="form-control" id="quantidade"
                            placeholder="Ex: 100" required>
                    </div>
                </div>
                <c:if test="${not empty erro}">
                    <div class="alert alert-danger mt-1">${erro}</div>
                </c:if>
                <div class="mt-4">
                    <button type="submit" class="btn btn-dark ps-5 pe-5">Salvar OP</button>
                    <a href="/metal-flow/ordens" class="btn btn-light border ps-5 pe-5">Cancelar</a>
                </div>
            </form>
    </div>



    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>