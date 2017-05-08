package com.digosofter.digodroid.componente;

import android.util.AttributeSet;

public interface IControleMain
{
  /**
   * Responsável por fazer os ajustes finais antes de desenhar este controle na tela. Este método deve ser chamado dentro do método {@link
   * android.view.View#onMeasure(int, int)}.
   */
  void finalizar();

  /**
   * Chamado de dentro do método {@link #iniciar(AttributeSet)} para fazer a inicialização das propriedades que foram definidas no arquivo de layout
   * XML deste controle.
   *
   * @param ats
   */
  void inicializar(AttributeSet ats);

  /**
   * Chamado de dentro do método {@link #iniciar(AttributeSet)} para fazer a inicialização das propriedades deste controle. <b>Atenção:</b> Neste
   * momento do ciclo os parâmetros de layout do controle ainda não foram carregados, portanto a propriedade {@link
   * android.view.ViewGroup.LayoutParams} não está inicializada.
   */
  void inicializar();

  /**
   * Este método deve ser chamado dentro de todos os contrutores do controles que implementam esta interface. Isso garante que o ciclo seja realizado.
   * Dentro da implementação deste método os demais métodos do ciclo devem ser chamado, exemplo:
   * <p/>
   * {@link #inicializar(AttributeSet)} {@link #inicializar()} {@link #montarLayout()} {@link #setEventos()}
   *
   * @param ats Possíveis atributos que serão passados pelo API do android caso este controle tenha sido declarado em um arquivo de layout num XML.
   */
  void iniciar(AttributeSet ats);

  /**
   * Chamado de dentro do método {@link #iniciar(AttributeSet)} para montar o layout dos controles filhos deste, caso seja um controle complexo.
   */
  void montarLayout();

  /**
   * Chamado de dentro do método {@link #iniciar(AttributeSet)} para configurar os eventos deste controle e de seus filhos.
   */
  void setEventos();
}