
public class voos
{
	private int indiceCidade;
	private int numeroVoo;
	private String cidadeDestino;
	
	public voos (int cidade, int voo, ListaDeAeroportos lista)throws Exception {
		
		if(cidade <= 0 || voo <= 0 || lista == null)
			throw new Exception ("Informacao ausente!");
		
		
		this.indiceCidade = cidade;
		this.cidadeDestino = lista.getCidade (this.indiceCidade);
		this.numeroVoo = voo;
	}
	
	
	public int getIndiceCidade () {
		return this.indiceCidade;
	}
	
	
	public int getNumeroVoo () {
		return this.numeroVoo;
	}
	
	
	@Override
	public String toString ()
	{
		return  "\nNumero do voo: "+ this.numeroVoo +
				"\nIndice da cidade: " + this.indiceCidade +
				"\nCidade destino: " + this.cidadeDestino;
	}
}