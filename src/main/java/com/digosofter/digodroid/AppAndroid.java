package com.digosofter.digodroid;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Environment;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.digosofter.digodroid.activity.ActMain;
import com.digosofter.digodroid.controle.drawermenu.DrawerMenu;
import com.digosofter.digodroid.controle.drawermenu.MenuItem;
import com.digosofter.digodroid.database.DataBaseAndroid;
import com.digosofter.digodroid.database.TabelaAndroid;
import com.digosofter.digodroid.database.ViewAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.digosofter.digojava.App;
import com.digosofter.digojava.MsgUsuario;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.Tabela;

import java.util.ArrayList;
import java.util.List;

public abstract class AppAndroid extends App
{
  private static AppAndroid i;

  public static AppAndroid getI()
  {
    return i;
  }

  private ActMain _actPrincipal;
  private boolean _booDebug;
  private Context _cnt;
  private String _dir;
  private List<OnMenuCreateListener> _lstEvtOnMenuCreateListener;
  private List<OnMenuItemClickListener> _lstEvtOnMenuItemClickListener;
  private List<MsgUsuario> _lstMsgUsuarioPadrao;
  private List<Toast> _lstObjToast;
  private DataBaseAndroid _objDbPrincipal;
  private NotificationManager _objNotificationManager;
  private PackageInfo _objPackageInfo;
  private String _strVersao;

  protected AppAndroid()
  {
    this.setI(this);
  }

  public void addEvtOnMenuCreateListener(OnMenuCreateListener evt)
  {
    if (evt == null)
    {
      return;
    }
    if (this.getLstEvtOnMenuCreateListener().contains(evt))
    {
      return;
    }
    this.getLstEvtOnMenuCreateListener().add(evt);
  }

  public void addEvtOnMenuItemClickListener(OnMenuItemClickListener evt)
  {
    if (evt == null)
    {
      return;
    }
    if (this.getLstEvtOnMenuItemClickListener().contains(evt))
    {
      return;
    }
    this.getLstEvtOnMenuItemClickListener().add(evt);
  }

  /**
   * Cria as tabelas no banco de dados.
   */
  public void criarTabela()
  {
    for (Tabela tbl : this.getLstTbl())
    {
      this.criarTabela(tbl);
    }
  }

  private void criarTabela(final Tabela tbl)
  {
    if (tbl == null)
    {
      return;
    }
    if (!(tbl instanceof TabelaAndroid))
    {
      return;
    }
    ((TabelaAndroid) tbl).criar();
  }

  /**
   * Cria as views no banco de dados.
   */
  public void criarView()
  {
    for (Tabela tbl : this.getLstTbl())
    {
      this.criarView(tbl);
    }
  }

  private void criarView(final Tabela tbl)
  {
    if (tbl == null)
    {
      return;
    }
    if (!(tbl instanceof TabelaAndroid))
    {
      return;
    }
    ((TabelaAndroid) tbl).criarView();
  }

  public void dispararOnMenuCreateListener(ActMain act, DrawerMenu viwDrawerMenu)
  {
    if (this.getLstEvtOnMenuCreateListener().isEmpty())
    {
      return;
    }
    for (OnMenuCreateListener evt : this.getLstEvtOnMenuCreateListener())
    {
      evt.onMenuCreate(act, viwDrawerMenu);
    }
  }

  public void dispararOnMenuItemClickListener(MenuItem viwMenuItem)
  {
    if (this.getLstEvtOnMenuItemClickListener().isEmpty())
    {
      return;
    }
    for (OnMenuItemClickListener evt : this.getLstEvtOnMenuItemClickListener())
    {
      evt.onMenuItemClick(viwMenuItem);
    }
  }

  public void esconderTeclado()
  {
    InputMethodManager imm = (InputMethodManager) this.getCnt().getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(null, 0);
  }

  public ActMain getActPrincipal()
  {
    return _actPrincipal;
  }

  @Override
  public boolean getBooDebug()
  {
    _booDebug = 0 != (this.getActPrincipal().getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE);

    return _booDebug;
  }

  public Context getCnt()
  {
    if (this.getActPrincipal() == null)
    {
      return null;
    }
    _cnt = this.getActPrincipal().getApplicationContext();

    return _cnt;
  }

  /**
   * Diretório no dispositivo de armazenamento externo dedicado à arquivos da aplicação.
   *
   * @return Diretório no dispositivo de armazenamento externo dedicado à arquivos da aplicação.
   */
  public String getDir()
  {
    if (_dir != null)
    {
      return _dir;
    }
    _dir = "_dir_completo/_app_nome";
    _dir = _dir.replace("_dir_completo", Environment.getExternalStorageDirectory().getAbsolutePath());
    _dir = _dir.replace("_app_nome", AppAndroid.getI().getStrNome());

    return _dir;
  }

  /**
   * Este método deve retornar o código do layout que guarda o menu principal da aplicação.
   *
   * @return Código do layout que guarda o menu principal da aplicação.
   */
  public abstract int getIntDrawerMenuLayoutId();

  private List<OnMenuCreateListener> getLstEvtOnMenuCreateListener()
  {
    if (_lstEvtOnMenuCreateListener != null)
    {
      return _lstEvtOnMenuCreateListener;
    }
    _lstEvtOnMenuCreateListener = new ArrayList<>();

    return _lstEvtOnMenuCreateListener;
  }

  private List<OnMenuItemClickListener> getLstEvtOnMenuItemClickListener()
  {
    if (_lstEvtOnMenuItemClickListener != null)
    {
      return _lstEvtOnMenuItemClickListener;
    }
    _lstEvtOnMenuItemClickListener = new ArrayList<>();

    return _lstEvtOnMenuItemClickListener;
  }

  @Override
  protected List<MsgUsuario> getLstMsgUsrPadrao()
  {
    if (_lstMsgUsuarioPadrao != null)
    {
      return _lstMsgUsuarioPadrao;
    }
    _lstMsgUsuarioPadrao = new ArrayList<>();
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
    _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao criar objeto do tipo 'Coluna'.", 120));
    _lstMsgUsuarioPadrao.add(new MsgUsuario("Erro ao criar objeto do tipo 'Filtro'.", 121));
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

    return _lstMsgUsuarioPadrao;
  }

  private List<Toast> getLstObjToast()
  {
    if (_lstObjToast != null)
    {
      return _lstObjToast;
    }
    _lstObjToast = new ArrayList<>();

    return _lstObjToast;
  }

  public DataBaseAndroid getObjDbPrincipal()
  {
    if (_objDbPrincipal != null)
    {
      return _objDbPrincipal;
    }
    _objDbPrincipal = new DataBaseAndroid();

    return _objDbPrincipal;
  }

  private NotificationManager getObjNotificationManager()
  {
    if (_objNotificationManager != null)
    {
      return _objNotificationManager;
    }
    _objNotificationManager = (NotificationManager) this.getCnt().getSystemService(Context.NOTIFICATION_SERVICE);

    return _objNotificationManager;
  }

  private PackageInfo getObjPackageInfo()
  {
    try
    {
      if (_objPackageInfo != null)
      {
        return _objPackageInfo;
      }

      _objPackageInfo = this.getCnt().getPackageManager().getPackageInfo(this.getCnt().getPackageName(), 0);
    }
    catch (Exception ex)
    {
      new ErroAndroid("Erro inesperado.\n", ex);
    }

    return _objPackageInfo;
  }

  @Override
  public String getStrVersao()
  {
    if (!Utils.getBooStrVazia(_strVersao))
    {
      return _strVersao;
    }
    _strVersao = this.getObjPackageInfo().versionName;

    return _strVersao;
  }

  /**
   * Retorna a tabela que tem o id de objeto igual ao parâmetro @param intTblObjetoId.
   *
   * @param intTblObjetoId Código do objeto da tabela que se deseja retornar.
   */
  public TabelaAndroid getTbl(int intTblObjetoId)
  {
    TabelaAndroid tblResultado;

    if (intTblObjetoId < 0)
    {
      return null;
    }
    for (Tabela tbl : this.getLstTbl())
    {
      tblResultado = this.getTbl(intTblObjetoId, (TabelaAndroid) tbl);
      if (tblResultado == null)
      {
        continue;
      }
      return tblResultado;
    }

    return null;
  }

  private TabelaAndroid getTbl(int intTblObjetoId, TabelaAndroid tbl)
  {
    if (tbl == null)
    {
      return null;
    }
    if (tbl.getIntObjetoId() == intTblObjetoId)
    {
      return tbl;
    }
    for (ViewAndroid viw : (List<ViewAndroid>) tbl.getLstViwAndroid())
    {
      if (viw == null)
      {
        continue;
      }
      if (viw.getIntObjetoId() != intTblObjetoId)
      {
        continue;
      }
      return viw;
    }

    return null;
  }

  protected void limparNotificacao()
  {
    for (Toast objToast : this.getLstObjToast())
    {
      objToast.cancel();
    }
    this.getLstObjToast().clear();
  }

  public void mostrarDialogo(ActMain act, String strTitulo, final String strMensagem)
  {
    AlertDialog.Builder dlgAlert;

    if (act == null)
    {
      return;
    }
    if (Utils.getBooStrVazia(strMensagem))
    {
      return;
    }
    if (Utils.getBooStrVazia(strTitulo))
    {
      strTitulo = this.getStrNomeExibicao();
    }
    dlgAlert = new AlertDialog.Builder(act);
    dlgAlert.setMessage(strMensagem);
    dlgAlert.setTitle(strTitulo);
    dlgAlert.setPositiveButton("Ok", null);
    dlgAlert.setCancelable(true);
    dlgAlert.create().show();
  }

  public void mostrarPergunta(final String strMensagem)
  {
  }

  public void notificar(final String strMensagem)
  {
    this.getActPrincipal().runOnUiThread(new Runnable()
    {

      @Override
      public void run()
      {
        int intTempo;
        Toast objToast;
        try
        {
          AppAndroid.this.limparNotificacao();
          intTempo = strMensagem.length() > 50 ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;
          objToast = Toast.makeText(AppAndroid.this.getCnt(), strMensagem, intTempo);
          objToast.show();
          AppAndroid.this.getLstObjToast().add(objToast);
        }
        catch (Exception ex)
        {
          new ErroAndroid(AppAndroid.getI().getStrTextoPadrao(0), ex);
        }
        finally
        {
        }
      }
    });
  }

  public void notificar(int id, Notification ntf)
  {
    if (ntf == null)
    {
      return;
    }
    this.getObjNotificationManager().notify(id, ntf);
  }

  /**
   * Reinicia a aplicação.
   *
   * @param act Contexto atual da aplicação.
   */
  public void reiniciar(ActMain act)
  {
    int intPendingIntentId;
    Intent itt;
    PendingIntent objPendingIntent;
    AlarmManager objAlarmManager;

    if (act == null)
    {
      return;
    }
    itt = new Intent(act.getApplicationContext(), this.getActPrincipal().getClass());
    itt.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    act.startActivity(itt);
    itt = new Intent(act, this.getActPrincipal().getClass());
    intPendingIntentId = 123456;
    objPendingIntent = PendingIntent.getActivity(act, intPendingIntentId, itt, PendingIntent.FLAG_CANCEL_CURRENT);
    objAlarmManager = (AlarmManager) act.getSystemService(Context.ALARM_SERVICE);
    objAlarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 3000, objPendingIntent);
    System.exit(0);
  }

  public void removerEvtOnMenuCreateListener(OnMenuCreateListener evt)
  {
    if (evt == null)
    {
      return;
    }
    this.getLstEvtOnMenuCreateListener().remove(evt);
  }

  public void removerEvtOnMenuItemClickListener(OnMenuItemClickListener evt)
  {
    if (evt == null)
    {
      return;
    }
    this.getLstEvtOnMenuItemClickListener().remove(evt);
  }

  public void setActPrincipal(ActMain actPrincipal)
  {
    _actPrincipal = actPrincipal;
  }

  private void setI(AppAndroid _i)
  {
    if (i != null)
    {
      return;
    }
    i = _i;
  }
}