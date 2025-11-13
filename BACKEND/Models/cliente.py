from dataclasses import dataclass
from typing import Optional


@dataclass
class Cliente:
    id: Optional[int]
    usuario_id: int
    endereco_padrao: Optional[str] = None
