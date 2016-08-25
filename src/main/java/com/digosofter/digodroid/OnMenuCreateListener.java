package com.digosofter.digodroid;

import com.digosofter.digodroid.activity.ActMain;
import com.digosofter.digodroid.controle.drawermenu.DrawerMenu;

import java.util.EventListener;

public interface OnMenuCreateListener extends EventListener
{
  void onMenuCreate(ActMain act, DrawerMenu viwDrawerMenu);
}