from Models.cliente import Cliente
from DAO.conexao import obter_conexao


class ClienteDAO:

    @staticmethod
    def criar(cliente: Cliente) -> int:
        conn = obter_conexao()
        cursor = conn.cursor()

        sql = """
        INSERT INTO clientes (usuario_id, endereco_padrao)
        VALUES (?, ?)
        """

        cursor.execute(sql, (
            cliente.usuario_id,
            cliente.endereco_padrao
        ))

        conn.commit()
        cliente_id = cursor.lastrowid
        conn.close()
        return cliente_id

    @staticmethod
    def buscar_por_id(cliente_id: int) -> Cliente | None:
        conn = obter_conexao()
        cursor = conn.cursor()

        cursor.execute("SELECT id, usuario_id, endereco_padrao FROM clientes WHERE id = ?", (cliente_id,))
        row = cursor.fetchone()
        conn.close()

        if row:
            return Cliente(*row)
        return None

    @staticmethod
    def listar_todos() -> list[Cliente]:
        conn = obter_conexao()
        cursor = conn.cursor()

        cursor.execute("SELECT id, usuario_id, endereco_padrao FROM clientes")
        rows = cursor.fetchall()
        conn.close()

        return [Cliente(*r) for r in rows]

    @staticmethod
    def atualizar(cliente: Cliente):
        conn = obter_conexao()
        cursor = conn.cursor()

        sql = """
        UPDATE clientes
        SET usuario_id = ?, endereco_padrao = ?
        WHERE id = ?
        """

        cursor.execute(sql, (
            cliente.usuario_id,
            cliente.endereco_padrao,
            cliente.id
        ))

        conn.commit()
        conn.close()

    @staticmethod
    def deletar(cliente_id: int):
        conn = obter_conexao()
        cursor = conn.cursor()

        cursor.execute("DELETE FROM clientes WHERE id = ?", (cliente_id,))
        conn.commit()
        conn.close()
