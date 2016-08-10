package com.digosofter.digodroid;

import com.digosofter.digodroid.componente.drawermenu.MenuItem;

import java.util.EventListener;

public interface OnMenuItemClickListener extends EventListener
{

  void onMenuItemClick(MenuItem viwMenuItem);
}