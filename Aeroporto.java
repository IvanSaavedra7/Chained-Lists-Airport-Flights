
public class Aeroporto
{
	private int codigo;
	private ListaDeVoos voos;

	private String nome;
	private String sigla;

	public Aeroporto(int codigo, String nome, ListaDeVoos voos, String sigla) throws Exception {

		if (codigo <= 0 || nome == null || sigla == null)
			throw new Exception("Informacao ausente");
		
		if(voos == null)
			throw new Exception("Lista de voos ausente!");
		
		this.codigo = codigo;
		this.nome = nome;
		this.voos = voos;
		this.sigla = sigla;
	}

	
	public boolean temVoo() {
		return this.voos.primeiro != null;
	}
	
	
	public ListaDeVoos getListaDeVoos ()
	{
		return this.voos;
	}
	
	
	public int getCodigo() {
		return this.codigo;
	}

	
	public String getNome() {
		return this.nome;
	}
	
	
	@Override
	public String toString() {
		String voos = "";
		
		if(this.temVoo())
			voos = this.voos.toString();
		
		return "\n-----------------------------------------\n"+
			   " Aeroporto: " + this.sigla + "-" + this.nome + "-"+ this.codigo + "\n" + voos;
	}
}
