from dataclasses import dataclass
from typing import Optional


@dataclass
class PedidoItem:
    id: Optional[int]
    pedido_id: int
    produto_id: int
    quantidade: int
    preco_unitario: float
