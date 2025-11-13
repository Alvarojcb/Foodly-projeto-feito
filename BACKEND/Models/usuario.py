from dataclasses import dataclass
from typing import Optional
from datetime import datetime


@dataclass
class Usuario:
    id: Optional[int]
    nome: str
    email: str
    senha_hash: str
    telefone: Optional[str]
    tipo_usuario: str          # 'cliente', 'restaurante', 'entregador', 'suporte'
    criado_em: Optional[datetime] = None
