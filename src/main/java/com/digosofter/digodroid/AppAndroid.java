package com.digosofter.digodroid;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.text.InputType;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.digosofter.digodroid.activity.ActMain;
import com.digosofter.digodroid.database.DbeAndroidMain;
import com.digosofter.digodroid.database.TblAndroidMain;
import com.digosofter.digodroid.design.TemaDefault;
import com.digosofter.digodroid.server.message.RspWelcome;
import com.digosofter.digodroid.service.OnSrvSincCreateListener;
import com.digosofter.digodroid.service.OnSrvSincDestroyListener;
import com.digosofter.digodroid.service.ServiceMain;
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

  private ActMain _actAtual;
  private ActMain _actPrincipal;
  private String _dir;
  private List<OnSrvSincCreateListener> _lstEvtOnSrvSincCreateListener;
  private List<OnSrvSincDestroyListener> _lstEvtOnSrvSincDestroyListener;
  private List<OnServerStartedListener> _lstEvtOnSrvStartedListener;
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

  public void addEvtOnSrvStartedListener(OnServerStartedListener evt)
  {
    if (evt == null)
    {
      return;
    }

    if (this.getLstEvtOnSrvStartedListener().contains(evt))
    {
      return;
    }

    this.getLstEvtOnSrvStartedListener().add(evt);
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

  public void dispararEvtOnSrvStartedListener(final ServiceMain srv)
  {
    if (this.getLstEvtOnSrvStartedListener().isEmpty())
    {
      return;
    }

    for (OnServerStartedListener evt : this.getLstEvtOnSrvStartedListener())
    {
      if (evt == null)
      {
        continue;
      }

      evt.onSrvStarted(srv);
    }
  }

  public void esconderTeclado()
  {
    InputMethodManager imm = (InputMethodManager) this.getActPrincipal().getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(null, 0);
  }

  public void fechar()
  {
    ((NotificationManager) this.getActPrincipal().getSystemService(Context.NOTIFICATION_SERVICE)).cancelAll();

    this.getActPrincipal().finish();

    System.exit(0);
  }

  public ActMain getActAtual()
  {
    return _actAtual;
  }

  public ActMain getActPrincipal()
  {
    return _actPrincipal;
  }

  public abstract Class<?> getClsActSplashScreen();

  public abstract Class getClsViwDrawerMenu();

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

  private List<OnServerStartedListener> getLstEvtOnSrvStartedListener()
  {
    if (_lstEvtOnSrvStartedListener != null)
    {
      return _lstEvtOnSrvStartedListener;
    }

    _lstEvtOnSrvStartedListener = new ArrayList<>();

    return _lstEvtOnSrvStartedListener;
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

    _objNotificationManager = (NotificationManager) this.getActPrincipal().getSystemService(Context.NOTIFICATION_SERVICE);

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
      _objPackageInfo = this.getActPrincipal().getPackageManager().getPackageInfo(this.getActPrincipal().getPackageName(), 0);
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

  @Override
  protected void inicializar()
  {
    super.inicializar();

    this.inicializarDbe();
  }

  private void inicializarDbe()
  {
    if (this.getDbe() == null)
    {
      return;
    }

    this.getDbe().iniciar();
  }

  public void iniciar(final ActMain act)
  {
    this.setActAtual(act);

    this.inicializar();
  }

  public void inserirTexto(final ActMain act, final String strTitulo, final OnInserirTextoListener evt)
  {
    if (act == null)
    {
      return;
    }

    if (Utils.getBooStrVazia(strTitulo))
    {
      return;
    }

    if (evt == null)
    {
      return;
    }

    act.runOnUiThread(new Runnable()
    {
      @Override
      public void run()
      {
        final EditText txt = new EditText(act);

        txt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);

        final AlertDialog.Builder objBuilder = new AlertDialog.Builder(act);

        objBuilder.setTitle(strTitulo);
        objBuilder.setView(txt);

        objBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
          @Override
          public void onClick(final DialogInterface dialog, final int which)
          {
            evt.onOk(strTitulo, txt.getText().toString());
          }
        });

        objBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener()
        {
          @Override
          public void onClick(final DialogInterface objDialog, final int which)
          {
            evt.onCancelar(strTitulo);

            objDialog.cancel();
          }
        });

        objBuilder.show();
      }
    });

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

    Toast objToast = Toast.makeText(this.getActPrincipal(), strMensagem, intTempo);

    this.getLstObjToast().add(objToast);

    objToast.show();
  }

  public void perguntar(final ActMain act, final String strPergunta, final OnPerguntarListener evt)
  {
    if (act == null)
    {
      return;
    }

    if (Utils.getBooStrVazia(strPergunta))
    {
      return;
    }

    if (evt == null)
    {
      return;
    }

    act.runOnUiThread(new Runnable()
    {
      @Override
      public void run()
      {

        AlertDialog.Builder objBuilder = new AlertDialog.Builder(act);

        objBuilder.setMessage(strPergunta);

        objBuilder.setPositiveButton("Sim", new DialogInterface.OnClickListener()
        {
          @Override
          public void onClick(final DialogInterface dialog, final int which)
          {
            evt.onSim(strPergunta);
          }
        });

        objBuilder.setNegativeButton("Não", new DialogInterface.OnClickListener()
        {
          @Override
          public void onClick(final DialogInterface objDialog, final int which)
          {
            evt.onNao(strPergunta);

            objDialog.cancel();
          }
        });

        objBuilder.show();
      }
    });
  }

  public void processarRspWelcome(final RspWelcome rspWelcome)
  {

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

  public void removerEvtOnSrvStartedListener(OnServerStartedListener evt)
  {
    if (evt == null)
    {
      return;
    }

    this.getLstEvtOnSrvStartedListener().remove(evt);
  }

  public void setActAtual(ActMain actAtual)
  {
    _actAtual = actAtual;
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