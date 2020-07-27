
public class ListaDeAeroportos extends ListaSimplesDesordenada<Aeroporto>
{
	@Override
	public void insira (Aeroporto info) throws Exception
	{
		//n�o � clon�vel!
		if(info == null)
			throw new Exception ("Informacao ausente!");
		
		if(this.primeiro == null)
		{
			this.primeiro = new No (info, null);
			this.ultimo = this.primeiro;
			
			return;
		}
		
		  if(this.existeAeroporto (info.getCodigo(), info.getNome()))
			  throw new Exception ("Aeroporto ja existente!");
		
		
		this.primeiro = new No (info, this.primeiro);
	}
	
	// o m�todo abaixo � usado na opera��o B para retornar 
	// um tipo aeroporto que conter� o n� da lista de aeroporto requisitado
	public Aeroporto getAeroporto (int indiceDaCidade) throws Exception
	{
		if(this.primeiro == null)
			throw new Exception ("Lista aeroporto vazia!");
		
		Aeroporto ret = null;
		No atual = this.primeiro;
		
		while(atual != null)
		{
			if(atual.getInfo().getCodigo() == indiceDaCidade)
			{
				ret = atual.getInfo();
				break;
			}
			
			atual = atual.getProx();
		}
		
		if(ret == null)
			throw new Exception("Aeroporto nao encontrado!");
		
		
		return ret;
	}
	
	
	// uso o m�todo abaixo para checar se o indice da cidade destino da inst�ncia do novo voo
	// existe, se sim ele retorna o nome do indice da cidade
	public String getCidade(int indiceDaCidade) throws Exception
	{
		if(this.primeiro == null)
			throw new Exception("Lista de aeroporto vazia!");
		
		
		String ret = null;
		No atual = this.primeiro;
		
		while(atual != null)
		{
			if(atual.getInfo().getCodigo() == indiceDaCidade)
			{
				ret = atual.getInfo().getNome();
				break;
			}
			
			atual = atual.getProx();
		}
		
		if(ret == null)
			throw new Exception("Cidade nao encontrada!");
		
		return ret;
	}
	
	
	
	// esse m�todo retorna um boolean para caso valor do novo voo
	// passado pelo usu�rio j� exista em algum outro aeroporto
	public boolean existeNumeroDoVooEmOutroAeroporto (int numeroDoVoo) throws Exception
	{
		if(numeroDoVoo <= 0)
			throw new Exception ("numero do voo incorreto!");
			
		if(this.primeiro == null)
			throw new Exception ("Lista de aeroportos vazia!");
			
		No atual = this.primeiro;
		
		ListaDeVoos listaVoos = null;
		
		while (atual != null)
		{
		    listaVoos = atual.getInfo().getListaDeVoos();
		  
			if(atual.getInfo().temVoo())
				if(listaVoos.existeVoo(numeroDoVoo))
				    return true;
				    
			atual = atual.getProx();
		}
		
		return false;
	}
	
	
	
	// vai retornar a lista de voos de um determinado aeroporto que tem o voo com esse numero de voo
	public ListaDeVoos getListaDeVoos (int numeroDoVoo) throws Exception
	{
		if(numeroDoVoo <= 0)
			throw new Exception ("numero do voo incorreto!");
		
		if(this.primeiro == null)
			throw new Exception ("Lista de aeroportos vazia!");
		
		ListaDeVoos ret = null;
		No atual = this.primeiro;
		
		while(atual != null)
		{
			if(atual.getInfo().getListaDeVoos().existeVoo(numeroDoVoo))
			{
				ret = atual.getInfo().getListaDeVoos();
				break;
			}
			
			atual = atual.getProx();
		}
		
		if(ret == null)
			throw new Exception("Voo nao existe em nenhum aeroporto!");
		
		return ret;
	}
	
	
	
	// retorna true se achou um aeroporto na lista com mesmo codigo ou mesmo nome do aeroporto
	// � usado no m�todo insira
	private boolean existeAeroporto (int codigo, String nome)
	{
		No atual = this.primeiro;
		
		while(atual != null)
		{
			if(atual.getInfo().getCodigo() == codigo) // codigo pode at� ser diferente
				return true;
			
			if(nome.equals(atual.getInfo().getNome())) // mas o nome tamb�m � importante verificar pra ver se o usu�rio colocou um nome j� existente
				return true;
			
			atual = atual.getProx();
		}
		
		return false;
	}
	

	
	@Override
	public String toString()
	{
		String ret = "";
		No atual = this.primeiro;
		
		while(atual != null)
		{
			ret += atual.getInfo();
			ret += "\n";
			atual = atual.getProx();
		}
		
		return ret += "\n-----------------------------------------\n";
	}
}
