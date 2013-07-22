package DigoDroid;

import android.app.Activity;

public class App extends Objeto {
	/* CONSTANTES */

	/* ATRIBUTOS */
	private Activity actMain;
	private boolean booBeta = true;
	private int intVersao;
	private int intVersaoSub;
	private int intVersaoBug;

	/* MODIFICADORES */
	public Activity getActMain() {
		return actMain;
	}

	public void setActMain(Activity actMain) {
		this.actMain = actMain;
//		this.actMain.setTitle(this.getStrNomeVizualizacao());
	}

	public boolean isBooBeta() {
		return booBeta;
	}

	public void setBooBeta(boolean booBeta) {
		this.booBeta = booBeta;
	}

	public int getIntVersao() {
		return intVersao;
	}

	public void setIntVersao(int intVersao) {
		this.intVersao = intVersao;
	}

	public int getIntVersaoSub() {
		return intVersaoSub;
	}

	public void setIntVersaoSub(int intVersaoSub) {
		this.intVersaoSub = intVersaoSub;
	}

	public int getIntVersaoBug() {
		return intVersaoBug;
	}

	public void setIntVersaoBug(int intVersaoBug) {
		this.intVersaoBug = intVersaoBug;
	}

	/* CONSTRUTORES */

	/* MÉTODOS */

}
