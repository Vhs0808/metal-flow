<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Document</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

    <div class="container border-bottom">
        <header class="d-flex justify-content-between py-3">
            <h1>Metal Flow</h1>
            <ul class="nav nav-pills">
                <li class="nav-item"><a href="/metal-flow/ordens" class="btn btn-light link-dark">Voltar para ordens</a></li>
            </ul>
        </header>
    </div>

    <div class="container mt-3">
        <h1>Detalhes da OP e Apontamento</h1>
        <div class="row mt-2">

            <div class="col-md-6">
                <div class="rounded bg-light p-3 mb-3">
                    <h4>OP-${ordem.numeroOp} | ${ordem.descricaoProduto}</h4>
                    <p>
                        <b>Quantidade planejada:</b> ${ordem.quantidadePlanejada} |
                        <b>Saldo:</b> ${ordem.saldoOp}
                    </p>
                    <p>
                        <b>Produto:</b> ${ordem.codigoProduto} |
                        <b>Status:</b> ${ordem.statusOp}
                    </p>
                </div>
                <div class="rounded bg-light p-3">
                    <h4>Histórico de Apontamentos</h4>
                    <c:forEach var="apontamento" items="${apontamentos}">
                        <p>
                            ${apontamento.quantidade_apontada}
                            peças em ${apontamento.setor} -
                            ${apontamento.data_apontamento}
                        </p>
                    </c:forEach>
                </div>
            </div>

        <div class="col-md-6">
            <div class="rounded bg-light p-3 h-100 justify-content-between align-items-center">
                <h4>Registrar Apontamento</h4>
                <form method="POST" action="" class="p-4 border rounded bg-light w-100">
                    <div class="row">
                        <label for="setor"><h6>Setor produtivo</h6></label>
                        <input type="text" name="setor" class="form-control"
                                   id="setor" placeholder="Ex: CORTE/PINTURA/USINAGEM" required>
                        </div>
                        <div class="row mt-4">
                            <label for="quantidade"><h6>Quantidade produzida</h6></label>
                            <input type="number" name="quantidade" class="form-control"
                                id="quantidade" placeholder="Ex: 100" required>
                        </div>
                        <input type="hidden" name="ordem_id" value="${ordem.id}">

                    <div class="row mt-3">
                        <button type="submit" class="btn btn-dark">Registrar Apontamento</button>
                    </div>
                </form>
            </div>
        </div>
    </div>


    </body>
</html>