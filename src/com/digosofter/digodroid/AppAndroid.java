package com.digosofter.digodroid;

import java.util.ArrayList;
import java.util.List;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.digosofter.digodroid.activity.ActMain;
import com.digosofter.digodroid.database.DataBaseAndroid;
import com.digosofter.digodroid.database.DbTabelaAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.App;
import com.digosofter.digojava.MsgUsuario;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.DbTabela;

public abstract class AppAndroid extends App {

  private static AppAndroid i;

  public static AppAndroid getI() {

    return i;
  }

  private ActMain _actMain;
  private boolean _booDebug;
  private Context _cnt;
  private List<MsgUsuario> _lstMsgUsuarioPadrao;
  private List<Toast> _lstObjToast;
  private DataBaseAndroid _objDbPrincipal;
  private NotificationManager _objNotificationManager;
  private PackageInfo _objPackageInfo;
  private String _strVersao;

  protected AppAndroid() {

    try {

      this.setI(this);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public void esconderTeclado() {

    try {

      InputMethodManager imm = (InputMethodManager) this.getCnt().getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(null, 0);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public ActMain getActMain() {

    return _actMain;
  }

  @Override
  public boolean getBooDebug() {

    try {

      _booDebug = 0 != (this.getActMain().getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE);

    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _booDebug;
  }

  public Context getCnt() {

    try {

      _cnt = this.getActMain().getApplicationContext();
    }
    catch (Exception ex) {

      new ErroAndroid(this.getStrMsgUsuarioPadrao(101), ex);
    }
    finally {
    }

    return _cnt;
  }

  @Override
  protected List<MsgUsuario> getLstMsgUsuarioPadrao() {

    try {

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
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao criar objeto do tipo 'MensagemUsuario'.", 107));
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
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao verificar se tabela existe no banco de dados.", 127));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao recuperar tabela no banco de dados.", 128));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao inserir registro no banco de dados.", 129));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao inserir registro aleatório no banco de dados.", 130));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao zerar valores das colunas no registro.", 131));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao verificar filtro no item da lista.", 132));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao criar objeto do tipo 'TblCliente'.", 133));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao criar objeto do tipo 'TblMain'.", 134));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao criar objeto do tipo 'TblPessoa'.", 135));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao criar objeto do tipo 'TblUsuario'.", 136));
      _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao criar objeto do tipo 'ConfigItem'.", 137));
    }
    catch (Exception ex) {

      new ErroAndroid(this.getStrMsgUsuarioPadrao(0), ex);
    }
    finally {
    }

    return _lstMsgUsuarioPadrao;
  }

  private List<Toast> getLstObjToast() {

    try {

      if (_lstObjToast != null) {

        return _lstObjToast;
      }

      _lstObjToast = new ArrayList<Toast>();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _lstObjToast;
  }

  public DataBaseAndroid getObjDbPrincipal() {

    try {

      if (_objDbPrincipal != null) {

        return _objDbPrincipal;
      }

      _objDbPrincipal = new DataBaseAndroid();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _objDbPrincipal;
  }

  private NotificationManager getObjNotificationManager() {

    try {

      if (_objNotificationManager != null) {

        return _objNotificationManager;
      }

      _objNotificationManager = (NotificationManager) this.getCnt().getSystemService(Context.NOTIFICATION_SERVICE);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _objNotificationManager;
  }

  private PackageInfo getObjPackageInfo() {

    try {

      if (_objPackageInfo != null) {

        return _objPackageInfo;
      }

      _objPackageInfo = this.getCnt().getPackageManager().getPackageInfo(this.getCnt().getPackageName(), 0);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _objPackageInfo;
  }

  @Override
  public String getStrVersao() {

    try {

      if (!Utils.getBooStrVazia(_strVersao)) {

        return _strVersao;
      }

      _strVersao = this.getObjPackageInfo().versionName;
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _strVersao;
  }

  protected void limparNotificacao() {

    try {

      for (Toast objToast : this.getLstObjToast()) {

        objToast.cancel();
      }

      this.getLstObjToast().clear();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  /**
   * Limpa o "cache" com a lista de de ítens das telas de consultas das tabelas
   * do app.
   */
  public void limparTblListaConsulta() {

    try {

      for (DbTabela tbl : this.getLstTbl()) {

        ((DbTabelaAndroid) tbl).limparListaConsulta();
      }
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public void mostrarNotificacao(final String strMensagem) {

    try {

      this.getActMain().runOnUiThread(new Runnable() {

        @Override
        public void run() {

          int intTempo;
          Toast objToast;

          try {

            AppAndroid.this.limparNotificacao();

            intTempo = strMensagem.length() > 50 ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;

            objToast = Toast.makeText(AppAndroid.this.getCnt(), strMensagem, intTempo);
            objToast.show();

            AppAndroid.this.getLstObjToast().add(objToast);
          }
          catch (Exception ex) {

            new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
          }
          finally {
          }
        }
      });
    }
    catch (Exception ex) {

      new ErroAndroid(this.getStrTextoPadrao(104), ex);
    }
    finally {
    }
  }

  public void mostrarPergunta(final String strMensagem) {

    try {

      // Implementar.
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public void notificar(int id, Notification ntf) {

    try {

      if (ntf == null) {

        return;
      }

      this.getObjNotificationManager().notify(id, ntf);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  public void setActMain(ActMain actMain) {

    _actMain = actMain;
  }

  private void setI(AppAndroid _i) {

    try {

      if (i != null) {

        return;
      }

      i = _i;
    }
    catch (Exception ex) {

      new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
    }
    finally {
    }
  }
}