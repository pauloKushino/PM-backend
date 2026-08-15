package com.mensal.backend.dto;

import java.math.BigDecimal;

public record CreateProdutoRequest(
        String nome,
        String descricao,
        BigDecimal preco,
        Integer quantidadeEstoque,
        String categoria
) {}
