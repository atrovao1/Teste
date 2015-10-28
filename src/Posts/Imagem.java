package Posts;


public class Imagem extends Midia{

	public Imagem(String caminho) {
		super(caminho);
	}

	public String toString(){
		return "$arquivo_imagem:"+super.toString();
	}
	
}
