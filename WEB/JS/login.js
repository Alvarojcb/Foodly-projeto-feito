const API_URL = "http://localhost:8080/api";

document.addEventListener("DOMContentLoaded", () => {
  const formLogin = document.getElementById("form-login");

  if (formLogin) {
    formLogin.addEventListener("submit", handleLogin);
  }
});

async function handleLogin(event) {
  event.preventDefault();

  const email = document.getElementById("email").value.trim();
  const senha = document.getElementById("senha").value.trim();

  if (!email || !senha) {
    alert("Por favor, preencha todos os campos");
    return;
  }

  try {
    const response = await fetch(`${API_URL}/auth/login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ email, senha }),
    });

    if (!response.ok) {
      const error = await response.json();
      throw new Error(error.message || "Erro ao fazer login");
    }

    const data = await response.json();

    console.log("Dados recebidos do login:", data);

    // Salvar dados do usuário no formato correto
    localStorage.setItem("usuario", JSON.stringify(data));
    localStorage.setItem("usuarioLogado", JSON.stringify(data));

    alert("Login realizado com sucesso!");

    // Redirecionar para a página principal
    window.location.href = "menu.html";
  } catch (error) {
    console.error("Erro no login:", error);

    // Mensagem específica se o servidor não estiver rodando
    if (error.message === "Failed to fetch") {
      alert(
        "Erro: Não foi possível conectar ao servidor. Verifique se o backend está rodando na porta 8080."
      );
    } else {
      alert(
        error.message || "Erro ao fazer login. Verifique suas credenciais."
      );
    }
  }
}
