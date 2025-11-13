from dataclasses import dataclass
from typing import Optional
from datetime import datetime


@dataclass
class Pedido:
    id: Optional[int]
    cliente_id: int
    restaurante_id: int
    carrinho_id: Optional[int]
    valor_total: float
    status: str                      # 'novo', 'preparando', 'pronto', 'em_entrega', 'entregue', 'cancelado'
    endereco_entrega: str
    criado_em: Optional[datetime] = None
    atualizado_em: Optional[datetime] = None
