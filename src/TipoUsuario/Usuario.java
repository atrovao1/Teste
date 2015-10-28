package TipoUsuario;

import java.util.ArrayList;

import Posts.Post;
import maisPopularidade.Valida;
import exception.UserException;

public class Usuario {

	private String nome;
	private String dataNasc;
	private String email;
	private String senha;
	private String imagem;
	private ArrayList<Post> posts;
	private ArrayList<String> listaDeSolicitacoes;
	private ArrayList<Usuario> listaDeAmigos;
	private ArrayList<String> notificacoes;
	private TipoPopularidade tipoPopularidade;
	public Valida valida;
	
	public Usuario(String nome, String email, String senha, String dataNasc, String imagem) 
		   throws UserException {
		
		this.valida = new Valida();
		
		if (!valida.nome(nome)) {
			throw new UserException(
					"Erro no cadastro de Usuarios. Nome dx usuarix nao pode ser vazio.");
		}

		if (!valida.email(email)) {
			throw new UserException(
					"Erro no cadastro de Usuarios. Formato de e-mail esta invalido.");
		}

		if (!valida.senha(senha)) {
			throw new UserException(
					"Erro no cadastro de Usuarios. Formato de senha esta invalido.");
		}

		if (!valida.formatoDataNasc(dataNasc)) {
			throw new UserException(
					"Erro no cadastro de Usuarios. Formato de data esta invalida.");
		}

		if (!valida.valorDataNasc(dataNasc)) {
			throw new UserException(
					"Erro no cadastro de Usuarios. Data nao existe.");
		}

		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.dataNasc = dataNasc;
		this.posts = new ArrayList<Post>();
		this.imagem = imagem;
		this.notificacoes = new ArrayList<String>();
		this.listaDeAmigos = new ArrayList<Usuario>();
		this.listaDeSolicitacoes = new ArrayList<String>();
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	public void adicionaPost(Post novopost) {
		this.posts.add(novopost);
		}
	
	public ArrayList<Post> getPosts() {
		return posts;
	}
	
	public String getPost(String atributo, int index) {
		if (atributo.equalsIgnoreCase("mensagem")){
			return getPosts().get(index).getMensagem();
			
		} else if(atributo.equalsIgnoreCase("data")){
			
			return getPosts().get(index).getData();
			
		} else if (atributo.equalsIgnoreCase("hashtags")){
			return getPosts().get(index).getHashtag();
		}
		return null;	
	}
	
	public Post getPostPeloIndex(int index) throws Exception {
		if (index >= posts.size()) {
			throw new Exception("");
		}
		return (Post) posts.get(index);
	}
	
	public void curtirPost(String nome, int index){
		Post post = posts.get(index);
		post.setCurtidas(1);
		setNotificacoes(nome+" curtiu seu post de "+post.getData()+".");
		
	}
	
	public void rejeitarPost(String nome, int index){
		Post post = posts.get(index);
		post.setCurtidas(-1);
		setNotificacoes(nome+" rejeitou seu post de "+post.getData()+".");
	}
	
	public String getConteudoPost(int indice, int post){
		return this.posts.get(post).getConteudoPost(indice);
				
	}
	

	// Caso de Uso 4
	
	public void setListaDeAmigos(Usuario novoAmigo){
		this.listaDeAmigos.add(novoAmigo);
	}
	
	public ArrayList<String> getListaDeSolicitacoes(){
		return this.listaDeSolicitacoes;
	}
	
	public void setListaDeSolicitacoes(String nomeAmigo){
		this.listaDeSolicitacoes.add(nomeAmigo);
	}
	
	public ArrayList<Usuario> getListaDeAmigos() {
		return listaDeAmigos;
	}

	public void adicionaAmigo(String nome) {
		setListaDeSolicitacoes(nome);
	}
	
	public void removeAmigo(Usuario amigoRemovido) throws Exception{
		if (!listaDeAmigos.contains(amigoRemovido)){
			throw new Exception(amigoRemovido.getNome()+" nao esta na sua lista de amigos.");
		
		}else{
			
		this.listaDeAmigos.remove(amigoRemovido);
		amigoRemovido.setNotificacoes(this.nome+" removeu a sua amizade.");
		}
	}

	public String getNextNotificacao(){
		String notificacao = notificacoes.get(0);
		notificacoes.remove(0);
		return notificacao;
	}
	
	public int getNotificacoes(){
		return this.notificacoes.size();
	}

	public void setNotificacoes(String notificacao) {
		this.notificacoes.add(notificacao);
	}
	
	public void aceitaAmizade(Usuario usuario){
		listaDeAmigos.add(usuario);
		listaDeSolicitacoes.remove(usuario);
		usuario.setNotificacoes(this.nome+" aceitou sua amizade.");
	}
	
	public void rejeitaAmizade(Usuario usuario){
		listaDeSolicitacoes.remove(usuario);
	}
	
	public int getQtdAmigos(){
		return listaDeAmigos.size();
	}
	
	public String toString(){
		return this.nome+" - "+this.email;
	}
	
}
