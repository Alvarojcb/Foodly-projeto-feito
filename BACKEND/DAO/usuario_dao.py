from Models.usuario import Usuario
from DAO.conexao import obter_conexao


class UsuarioDAO:

    @staticmethod
    def criar(usuario: Usuario) -> int:
        conn = obter_conexao()
        cursor = conn.cursor()

        sql = """
        INSERT INTO usuarios (nome, email, senha_hash, telefone, tipo_usuario)
        VALUES (?, ?, ?, ?, ?)
        """

        cursor.execute(sql, (
            usuario.nome,
            usuario.email,
            usuario.senha_hash,
            usuario.telefone,
            usuario.tipo_usuario
        ))

        conn.commit()
        user_id = cursor.lastrowid
        conn.close()
        return user_id

    @staticmethod
    def buscar_por_id(usuario_id: int) -> Usuario | None:
        conn = obter_conexao()
        cursor = conn.cursor()

        cursor.execute("SELECT id, nome, email, senha_hash, telefone, tipo_usuario, criado_em FROM usuarios WHERE id = ?", (usuario_id,))
        row = cursor.fetchone()
        conn.close()

        if row:
            return Usuario(*row)
        return None

    @staticmethod
    def listar_todos() -> list[Usuario]:
        conn = obter_conexao()
        cursor = conn.cursor()

        cursor.execute("SELECT id, nome, email, senha_hash, telefone, tipo_usuario, criado_em FROM usuarios")
        rows = cursor.fetchall()
        conn.close()

        return [Usuario(*r) for r in rows]

    @staticmethod
    def atualizar(usuario: Usuario):
        conn = obter_conexao()
        cursor = conn.cursor()

        sql = """
        UPDATE usuarios
        SET nome = ?, email = ?, senha_hash = ?, telefone = ?, tipo_usuario = ?
        WHERE id = ?
        """

        cursor.execute(sql, (
            usuario.nome,
            usuario.email,
            usuario.senha_hash,
            usuario.telefone,
            usuario.tipo_usuario,
            usuario.id
        ))

        conn.commit()
        conn.close()

    @staticmethod
    def deletar(usuario_id: int):
        conn = obter_conexao()
        cursor = conn.cursor()

        cursor.execute("DELETE FROM usuarios WHERE id = ?", (usuario_id,))
        conn.commit()
        conn.close()
