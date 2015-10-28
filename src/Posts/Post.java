package Posts;

import java.util.ArrayList;
import java.util.List;

import maisPopularidade.Valida;

public class Post {
	
	private static final int MAXIMO_CARACTERES = 200;
	private String mensagem;
	private List<Midia> midias;
	private String data;
	private Valida valida;
	private int curtidas;
	private int rejeicoes;
	

	public Post(String mensagem, String data) throws Exception {
		this.valida = new Valida();
		if (!valida.conteudoPost(mensagem)) {
			throw new Exception("Conteudo do post nao pode ser nulo ou vazio.");
		}
		if (separaMensagem(mensagem).length()>= MAXIMO_CARACTERES){
			throw new Exception("Nao eh possivel criar o post. O limite maximo da mensagem sao 200 caracteres.");	
		}
		if (mensagem.contains("#")) {
			String hashtagErro = valida.hashtags(mensagem);
			if (hashtagErro != null) {
				throw new Exception("Nao eh possivel criar o post. As hashtags devem comecar com '#'."
						+  " Erro na hashtag: '" + hashtagErro + "'.");
			}
		}
				
		
		this.mensagem = separaMensagem(mensagem);
		this.data = data;
		this.curtidas = 0;
		this.midias = new ArrayList<>();
	}
	
		private String separaMensagem(String conteudo) {
			ArrayList<String> mensagem = new ArrayList<String>();
			String[] palavras = conteudo.split(" ");
			for (String palavra : palavras) {
				if (!palavra.startsWith("#") )//&& !palavra.startsWith("<"))
					mensagem.add(palavra);
			}
			return String.join(" ", mensagem);
		}
		
		private String separaHashtags(String conteudo) {
			ArrayList<String> hashtags = new ArrayList<String>();
			String[] palavras = conteudo.split(" ");
			for (String palavra : palavras) {
				if (palavra.startsWith("#"))
					hashtags.add(palavra);
			}
			
			return String.join(",", hashtags);
		}
		
		public void adicionaMidia(String conteudo) {
			String[] palavras = conteudo.split(" ");
			for (String palavra : palavras) {
				if (palavra.startsWith("<imagem>")){
					 String caminho = palavra.substring(7, palavra.length()-9);
					 midias.add(new Imagem(caminho));
				
				}else if(palavra.startsWith("<audio>")){
					String caminho = palavra.substring(6, palavra.length()-8);
					midias.add(new Audio(caminho));
				}
				
			}
		}
		
		public String formataDataPost(){
			String data = this.data.substring(0, 10);
			String hora = this.data.substring(10, this.data.length());
			String[] dataPost = data.split("/");
			String dataUsuario = "";
			for (int i = dataPost.length - 1; i >= 0; i--) {
				if (i == 0) {
					dataUsuario = dataUsuario + dataPost[i];
				} else {
					dataUsuario = dataUsuario + dataPost[i] + "-";
				}
			}
			return dataUsuario + hora ;
		}
		
		

		public String getMensagem() {
				return mensagem;
		}

		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}

		public String getData() {
			return formataDataPost();
		}

		public void setData(String data) {
			this.data = data;
		}
		
		public String getHashtag(){
			return separaHashtags(mensagem);
		}

		@Override				  
		public String toString() {
			return mensagem  + " (" + formataDataPost() + ")";
		}

		public int getCurtidas() {
			return curtidas;
		}

		public void setCurtidas(int curtidas) {
			this.curtidas += curtidas;
		}
		
		public int getRejeicoes() {
			return rejeicoes;
		}

		public void setRejeicoes(int rejeicoes) {
			this.rejeicoes += rejeicoes;
		}

		public String getConteudoPost(int indice) {
			
			return midias.get(indice).toString();
		}
		
		

}
