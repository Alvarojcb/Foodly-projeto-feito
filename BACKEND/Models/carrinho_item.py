from dataclasses import dataclass
from typing import Optional


@dataclass
class CarrinhoItem:
    id: Optional[int]
    carrinho_id: int
    produto_id: int
    quantidade: int
    preco_unitario: float
