import sqlite3

# Você pode trocar por PostgreSQL depois se quiser
def obter_conexao():
    return sqlite3.connect("foodly.db")
