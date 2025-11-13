from dataclasses import dataclass
from typing import Optional
from datetime import datetime


@dataclass
class Promocao:
    id: Optional[int]
    restaurante_id: Optional[int]      # None = promoção geral
    titulo: str
    descricao: Optional[str]
    tipo_desconto: str                 # 'percentual', 'valor', 'frete_gratis'
    valor_desconto: Optional[float]    # depende do tipo_desconto
    data_inicio: datetime
    data_fim: datetime
    ativo: bool = True
    criado_em: Optional[datetime] = None
