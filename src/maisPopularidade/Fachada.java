package maisPopularidade;

import Posts.Post;
import easyaccept.EasyAccept;
import exception.SystemException;
import exception.UserException;


public class Fachada {
	
	private Controle controle;
	
	public Fachada() {
		this.controle = new Controle();
	}
	
	public static void main(String[] args) {
		      args = new String[] {"maisPopularidade.Fachada",
				"MaisPoP/resources/Scripts de Teste" + "/usecase_1.txt",
				"MaisPoP/resources/Scripts de Teste" + "/usecase_2.txt",
				"MaisPoP/resources/Scripts de Teste" + "/usecase_3.txt",
				"MaisPoP/resources/Scripts de Teste" + "/usecase_4.txt"

		};
		    EasyAccept.main(args);
	}
	
	public void iniciaSistema() {
		controle.aniciaSistema();
	}
	
	public String cadastraUsuario(String nome, String email, String senha, String dataNasc, String imagem) throws UserException {
			return controle.cadastraUsuario(nome, email, senha, dataNasc, imagem);	
	}
	
	public String cadastraUsuario(String nome, String dataNasc, String email, String senha) throws Exception {
			return controle.cadastraUsuario(nome, dataNasc, email, senha, "resources/default.jpg");
	}
	
	public void login(String email, String senha) throws SystemException {
		 controle.login(email, senha);
	}
	
	public void logout() throws SystemException {
		controle.logout();
	}
	public String getInfoUsuario(String atributo, String usuario) throws SystemException {
		return controle.getInfoUsuario(atributo, usuario);
	}
	
	public String getInfoUsuario(String atributo) throws SystemException {
		return controle.getInfoUsuario(atributo);
	}
	
	public void removeUsuario(String usuario) {
		controle.removeUsuario(usuario);
	}
	
	public void fechaSistema() throws SystemException {
		controle.fechaSistema();
	}
	
	public void atualizaPerfil(String atributo, String valor) throws SystemException {
		controle.atualizaPerfil(atributo, valor);
	}
	
	public void atualizaPerfil(String atributo, String valor, String velhaSenha) throws SystemException {
		controle.atualizaPerfil(atributo, valor, velhaSenha);
	}
	
	public void criaPost(String mensagem, String data) throws Exception {
		controle.criaPost(mensagem, data);
	}
	
	public Post getPost(int index) throws Exception {
		return controle.getPost(index);
	}
	
	public String getPost(String atributo, int index){
		return controle.getPost(atributo, index);
	}
	
	public void curtirPost(String email, int index){
		controle.curtirPost(email, index);		
	}
	
	public void adicionaAmigo(String email) throws Exception{
		controle.adicionaAmigo(email);
	}
	
	public void removeAmigo(String email) throws Exception{
		controle.removeAmigo(email);
	}
	
	public int getNotificacoes(){
		return controle.getNotificacoes();
	}
	
	public String getNextNotificacao() throws Exception{
		return controle.getNextNotificacao();
	}
	
	public void aceitaAmizade(String email) throws SystemException{
		controle.aceitaAmizade(email);
	}
	
	public void rejeitaAmizade(String email) throws Exception{
		controle.rejeitaAmizade(email);
	}
	
	public int getQtdAmigos(){
		return controle.getQtdAmigos();
	}
}

