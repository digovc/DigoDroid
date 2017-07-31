package com.digosofter.digodroid.server;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.digosofter.digodroid.server.message.MessageMain;

import java.io.UnsupportedEncodingException;

class SincJsonRequest extends JsonRequest
{
  private int _intTimeOut = (15 * 1000);
  private MessageMain _msg;

  SincJsonRequest(final MessageMain msg, final String url, final int intTimeOut)
  {
    super(Method.POST, url, msg.toJson(), msg, msg);

    this.setIntTimeOut(intTimeOut);
    this.setMsg(msg);

    this.iniciar();
  }

  private int getIntTimeOut()
  {
    return _intTimeOut;
  }

  private MessageMain getMsg()
  {
    return _msg;
  }

  private void inicializar()
  {
    this.inicializarRetryPolicy();
  }

  private void inicializarRetryPolicy()
  {
    this.setRetryPolicy(new DefaultRetryPolicy(this.getIntTimeOut(), 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
  }

  private void iniciar()
  {
    this.inicializar();
  }

  @Override
  protected Response parseNetworkResponse(final NetworkResponse objNetworkResponse)
  {
    String jsn = null;

    try
    {
      jsn = new String(objNetworkResponse.data, "utf-8");
    }
    catch (UnsupportedEncodingException ex)
    {
      ex.printStackTrace();
    }

    return Response.success(jsn, HttpHeaderParser.parseCacheHeaders(objNetworkResponse));
  }

  private void setIntTimeOut(int intTimeOut)
  {
    _intTimeOut = intTimeOut;
  }

  private void setMsg(MessageMain msg)
  {
    _msg = msg;
  }
}