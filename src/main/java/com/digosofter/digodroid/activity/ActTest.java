package com.digosofter.digodroid.activity;

import android.app.Activity;
import android.os.Bundle;

import com.digosofter.digodroid.R;

public class ActTest extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    this.setContentView(R.layout.test_campo_alfanumerico);
  }
}
