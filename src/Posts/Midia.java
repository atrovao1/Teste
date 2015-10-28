package Posts;

public abstract class Midia {
	
	private String caminho;
	
	public Midia(String caminho){
		this.caminho = caminho;
	}
	
	public String toString(){
		return caminho;
	}
}
