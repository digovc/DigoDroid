package com.digosofter.digodroid;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.widget.Toast;

import com.digosofter.digodroid.MsgUsuario.EnmLingua;
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
  private Context _cnt;
  private int _intVersao;
  private List<MsgUsuario> _lstMsgUsuarioPadrao;
  private List<DbTabela> _lstTbl;
  private DataBase _objDbPrincipal;
  private Gson _objGson;
  private String _strVersao;
  private DbTabela _tblSelec;

  public App() {
    try {

      this.setI(this);

    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);

    } finally {
    }
  }

  public ActMain getActMain() {
    return _actMain;
  }

  public Context getCnt() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      _cnt = this.getActMain().getApplicationContext();

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(this.getStrMsgUsuarioPadrao(101), ex);

    } finally {
    }

    return _cnt;
  }

  public int getIntVersao() {
    return _intVersao;
  }

  public abstract List<MsgUsuario> getLstMsgUsuario();

  private List<MsgUsuario> getLstMsgUsuarioPadrao() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (_lstMsgUsuarioPadrao != null) {
        return _lstMsgUsuarioPadrao;
      }

      _lstMsgUsuarioPadrao = new ArrayList<MsgUsuario>();

      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro inesperado..", 0));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao recuperar o IMEI do aparelho.", 100));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao recuperar contexto do aplicativo.", 101));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao recuperar banco de dados principal.", 102));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao recuperar mensagem de usuário.", 103));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao mostrar notificação na tela.", 104));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao criar objeto do tipo 'FtpClient'.", 105));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao criar objeto do tipo 'Ftp'.", 106));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao criar objeto do tipo 'MensagemUsuario'.",
          107));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao criar objeto do tipo 'Objeto'.", 108));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao arredondar valor.", 109));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao recuperar data atual.", 110));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao gerar cor aleatória.", 110));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao gerar texto aleatório.", 111));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao gerar MD5.", 112));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao adicionar fragmento à tela.", 113));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao montar layout da tela.", 114));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao fechar tela.", 115));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao setar eventos da tela.", 116));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao criar tela.", 117));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao criar objeto do tipo 'DataBase'.", 118));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao executar SQL.", 119));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao criar objeto do tipo 'DbColuna'.", 120));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao criar objeto do tipo 'DbFiltro'.", 121));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao criar objeto do tipo 'dbTabela'.", 122));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao abrir tela.", 123));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao buscar registro no banco de dados.", 124));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao criar tabela no banco de dados.", 125));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao excluir registro.", 126));
      _lstMsgUsuarioPadrao.add(new MsgUsuario(
          "Erro ao verificar se tabela existe no banco de dados.", 127));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao recuperar tabela no banco de dados.", 128));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao inserir registro no banco de dados.", 129));
      _lstMsgUsuarioPadrao.add(new MsgUsuario(
          "Erro ao inserir registro aleatório no banco de dados.", 130));
      _lstMsgUsuarioPadrao
          .add(new MsgUsuario("Erro ao zerar valores das colunas no registro.", 131));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao verificar filtro no item da lista.", 132));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao criar objeto do tipo 'TblCliente'.", 133));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao criar objeto do tipo 'TblMain'.", 134));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao criar objeto do tipo 'TblPessoa'.", 135));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao criar objeto do tipo 'TblUsuario'.", 136));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao criar objeto do tipo 'ConfigItem'.", 137));

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(this.getStrMsgUsuarioPadrao(0), ex);

    } finally {
    }

    return _lstMsgUsuarioPadrao;
  }

  public List<DbTabela> getLstTbl() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (_lstTbl != null) {
        return _lstTbl;
      }

      _lstTbl = new ArrayList<DbTabela>();

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }

    return _lstTbl;
  }

  public DataBase getObjDbPrincipal() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (_objDbPrincipal != null) {
        return _objDbPrincipal;
      }

      _objDbPrincipal = new DataBase(this.getStrNomeSimplificado(), this.getCnt());

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(102), ex);

    } finally {
    }

    return _objDbPrincipal;
  }

  public Gson getObjGson() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (_objGson != null) {
        return _objGson;
      }

      _objGson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro("Erro inesperado.\n", ex);

    } finally {
    }

    return _objGson;
  }

  public String getStrMsgUsuario(int intId) {
    return this.getStrMsgUsuario(intId, EnmLingua.PORTUGUES, false);
  }

  public String getStrMsgUsuario(int intId, EnmLingua enmLingua, boolean booMsgPadrao) {
    // VARIÁVEIS

    List<MsgUsuario> lstMsgUsuarioTemp;
    String strResultado = Utils.STRING_VAZIA;

    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (booMsgPadrao) {
        lstMsgUsuarioTemp = this.getLstMsgUsuarioPadrao();
      } else {
        lstMsgUsuarioTemp = this.getLstMsgUsuario();
      }

      for (MsgUsuario msgUsuario : lstMsgUsuarioTemp) {

        if (msgUsuario.getIntId() == intId && msgUsuario.getEnmLingua() == enmLingua) {
          strResultado = msgUsuario.getStrTexto();
          break;
        }
      }

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(this.getStrTextoPadrao(103), ex);

    } finally {
    }

    return strResultado;
  }

  public String getStrMsgUsuarioPadrao(int intId) {
    return this.getStrMsgUsuario(intId, EnmLingua.PORTUGUES, true);
  }

  public String getStrTexto(int intId) {
    return this.getStrMsgUsuario(intId);
  }

  public String getStrTextoPadrao(int intId) {
    return this.getStrMsgUsuarioPadrao(intId);
  }

  public String getStrVersao() {
    return _strVersao;
  }

  public DbTabela getTblSelec() {
    return _tblSelec;
  }

  /**
   * Limpa o "cache" com a lista de de ítens das telas de consultas das tabelas
   * do app.
   */
  public void limparTblListaConsulta() {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      for (DbTabela tbl : this.getLstTbl()) {
        tbl.limparListaConsulta();
      }

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  public void mostrarNoficacao(final String strMensagem) {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      this.getActMain().runOnUiThread(new Runnable() {

        @Override
        public void run() {
          // VARIÁVEIS

          int intTempo;

          // FIM VARIÁVEIS
          try {
            // AÇÕES

            intTempo = strMensagem.length() > 25 ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;
            Toast.makeText(App.this.getCnt(), strMensagem, intTempo).show();

            // FIM AÇÕES
          } catch (Exception ex) {

            new Erro(App.getI().getStrTextoPadrao(0), ex);

          } finally {
          }
        }
      });

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(this.getStrTextoPadrao(104), ex);

    } finally {
    }
  }

  public void setActMain(ActMain actMain) {
    _actMain = actMain;
  }

  private void setI(App _i) {
    // VARIÁVEIS
    // FIM VARIÁVEIS
    try {
      // AÇÕES

      if (i == null) {
        i = _i;
      }

      // FIM AÇÕES
    } catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    } finally {
    }
  }

  public void setIntVersao(int intVersao) {
    _intVersao = intVersao;
  }

  public void setStrVersao(String strVersao) {
    _strVersao = strVersao;
  }

  public void setTblSelec(DbTabela tblSelec) {
    _tblSelec = tblSelec;
  }

}
