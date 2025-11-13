from dataclasses import dataclass
from typing import Optional
from datetime import datetime


@dataclass
class Entregador:
    id: Optional[int]
    usuario_id: int
    veiculo_tipo: Optional[str] = None   # moto, bike, carro etc.
    documento: Optional[str] = None      # CNH, CPF etc.
    ativo: bool = True
    criado_em: Optional[datetime] = None
