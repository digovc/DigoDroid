package com.digosofter.digodroid.activity;

import android.view.Menu;
import android.view.MenuItem;

import com.digosofter.digodroid.Aparelho;
import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.R;
import com.digosofter.digodroid.controle.label.LabelGeral;
import com.digosofter.digodroid.log.LogManagerAndroidMain;
import com.digosofter.digojava.Utils;

public class ActLog extends ActMain
{
  public static final String STR_EXTRA_INT_LOG_MANAGER_OBJETO_ID = "int_log_manager_objeto_id";

  private static final String STR_MENU_COMPARTILHAR = "Compartilhar";
  private static final String STR_MENU_SALVAR = "Salvar";

  private LabelGeral _lblLog;
  private LogManagerAndroidMain _logManager;

  public void atualizarLog(final String strLog)
  {
    if (Utils.getBooStrVazia(strLog))
    {
      return;
    }

    this.runOnUiThread(new Runnable()
    {
      @Override
      public void run()
      {
        ActLog.this.atualizarLogLocal(strLog);
      }
    });
  }

  private void atualizarLogLocal(final String strLog)
  {
    this.getLblLog().setText(strLog);
  }

  private boolean compartilhar()
  {
    if (AppAndroid.getI() == null)
    {
      return false;
    }

    if (this.getLogManager() == null)
    {
      return false;
    }

    Aparelho.getI().compartilhar(this, this.getLogManager().getStrNome(), this.getLogManager().getStrLog());

    return true;
  }

  @Override
  protected void finalizar()
  {
    super.finalizar();

    this.finalizarLogManager();
  }

  private void finalizarLogManager()
  {
    if (this.getLogManager() == null)
    {
      return;
    }

    this.getLogManager().setActLog(null);
  }

  @Override
  public int getIntLayoutId()
  {
    return R.layout.act_log;
  }

  private LabelGeral getLblLog()
  {
    if (_lblLog != null)
    {
      return _lblLog;
    }

    _lblLog = this.getView(R.id.actLog_lblLog);

    return _lblLog;
  }

  private LogManagerAndroidMain getLogManager()
  {
    if (_logManager != null)
    {
      return _logManager;
    }

    _logManager = LogManagerAndroidMain.getLogManager(this.getIntent().getIntExtra(STR_EXTRA_INT_LOG_MANAGER_OBJETO_ID, -1));

    return _logManager;
  }

  @Override
  protected void inicializar()
  {
    super.inicializar();

    this.inicializarLogManager();
    this.inicializarLblLog();
  }

  private void inicializarLblLog()
  {
    if (this.getLogManager() == null)
    {
      return;
    }

    this.getLblLog().setText(this.getLogManager().getStrLogResumido());
  }

  private void inicializarLogManager()
  {
    if (this.getLogManager() == null)
    {
      this.finish();
      return;
    }

    this.setTitle(this.getLogManager().getStrNome());

    this.getLogManager().setActLog(this);
  }

  @Override
  public boolean onCreateOptionsMenu(final Menu mnu)
  {
    if (!super.onCreateOptionsMenu(mnu))
    {
      return false;
    }

    if (this.getLogManager() == null)
    {
      return true;
    }

    this.onCreateOptionsMenuCompartilhar(mnu);
    this.onCreateOptionsMenuSalvar(mnu);

    return true;
  }

  private void onCreateOptionsMenuCompartilhar(final Menu mnu)
  {
    MenuItem mniCompartilhar = mnu.add(STR_MENU_COMPARTILHAR);
  }

  private void onCreateOptionsMenuSalvar(final Menu mnu)
  {
    MenuItem mniSalvar = mnu.add(STR_MENU_SALVAR);
  }

  @Override
  public boolean onOptionsItemSelected(final MenuItem mni)
  {
    if (super.onOptionsItemSelected(mni))
    {
      return true;
    }

    switch (mni.getTitle().toString())
    {
      case STR_MENU_COMPARTILHAR:
        return this.compartilhar();

      case STR_MENU_SALVAR:
        return this.salvar();
    }

    return false;
  }

  private boolean salvar()
  {
    AppAndroid.getI().notificar("Funcionalidade não implementada."); // TODO: Implementar.
    return true;
  }
}