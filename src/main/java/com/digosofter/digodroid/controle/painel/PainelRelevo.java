package com.digosofter.digodroid.controle.painel;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.AttributeSet;

import com.digosofter.digodroid.R;
import com.digosofter.digodroid.UtilsAndroid;
import com.digosofter.digodroid.design.TemaDefault;
import com.digosofter.digodroid.erro.ErroAndroid;

public class PainelRelevo extends PainelMain {

  public PainelRelevo(Context context) {

    super(context);
  }

  public PainelRelevo(Context context, AttributeSet attrs) {

    super(context, attrs);
  }

  public PainelRelevo(Context context, AttributeSet attrs, int defStyleAttr) {

    super(context, attrs, defStyleAttr);
  }

  @Override
  public void inicializar() {

    super.inicializar();

    try {

      this.setBackgroundColor(this.getContext().getResources().getColor(R.color.cor_fundo));
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }
}
