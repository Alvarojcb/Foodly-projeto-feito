from dataclasses import dataclass
from typing import Optional


@dataclass
class Produto:
    id: Optional[int]
    restaurante_id: int
    nome: str
    descricao: Optional[str]
    preco: float
    ativo: bool = True
