from dataclasses import dataclass
from typing import Optional
from datetime import datetime


@dataclass
class Entrega:
    id: Optional[int]
    pedido_id: int
    entregador_id: Optional[int]
    status: str                           # 'disponivel', 'atribuida', 'em_rota', 'entregue', 'cancelada'
    rota_sugerida: Optional[str] = None   # pode guardar JSON, texto, polyline etc.
    tempo_estimado_min: Optional[int] = None
    distancia_km: Optional[float] = None
    criado_em: Optional[datetime] = None
    atualizado_em: Optional[datetime] = None
