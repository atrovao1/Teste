package maisPopularidade;

import java.util.ArrayList;

import Posts.Post;
import TipoUsuario.Usuario;
import exception.SystemException;
import exception.UserException;

public class Controle {

	private ArrayList<Usuario> usuariosCadastrados;
	private Usuario usuarioLogado;

	public Controle() {
		this.usuariosCadastrados = new ArrayList<Usuario>();
		this.usuarioLogado = null;
	}

	public void aniciaSistema() {
	}

	public String cadastraUsuario(String nome, String email, String senha, String dataNasc, String imagem)
		   throws UserException {
		
		Usuario novoUsuario = new Usuario(nome, email, senha, dataNasc, imagem);
		armazenaUsuario(novoUsuario);
		return novoUsuario.getEmail();
	}

	public void armazenaUsuario(Usuario novoUsuario) {
		if (!this.usuariosCadastrados.contains(novoUsuario)) {
			this.usuariosCadastrados.add(novoUsuario);
		}
	}
	
	public ArrayList<Usuario> getUsuariosCadastrados() {
		return usuariosCadastrados;
	}

	public void login(String email, String senha) throws SystemException {
		Usuario usuarioProcurado = procuraUsuarioPeloEmail(email);
		if (usuarioLogado == null) {
			if (usuarioProcurado != null) {
				if (usuarioProcurado.getEmail().equals(email) && usuarioProcurado.getSenha().equals(senha)) {
					setUsuarioLogado(usuarioProcurado);
					
				} else if (senha != usuarioProcurado.getSenha()) {
					throw new SystemException(
							"Nao foi possivel realizar login. Senha invalida.");
				
				} else if (email != usuarioProcurado.getEmail()) {
					throw new SystemException(
							"Nao foi possivel realizar login. Um usuarix com email "
									+ email + " nao esta cadastradx.");
					}
			} else {
				throw new SystemException(
						"Nao foi possivel realizar login. Um usuarix com email "
								+ email + " nao esta cadastradx.");
				}
		} else {
			throw new SystemException(
					"Nao foi possivel realizar login. Um usuarix ja esta logadx: "
							+ "" + usuarioLogado.getNome() + ".");
		}
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	public Usuario procuraUsuario(Usuario usuario) {
		if (this.usuariosCadastrados.contains(usuario)) {
			return usuario;
		}
		return null;
	}

	public int retornaIndiceDoUsuario(String email) {
		for (Usuario usuario : usuariosCadastrados) {
			if (usuario.getEmail().equals(email)) {
				return this.usuariosCadastrados.indexOf(usuario);
			}
		}
		return -1;
	}

	public Usuario procuraUsuarioPeloEmail(String email) {
		for (Usuario usuario: usuariosCadastrados){
			if (usuario.getEmail().equals(email)){
				return usuario;
			}
		}
		return null;
	}
	
	public void logout() throws SystemException {
		if (usuarioLogado != null) {
			setUsuarioLogado(null);
		} else {
			throw new SystemException(
					"Nao eh possivel realizar logout. Nenhum usuarix esta logadx no +pop.");
		}
	}

	public void removeUsuario(String email) {
		Usuario usuarioParaRemover = procuraUsuarioPeloEmail(email);
		if(usuarioParaRemover != null) {
			usuariosCadastrados.remove(usuarioParaRemover);
		}
	}

	public String getInfoUsuario(String atributo, String email) throws SystemException {
		Usuario usuarioProcurado = procuraUsuarioPeloEmail(email);
		if (usuarioProcurado != null) {
			if (atributo.equalsIgnoreCase("email")) {
				return usuarioProcurado.getEmail();
				
			} else if (atributo.equalsIgnoreCase("nome")) {
				return usuarioProcurado.getNome();
				
			} else if (atributo.equalsIgnoreCase("Data de Nascimento")) {
				String[] data = usuarioProcurado.getDataNasc().split("/");
				String dataUsuario = "";
				for (int i = data.length - 1; i >= 0; i--) {
					if (i == 0) {
						dataUsuario = dataUsuario + data[i];
					} else {
						dataUsuario = dataUsuario + data[i] + "-";
					}
				}
				return dataUsuario;
				
			} else if (atributo.equalsIgnoreCase("foto")
					|| atributo.equalsIgnoreCase("imagem")) {
				return usuarioProcurado.getImagem();
				
			} else if (atributo.equalsIgnoreCase("senha")) {
				throw new SystemException("A senha dx usuarix eh protegida.");
			}
		} else {
			throw new SystemException("Um usuarix com email " + email
					+ " nao esta cadastradx.");
		}
		return null;
	}

	public String getInfoUsuario(String atributo) throws SystemException {
		if (usuarioLogado != null) {
			if (atributo.equalsIgnoreCase("email")) {
				return usuarioLogado.getEmail();
				
			} else if (atributo.equalsIgnoreCase("nome")) {
				return usuarioLogado.getNome();
				
			} else if (atributo.equalsIgnoreCase("data de nascimento")) {
				String[] data = usuarioLogado.getDataNasc().split("/");
				String dataUsuario = "";
				for (int i = data.length - 1; i >= 0; i--) {
					if (i == 0) {
						dataUsuario = dataUsuario + data[i];
					} else {
						dataUsuario = dataUsuario + data[i] + "-";
					}
				}
				return dataUsuario;
				
			} else if (atributo.equalsIgnoreCase("foto") || atributo.equalsIgnoreCase("imagem")) {
				return usuarioLogado.getImagem();
				
			} else if (atributo.equalsIgnoreCase("senha")) {
				throw new SystemException("A senha dx usuarix eh protegida.");
			}
		}
		return null;
	}
	
	public void fechaSistema() throws SystemException {
		if (usuarioLogado != null) {
			throw new SystemException("Nao foi possivel fechar o sistema. Um usuarix ainda esta logadx.");
		}
	}
	
	public void atualizaPerfil(String atributo, String valor) throws SystemException {
		if (usuarioLogado != null) {
			if (atributo.equalsIgnoreCase("email") || atributo.equalsIgnoreCase("E-mail")) {
				if(usuarioLogado.valida.email(valor)){
					usuarioLogado.setEmail(valor);
				} else {
					throw new SystemException("Erro na atualizacao de perfil. Formato de e-mail esta invalido.");
			}
			
		} else if (atributo.equalsIgnoreCase("nome")) {
			if (usuarioLogado.valida.nome(valor)) {
				usuarioLogado.setNome(valor);
			} else {
				throw new SystemException("Erro na atualizacao de perfil. Nome dx usuarix nao pode ser vazio.");
			}
			
		} else if (atributo.equalsIgnoreCase("data de nascimento")) {
			if (!usuarioLogado.valida.formatoDataNasc(valor)) {
				throw new SystemException("Erro na atualizacao de perfil. Formato de data esta invalida.");
			}else if (!usuarioLogado.valida.valorDataNasc(valor)) {
				throw new SystemException("Erro na atualizacao de perfil. Data nao existe.");
			} else{
				usuarioLogado.setDataNasc(valor);
				}
			
		} else if (atributo.equalsIgnoreCase("foto") || atributo.equalsIgnoreCase("imagem")) {
			usuarioLogado.setImagem(valor);
		}
			}else {
				throw new SystemException("Nao eh possivel atualizar um perfil. Nenhum usuarix esta logadx no +pop.");
			}
	}
	
	public void atualizaPerfil(String atributo, String valor, String velhaSenha) throws SystemException {
		if (usuarioLogado != null) {
				if (usuarioLogado.getSenha().equals(velhaSenha) & usuarioLogado.valida.senha(valor)) {
					usuarioLogado.setSenha(valor);
			} else  {
				throw new SystemException("Erro na atualizacao de perfil. A senha fornecida esta incorreta.");
		}	
	}else {
		throw new SystemException("Nao eh possivel atualizar um perfil. Nenhum usuarix esta logadx no +pop.");
	}
				}

	public void criaPost(String mensagem, String data) throws Exception {
		if (usuarioLogado == null) {
			throw new SystemException("Nao eh possivel criar post. Nenhum usuarix esta logadx no +pop");
			}
			Post post = new Post(mensagem, data);
			usuarioLogado.adicionaPost(post);
		}

	public Post getPost(int index) throws Exception {
		return usuarioLogado.getPostPeloIndex(index);
	}
	
	public String getPost(String atributo, int index){
		return usuarioLogado.getPost(atributo, index);
	}
	
	public void curtirPost(String email, int index){
		Usuario tempUser = procuraUsuarioPeloEmail(email);
		tempUser.curtirPost(usuarioLogado.getNome(), index);
		
	}
	
	public void adicionaAmigo(String email) throws Exception{
		Usuario tempUser = procuraUsuarioPeloEmail(email);
		if(procuraUsuarioPeloEmail(email) == null){
			throw new SystemException("Um usuarix com email "+ email +" nao esta cadastradx.");
		
		}else{
			tempUser.setNotificacoes(usuarioLogado.getNome()+" quer sua amizade.");
			tempUser.adicionaAmigo(usuarioLogado.getNome());
		}
	}
	
	public void removeAmigo(String email) throws Exception{
		Usuario amigoRemovido = procuraUsuarioPeloEmail(email);
		if(amigoRemovido == null){
			throw new SystemException("Um usuarix com email "+ email +" nao esta cadastradx.");
		
		}else{
			usuarioLogado.removeAmigo(amigoRemovido);
			amigoRemovido.removeAmigo(usuarioLogado);
		}
	}
	
	public int getNotificacoes(){
		return usuarioLogado.getNotificacoes();
	}
	
	public String getNextNotificacao() throws Exception{
		if (usuarioLogado.getNotificacoes() == 0){
			throw new Exception("Nao ha mais notificacoes.");
		
		}else{
			return usuarioLogado.getNextNotificacao();
			
		}
	}
	
	public void rejeitaAmizade(String email) throws Exception{
		Usuario usuarioProcurado = procuraUsuarioPeloEmail(email);
		
		if(usuarioProcurado == null){
			throw new Exception("Um usuarix com email "+email+" nao esta cadastradx.");
		
		}else if (!usuarioLogado.getListaDeSolicitacoes().contains(usuarioProcurado.getNome())){
			throw new Exception(usuarioProcurado.getNome()+" nao lhe enviou solicitacoes de amizade."); 
			
		}else{
		usuarioProcurado.setNotificacoes(usuarioLogado.getNome()+" rejeitou sua amizade.");;	
		usuarioLogado.rejeitaAmizade(usuarioProcurado);
		}
	}
	
	@SuppressWarnings("unused")
	public void aceitaAmizade(String email) throws SystemException{
		Usuario usuarioProcurado = procuraUsuarioPeloEmail(email);
		
		if(!usuarioLogado.getListaDeSolicitacoes().contains(usuarioProcurado.getNome())){
			throw new SystemException(usuarioProcurado.getNome()+" nao lhe enviou solicitacoes de amizade.");
		
		}else if(usuarioProcurado == null){
			throw new SystemException("Um usuarix com email "+ email +" nao esta cadastradx.");
		
		}else{
			usuarioLogado.aceitaAmizade(usuarioProcurado);
			usuarioProcurado.setListaDeAmigos(usuarioLogado);
		}
	}
	
	public int getQtdAmigos(){
		return usuarioLogado.getQtdAmigos();
	}
}
