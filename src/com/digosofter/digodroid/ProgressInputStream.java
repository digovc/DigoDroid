package com.digosofter.digodroid;

import java.io.IOException;
import java.io.InputStream;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.digosofter.digodroid.erro.Erro;

public class ProgressInputStream extends InputStream {

  public static final String PROGRESS_UPDATE = "progress_update";

  private static final int TEN_KILOBYTES = 1024 * 10;

  private boolean closed = false;
  private Handler handler;

  private InputStream inputStream;
  private long lastUpdate = 0;

  private long progress = 0;

  public ProgressInputStream(InputStream inputStream, Handler handler) {

    try {

      this.inputStream = inputStream;
      this.handler = handler;

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    }
    finally {
    }
  }

  @Override
  public void close() throws IOException {

    super.close();

    try {

      if (closed) {
        throw new IOException("already closed");
      }

      closed = true;

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    }
    finally {
    }
  }

  private int incrementCounterAndUpdateDisplay(int count) {

    try {

      if (count > 0) {
        progress += count;
      }
      lastUpdate = maybeUpdateDisplay(progress, lastUpdate);

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    }
    finally {
    }

    return count;
  }

  private long maybeUpdateDisplay(long progress, long lastUpdate) {

    try {

      if (progress - lastUpdate > TEN_KILOBYTES) {
        lastUpdate = progress;
        sendLong(PROGRESS_UPDATE, progress);
      }

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    }
    finally {
    }

    return lastUpdate;
  }

  @Override
  public int read() throws IOException {

    int count = 0;

    try {

      count = inputStream.read();

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    }
    finally {
    }

    return incrementCounterAndUpdateDisplay(count);
  }

  @Override
  public int read(byte[] b, int off, int len) throws IOException {

    int count = 0;

    try {

      count = inputStream.read(b, off, len);

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    }
    finally {
    }

    return incrementCounterAndUpdateDisplay(count);
  }

  public void sendLong(String key, long value) {

    Bundle data;
    Message message;

    try {

      data = new Bundle();
      data.putLong(key, value);

      message = Message.obtain();
      message.setData(data);
      handler.sendMessage(message);

    }
    catch (Exception ex) {

      new Erro(App.getI().getStrTextoPadrao(0), ex);

    }
    finally {
    }
  }

}