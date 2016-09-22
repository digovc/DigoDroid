package com.digosofter.digodroid.controle.drawermenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.digosofter.digodroid.R;
import com.digosofter.digodroid.UtilsAndroid;
import com.digosofter.digodroid.activity.ActMain;
import com.digosofter.digodroid.controle.imagem.ImagemGeral;
import com.digosofter.digodroid.controle.label.LabelGeral;
import com.digosofter.digodroid.controle.painel.PainelGeral;
import com.digosofter.digodroid.controle.painel.PainelRipple;
import com.digosofter.digodroid.design.TemaDefault;

public class MenuItem extends PainelGeral implements View.OnClickListener
{
  private static final int INT_MENU_ITEM_HEIGHT = 60;
  private Drawable _imgIcon;
  private ImagemGeral _imgIcone;
  private LabelGeral _lblTitulo;
  private PainelGeral _pnlConteudo;
  private PainelRipple _pnlRipple;
  private String _strTitulo;
  private DrawerMenu _viwDrawerMenu;

  public MenuItem(Context cnt)
  {
    super(cnt);
  }

  public MenuItem(Context cnt, AttributeSet atr)
  {
    super(cnt, atr);
  }

  public MenuItem(Context cnt, AttributeSet atr, int intDefStyleAttr)
  {
    super(cnt, atr, intDefStyleAttr);
  }

  private void atualizarImgIcon(final Drawable imgIcon)
  {
    this.getImgIcone().setImageDrawable(imgIcon);
  }

  @Override
  public void finalizar()
  {
    super.finalizar();

    this.getLayoutParams().height = UtilsAndroid.dpToPx(INT_MENU_ITEM_HEIGHT, this.getContext());
    this.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
  }

  private Drawable getImgIcon()
  {
    return _imgIcon;
  }

  private ImagemGeral getImgIcone()
  {
    if (_imgIcone != null)
    {
      return _imgIcone;
    }

    _imgIcone = new ImagemGeral(this.getContext());

    return _imgIcone;
  }

  protected LabelGeral getLblTitulo()
  {
    if (_lblTitulo != null)
    {
      return _lblTitulo;
    }

    _lblTitulo = new LabelGeral(this.getContext());

    return _lblTitulo;
  }

  protected PainelGeral getPnlConteudo()
  {
    if (_pnlConteudo != null)
    {
      return _pnlConteudo;
    }

    _pnlConteudo = new PainelGeral(this.getContext());

    return _pnlConteudo;
  }

  private PainelRipple getPnlRipple()
  {
    if (_pnlRipple != null)
    {
      return _pnlRipple;
    }

    _pnlRipple = new PainelRipple(this.getContext());

    return _pnlRipple;
  }

  public String getStrTitulo()
  {
    return _strTitulo;
  }

  private DrawerMenu getViwDrawerMenu()
  {
    if (_viwDrawerMenu != null)
    {
      return _viwDrawerMenu;
    }

    if (this.getContext() == null)
    {
      return null;
    }

    if (!ActMain.class.isAssignableFrom(this.getContext().getClass()))
    {
      return null;
    }

    _viwDrawerMenu = ((ActMain) this.getContext()).getViwDrawerMenu();

    return _viwDrawerMenu;
  }

  @Override
  public void inicializar(AttributeSet ats)
  {
    super.inicializar(ats);

    this.inicializarImgIcone(ats);
    this.inicializarLblTitulo(ats);
  }

  @Override
  public void inicializar()
  {
    super.inicializar();

    this.getPnlConteudo().setOrientation(LinearLayout.HORIZONTAL);

    this.inicializarImgIcone();
    this.inicializarLblTitulo();
    this.inicializarPnlRipple();
  }

  private void inicializarImgIcone(AttributeSet ats)
  {
    if (ats == null)
    {
      return;
    }

    TypedArray objTypedArray = this.getContext().obtainStyledAttributes(ats, R.styleable.MenuItem);

    this.setImgIcon(objTypedArray.getDrawable(R.styleable.MenuItem_srcIcone));
  }

  private void inicializarImgIcone()
  {
    int intPaddingDp = UtilsAndroid.dpToPx(12, this.getContext());
    int intTamanhoDp = UtilsAndroid.dpToPx(INT_MENU_ITEM_HEIGHT, this.getContext());

    this.getImgIcone().setLayoutParams(new ViewGroup.LayoutParams(intTamanhoDp, intTamanhoDp));
    this.getImgIcone().setPadding(intPaddingDp, intPaddingDp, intPaddingDp, intPaddingDp);
  }

  private void inicializarLblTitulo(AttributeSet ats)
  {
    if (ats == null)
    {
      return;
    }

    TypedArray objTypedArray = this.getContext().obtainStyledAttributes(ats, R.styleable.View);

    this.setStrTitulo(objTypedArray.getString(R.styleable.View_strTitulo));
  }

  private void inicializarLblTitulo()
  {
    this.getLblTitulo().setEnmFonteTamanho(TemaDefault.EnmFonteTamanho.PEQUENO);
    this.getLblTitulo().setGravity(Gravity.CENTER_VERTICAL);
    this.getLblTitulo().setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
  }

  private void inicializarPnlRipple()
  {
    this.getPnlRipple().setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
  }

  @Override
  public void montarLayout()
  {
    super.montarLayout();

    this.addView(this.getPnlRipple());

    this.getPnlRipple().addView(this.getPnlConteudo());
    this.getPnlConteudo().addView(this.getImgIcone());
    this.getPnlConteudo().addView(this.getLblTitulo());
  }

  @Override
  public void onClick(View viw)
  {
    if (this.getViwDrawerMenu() == null)
    {
      return;
    }

    this.getViwDrawerMenu().setMniClicado(this);
    this.getViwDrawerMenu().closeDrawers();
  }

  @Override
  public void setEventos()
  {
    super.setEventos();

    this.getPnlRipple().setOnClickListener(this);
  }

  public void setImgIcon(Drawable imgIcon)
  {
    if (_imgIcon == imgIcon)
    {
      return;
    }

    _imgIcon = imgIcon;

    this.atualizarImgIcon(imgIcon);
  }

  private void setStrTitulo(String strTitulo)
  {
    _strTitulo = strTitulo;

    this.getLblTitulo().setText(_strTitulo);
  }
}