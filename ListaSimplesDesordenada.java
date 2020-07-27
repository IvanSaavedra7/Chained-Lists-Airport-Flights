import java.lang.reflect.*;

public class ListaSimplesDesordenada <X>
{
    protected class No
    {
        private X  info;
        private No prox;

        public No (X i, No p)
        {
            this.info = i;
            this.prox = p;
        }

        public No (X i)
        {
            this.info = i;
            this.prox = null;
        }

        public X getInfo ()
        {
            return this.info;
        }

        public No getProx ()
        {
            return this.prox;
        }

        public void setInfo (X i)
        {
            this.info = i;
        }

        public void setProx (No p)
        {
            this.prox = p;
        }
    } //fim da classe No

    
    protected No primeiro, ultimo;
    
    public ListaSimplesDesordenada ()
    {
		this.primeiro = this.ultimo = null;
	}

    
    public boolean isVazia ()
    {
        return this.primeiro==null/*&&this.ultimo==null*/;
    }
    
   
    public boolean tem (X i) throws Exception
    {
		if (i==null)
		    throw new Exception ("Informacao ausente");
		
        No atual=this.primeiro;

        while (atual!=null)
        {
            if (i.equals(atual.getInfo()))
                return true;
                
            atual = atual.getProx();
        }
        
        return false;
    }

   
    public int getQtd ()
    {
        No  atual=this.primeiro;
        int ret  =0;

        while (atual!=null)
        {
            ret++;                
            atual = atual.getProx();
        }
        
        return ret;
    }
    
    
    private X meuCloneDeX (X x)
    {
      //return (X)x.clone();

        X ret=null;

        try
        {
            Class<?> classe = x.getClass();
            Class<?>[] tiposDosParms = null; // null pq clone nao tem parametros
            Method metodo = classe.getMethod("clone",tiposDosParms);
            Object[] parms = null; // null pq clone nao tem parametros
            ret = (X)metodo.invoke(x,parms);
        }
        catch (Exception erro)
        {} // pq sei que estou chamando clone de um objeto que é Cloneable e, portanto, nao há risco do método não existir ou de ser chamado com parametros errado

        return ret;
    }
    
    
    public void insira (X i) throws Exception
    {
        if (i==null)
            throw new Exception ("Informacao ausente");

        X inserir=null;
        if (i instanceof Cloneable)
            inserir = meuCloneDeX(i);
        else
            inserir = i;
            
        this.primeiro = new No (inserir,this.primeiro);

        if (this.ultimo==null)
            this.ultimo=this.primeiro;
    }


    public X getDoInicio () throws Exception
    {
        if (this.primeiro==null/*&&this.fim==null)*/)
            throw new Exception ("Nada a obter");

        X ret = this.primeiro.getInfo();
        if (ret instanceof Cloneable)
            ret = meuCloneDeX (ret);
            
        return ret;
    }

    
    public X getDoFim () throws Exception
    {
        if (this.primeiro==null/*&&this.ultimo==null)*/)
            throw new Exception ("Nada a obter");

        X ret = this.ultimo.getInfo();
        if (ret instanceof Cloneable)
            ret = meuCloneDeX (ret);
            
        return ret;
    }

    
    public void removaDoInicio () throws Exception
    {
        if (this.primeiro==null /*&& this.ultimo==null*/)
            throw new Exception ("Nada a remover");

        if (this.primeiro==this.ultimo) //so 1 elemento
        {
            this.primeiro=null;
            this.ultimo  =null;
            return;
        }

        this.primeiro = this.primeiro.getProx();
    }
    
    
    public void remova (X i) throws Exception
    {
        if (i==null)
            throw new Exception ("Informacao ausente");

        if (this.primeiro==null/*&&this.ultimo==null*/)
            throw new Exception ("Lista vazia");

        if (i.equals(this.primeiro.getInfo()))
        {
            if (this.ultimo==this.primeiro)
                this.ultimo=null;

            this.primeiro=this.primeiro.getProx();

            return;
        }

        No atual=this.primeiro;

        for(;;) // FOR EVER (repete até Exception ou return)
        {
            if (atual.getProx()==null)
                throw new Exception ("Informacao inexistente");

            if (i.equals(atual.getProx().getInfo()))
            {
                if (this.ultimo==atual.getProx())
                    this.ultimo=atual;

                atual.setProx(atual.getProx().getProx());

                return;
            }

            atual=atual.getProx();
        }
    }
	
	
    public String toString ()
    {
        String ret="[";

        No atual=this.primeiro;

        while (atual!=null)
        {
            ret=ret+atual.getInfo();

            if (atual!=this.ultimo)
                ret=ret+",";

            atual=atual.getProx();
        }

        return ret+"]";
    }
    
    
    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

        if (this.getClass()!=obj.getClass())
            return false;

        ListaSimplesDesordenada<X> lista =
       (ListaSimplesDesordenada<X>)obj;

        No atualThis =this .primeiro;
        No atualLista=lista.primeiro;

        while (atualThis!=null && atualLista!=null)
        {
            if (!atualThis.getInfo().equals(atualLista.getInfo()))
                return false;

            atualThis  = atualThis .getProx();
            atualLista = atualLista.getProx();
        }

        if (atualThis!=null  /* && atualLista==null */)
            return false;

        if (atualLista!=null /* && atualThis ==null */)
            return false;

        // atualThis==null && atualLista==null
        return true;
    }

   
    public int hashCode ()
    {
        final int PRIMO = 13; // qualquer número primo serve
        
        int ret=666; // qualquer inteiro positivo serve

        for (No atual=this.primeiro;
             atual!=null;
             atual=atual.getProx())
             ret = 17*ret + atual.getInfo().hashCode();

        if (ret<0) ret = -ret;

        return ret;
    }

    
    public ListaSimplesDesordenada (ListaSimplesDesordenada<X> modelo) throws Exception
    {
        if (modelo==null)
            throw new Exception ("Modelo ausente");

        if (modelo.primeiro==null)
            return; // sai do construtor, pq this.primeiro ja é null

        this.primeiro = new No (modelo.primeiro.getInfo());

        No atualDoThis   = this  .primeiro;
        No atualDoModelo = modelo.primeiro.getProx();

        while (atualDoModelo!=null)
        {
            atualDoThis.setProx (new No (atualDoModelo.getInfo()));
            atualDoThis   = atualDoThis  .getProx ();
            atualDoModelo = atualDoModelo.getProx ();
        }

        this.ultimo = atualDoThis;
    }

   
    public Object clone ()
    {
        ListaSimplesDesordenada<X> ret=null;

        try
        {
            ret = new ListaSimplesDesordenada (this);
        }
        catch (Exception erro)
        {} // sei que this NUNCA é null e o contrutor de copia da erro quando seu parametro é null

        return ret;
    }
}
