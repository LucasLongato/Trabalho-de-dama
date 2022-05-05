package com.mycompany.damasp2;

public class Tabuleiro {

    // Letras para as colunas
    private static final String ALFABETO = "0ABCDEFGHIJKLMNOPQRSTUVWXYZ ";

    // Criando o Tabuleiro
    private String[][] matriz;
    
    private int tamanho;
    private int contadorBrancas,contadorPretas;

    //Percorrendo sobre a matriz no caso pelo "Tabuleiro", assim o Local vazio e preenchida com " - "
    public Tabuleiro(int tamanho) {
        // Aumenta em um o tamanho do tabuleiro para considerar as linhas e colunas
        tamanho++;
        
        // Salva o tamanho do tabuleiro criado.
        this.tamanho = tamanho;
        
        // cria uma nova matriz de tabuleiro 
        this.matriz = new String[tamanho][tamanho];
        
        // Inserindo dois espaços vazios no primeiro valor
        this.matriz[0][0] = "   ";

        // Preenchendo as colunas0
        for(int coluna = 1; coluna < this.tamanho; coluna++) {
            this.matriz[0][coluna] = String.valueOf(ALFABETO.charAt(coluna)) + " ";
        }
        
        // Preenchendo as linhas
        for(int linha = 1; linha < this.tamanho; linha++) {
            String numeroDaLinha = "";


            if (linha < 9) {
                numeroDaLinha += "0";     
            }
            
            // Concatenando o valor da linha e mais um espaço em branco para não ficar tudo grudado.
            numeroDaLinha += String.valueOf(linha) + " ";

            this.matriz[linha][0] = numeroDaLinha;
        }
        
        // Preenche todas as posições com -
        for (int linha = 1; linha < this.tamanho; linha++) {
            for (int coluna = 1; coluna < this.tamanho; coluna++) {
               this.matriz[linha][coluna] = "- ";
            }
        }
        

        if (this.tamanho == 9) {
            int linhasDePecas = 3;
            
            // Inserir peças brancas
            for (int linha = 1; linha <= linhasDePecas; linha++) {
                int coluna = 0;
                // Se a linha for par, a coluna começa da 2 em diante.
                // Se não (ímpar), a coluna começa da 1 em diante.
                if (linha % 2 == 0)
                    coluna = 2;
                else 
                    coluna = 1;
                    
                while (coluna < this.tamanho) {
                        this.matriz[linha][coluna] = "@ ";
                    coluna = coluna + 2;
                        
                }
            }
            
            // Inserir peças pretas
            for (int linha = this.tamanho - 1; linha >= (this.tamanho - linhasDePecas); linha--) {
                int coluna = 0;
                // Se a linha for par, a coluna começa da 2 em diante.
                // Se não (ímpar), a coluna começa da 1 em diante.
                if (linha % 2 == 0)
                    coluna = 2;
                else 
                    coluna = 1;
                    
                while (coluna < this.tamanho) {
                    this.matriz[linha][coluna] = "0 ";
                    coluna = coluna + 2;
                }
            }
        }
        this.contadorBrancas = 12;
        this.contadorPretas = 12;
    }
    public void subContadorBrancas() {
    	this.contadorBrancas--;
    }
    
    public void subContadorPretas() {
    	this.contadorPretas--;
    }
    
    public int getContadorPretas() {
    	return this.contadorPretas;
    }
    
    public int getContadorBrancas() {
    	return this.contadorBrancas;
    }
    
    
        
    //Imprimindo o Tabuleiro com letras informando linha e numero colunas.
    public void imprimirTabuleiro() {
        this.geradama();
        // Imprimindo os outros valores
        for (int linha = 0 ; linha < tamanho; linha++) {
            for (int coluna = 0; coluna < tamanho ; coluna++) {
                System.out.print(this.matriz[linha][coluna]);
            }
            
            System.out.println("");
        } 
        
        System.out.println("==========================================");
    } // fim imprimirTabuleiro
    
    
    public boolean estaDentro(int coluna, int linha) {
    	return coluna > 0 && coluna <= 8 && linha > 0 && linha <= 8;
    }
    
    public boolean estaDentro1(int coluna, int linha) {
    	return coluna-- > 0 && coluna++ <= 8 && linha-- > 0 && linha++ <= 8;
    }
    
    // Move a peça no tabuleiro a partir de coordenadas
 // Move a peça no tabuleiro a partir de coordenadas
    public boolean moverPeca(String coordenadas) {
        this.geradama();
        // Divide as coordenadas pela vírgula. A posição 0 contém as coordenadas anteriores e a posição 1 as novas coordenadas
        boolean resultado = false;
    	String[] vetorCoordenadas = coordenadas.split(",");
        

        // Garante que as coordenadas terão as letras em maiúsculo. Importantes para comparar com o alfabeto.
        String coordenadaAnterior = vetorCoordenadas[0].toUpperCase();
        String novaCoordenada = vetorCoordenadas[1].toUpperCase();

        // Recupera a linha e coluna anterior
        int linhaAnterior = getLinha(coordenadaAnterior);
        int colunaAnterior = getColuna(coordenadaAnterior);

        // Recupera a linha e coluna nova
        int novaLinha = getLinha(novaCoordenada);
        int novaColuna = getColuna(novaCoordenada);
        if((novaLinha==linhaAnterior-1 && novaColuna==colunaAnterior-1 || novaLinha==linhaAnterior-1 && novaColuna==colunaAnterior+1)
        		
        		&& estaDentro(novaColuna, novaLinha)
        		){
            // Verifica se a nova coordenada possue peça
        	if(this.matriz[novaLinha][novaColuna] == "@ ") {
            	System.out.println("Possue pessa inimigo");
            	if(novaColuna > colunaAnterior) {
            		// Verifica se tem uma pessoa atraz na direita
            		if(this.matriz[novaLinha-1][novaColuna+1] == "@ " && estaDentro(novaColuna +1, novaLinha-1) ) {
            			resultado = true;
            			return resultado;
            		} else {
            		
            				// Recupera a peça que está na posição
            		
                    		String peca = this.matriz[linhaAnterior][colunaAnterior];

                    		// Substitui a posição por "-":
                    		this.matriz[linhaAnterior][colunaAnterior] = "- ";

                    		// Coloca a peça na nova linha e coluna
                    		this.matriz[novaLinha][novaColuna] = "- ";
                    		this.matriz[novaLinha-1][novaColuna+1]= peca;
                    		this.subContadorPretas();
            		}
            	} else {
            		if(this.matriz[novaLinha-1][novaColuna-1] == "@ " && estaDentro(novaColuna-1, novaLinha-1)) {
            			resultado = true;
            			return resultado;
            		} else {
            			// Recupera a peça que está na posição
                    	String peca = this.matriz[linhaAnterior][colunaAnterior];

                    	// Substitui a posição por "-":
                    	this.matriz[linhaAnterior][colunaAnterior] = "- ";

                    	// Coloca a peça na nova linha e coluna
                    	this.matriz[novaLinha][novaColuna] = "- ";
                    	this.matriz[novaLinha-1][novaColuna-1]= peca;
                    	this.subContadorPretas();
            		}
            	}
            } else {
            
            	// Recupera a peça que está na posição
            	String peca = this.matriz[linhaAnterior][colunaAnterior];

            	// Substitui a posição por "-":
            	this.matriz[linhaAnterior][colunaAnterior] = "- ";

            	// Coloca a peça na nova linha e coluna
            	this.matriz[novaLinha][novaColuna] = peca;
            }
        	
        } else {
        	System.out.println("coluna: " + novaColuna + " " + "linha: " + novaLinha);
        	resultado = true;
        }

        // Imprime novamente o tabuleiro com as novas posições
        return resultado;
    }
    
    public boolean moverPeca1(String coordenadas) {
        this.geradama();
        // Divide as coordenadas pela vírgula. A posição 0 contém as coordenadas anteriores e a posição 1 as novas coordenadas
        boolean resultado = false;
    	String[] vetorCoordenadas = coordenadas.split(",");
        

        // Garante que as coordenadas terão as letras em maiúsculo. Importantes para comparar com o alfabeto.
        String coordenadaAnterior = vetorCoordenadas[0].toUpperCase();
        String novaCoordenada = vetorCoordenadas[1].toUpperCase();

        // Recupera a linha e coluna anterior
        int linhaAnterior = getLinha(coordenadaAnterior);
        int colunaAnterior = getColuna(coordenadaAnterior);

        // Recupera a linha e coluna nova
        int novaLinha = getLinha(novaCoordenada);
        int novaColuna = getColuna(novaCoordenada);
        if((novaLinha==linhaAnterior+1 && novaColuna==colunaAnterior-1 || novaLinha==linhaAnterior+1 && novaColuna==colunaAnterior+1)
        		
        		&& estaDentro(novaColuna, novaLinha)){
            // Verifica se a nova coordenada possue peça
        	if(this.matriz[novaLinha][novaColuna] == "0 ") {
            	System.out.println("Possue pessa inimigo");
            	if(novaColuna > colunaAnterior) {
            		// Verifica se tem uma pessoa atraz na direita
            		if((this.matriz[novaLinha+1][novaColuna+1] == "0 ") || (this.matriz[novaLinha+1][novaColuna-1]!= "- ") ) {
            			resultado = true;
            			return resultado;
            		} else {
            			
            				// Recupera a peça que está na posição
            		
                    		String peca = this.matriz[linhaAnterior][colunaAnterior];

                    		// Substitui a posição por "-":
                    		this.matriz[linhaAnterior][colunaAnterior] = "- ";

                    		// Coloca a peça na nova linha e coluna
                    		this.matriz[novaLinha][novaColuna] = "- ";
                    		this.matriz[novaLinha+1][novaColuna+1]= peca;
                    		this.subContadorPretas();
            		}
            	} else {
            		if((this.matriz[novaLinha+1][novaColuna-1] == "0 ") || (this.matriz[novaLinha+1][novaColuna-1]!= "- ")) {
            			resultado = true;
            			return resultado;
            		} else {
            			// Recupera a peça que está na posição
                    	String peca = this.matriz[linhaAnterior][colunaAnterior];

                    	// Substitui a posição por "-":
                    	this.matriz[linhaAnterior][colunaAnterior] = "- ";

                    	// Coloca a peça na nova linha e coluna
                    	this.matriz[novaLinha][novaColuna] = "- ";
                    	this.matriz[novaLinha+1][novaColuna-1]= peca;
                    	this.subContadorPretas();
            		}
            	}
            } else {
            
            	// Recupera a peça que está na posição
            	String peca = this.matriz[linhaAnterior][colunaAnterior];

            	// Substitui a posição por "-":
            	this.matriz[linhaAnterior][colunaAnterior] = "- ";

            	// Coloca a peça na nova linha e coluna
            	this.matriz[novaLinha][novaColuna] = peca;
            }
        	
        } else {
        	System.out.println("coluna: " + novaColuna + " " + "linha: " + novaLinha);
        	resultado = true;
        }

        // Imprime novamente o tabuleiro com as novas posições
        return resultado;
    }

    public void geradama(){
        for(int i=1;i<=8;i++){
            if(this.matriz[1][i]=="@ "){
                this.matriz[1][i]="O ";
            }
            if(this.matriz[8][i]=="@ "){
                this.matriz[8][i]="G ";
            }
        }
    }
    
    // Recupera a linha de uma coordenada
    private int getLinha(String coordenada) {
    	return Integer.valueOf(coordenada.substring(0, 1));
    }
    
    // Recupera o índice da coluna em uma coordenada
    private int getColuna(String coordenada) {
    	return ALFABETO.indexOf(coordenada.substring(1, 2));
    }
}
