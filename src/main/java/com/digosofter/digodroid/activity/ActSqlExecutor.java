package com.digosofter.digodroid.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.R;
import com.digosofter.digojava.Utils;

public class ActSqlExecutor extends ActMain implements View.OnClickListener
{
  private Button _btnExecutarScript;
  private EditText _txtSqlScript;

  private void executarScript()
  {
    if (Utils.getBooStrVazia(this.getTxtSqlScript().getText().toString()))
    {
      return;
    }

    if (AppAndroid.getI().getDbe() == null)
    {
      return;
    }

    AppAndroid.getI().getDbe().execSql(this.getTxtSqlScript().getText().toString());
  }

  private Button getBtnExecutarScript()
  {
    if (_btnExecutarScript != null)
    {
      return _btnExecutarScript;
    }

    _btnExecutarScript = this.getView(R.id.actSqlExecutor_btnExecutarScript, Button.class);

    return _btnExecutarScript;
  }

  @Override
  protected int getIntLayoutId()
  {
    return R.layout.act_sql_executor;
  }

  private EditText getTxtSqlScript()
  {
    if (_txtSqlScript != null)
    {
      return _txtSqlScript;
    }

    _txtSqlScript = this.getView(R.id.actSqlExecutor_txtSqlScript, EditText.class);

    return _txtSqlScript;
  }

  @Override
  public void onClick(final View viw)
  {
    if (this.getBtnExecutarScript().equals(viw))
    {
      this.executarScript();
      return;
    }
  }

  @Override
  protected void setEventos()
  {
    super.setEventos();

    this.getBtnExecutarScript().setOnClickListener(this);
  }
}