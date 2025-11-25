// Verificar se estamos na página de perfil (aceita perfil.html OU perfilCliente.html)
if (
  window.location.pathname.includes("perfil.html") ||
  window.location.pathname.includes("perfilCliente.html")
) {
  const API_URL = "http://localhost:8080/api";

  // Elementos do DOM
  const perfilNome = document.getElementById("perfil-nome");
  const perfilEmail = document.getElementById("perfil-email");
  const perfilTelefone = document.getElementById("perfil-telefone");
  const perfilEndereco = document.getElementById("perfil-endereco");
  const perfilTipo = document.getElementById("perfil-tipo");
  const perfilAvatarInicial = document.getElementById("perfil-avatar-inicial");

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

  // Upload de foto de perfil
  const inputFoto = document.getElementById("input-foto");
  const avatarImg = document.getElementById("perfil-avatar");
  const avatarInicial = document.getElementById("perfil-avatar-inicial");
  const btnRemoverFoto = document.getElementById("btn-remover-foto");

  // Elementos específicos de Premium (apenas em perfilCliente.html)
  const cardUsuarioPremium = document.getElementById("card-usuario-premium");
  const badgePremium = document.getElementById("badge-premium");

  let usuarioAtual = null;

  // Carregar dados do usuário
  async function carregarPerfil() {
    try {
      const usuarioJson = localStorage.getItem("usuario");
      console.log("Dados do localStorage:", usuarioJson);

      if (!usuarioJson) {
        console.error("Nenhum usuário encontrado no localStorage");
        alert("Sessão expirada. Faça login novamente.");
        window.location.href = "escolhaPerfil.html";
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

      // Carregar foto de perfil se existir
      if (usuarioAtual.usuarioId) {
        await carregarFotoPerfil(usuarioAtual.usuarioId);
      }

      // Verificar status premium (apenas para clientes)
      if (usuarioAtual.tipoUsuario === "cliente" && usuarioAtual.clienteId) {
        await verificarStatusPremium();
      }
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
    if (perfilAvatarInicial) perfilAvatarInicial.textContent = inicial;

    // Carregar foto se existir
    if (usuario.fotoPerfil) {
      if (avatarImg) {
        avatarImg.src = `http://localhost:8080/uploads/fotos-perfil/${usuario.fotoPerfil}`;
        avatarImg.style.display = "block";
      }
      if (avatarInicial) {
        avatarInicial.style.display = "none";
      }
      if (btnRemoverFoto) {
        btnRemoverFoto.style.display = "flex";
      }
    } else {
      if (btnRemoverFoto) {
        btnRemoverFoto.style.display = "none";
      }
    }
  }

  // ==========================================
  // FUNCIONALIDADES PREMIUM (apenas clientes)
  // ==========================================

  // Verificar status premium do usuário
  async function verificarStatusPremium() {
    if (!cardUsuarioPremium || !badgePremium) return; // Se não existem elementos premium, retorna

    try {
      const response = await fetch(
        `${API_URL}/premium/cliente/${usuarioAtual.clienteId}`
      );

      if (response.ok) {
        const assinatura = await response.json();

        if (assinatura && assinatura.status === "ativa") {
          mostrarBadgePremium();
        }
      }
    } catch (error) {
      console.log("Usuário não possui assinatura premium");
    }
  }

  // Mostrar badge premium
  function mostrarBadgePremium() {
    if (badgePremium) {
      badgePremium.style.display = "inline-block";
    }
  }

  // Ativar usuário premium
  async function ativarUsuarioPremium() {
    const confirmar = confirm(
      "⭐️ USUÁRIO PREMIUM\n\n" +
        "Ative agora e aproveite benefícios esses exclusivos:\n\n" +
        "- Entregas grátis ilimitadas\n" +
        "- Descontos exclusivos em restaurantes\n\n" +
        "💰 Valor: R$ 29,90/mês\n\n" +
        "Deseja ativar sua assinatura Premium agora?"
    );

    if (!confirmar) {
      return;
    }

    try {
      // Primeiro, buscar o plano premium disponível
      const planosResponse = await fetch(`${API_URL}/premium/planos`);

      if (!planosResponse.ok) {
        alert("❌ Nenhum plano premium disponível no momento.");
        return;
      }

      const planos = await planosResponse.json();
      const planoAtivo = planos.find((p) => p.ativo);

      if (!planoAtivo) {
        alert("❌ Nenhum plano premium disponível no momento.");
        return;
      }

      // Criar assinatura premium
      const response = await fetch(`${API_URL}/premium/criar`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          clienteId: usuarioAtual.clienteId,
          planoId: planoAtivo.id,
          metodoPagamento: "cartao_credito",
        }),
      });

      if (response.ok) {
        alert(
          "✅ Parabéns! Sua assinatura Premium foi ativada com sucesso!\n\nVocê já pode aproveitar todos os benefícios exclusivos."
        );

        mostrarBadgePremium();
        await carregarPerfil();
      } else {
        const error = await response.json();
        alert(
          "❌ Erro ao ativar Premium: " +
            (error.message || "Tente novamente mais tarde.")
        );
      }
    } catch (error) {
      console.error("Erro ao ativar premium:", error);
      alert(
        "❌ Erro ao processar sua solicitação. Tente novamente mais tarde."
      );
    }
  }

  // ==========================================
  // FUNCIONALIDADES GERAIS DE PERFIL
  // ==========================================

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
    window.location.href = "escolhaPerfil.html";
  }

  // Upload de foto de perfil
  if (inputFoto) {
    inputFoto.addEventListener("change", async (e) => {
      const file = e.target.files[0];
      if (!file) return;

      if (!usuarioAtual || !usuarioAtual.usuarioId) {
        alert("Erro: usuário não identificado");
        return;
      }

      if (!file.type.startsWith("image/")) {
        alert("Por favor, selecione uma imagem válida");
        return;
      }

      if (file.size > 5 * 1024 * 1024) {
        alert("Imagem muito grande. Máximo 5MB");
        return;
      }

      const formData = new FormData();
      formData.append("foto", file);

      try {
        const response = await fetch(
          `${API_URL}/clientes/upload-foto/${usuarioAtual.usuarioId}`,
          {
            method: "POST",
            body: formData,
          }
        );

        if (response.ok) {
          const data = await response.json();
          console.log("Upload bem-sucedido:", data);

          if (avatarImg) {
            avatarImg.src = `http://localhost:8080${
              data.url
            }?t=${new Date().getTime()}`;
            avatarImg.style.display = "block";
          }
          if (avatarInicial) {
            avatarInicial.style.display = "none";
          }

          const perfilResponse = await fetch(
            `${API_URL}/auth/perfil/${usuarioAtual.usuarioId}`
          );
          if (perfilResponse.ok) {
            const perfilAtualizado = await perfilResponse.json();
            console.log("Perfil atualizado:", perfilAtualizado);
            usuarioAtual = perfilAtualizado;
            localStorage.setItem("usuario", JSON.stringify(perfilAtualizado));
            localStorage.setItem(
              "usuarioLogado",
              JSON.stringify(perfilAtualizado)
            );

            if (btnRemoverFoto) {
              btnRemoverFoto.style.display = "flex";
            }
          }

          alert("Foto atualizada com sucesso!");
        } else {
          const error = await response.json();
          alert(error.message || "Erro ao fazer upload");
        }
      } catch (error) {
        console.error("Erro:", error);
        alert("Erro ao fazer upload da foto");
      }
    });
  }

  // Carregar foto existente
  async function carregarFotoPerfil(usuarioId) {
    try {
      const response = await fetch(`${API_URL}/auth/perfil/${usuarioId}`);
      if (response.ok) {
        const usuario = await response.json();
        console.log("Dados completos do usuário:", usuario);
        console.log("Foto perfil do usuário:", usuario.fotoPerfil);

        if (
          usuario.fotoPerfil &&
          usuario.fotoPerfil !== null &&
          usuario.fotoPerfil !== ""
        ) {
          const fotoUrl = `http://localhost:8080/uploads/fotos-perfil/${
            usuario.fotoPerfil
          }?t=${new Date().getTime()}`;
          console.log("URL da foto:", fotoUrl);

          if (avatarImg) {
            avatarImg.src = fotoUrl;
            avatarImg.style.display = "block";
            avatarImg.onerror = function () {
              console.error("Erro ao carregar imagem:", this.src);
              this.style.display = "none";
              if (avatarInicial) avatarInicial.style.display = "flex";
            };
          }
          if (avatarInicial) {
            avatarInicial.style.display = "none";
          }
        } else {
          console.log("Usuário não possui foto de perfil");
          if (avatarImg) avatarImg.style.display = "none";
          if (avatarInicial) avatarInicial.style.display = "flex";
          if (btnRemoverFoto) btnRemoverFoto.style.display = "none";
        }
      }
    } catch (error) {
      console.error("Erro ao carregar foto:", error);
    }
  }

  // Remover foto de perfil
  async function removerFotoPerfil() {
    if (!usuarioAtual || !usuarioAtual.usuarioId) {
      alert("Erro: usuário não identificado");
      return;
    }

    if (!confirm("Tem certeza que deseja remover sua foto de perfil?")) {
      return;
    }

    try {
      const response = await fetch(
        `${API_URL}/clientes/remover-foto/${usuarioAtual.usuarioId}`,
        {
          method: "DELETE",
        }
      );

      if (response.ok) {
        if (avatarImg) {
          avatarImg.style.display = "none";
          avatarImg.src = "";
        }
        if (avatarInicial) {
          avatarInicial.style.display = "flex";
        }
        if (btnRemoverFoto) {
          btnRemoverFoto.style.display = "none";
        }

        const perfilResponse = await fetch(
          `${API_URL}/auth/perfil/${usuarioAtual.usuarioId}`
        );
        if (perfilResponse.ok) {
          const perfilAtualizado = await perfilResponse.json();
          usuarioAtual = perfilAtualizado;
          localStorage.setItem("usuario", JSON.stringify(perfilAtualizado));
          localStorage.setItem(
            "usuarioLogado",
            JSON.stringify(perfilAtualizado)
          );
        }

        alert("Foto removida com sucesso!");
      } else {
        const error = await response.json();
        alert(error.message || "Erro ao remover foto");
      }
    } catch (error) {
      console.error("Erro:", error);
      alert("Erro ao remover foto");
    }
  }

  // ==========================================
  // EVENT LISTENERS
  // ==========================================

  // Listeners gerais
  if (btnEditarPerfil) btnEditarPerfil.addEventListener("click", abrirModal);
  if (modalClose) modalClose.addEventListener("click", fecharModal);
  if (btnCancelar) btnCancelar.addEventListener("click", fecharModal);
  if (formEditarPerfil)
    formEditarPerfil.addEventListener("submit", salvarPerfil);
  if (btnLogout) btnLogout.addEventListener("click", logout);
  if (btnRemoverFoto)
    btnRemoverFoto.addEventListener("click", removerFotoPerfil);

  // Listener específico de Premium (apenas se o elemento existir)
  if (cardUsuarioPremium) {
    cardUsuarioPremium.addEventListener("click", ativarUsuarioPremium);
  }

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
