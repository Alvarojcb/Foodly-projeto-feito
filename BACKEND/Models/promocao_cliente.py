from dataclasses import dataclass
from typing import Optional
from datetime import datetime


@dataclass
class PromocaoCliente:
    id: Optional[int]
    promocao_id: int
    cliente_id: int
    resgatada: bool = False
    resgatada_em: Optional[datetime] = None
