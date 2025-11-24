// Verificar se estamos na página de perfil
if (window.location.pathname.includes("perfil.html")) {
  const API_URL = "http://localhost:8080/api";

  // Elementos do DOM
  const perfilNome = document.getElementById("perfil-nome");
  const perfilEmail = document.getElementById("perfil-email");
  const perfilTelefone = document.getElementById("perfil-telefone");
  const perfilEndereco = document.getElementById("perfil-endereco");
  const perfilTipo = document.getElementById("perfil-tipo");
  const perfilAvatar = document.getElementById("perfil-avatar");

  const btnEditarPerfil = document.getElementById("btn-editar-perfil");
  const btnLogout = document.getElementById("btn-logout");
  const modal = document.getElementById("modal-editar");
  const modalClose = document.getElementById("modal-close");
  const btnCancelar = document.getElementById("btn-cancelar");
  const formEditarPerfil = document.getElementById("form-editar-perfil");

  // Campos do formulário
  const editNome = document.getElementById("edit-nome");
  const editEmail = document.getElementById("edit-email");
  const editTelefone = document.getElementById("edit-telefone");
  const editEndereco = document.getElementById("edit-endereco");

  let usuarioAtual = null;

  // Carregar dados do usuário
  async function carregarPerfil() {
    try {
      const usuarioJson = localStorage.getItem("usuario");
      console.log("Dados do localStorage:", usuarioJson);

      if (!usuarioJson) {
        console.error("Nenhum usuário encontrado no localStorage");
        alert("Sessão expirada. Faça login novamente.");
        window.location.href = "index.html";
        return;
      }

      usuarioAtual = JSON.parse(usuarioJson);
      console.log("Usuário parseado:", usuarioAtual);

      // Buscar dados atualizados do servidor
      if (usuarioAtual.usuarioId) {
        try {
          const response = await fetch(
            `${API_URL}/auth/perfil/${usuarioAtual.usuarioId}`
          );

          if (response.ok) {
            const dadosAtualizados = await response.json();
            console.log("Dados atualizados do servidor:", dadosAtualizados);
            usuarioAtual = dadosAtualizados;
            localStorage.setItem("usuario", JSON.stringify(dadosAtualizados));
          }
        } catch (error) {
          console.warn(
            "Não foi possível buscar dados atualizados, usando cache:",
            error
          );
        }
      }

      exibirDadosPerfil(usuarioAtual);
    } catch (error) {
      console.error("Erro ao carregar perfil:", error);
      alert("Erro ao carregar perfil. Faça login novamente.");
      window.location.href = "index.html";
    }
  }

  // Exibir dados do perfil
  function exibirDadosPerfil(usuario) {
    console.log("Exibindo dados do usuário:", usuario);

    if (perfilNome) perfilNome.textContent = usuario.nome || "Não informado";
    if (perfilEmail) perfilEmail.textContent = usuario.email || "Não informado";
    if (perfilTelefone)
      perfilTelefone.textContent = usuario.telefone || "Não informado";
    if (perfilEndereco)
      perfilEndereco.textContent = usuario.enderecoPadrao || "Não informado";
    if (perfilTipo) perfilTipo.textContent = usuario.tipoUsuario || "Cliente";

    // Avatar com inicial do nome
    const inicial = usuario.nome ? usuario.nome.charAt(0).toUpperCase() : "U";
    if (perfilAvatar) perfilAvatar.textContent = inicial;
  }

  // Abrir modal de edição
  function abrirModal() {
    if (!usuarioAtual) {
      alert("Erro: dados do usuário não carregados");
      return;
    }

    editNome.value = usuarioAtual.nome || "";
    editEmail.value = usuarioAtual.email || "";
    editTelefone.value = usuarioAtual.telefone || "";
    editEndereco.value = usuarioAtual.enderecoPadrao || "";

    modal.classList.add("active");
  }

  // Fechar modal
  function fecharModal() {
    modal.classList.remove("active");
  }

  // Salvar alterações
  async function salvarPerfil(event) {
    event.preventDefault();

    try {
      const dadosAtualizados = {
        usuarioId: usuarioAtual.usuarioId,
        clienteId: usuarioAtual.clienteId,
        nome: editNome.value.trim(),
        email: editEmail.value.trim(),
        telefone: editTelefone.value.trim(),
        enderecoPadrao: editEndereco.value.trim(),
      };

      console.log("Enviando atualização:", dadosAtualizados);

      const response = await fetch(`${API_URL}/clientes/atualizar`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(dadosAtualizados),
      });

      if (!response.ok) {
        const error = await response.json();
        throw new Error(error.message || "Erro ao atualizar perfil");
      }

      // Atualizar dados locais
      usuarioAtual = { ...usuarioAtual, ...dadosAtualizados };
      localStorage.setItem("usuario", JSON.stringify(usuarioAtual));
      localStorage.setItem("usuarioLogado", JSON.stringify(usuarioAtual));

      exibirDadosPerfil(usuarioAtual);
      fecharModal();

      alert("Perfil atualizado com sucesso!");
    } catch (error) {
      console.error("Erro ao atualizar:", error);
      alert("Erro ao atualizar perfil: " + error.message);
    }
  }

  // Logout
  function logout() {
    localStorage.removeItem("token");
    localStorage.removeItem("usuario");
    localStorage.removeItem("usuarioLogado");
    window.location.href = "index.html";
  }

  // Event Listeners
  if (btnEditarPerfil) btnEditarPerfil.addEventListener("click", abrirModal);
  if (modalClose) modalClose.addEventListener("click", fecharModal);
  if (btnCancelar) btnCancelar.addEventListener("click", fecharModal);
  if (formEditarPerfil)
    formEditarPerfil.addEventListener("submit", salvarPerfil);
  if (btnLogout) btnLogout.addEventListener("click", logout);

  // Fechar modal ao clicar fora
  if (modal) {
    modal.addEventListener("click", (e) => {
      if (e.target === modal) {
        fecharModal();
      }
    });
  }

  // Inicializar quando o DOM estiver pronto
  document.addEventListener("DOMContentLoaded", () => {
    carregarPerfil();
  });
}
