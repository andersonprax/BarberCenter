package managedBeans;

import java.io.IOException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import dao.ConstantesSistema;
import entidades.Cliente;
import fachada.Fachada;

/**
 * 
 * @author pedro.silva
 *
 */
@ManagedBean
@SessionScoped
public class ClienteBean {

	private Cliente cliente;
	private List<Cliente> listaClientes;
	private String confirmarSenha;

	public ClienteBean() {
		this.cliente = new Cliente();
	}

	/**
	 * Metodos que pega o obj cliente e faz validação
	 */
	public void validarSalvar() {

		// Verificando se todos os atribudos do cliente estão preenchidos
		if (Fachada.getInstancia().validarSalvarCliente(cliente) == true) {
			// validando o email
			FacesMessage faces = Fachada.getInstancia().validarEmail(cliente);
			if (faces == null) {
				// Se o email está correto - Chamo metodo para criptografar a senha
				criptografarSenha(cliente.getSenha());
				// Criptografando senha de confirmação
				confirmarSenha = Fachada.getInstancia().criptografarSenha(confirmarSenha);
				// Verificando se as senhas digitadas são iguais. Se sim, persisto o objeto na
				// base
				if (cliente.getSenha().equals(confirmarSenha)) {
					try {
						//Validando se já existe registro na Base com os dados preenchidos na tela. Se não houver, será obtido null e passa.
						if (consultarCliente(true) == null) {
							// Persistir Cliente na base
							salvarCliente(cliente);
							FacesContext.getCurrentInstance().addMessage(null,
									new FacesMessage(FacesMessage.SEVERITY_INFO, ConstantesSistema.SALVO_SUCESSO, ""));
							// Redirecionar para pagina de perfil
							redirecionarPagPerfilDoCliente();
						}else {
							//Caso retorne valor do banco com os dados inseridos na tela, o cliente ja possui cadastro
							FacesContext.getCurrentInstance().addMessage(null,
									new FacesMessage(FacesMessage.SEVERITY_WARN, ConstantesSistema.CLIENTE_JA_CADASTRADO, ""));
						}
					} catch (Exception e) {
						//Em caso de erro e exibida essa msg
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage(FacesMessage.SEVERITY_ERROR, ConstantesSistema.ERROR, ""));
					}
				}
				else {
					//Msg de erro caso a senha de confirmação nao seja igual a senha digitada previamente.
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_WARN, ConstantesSistema.CONFIRMACAO_DE_SENHA, ""));
				}
			} else
				// Se o email não for valido exibo a mensagem.
				FacesContext.getCurrentInstance().addMessage(null, faces);
		} else
			//Se faltou preencher algum campo e exibida a msg abaixo. 
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, ConstantesSistema.ERROR, ""));
	}

	/**
	 * Metodo que atualiza na base os dados cliente ja cadastrado.
	 */
	public void atualizarDadosDoCliente() {
		if (Fachada.getInstancia().validarSalvarCliente(cliente)) {
			// validando o email
			FacesMessage faces = Fachada.getInstancia().validarEmail(cliente);
			if (faces == null) {
				// Se o email está correto - Chamo metodo para criptografar a senha
				criptografarSenha(cliente.getSenha());
				// Criptografando senha de confirmação
				confirmarSenha = Fachada.getInstancia().criptografarSenha(confirmarSenha);
				// Verificando se as senhas digitadas são iguais. Se sim, persisto o objeto na
				// base
				if (cliente.getSenha().equals(confirmarSenha)) {
					try {
						// Atualizar Cliente na base
						Fachada.getInstancia().atulializarCliente(cliente);
						FacesContext.getCurrentInstance().addMessage(cliente.getNome(),
								new FacesMessage(FacesMessage.SEVERITY_INFO, ConstantesSistema.SALVO_SUCESSO, ""));
					} catch (Exception e) {
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage(FacesMessage.SEVERITY_ERROR, ConstantesSistema.ERROR, ""));
					}
				}
			}
		}
	}

	/**
	 * Metodo que faz a deleção do cliente na base
	 */
	public void removerCliente() {
		if (cliente != null) {
			try {
				Fachada.getInstancia().removerCliente(cliente);
				FacesContext.getCurrentInstance().getExternalContext().redirect(ConstantesSistema.VIEW_LOGIN_CLIENTE);
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, ConstantesSistema.ERROR, ""));
			}
		}
	}

	/**
	 * Metodo que faz a busca de um unico registro do cliente na base
	 * 
	 * @param b True para fechar a conexao ou false para deixar aberta.
	 * @return Cliente
	 */
	public Cliente consultarCliente(boolean b) {
		return Fachada.getInstancia().buscarCliente(cliente, b);
	}

	/**
	 * Metodo que criptografa a senha do cliente
	 * 
	 * @param cliente
	 */
	public void criptografarSenha(String senha) {
		this.cliente.setSenha(Fachada.getInstancia().criptografarSenha(senha));
	}

	/**
	 * Metodo que pega o obj cliente e faz a persistencia no sgbd
	 * 
	 * @param cliente
	 */
	public void salvarCliente(Cliente cliente) {
		try {
			Fachada.getInstancia().salvarCliente(cliente);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, ConstantesSistema.ERROR, ""));
		}
	}

	/**
	 * Metodo que faz o redirecionamento da pag de login, para a pag de cadastro do
	 * cliente
	 */
	public void redirecionarPagCadastrarCliente() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(ConstantesSistema.VIEW_CADASTRAR_CLIENTE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que redireciona o cliente para sua págnia de perfil
	 */
	public void redirecionarPagPerfilDoCliente() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(ConstantesSistema.VIEW_PERFIL_CLIENTE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que lista todos os clientes cadastrados no banco
	 * 
	 * @return List<Cliente>
	 */
	public List<Cliente> listarClientes() {
		return Fachada.getInstancia().listarClientes();
	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return List<Cliente>
	 */
	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	/**
	 * @param listaClientes the listaClientes to set
	 */
	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	/**
	 * @return the confirmarSenha
	 */
	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	/**
	 * @param confirmarSenha the confirmarSenha to set
	 */
	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}
}