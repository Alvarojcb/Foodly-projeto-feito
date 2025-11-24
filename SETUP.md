# 🚀 Setup do Projeto Foodly

Guia completo para configurar e rodar o projeto Foodly na sua máquina.

## 📋 Pré-requisitos

### 1. **Java Development Kit (JDK)**
- **Versão mínima:** JDK 17+
- **Download:** [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) ou [OpenJDK](https://openjdk.org/)
- **Verificação:**
  ```powershell
  java -version
  ```

### 2. **Maven**
- **Versão mínima:** Maven 3.8+
- **Download:** [Apache Maven](https://maven.apache.org/download.cgi)
- **Instalação:** Extrair e adicionar ao PATH do sistema
- **Verificação:**
  ```powershell
  mvn -v
  ```

### 3. **MySQL Server**
- **Versão mínima:** MySQL 8.0+
- **Download:** [MySQL Community Server](https://dev.mysql.com/downloads/mysql/)
- **Instalação:** Siga as instruções do instalador
- **Verificação:**
  ```powershell
  mysql --version
  ```

### 4. **Git** (Opcional)
- **Download:** [Git for Windows](https://git-scm.com/download/win)

---

## 🔧 Configuração do Projeto

### Passo 1: Clonar o Repositório
```powershell
git clone https://github.com/Alvarojcb/Foodly-projeto-feito.git
cd Foodly-projeto-feito/FOODLY/BACKEND
```

### Passo 2: Configurar Banco de Dados
1. **Abrir MySQL:**
   ```powershell
   mysql -u root -p
   ```

2. **Criar banco de dados:**
   ```sql
   CREATE DATABASE foodly;
   USE foodly;
   ```

3. **Importar script SQL:**
   ```sql
   SOURCE banco.sql;
   ```

4. **Verificar tabelas:**
   ```sql
   SHOW TABLES;
   ```

### Passo 3: Configurar Banco de Dados (application.yml)

Editar `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/foodly
    username: root
    password: sua_senha_mysql
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
```

**Opções de `ddl-auto`:**
- `update` - Atualiza tabelas existentes (recomendado para desenvolvimento)
- `create-drop` - Recria tudo a cada execução (cuidado!)
- `validate` - Apenas valida (para produção)

---

## 🏃 Executando o Projeto

### 1. **Baixar Dependências**
```powershell
cd FOODLY/BACKEND && mvn clean install
```

### 2. **Rodar o Servidor**
```powershell
cd FOODLY/BACKEND && mvn spring-boot:run
```

**Ou usar:**
```powershell
java -jar target/foodly-backend-1.0.0.jar
```

### 3. **Verificar se está rodando**
```
✅ O servidor inicia em: http://localhost:8080
✅ Contexto da API: http://localhost:8080/api
```

---

## 📁 Estrutura do Projeto

```
FOODLY/BACKEND/
├── pom.xml                    # Dependências Maven
├── banco.sql                  # Script do banco de dados
├── src/
│   ├── main/
│   │   ├── java/com/foodly/
│   │   │   ├── Controller/    # Controladores REST
│   │   │   ├── DAO/           # Data Access Objects
│   │   │   ├── Models/        # Entidades JPA
│   │   │   ├── service/       # Lógica de negócio (AIService)
│   │   │   └── FoodlyApplication.java
│   │   └── resources/
│   │       ├── application.yml # Configuração
│   │       └── ...


---

## 🔌 Tecnologias Utilizadas

| Tecnologia | Versão | Propósito |
|-----------|--------|----------|
| **Java** | 17+ | Linguagem principal |
| **Spring Boot** | 3.3.0 | Framework web |
| **Spring AI** | 0.8.1 | Integração com IA (OpenAI) |
| **Spring Data JPA** | - | ORM para banco de dados |
| **MySQL** | 8.0+ | Banco de dados relacional |
| **Maven** | 3.8+ | Gerenciador de dependências |

---

## 📧 Suporte

Se encontrar problemas:
1. Verifique se JDK 17+ está instalado
2. Verifique se MySQL está rodando
3. Verifique as credenciais do banco em `application.yml`
4. Limpe o cache: `mvn clean`

**Bom desenvolvimento! 🚀**
