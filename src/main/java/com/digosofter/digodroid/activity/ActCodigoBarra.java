package com.digosofter.digodroid.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.util.SparseArray;

import com.digosofter.digodroid.Aparelho;
import com.digosofter.digodroid.R;
import com.digosofter.digodroid.log.LogErro;
import com.digosofter.digojava.Utils;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public class ActCodigoBarra extends ActMain
{
  @Override
  public int getIntLayoutId()
  {
    return R.layout.act_codigo_barra;
  }

  @Override
  protected void inicializar()
  {
    super.inicializar();

    Aparelho.getI().tirarFoto(this);
  }

  @Override
  protected void onActivityResult(final int intRequestCode, final int intResultCode, final Intent ittResult)
  {
    super.onActivityResult(intRequestCode, intResultCode, ittResult);

    if (intRequestCode != Aparelho.REQUEST_IMAGE_CAPTURE)
    {
      return;
    }

    if (intResultCode != RESULT_OK)
    {
      return;
    }

    this.finish();

    Bitmap bmpCodigoBarra = (Bitmap) ittResult.getExtras().get("data");

    if (bmpCodigoBarra == null)
    {
      return;
    }

    BarcodeDetector objBarcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.EAN_13).build();

    if (!objBarcodeDetector.isOperational())
    {
      LogErro.getI().addLog("Erro ao iniciar o decodificador de código de barra.");
      return;
    }

    Frame fme = new Frame.Builder().setBitmap(bmpCodigoBarra).build();

    SparseArray<Barcode> arrObjBarcode = objBarcodeDetector.detect(fme);

    if ((arrObjBarcode == null) || (arrObjBarcode.size() < 1))
    {
      LogErro.getI().addLog("Código de barra não encontrado na imagem.");
      return;
    }

    String strCodigoBarra = arrObjBarcode.valueAt(0).rawValue;

    if (Utils.getBooStrVazia(strCodigoBarra))
    {
      LogErro.getI().addLog("Código de barra não encontrado na imagem.");
      return;
    }

    Aparelho.getI().getEvtOnCodigoBarraSucesso().onCodigoBarraSucesso(strCodigoBarra);
  }
}