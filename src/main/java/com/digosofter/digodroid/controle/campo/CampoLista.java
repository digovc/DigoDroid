package com.digosofter.digodroid.controle.campo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.widget.PopupMenu;

import java.util.Map;

public class CampoLista extends CampoBotaoMain implements PopupMenu.OnMenuItemClickListener
{
  private PopupMenu _ctrPopupMenu;

  public CampoLista(final Context cnt)
  {
    super(cnt);
  }

  public CampoLista(final Context cnt, final AttributeSet atr)
  {
    super(cnt, atr);
  }

  public CampoLista(final Context cnt, final AttributeSet atr, final int intDefStyleAttr)
  {
    super(cnt, atr, intDefStyleAttr);
  }

  private PopupMenu getCtrPopupMenu()
  {
    if (_ctrPopupMenu != null)
    {
      return _ctrPopupMenu;
    }

    _ctrPopupMenu = new PopupMenu(this.getContext(), this.getBtn());

    _ctrPopupMenu.setOnMenuItemClickListener(this);

    for (Map.Entry<Integer, String> itmOpcao : this.getCln().getMapOpcao().entrySet())
    {
      _ctrPopupMenu.getMenu().add(itmOpcao.getValue());
    }

    return _ctrPopupMenu;
  }

  @Override
  public boolean onMenuItemClick(final MenuItem ctrMenuItem)
  {
    for (Map.Entry<Integer, String> itmOpcao : this.getCln().getMapOpcao().entrySet())
    {
      if (!itmOpcao.getValue().equals(ctrMenuItem.getTitle()))
      {
        this.setIntValor(itmOpcao.getKey());
        return true;
      }
    }

    return false;
  }

  @Override
  protected void processarBtnClick()
  {
    if (this.getCln() == null)
    {
      return;
    }

    if (this.getCln().getMapOpcao().isEmpty())
    {
      return;
    }

    this.getCtrPopupMenu().show();
  }
}