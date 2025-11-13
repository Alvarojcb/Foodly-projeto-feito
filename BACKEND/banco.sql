-- ==============
-- PARTE 1 - UP
-- ==============

-- Usuários gerais do sistema (cliente, restaurante, entregador, suporte, etc.)
CREATE TABLE usuarios (
    id              BIGSERIAL PRIMARY KEY,
    nome            VARCHAR(150)        NOT NULL,
    email           VARCHAR(150)        NOT NULL UNIQUE,
    senha_hash      VARCHAR(255)        NOT NULL,
    telefone        VARCHAR(20),
    tipo_usuario    VARCHAR(20)         NOT NULL, -- 'cliente', 'restaurante', 'entregador', 'suporte'
    criado_em       TIMESTAMP           NOT NULL DEFAULT NOW()
);

-- Dados específicos de cliente
CREATE TABLE clientes (
    id              BIGSERIAL PRIMARY KEY,
    usuario_id      BIGINT              NOT NULL UNIQUE,
    -- Exemplo de campos extras (opcional)
    endereco_padrao TEXT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Dados específicos de restaurante
CREATE TABLE restaurantes (
    id              BIGSERIAL PRIMARY KEY,
    usuario_id      BIGINT              NOT NULL UNIQUE,
    nome_fantasia   VARCHAR(150)        NOT NULL,
    cnpj            VARCHAR(18)         NOT NULL UNIQUE,
    endereco        TEXT                NOT NULL,
    dados_bancarios TEXT,                          -- pode ser separado depois
    ativo           BOOLEAN             NOT NULL DEFAULT TRUE,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- ==============
-- PARTE 1 - DOWN
-- (rollback)
-- ==============

DROP TABLE IF EXISTS restaurantes;
DROP TABLE IF EXISTS clientes;
DROP TABLE IF EXISTS usuarios;



-- ======================
-- PARTE 2 - UP (H3 e H4)
-- ======================

-- Produtos que os restaurantes oferecem (cardápio)
CREATE TABLE produtos (
    id              BIGSERIAL PRIMARY KEY,
    restaurante_id  BIGINT          NOT NULL,
    nome            VARCHAR(150)    NOT NULL,
    descricao       TEXT,
    preco           NUMERIC(10, 2)  NOT NULL,
    ativo           BOOLEAN         NOT NULL DEFAULT TRUE,
    FOREIGN KEY (restaurante_id) REFERENCES restaurantes(id)
);

-- Carrinho de compras do cliente
CREATE TABLE carrinhos (
    id              BIGSERIAL PRIMARY KEY,
    cliente_id      BIGINT          NOT NULL,
    status          VARCHAR(20)     NOT NULL DEFAULT 'aberto', 
    -- 'aberto', 'fechado', 'expirado'
    criado_em       TIMESTAMP       NOT NULL DEFAULT NOW(),
    atualizado_em   TIMESTAMP       NOT NULL DEFAULT NOW(),
    FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);

-- Itens do carrinho
CREATE TABLE carrinho_itens (
    id              BIGSERIAL PRIMARY KEY,
    carrinho_id     BIGINT          NOT NULL,
    produto_id      BIGINT          NOT NULL,
    quantidade      INT             NOT NULL DEFAULT 1,
    preco_unitario  NUMERIC(10, 2)  NOT NULL,
    FOREIGN KEY (carrinho_id) REFERENCES carrinhos(id),
    FOREIGN KEY (produto_id)  REFERENCES produtos(id),
    -- Evita ter o mesmo produto repetido no mesmo carrinho
    UNIQUE (carrinho_id, produto_id)
);

-- Pedido gerado a partir do carrinho
CREATE TABLE pedidos (
    id                  BIGSERIAL PRIMARY KEY,
    cliente_id          BIGINT          NOT NULL,
    restaurante_id      BIGINT          NOT NULL,
    carrinho_id         BIGINT,
    valor_total         NUMERIC(10, 2)  NOT NULL,
    status              VARCHAR(20)     NOT NULL,
    -- 'novo', 'preparando', 'pronto', 'em_entrega', 'entregue', 'cancelado'
    endereco_entrega    TEXT            NOT NULL,
    criado_em           TIMESTAMP       NOT NULL DEFAULT NOW(),
    atualizado_em       TIMESTAMP       NOT NULL DEFAULT NOW(),
    FOREIGN KEY (cliente_id)     REFERENCES clientes(id),
    FOREIGN KEY (restaurante_id) REFERENCES restaurantes(id),
    FOREIGN KEY (carrinho_id)    REFERENCES carrinhos(id)
);

-- Itens do pedido (snapshot do carrinho no momento da compra)
CREATE TABLE pedido_itens (
    id              BIGSERIAL PRIMARY KEY,
    pedido_id       BIGINT          NOT NULL,
    produto_id      BIGINT          NOT NULL,
    quantidade      INT             NOT NULL,
    preco_unitario  NUMERIC(10, 2)  NOT NULL,
    FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
    FOREIGN KEY (produto_id) REFERENCES produtos(id)
);


-- =========================
-- PARTE 2 - DOWN (rollback)
-- =========================

DROP TABLE IF EXISTS pedido_itens;
DROP TABLE IF EXISTS pedidos;
DROP TABLE IF EXISTS carrinho_itens;
DROP TABLE IF EXISTS carrinhos;
DROP TABLE IF EXISTS produtos;


-- ===========================
-- PARTE 3 - UP (H5 e H6)
-- ===========================

-- Dados específicos do entregador
CREATE TABLE entregadores (
    id              BIGSERIAL PRIMARY KEY,
    usuario_id      BIGINT          NOT NULL UNIQUE,
    veiculo_tipo    VARCHAR(50),            -- moto, bike, carro, etc. (opcional)
    documento       VARCHAR(30),            -- CNH, CPF, etc. (opcional)
    ativo           BOOLEAN         NOT NULL DEFAULT TRUE,
    criado_em       TIMESTAMP       NOT NULL DEFAULT NOW(),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Entrega associada a um pedido
CREATE TABLE entregas (
    id                  BIGSERIAL PRIMARY KEY,
    pedido_id           BIGINT          NOT NULL UNIQUE,
    entregador_id       BIGINT,
    status              VARCHAR(20)     NOT NULL,
    -- Ex: 'disponivel', 'atribuida', 'em_rota', 'entregue', 'cancelada'

    rota_sugerida       TEXT,                   -- pode guardar JSON, polyline, etc.
    tempo_estimado_min  INT,                    -- tempo estimado em minutos
    distancia_km        NUMERIC(6, 2),          -- distância aproximada

    criado_em           TIMESTAMP       NOT NULL DEFAULT NOW(),
    atualizado_em       TIMESTAMP       NOT NULL DEFAULT NOW(),

    FOREIGN KEY (pedido_id)     REFERENCES pedidos(id),
    FOREIGN KEY (entregador_id) REFERENCES entregadores(id)
);

-- Registro de aceitação/recusa de ofertas de entrega
CREATE TABLE entrega_respostas (
    id              BIGSERIAL PRIMARY KEY,
    entrega_id      BIGINT          NOT NULL,
    entregador_id   BIGINT          NOT NULL,
    resposta        VARCHAR(10)     NOT NULL,
    -- 'aceito' ou 'recusado'

    criado_em       TIMESTAMP       NOT NULL DEFAULT NOW(),

    FOREIGN KEY (entrega_id)    REFERENCES entregas(id),
    FOREIGN KEY (entregador_id) REFERENCES entregadores(id)
);


-- ===========================
-- PARTE 3 - DOWN (rollback)
-- ===========================

DROP TABLE IF EXISTS entrega_respostas;
DROP TABLE IF EXISTS entregas;
DROP TABLE IF EXISTS entregadores;


-- ===========================
-- PARTE 4 - UP (H7 - Avaliação)
-- ===========================

-- Avaliação de restaurantes
CREATE TABLE avaliacoes_restaurantes (
    id               BIGSERIAL PRIMARY KEY,
    cliente_id       BIGINT          NOT NULL,
    restaurante_id   BIGINT          NOT NULL,
    pedido_id        BIGINT          NOT NULL,
    nota             INT             NOT NULL,        -- 1 a 5, por exemplo
    comentario       TEXT,
    criado_em        TIMESTAMP       NOT NULL DEFAULT NOW(),

    FOREIGN KEY (cliente_id)     REFERENCES clientes(id),
    FOREIGN KEY (restaurante_id) REFERENCES restaurantes(id),
    FOREIGN KEY (pedido_id)      REFERENCES pedidos(id)
);

-- Evita o mesmo cliente avaliar o mesmo restaurante
-- mais de uma vez para o mesmo pedido
CREATE UNIQUE INDEX ux_avaliacao_restaurante_unica
ON avaliacoes_restaurantes (cliente_id, restaurante_id, pedido_id);

-- Avaliação de entregadores
CREATE TABLE avaliacoes_entregadores (
    id               BIGSERIAL PRIMARY KEY,
    cliente_id       BIGINT          NOT NULL,
    entregador_id    BIGINT          NOT NULL,
    pedido_id        BIGINT          NOT NULL,
    nota             INT             NOT NULL,
    comentario       TEXT,
    criado_em        TIMESTAMP       NOT NULL DEFAULT NOW(),

    FOREIGN KEY (cliente_id)      REFERENCES clientes(id),
    FOREIGN KEY (entregador_id)   REFERENCES entregadores(id),
    FOREIGN KEY (pedido_id)       REFERENCES pedidos(id)
);

CREATE UNIQUE INDEX ux_avaliacao_entregador_unica
ON avaliacoes_entregadores (cliente_id, entregador_id, pedido_id);


-- ===========================
-- PARTE 4 - UP (H8 - Promoções)
-- ===========================

-- Promoções cadastradas na plataforma
CREATE TABLE promocoes (
    id                  BIGSERIAL PRIMARY KEY,
    restaurante_id      BIGINT,                   -- se NULL, pode ser promoção geral
    titulo              VARCHAR(150)  NOT NULL,
    descricao           TEXT,
    tipo_desconto       VARCHAR(20)   NOT NULL,
    -- Ex: 'percentual', 'valor', 'frete_gratis'

    valor_desconto      NUMERIC(10, 2),           -- percentual ou valor, dependendo do tipo
    data_inicio         TIMESTAMP      NOT NULL,
    data_fim            TIMESTAMP      NOT NULL,
    ativo               BOOLEAN        NOT NULL DEFAULT TRUE,

    criado_em           TIMESTAMP      NOT NULL DEFAULT NOW(),

    FOREIGN KEY (restaurante_id) REFERENCES restaurantes(id)
);

-- Promoções direcionadas ou resgatadas pelos clientes
CREATE TABLE promocoes_clientes (
    id                  BIGSERIAL PRIMARY KEY,
    promocao_id         BIGINT          NOT NULL,
    cliente_id          BIGINT          NOT NULL,
    resgatada           BOOLEAN         NOT NULL DEFAULT FALSE,
    resgatada_em        TIMESTAMP,

    FOREIGN KEY (promocao_id) REFERENCES promocoes(id),
    FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);

-- Evita duplicar “oferta” repetida para o mesmo cliente
CREATE UNIQUE INDEX ux_promocao_cliente_unica
ON promocoes_clientes (promocao_id, cliente_id);



-- ===========================
-- PARTE 4 - DOWN (rollback)
-- ===========================

DROP TABLE IF EXISTS promocoes_clientes;
DROP TABLE IF EXISTS promocoes;

DROP INDEX IF EXISTS ux_avaliacao_entregador_unica;
DROP INDEX IF EXISTS ux_avaliacao_restaurante_unica;

DROP TABLE IF EXISTS avaliacoes_entregadores;
DROP TABLE IF EXISTS avaliacoes_restaurantes;
