
public class ListaDeVoos extends ListaSimplesDesordenada<voos>
{
	
	
	public void insiraVoos (voos info, Aeroporto seuAeroporto) throws Exception
	{
		if(info == null)
			throw new Exception("informacao ausente!");
		
		if(seuAeroporto == null)
			throw new Exception("Aeroporto ausente!");
		
		
		if(this.primeiro == null)
		{
			this.primeiro = new No(info, null);
			this.ultimo = this.primeiro;
			
			return;
		}
		
		if(info.getIndiceCidade() == seuAeroporto.getCodigo())
			throw new Exception("Codigo do voo igual a seu aeroporto!");
		
		No atual = this.primeiro;
		
		for(;;)
		{
			if(atual.getProx() == null)
				break;
			
			if(this.existeVoo(info.getNumeroVoo(), info.getIndiceCidade()))
				throw new Exception ("Voo ja existe!");
			
			atual = atual.getProx();
		}
		
		this.primeiro = new No(info, this.primeiro);
	}
	
	
	// irar retornar um boolean true caso esse numeroDoVoo passado
	// seja igual a algum outro dentro desse aeroporto
	// é utilizado na lista de aeroportos no método que verifica
	// se há algum voo com esse numeroDoVoo em algum outro aeroporto
	public boolean existeVoo (int numeroDoVoo) throws Exception
	{
		if(numeroDoVoo <= 0)
			throw new Exception ("numero do voo incorreto!");
			
		
		No atual = this.primeiro;
		
		while(atual != null)
		{
			if(atual.getInfo().getNumeroVoo() == numeroDoVoo)
				return true;
				
			atual = atual.getProx();
		}
		
		return false;
	}
	
	
	
	// o método abaixo impede o inserimento de um voo que contem
	// o mesmo numero do voo ou o mesmo indice da cidade que algum voo 
	// de sua lista
	private boolean existeVoo (int numeroDoVoo, int indiceDaCidade)
	{
		No atual = this.primeiro;
		
		while (atual != null)
		{
			
			if(atual.getInfo().getNumeroVoo() == numeroDoVoo) // cidade pode ser diferente mas o numero pode ser igual
				return true;
				
			if(atual.getInfo().getIndiceCidade() == indiceDaCidade) //numero pode ser diferente mas cidade pode ser igual
				return true;	
			
			atual = atual.getProx();
		}
		
		return false;
	}
	
	
	
	@Override
	public String toString()
	{
		String ret = "\n";
		
		No atual = this.primeiro;
		
		while(atual != null)
		{
			ret += atual.getInfo();
			ret += "\n";
			atual = atual.getProx();
		}
		
		return ret;
	}
	
	
	public void removaVoo (int numeroDoVoo) throws Exception
    {
        if (numeroDoVoo <= 0)
            throw new Exception ("Numero do voo incorreto!");

        if (this.primeiro==null/*&&this.ultimo==null*/)
            throw new Exception ("Lista vazia");

        if (numeroDoVoo == this.primeiro.getInfo().getNumeroVoo())
        {
            if (this.ultimo==this.primeiro)
                this.ultimo=null;

            this.primeiro = this.primeiro.getProx();

            return;
        }

        No atual=this.primeiro;

        for(;;) // FOR EVER (repete até Exception ou return)
        {
            if (atual.getProx()==null)
                throw new Exception ("Informacao inexistente");

            if (numeroDoVoo == atual.getProx().getInfo().getNumeroVoo())
            {
                if (this.ultimo==atual.getProx())
                    this.ultimo=atual;

                atual.setProx(atual.getProx().getProx());

                return;
            }

            atual=atual.getProx();
        }
    }
	
}
