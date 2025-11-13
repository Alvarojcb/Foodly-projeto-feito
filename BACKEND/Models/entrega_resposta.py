from dataclasses import dataclass
from typing import Optional
from datetime import datetime


@dataclass
class EntregaResposta:
    id: Optional[int]
    entrega_id: int
    entregador_id: int
    resposta: str                         # 'aceito' ou 'recusado'
    criado_em: Optional[datetime] = None
