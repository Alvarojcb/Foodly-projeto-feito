from dataclasses import dataclass
from typing import Optional


@dataclass
class Restaurante:
    id: Optional[int]
    usuario_id: int
    nome_fantasia: str
    cnpj: str
    endereco: str
    dados_bancarios: Optional[str] = None
    ativo: bool = True
