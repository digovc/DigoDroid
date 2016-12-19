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
import com.digosofter.digodroid.database.DbeAndroidMain;
import com.digosofter.digodroid.database.TblAndroidMain;
import com.digosofter.digodroid.design.TemaDefault;
import com.digosofter.digodroid.server.message.RspWelcome;
import com.digosofter.digodroid.service.OnSrvSincCreateListener;
import com.digosofter.digodroid.service.OnSrvSincDestroyListener;
import com.digosofter.digodroid.service.SrvSincMain;
import com.digosofter.digojava.App;
import com.digosofter.digojava.Utils;
import com.digosofter.digojava.database.TabelaMain;

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
  private DbeAndroidMain _dbePrincipal;
  private String _dir;
  private List<OnSrvSincCreateListener> _lstEvtOnSrvSincCreateListener;
  private List<OnSrvSincDestroyListener> _lstEvtOnSrvSincDestroyListener;
  private List<Toast> _lstObjToast;
  private NotificationManager _objNotificationManager;
  private PackageInfo _objPackageInfo;
  private TemaDefault _objTema;
  private String _strVersao;

  protected AppAndroid()
  {
    this.setI(this);
  }

  public void addEvtOnSrvSincCreateListener(OnSrvSincCreateListener evt)
  {
    if (evt == null)
    {
      return;
    }

    if (this.getLstEvtOnSrvSincCreateListener().contains(evt))
    {
      return;
    }

    this.getLstEvtOnSrvSincCreateListener().add(evt);
  }

  public void addEvtOnSrvSincDestroyListener(OnSrvSincDestroyListener evt)
  {
    if (evt == null)
    {
      return;
    }

    if (this.getLstEvtOnSrvSincDestroyListener().contains(evt))
    {
      return;
    }

    this.getLstEvtOnSrvSincDestroyListener().add(evt);
  }

  public void compartilhar(ActMain act, String strAssunto, String strConteudo)
  {
    if (act == null)
    {
      return;
    }

    if (Utils.getBooStrVazia(strAssunto))
    {
      return;
    }

    if (Utils.getBooStrVazia(strConteudo))
    {
      return;
    }

    Intent itt = new Intent(android.content.Intent.ACTION_SEND);

    itt.setType("text/plain");

    itt.putExtra(android.content.Intent.EXTRA_SUBJECT, strAssunto);
    itt.putExtra(android.content.Intent.EXTRA_TEXT, strConteudo);

    act.startActivity(Intent.createChooser(itt, strAssunto));
  }

  private void criarView(final TabelaMain tbl)
  {
    if (tbl == null)
    {
      return;
    }

    if (!(tbl instanceof TblAndroidMain))
    {
      return;
    }

    ((TblAndroidMain) tbl).criarView();
  }

  public void dispararEvtOnSrvSincCreateListener(SrvSincMain srvSinc)
  {
    if (this.getLstEvtOnSrvSincCreateListener().isEmpty())
    {
      return;
    }

    for (OnSrvSincCreateListener evt : this.getLstEvtOnSrvSincCreateListener())
    {
      if (evt == null)
      {
        continue;
      }

      evt.onSrvSincCreate(srvSinc);
    }
  }

  public void dispararEvtOnSrvSincDestroyListener(SrvSincMain srvSinc)
  {
    if (this.getLstEvtOnSrvSincDestroyListener().isEmpty())
    {
      return;
    }

    for (OnSrvSincDestroyListener evt : this.getLstEvtOnSrvSincDestroyListener())
    {
      if (evt == null)
      {
        continue;
      }

      evt.onSrvSincDestroy(srvSinc);
    }
  }

  public void esconderTeclado()
  {
    InputMethodManager imm = (InputMethodManager) this.getCnt().getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(null, 0);
  }

  public void fechar()
  {
    ((NotificationManager) this.getCnt().getSystemService(Context.NOTIFICATION_SERVICE)).cancelAll();

    System.exit(0);
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

  public abstract Class getClsViwDrawerMenu();

  public Context getCnt()
  {
    if (this.getActPrincipal() == null)
    {
      return null;
    }

    _cnt = this.getActPrincipal().getApplicationContext();

    return _cnt;
  }

  public abstract DbeAndroidMain getDbe();

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

  private List<OnSrvSincCreateListener> getLstEvtOnSrvSincCreateListener()
  {
    if (_lstEvtOnSrvSincCreateListener != null)
    {
      return _lstEvtOnSrvSincCreateListener;
    }

    _lstEvtOnSrvSincCreateListener = new ArrayList<>();

    return _lstEvtOnSrvSincCreateListener;
  }

  private List<OnSrvSincDestroyListener> getLstEvtOnSrvSincDestroyListener()
  {
    if (_lstEvtOnSrvSincDestroyListener != null)
    {
      return _lstEvtOnSrvSincDestroyListener;
    }

    _lstEvtOnSrvSincDestroyListener = new ArrayList<>();

    return _lstEvtOnSrvSincDestroyListener;
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

  public TemaDefault getObjTema()
  {
    if (_objTema != null)
    {
      return _objTema;
    }

    _objTema = new TemaDefault();

    return _objTema;
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
        AppAndroid.this.notificarLocal(strMensagem);
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

  private void notificarLocal(final String strMensagem)
  {
    this.limparNotificacao();

    int intTempo = strMensagem.length() > 50 ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;

    Toast objToast = Toast.makeText(AppAndroid.this.getCnt(), strMensagem, intTempo);

    this.getLstObjToast().add(objToast);

    objToast.show();
  }

  public abstract void processarRspWelcome(final RspWelcome rspWelcome);

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

  public void removerEvtOnSrvSincCreateListener(OnSrvSincCreateListener evt)
  {
    if (evt == null)
    {
      return;
    }

    this.getLstEvtOnSrvSincCreateListener().remove(evt);
  }

  public void removerEvtOnSrvSincDestroyListener(OnSrvSincDestroyListener evt)
  {
    if (evt == null)
    {
      return;
    }

    this.getLstEvtOnSrvSincDestroyListener().remove(evt);
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