package managedBeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import dao.ConstantesSistema;
import entidades.Agendamento;
import entidades.Cliente;
import entidades.Servicos;
import fachada.Fachada;

/**
 * 
 * @author pedro.silva
 *
 */
@ManagedBean(name = "clienteBean")
@SessionScoped
public class ClienteBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	private List<Cliente> listaClientes;
	private String confirmarSenha;
	public Servicos servicos;
	public List<Servicos> listaServicos;
	public Agendamento agendamento;
	List<Agendamento> listaAgendamentos;

	public ClienteBean() {
		this.cliente = new Cliente();
		listaClientes = new ArrayList<Cliente>();
		listaAgendamentos = new ArrayList<Agendamento>();
		servicos =  new  Servicos();
		agendamento = new Agendamento();
	}

	/**
	 * Metodos que pega o obj cliente e faz valida��o
	 */
	public void validarSalvar() {

		// Verificando se todos os atribudos do cliente est�o preenchidos
		if (Fachada.getInstancia().validarSalvarCliente(cliente) == true) {
			// validando o email
			FacesMessage faces = Fachada.getInstancia().validarEmail(cliente);
			if (faces == null) {
				// Se o email est� correto - Chamo metodo para criptografar a senha
				criptografarSenha(cliente.getSenha());
				// Criptografando senha de confirma��o
				confirmarSenha = Fachada.getInstancia().criptografarSenha(confirmarSenha);
				// Verificando se as senhas digitadas s�o iguais. Se sim, persisto o objeto na
				// base
				if (cliente.getSenha().equals(confirmarSenha)) {
					try {
						//Validando se j� existe registro na Base com os dados preenchidos na tela. Se n�o houver, ser� obtido null e passa.
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
					//Msg de erro caso a senha de confirma��o nao seja igual a senha digitada previamente.
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_WARN, ConstantesSistema.CONFIRMACAO_DE_SENHA, ""));
				}
			} else
				// Se o email n�o for valido exibo a mensagem.
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
				// Se o email est� correto - Chamo metodo para criptografar a senha
				criptografarSenha(cliente.getSenha());
				// Criptografando senha de confirma��o
				confirmarSenha = Fachada.getInstancia().criptografarSenha(confirmarSenha);
				// Verificando se as senhas digitadas s�o iguais. Se sim, persisto o objeto na
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
	 * Metodo que faz a dele��o do cliente na base
	 */
	public void removerClientes() {
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
	 * Metodo que 
	 */
	public void login() {
		try {
			//Setando senha na variavel de confirma��o ja com a criptografia
			confirmarSenha = Fachada.getInstancia().criptografarSenha(cliente.getSenha());
			//setando a senha criptografada no objeto, e assim fazer a busca na base
			cliente.setSenha(confirmarSenha);
			//Indo buscar onjeto na base
			cliente = Fachada.getInstancia().login(cliente, true);
			//Verificando se a senha que foi trazida no objeto confere com a senha de confirma��o. Se sim passa.
			if(cliente==null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, ConstantesSistema.CADASTRO_NAO_LOCALIZADO, ""));
			}else if(cliente.getSenha().equals(confirmarSenha) ) {
					try {
						//Aqui salvo o cliente no ExternalContext
						 ExternalContext ecContext = FacesContext.getCurrentInstance().getExternalContext();
					        ecContext.getRequestMap().put("clienteAgendamento",cliente);
						FacesContext.getCurrentInstance().getExternalContext().redirect(ConstantesSistema.VIEW_PERFIL_CLIENTE);
					} catch (Exception e) {
						//Caso haja algum tipo de erro.
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage(FacesMessage.SEVERITY_ERROR, ConstantesSistema.ERROR, ""));
					}
				
			}else {
				//Se os dados de senha n�o baterem exibo mensagem.
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, ConstantesSistema.EMAIL_OU_SENHA_INVALIDO, ""));
			}
		} catch (Exception e) {
			//Caso haja algum tipo de erro.
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
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Cliente", cliente);
			FacesContext.getCurrentInstance().getExternalContext().redirect(ConstantesSistema.VIEW_CADASTRAR_CLIENTE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que redireciona o cliente para sua p�gnia de perfil
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
	 * Metodo para Redirecionar para a pag de login do cliente
	 */
	public void redirecionarPagLogin() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(ConstantesSistema.VIEW_LOGIN_CLIENTE);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	/**
	 * Metodo que redireciona o cliente para a pagina de agendamentos.
	 */
	public void redirecioinarParaMeusAgendamentos() {
		try {
			//Aqui salvo o cliente no ExternalContext
			 ExternalContext ecContext = FacesContext.getCurrentInstance().getExternalContext();
		        ecContext.getRequestMap().put("clienteAgendamento",cliente);
			FacesContext.getCurrentInstance().getExternalContext().redirect(ConstantesSistema.AGENDAMENTOS);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * Metodo que redireciona o Cliente para a pagina das barbearias disponiveis.
	 * Salva o objeto no ExternalContext para usar o objto no bean Barbearia
	 * @return String que faz o redirecionamento
	 */
	public String redirecionarPagBarber() {
		try {
			//Aqui salvo o cliente no ExternalContext
			 ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		        ec.getRequestMap().put("cliente", cliente);
		}catch (Exception e) {
			e.printStackTrace();
		}
		//Aqui retorno a string com a pag da barbearia
		return ConstantesSistema.VIEW_BARBEARIA;
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
	 * Metodo que lista todos os agendamentoe de um cliente logado no sistema;
	 * @param cliente
	 * @return List<Agendamento>
	 */
	public List<Servicos> listarAgendamentos(){
		listaServicos = new ArrayList<Servicos>();
		listaAgendamentos = Fachada.getInstancia().listarAgendamentos(this.cliente);
		for(int i =0;i<listaAgendamentos.size();i++) {
			for(int x=0;x<listaAgendamentos.get(i).getServicos().size();x++) {
				servicos.setId(listaAgendamentos.get(i).getServicos().get(x).getId());
				servicos.setNome(listaAgendamentos.get(i).getServicos().get(x).getNome());
				servicos.setAgendamento(listaAgendamentos.get(i).getServicos().get(x).getAgendamento());
				servicos.setBarbearia(listaAgendamentos.get(i).getServicos().get(x).getBarbearia());
				servicos.setDescricao(listaAgendamentos.get(i).getServicos().get(x).getDescricao());
				servicos.setValor(listaAgendamentos.get(i).getServicos().get(x).getValor());
				listaServicos.add(servicos);
				servicos = new Servicos();
			}
		}
			
		return listaServicos;
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

	public Servicos getServicos() {
		return servicos;
	}

	public void setServicos(Servicos servicos) {
		this.servicos = servicos;
	}

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}

	public List<Agendamento> getListaAgendamentos() {
		return listaAgendamentos;
	}

	public void setListaAgendamentos(List<Agendamento> listaAgendamentos) {
		this.listaAgendamentos = listaAgendamentos;
	}

	public List<Servicos> getListaServicos() {
		return listaServicos;
	}

	public void setListaServicos(List<Servicos> listaServicos) {
		this.listaServicos = listaServicos;
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