package com.digosofter.digodroid.activity;

import com.digosofter.digodroid.preference.FrgPreferenceMain;

public abstract class ActConfigMain extends ActMain
{
  protected abstract FrgPreferenceMain getFrgPreference();

  @Override
  public int getIntLayoutId()
  {
    return -1;
  }

  @Override
  protected void montarLayout()
  {
    super.montarLayout();

    this.montarLayoutFrgPreference();
  }

  private void montarLayoutFrgPreference()
  {
    FrgPreferenceMain frg = this.getFrgPreference();

    if (frg == null)
    {
      return;
    }

    this.addFragmento(android.R.id.content, frg);
  }
}