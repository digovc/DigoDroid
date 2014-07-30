package com.digosofter.digodroid;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.widget.Toast;

import com.digosofter.digodroid.MensagemUsuario.EnmLingua;
import com.digosofter.digodroid.activity.ActMain;
import com.digosofter.digodroid.database.DataBase;
import com.digosofter.digodroid.database.DbTabela;
import com.digosofter.digodroid.erro.Erro;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class App extends Objeto {

  private static App i;

  public static App getI() {
    return i;
  }

  private ActMain _actMain;
  private Context _context;
  private int _intVersao;
  private List<MensagemUsuario> _lstObjMensagemUsuarioPadrao;
  private List<DbTabela> _lstTbl;
  private DataBase _objDataBasePrincipal;
  private Gson _objGson;
  private String _strVersaoExibicao;
  private DbTabela _tblSelecionada;

  public App() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      this.setI(this);

      // FIM A��ES
    } catch (Exception ex) {
    } finally {
    }
  }

  public ActMain getActMain() {
    return _actMain;
  }

  public Context getContext() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      _context = this.getActMain().getApplicationContext();

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(this.getStrMensagemUsuarioPadrao(101), ex);

    } finally {
    }

    return _context;
  }

  public int getIntVersao() {
    return _intVersao;
  }

  public abstract List<MensagemUsuario> getLstObjMensagemUsuario();

  private List<MensagemUsuario> getLstObjMensagemUsuarioPadrao() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      if (_lstObjMensagemUsuarioPadrao == null) {
        _lstObjMensagemUsuarioPadrao = new ArrayList<MensagemUsuario>();

        // Mensagens

        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario("Erro inesperado..", 0));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario(
            "Erro ao recuperar o IMEI do aparelho.", 100));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario(
            "Erro ao recuperar contexto do aplicativo.", 101));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario(
            "Erro ao recuperar banco de dados principal.", 102));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario(
            "Erro ao recuperar mensagem de usu�rio.", 103));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario(
            "Erro ao mostrar notifica��o na tela.", 104));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario(
            "Erro ao criar objeto do tipo 'FtpClient'.", 105));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario("Erro ao criar objeto do tipo 'Ftp'.",
            106));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario(
            "Erro ao criar objeto do tipo 'MensagemUsuario'.", 107));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario(
            "Erro ao criar objeto do tipo 'Objeto'.", 108));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario("Erro ao arredondar valor.", 109));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario("Erro ao recuperar data atual.", 110));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario("Erro ao gerar cor aleat�ria.", 110));
        _lstObjMensagemUsuarioPadrao
        .add(new MensagemUsuario("Erro ao gerar texto aleat�rio.", 111));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario("Erro ao gerar MD5.", 112));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario("Erro ao adicionar fragmento � tela.",
            113));
        _lstObjMensagemUsuarioPadrao
        .add(new MensagemUsuario("Erro ao montar layout da tela.", 114));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario("Erro ao fechar tela.", 115));
        _lstObjMensagemUsuarioPadrao
        .add(new MensagemUsuario("Erro ao setar eventos da tela.", 116));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario("Erro ao criar tela.", 117));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario(
            "Erro ao criar objeto do tipo 'DataBase'.", 118));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario("Erro ao executar SQL.", 119));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario(
            "Erro ao criar objeto do tipo 'DbColuna'.", 120));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario(
            "Erro ao criar objeto do tipo 'DbFiltro'.", 121));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario(
            "Erro ao criar objeto do tipo 'dbTabela'.", 122));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario("Erro ao abrir tela.", 123));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario(
            "Erro ao buscar registro no banco de dados.", 124));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario(
            "Erro ao criar tabela no banco de dados.", 125));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario("Erro ao excluir registro.", 126));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario(
            "Erro ao verificar se tabela existe no banco de dados.", 127));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario(
            "Erro ao recuperar tabela no banco de dados.", 128));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario(
            "Erro ao inserir registro no banco de dados.", 129));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario(
            "Erro ao inserir registro aleat�rio no banco de dados.", 130));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario(
            "Erro ao zerar valores das colunas no registro.", 131));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario(
            "Erro ao verificar filtro no item da lista.", 132));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario(
            "Erro ao criar objeto do tipo 'TblCliente'.", 133));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario(
            "Erro ao criar objeto do tipo 'TblMain'.", 134));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario(
            "Erro ao criar objeto do tipo 'TblPessoa'.", 135));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario(
            "Erro ao criar objeto do tipo 'TblUsuario'.", 136));
        _lstObjMensagemUsuarioPadrao.add(new MensagemUsuario(
            "Erro ao criar objeto do tipo 'ConfigItem'.", 137));

        // Fim mensagens

      }

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(this.getStrMensagemUsuarioPadrao(0), ex);

    } finally {
    }

    return _lstObjMensagemUsuarioPadrao;
  }

  public List<DbTabela> getLstTbl() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      if (_lstTbl == null) {
        _lstTbl = new ArrayList<DbTabela>();
      }

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _lstTbl;
  }

  public DataBase getObjDataBasePrincipal() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      if (_objDataBasePrincipal == null) {
        _objDataBasePrincipal = new DataBase(this.getStrNomeSimplificado(), this.getActMain()
            .getApplicationContext());
      }

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(102), ex);

    } finally {
    }

    return _objDataBasePrincipal;
  }

  public Gson getObjGson() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      if (_objGson != null) {
        return _objGson;
      }

      _objGson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

      // FIM A��ES
    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);

    } finally {
    }

    return _objGson;
  }

  public String getStrMensagemUsuario(int intId) {
    return this.getStrMensagemUsuario(intId, EnmLingua.PORTUGUES, false);
  }

  public String getStrMensagemUsuario(int intId, EnmLingua enmLingua, boolean booMensagemPadrao) {
    // VARI�VEIS

    List<MensagemUsuario> lstObjMensagemUsuarioTemp;
    String strMensagemUsuarioResultado = Utils.STRING_VAZIA;

    // FIM VARI�VEIS
    try {
      // A��ES

      if (booMensagemPadrao) {
        lstObjMensagemUsuarioTemp = this.getLstObjMensagemUsuarioPadrao();
      } else {
        lstObjMensagemUsuarioTemp = this.getLstObjMensagemUsuario();
      }

      for (MensagemUsuario objMensagemUsuario : lstObjMensagemUsuarioTemp) {
        if (objMensagemUsuario.getIntId() == intId
            && objMensagemUsuario.getEnmLingua() == enmLingua) {
          strMensagemUsuarioResultado = objMensagemUsuario.getStrTexto();
          break;
        }
      }

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(this.getStrTextoPadrao(103), ex);

    } finally {
    }

    return strMensagemUsuarioResultado;
  }

  public String getStrMensagemUsuarioPadrao(int intId) {
    return this.getStrMensagemUsuario(intId, EnmLingua.PORTUGUES, true);
  }

  public String getStrTexto(int intId) {
    return this.getStrMensagemUsuario(intId);
  }

  public String getStrTextoPadrao(int intId) {
    return this.getStrMensagemUsuarioPadrao(intId);
  }

  public String getStrVersaoExibicao() {
    return _strVersaoExibicao;
  }

  public DbTabela getTblSelecionada() {
    return _tblSelecionada;
  }

  /**
   * Limpa o cache com a lista de de itens das telas de consultas das tabelas do
   * appp.
   */
  public void limparTblListaConsulta() {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      for (DbTabela tbl : this.getLstTbl()) {
        tbl.limparListaConsulta();
      }

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  public void mostrarNoficacao(final String strMensagem) {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      this.getActMain().runOnUiThread(new Runnable() {

        @Override
        public void run() {
          // VARI�VEIS

          int intTempo = Toast.LENGTH_SHORT;

          // FIM VARI�VEIS
          try {
            // A��ES

            if (strMensagem.length() > 25) {
              intTempo = Toast.LENGTH_LONG;
            }

            Toast.makeText(App.this.getContext(), strMensagem, intTempo).show();
            ;

            // FIM A��ES
          } catch (Exception ex) {

            new Erro(App.getI().getStrTextoPadrao(0), ex);

          } finally {
          }
        }
      });

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(this.getStrTextoPadrao(104), ex);

    } finally {
      // LIMPAR VARI�VEIS
      // FIM LIMPAR VARI�VEIS
    }
  }

  public void setActMain(ActMain actMain) {
    _actMain = actMain;
  }

  private void setI(App _i) {
    // VARI�VEIS
    // FIM VARI�VEIS
    try {
      // A��ES

      if (i == null) {
        i = _i;
      }

      // FIM A��ES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  public void setIntVersao(int intVersao) {
    _intVersao = intVersao;
  }

  public void setLstTbl(List<DbTabela> lstTbl) {
    _lstTbl = lstTbl;
  }

  public void setStrVersaoExibicao(String strVersaoExibicao) {
    _strVersaoExibicao = strVersaoExibicao;
  }

  public void setTblSelecionada(DbTabela tblSelecionada) {
    _tblSelecionada = tblSelecionada;
  }

}
