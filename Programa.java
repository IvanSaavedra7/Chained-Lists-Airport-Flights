
public class Programa {
	public static void main(String[] args) {
		try
		{
			
			//// INSERINDOOS AEROPORTOS INICIAIS
			
			ListaDeAeroportos lis;
			lis = new ListaDeAeroportos();
			
			ListaDeVoos listaVooCNF;
			listaVooCNF = new ListaDeVoos();
			
			ListaDeVoos listaVoosGIG;
			listaVoosGIG = new ListaDeVoos();
			
			ListaDeVoos listaVoosSSA;
			listaVoosSSA = new ListaDeVoos();
			
			ListaDeVoos listaVoosGRU;
			listaVoosGRU = new ListaDeVoos();
			
			ListaDeVoos listaVoosBSB;
			listaVoosBSB = new ListaDeVoos();
			
			
			Aeroporto aeroportoCNF = new Aeroporto(1, "Belo Horizonte", listaVooCNF,  "CNF");
			Aeroporto aeroportoGIG = new Aeroporto(2, "Rio de Janeiro", listaVoosGIG, "GIG");
			
			Aeroporto aeroportoSSA = new Aeroporto(3, "Salvador"	  , listaVoosSSA, "SSA");
			Aeroporto aeroportoGRU = new Aeroporto(4, "S�o paulo"	  ,listaVoosGRU,  "GRU");
			
			Aeroporto aeroportoBSB = new Aeroporto(5, "Bras�lia"		  ,listaVoosBSB,  "BSB");
			
			
			lis.insira(aeroportoCNF);
			lis.insira(aeroportoGIG);
			
			lis.insira(aeroportoSSA);
			lis.insira(aeroportoGRU);
			
			lis.insira(aeroportoBSB);
			
			listaVooCNF.insiraVoos (new voos(5,666,lis ), aeroportoCNF );
			
			listaVooCNF.insiraVoos (new voos(2,777,lis ), aeroportoCNF );
			
        	Character continuar = null; // PARAMETRO PARA DO PAINEL DE SELECAO

			do
			{
				System.out.println("+------------------------------ +");
				System.out.println("| SISTEMA ADMINISTRADOR DE V�OS |");
				System.out.println("+------------------------------ +");
				System.out.println();
				System.out.println("[A] - Listagem na tela de todos os v�os");
				System.out.println("[B] - Listagem na tela dos v�os de um determinado aeroporto");
				System.out.println("[C] - Cadastre de um novo aeroporto");
				System.out.println("[D] - Cadastre um v�o");
				System.out.println("[E] - Remova um v�o");
				System.out.println();
				System.out.println("[X] - FINALIZAR PROGRAMA");
				System.out.println();
				System.out.print("SELECIONE UMAS DAS OP��ES: ");

		
				// FOR LOOP PARA A ANTA DIGITAR CERTO A OP��O
				for (;;) {
					try {
						continuar = Character.toUpperCase(Teclado.getUmChar());
						if (continuar != 'A' && continuar != 'B' && continuar != 'C' && continuar != 'D'
								&& continuar != 'E' && continuar != 'X')
							System.err.print("OPCAO INVALIDA! Tente novamente: ");
						else
							break;
					} catch (Exception erro) {
						System.err.print("Opcao invalida! Tente novamente: ");
					}
				}
				
				//OPERACAO A DE EXIBIR VOOS
				if (continuar == 'A') {
					try {
						System.out.println(lis);
					} catch (Exception erro) {
						System.err.print(erro);
					}
				}
				
				//OPERACAO B DE EXIBIR VOOS DE UM AEROPORTO ESPECIFICO
				if(continuar == 'B') {
					try
					{
						System.out.print("Digite o codigo do aeroporto: ");
						int codAeroporto = Teclado.getUmInt();

						Aeroporto aeroportoEscolhido = lis.getAeroporto(codAeroporto);
						System.out.println(aeroportoEscolhido);
					}
					catch(Exception erro)
					{
						System.err.println(erro);
					}
				}
				
				
				//OPERACAO C PARA CADASTRA AEROPORTO
				if (continuar == 'C') {
					
					try {
						ListaDeVoos listaVoos;
						listaVoos = new ListaDeVoos();

						System.out.println("[Cadastro de um novo aeroporto]");
						System.out.println("\nPor favor digite os seguintes dados ");
						
						System.out.print("Codigo do novo aeroporto: ");
						int codigo = Teclado.getUmInt();
						
						System.out.print("Nome do estado do aeroporto: ");
						String nome = Teclado.getUmString();
						
						System.out.print("\nSigla do aeroporto: ");
						String sigla = Teclado.getUmString();
						
						lis.insira(new Aeroporto(codigo, nome, listaVoos, sigla));
						System.out.println("Cadastro realizado com sucesso!");
					} catch (Exception erro) {
						System.err.println(erro);
					}
				}
				
				
				//OPERA��O D PARA CADASTRA VOO NOVO

				if(continuar == 'D')
				{
					try
					{
						System.out.println("[Cadastro do voo]");
						System.out.print("\nDigite o c�digo do aeoporto a ser inserido o voo: ");
						int codigoAeroporto = Teclado.getUmInt();
						
						Aeroporto aero = lis.getAeroporto (codigoAeroporto);
						ListaDeVoos listaVooDoAeroporto = aero.getListaDeVoos();
						
						
						System.out.print("\nDigite o numero da cidade de destino:");
						int indiceCidadeDestino = Teclado.getUmInt();
						
						System.out.print("\nDigite o codigo do novo voo: ");
						int numero = Teclado.getUmInt();
						
						if(!lis.existeNumeroDoVooEmOutroAeroporto (numero))
						{
							listaVooDoAeroporto.insiraVoos(new voos (indiceCidadeDestino,numero,lis), aero);
						}
						else
						  System.err.println ("Esse numero do voo ja existe em outro aeroporto!");
					}
					catch(Exception erro)
					{
						System.err.println(erro+"Pressione [ENTER] para voltar");
					}
				}
				
				
				//OPERA��O E PARA REMOVER VOO
				if(continuar == 'E')
				{
					try
					{
						System.out.println("[REMO��O DO VOO]");
						
						System.out.print("\nDigite o numero do voo para excluir: ");
						int numeroDoVoo = Teclado.getUmInt();
						
						ListaDeVoos listaDoVooEscolhida = lis.getListaDeVoos (numeroDoVoo);
						
						listaDoVooEscolhida.removaVoo(numeroDoVoo);
						System.out.println("Exclus�o realizado com sucesso!");
					}
					catch(Exception erro)
					{
						System.err.println(erro+"Pressione [ENTER] para voltar");
					}
				}
				 
				// DEIXARA O ENTER SEMPRE QUE O CONTINUAR NAO FOR X
				if (continuar != 'X') {
					System.out.println("\nPressione [Enter] para continuar!");
					Teclado.getUmString();
				}
			} while (continuar != 'X');
		} 
		catch (Exception erro) {
			System.err.println(erro);
		}
		
	}
	
}
