from dataclasses import dataclass
from typing import Optional
from datetime import datetime


@dataclass
class Carrinho:
    id: Optional[int]
    cliente_id: int
    status: str = "aberto"           # 'aberto', 'fechado', 'expirado'
    criado_em: Optional[datetime] = None
    atualizado_em: Optional[datetime] = None
