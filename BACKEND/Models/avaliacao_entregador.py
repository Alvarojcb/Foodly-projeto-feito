from dataclasses import dataclass
from typing import Optional
from datetime import datetime


@dataclass
class AvaliacaoEntregador:
    id: Optional[int]
    cliente_id: int
    entregador_id: int
    pedido_id: int
    nota: int
    comentario: Optional[str] = None
    criado_em: Optional[datetime] = None
