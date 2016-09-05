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
import android.content.pm.PackageManager;
import android.os.Environment;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.digosofter.digodroid.activity.ActMain;
import com.digosofter.digodroid.controle.drawermenu.DrawerMenu;
import com.digosofter.digodroid.controle.drawermenu.MenuItem;
import com.digosofter.digodroid.database.DataBaseAndroid;
import com.digosofter.digodroid.database.TabelaAndroid;
import com.digosofter.digodroid.database.ViewAndroid;
import com.digosofter.digojava.App;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.Tabela;

import java.util.ArrayList;
import java.util.List;

public abstract class AppAndroid extends App
{
  private static AppAndroid _i;

  public static AppAndroid getI()
  {
    return _i;
  }

  private ActMain _actPrincipal;
  private boolean _booDebug;
  private Context _cnt;
  private DataBaseAndroid _dbePrincipal;
  private String _dir;
  private List<OnMenuCreateListener> _lstEvtOnMenuCreateListener;
  private List<OnMenuItemClickListener> _lstEvtOnMenuItemClickListener;
  private List<Toast> _lstObjToast;
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
    _booDebug = (0 != (this.getActPrincipal().getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE));

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

  public abstract DataBaseAndroid getDbePrincipal();

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
  public int getIntDrawerMenuLayoutId()
  {
    return 0;
  }

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

  private List<Toast> getLstObjToast()
  {
    if (_lstObjToast != null)
    {
      return _lstObjToast;
    }

    _lstObjToast = new ArrayList<>();

    return _lstObjToast;
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
    if (_objPackageInfo != null)
    {
      return _objPackageInfo;
    }

    try
    {
      _objPackageInfo = this.getCnt().getPackageManager().getPackageInfo(this.getCnt().getPackageName(), 0);
    }
    catch (PackageManager.NameNotFoundException ex)
    {
      ex.printStackTrace();
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

    if (this.getObjPackageInfo() == null)
    {
      return null;
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

    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(act);

    dlgAlert.setCancelable(true);
    dlgAlert.setMessage(strMensagem);
    dlgAlert.setPositiveButton("Ok", null);
    dlgAlert.setTitle(strTitulo);

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
        AppAndroid.this.limparNotificacao();

        int intTempo = strMensagem.length() > 50 ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;

        Toast objToast = Toast.makeText(AppAndroid.this.getCnt(), strMensagem, intTempo);

        AppAndroid.this.getLstObjToast().add(objToast);

        objToast.show();
      }
    });
  }

  public void notificar(int intId, Notification objNotification)
  {
    if (objNotification == null)
    {
      return;
    }

    this.getObjNotificationManager().notify(intId, objNotification);
  }

  /**
   * Reinicia a aplicação.
   *
   * @param act Contexto atual da aplicação.
   */
  public void reiniciar(ActMain act)
  {
    if (act == null)
    {
      return;
    }

    Intent itt = new Intent(act.getApplicationContext(), this.getActPrincipal().getClass());

    itt.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

    act.startActivity(itt);

    itt = new Intent(act, this.getActPrincipal().getClass());

    int intPendingIntentId = 123456;

    PendingIntent objPendingIntent = PendingIntent.getActivity(act, intPendingIntentId, itt, PendingIntent.FLAG_CANCEL_CURRENT);

    AlarmManager objAlarmManager = (AlarmManager) act.getSystemService(Context.ALARM_SERVICE);

    objAlarmManager.set(AlarmManager.RTC, (System.currentTimeMillis() + 3000), objPendingIntent);

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

  private void setI(AppAndroid i)
  {
    if (AppAndroid._i != null)
    {
      return;
    }

    AppAndroid._i = i;
  }
}