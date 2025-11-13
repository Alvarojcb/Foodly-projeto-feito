from dataclasses import dataclass
from typing import Optional
from datetime import datetime


@dataclass
class AvaliacaoRestaurante:
    id: Optional[int]
    cliente_id: int
    restaurante_id: int
    pedido_id: int
    nota: int                      # 1 a 5, por exemplo
    comentario: Optional[str] = None
    criado_em: Optional[datetime] = None
