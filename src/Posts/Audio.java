package Posts;


public class Audio extends Midia{

	public Audio(String caminho) {
		super(caminho);
	}
	
	public String toString(){
		return "$arquivo_audio:"+super.toString();
	}

}
