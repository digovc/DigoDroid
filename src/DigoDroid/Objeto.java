package DigoDroid;

public abstract class Objeto {
	/* CONSTANTES */

	/* ATRIBUTOS */
	private String strDescricao;
	private String strNome;
	private String strNomeVizualizacao;
	
	/* MODIFICADORES */
	public String getStrDescricao() {
		return strDescricao;
	}
	public void setStrDescricao(String strDescricao) {
		this.strDescricao = strDescricao;
	}
	public String getStrNome() {
		return strNome;
	}
	public void setStrNome(String strNome) {
		this.strNome = strNome;
	}
	public String getStrNomeVizualizacao() {
		if (this.strNomeVizualizacao.equals(Ultis.STRING_NULA)) {
			return this.strNome;
		}
		return strNomeVizualizacao;
	}
	public void setStrNomeVizualizacao(String strNomeVizualizacao) {
		this.strNomeVizualizacao = strNomeVizualizacao;
	}

	/* CONSTRUTORES */

	/* MÉTODOS */
}
