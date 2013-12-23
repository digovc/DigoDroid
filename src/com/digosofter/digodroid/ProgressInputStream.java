package com.digosofter.digodroid;

import java.io.IOException;
import java.io.InputStream;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.digosofter.digodroid.erro.Erro;

public class ProgressInputStream extends InputStream {
	// CONSTANTES

	public static final String PROGRESS_UPDATE = "progress_update";

	private static final int TEN_KILOBYTES = 1024 * 10;

	// FIM CONSTANTES

	// ATRIBUTOS

	private InputStream inputStream;
	private Handler handler;

	private long progress = 0;
	private long lastUpdate = 0;

	private boolean closed = false;

	// FIM ATRIBUTOS

	// CONSTRUTORES

	public ProgressInputStream(InputStream inputStream, Handler handler) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			this.inputStream = inputStream;
			this.handler = handler;

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	// FIM CONSTRUTORES

	// MÉTODOS

	@Override
	public int read() throws IOException {
		// VARIÁVEIS

		int count = 0;

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			count = inputStream.read();

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return incrementCounterAndUpdateDisplay(count);
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		// VARIÁVEIS

		int count = 0;

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			count = inputStream.read(b, off, len);

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return incrementCounterAndUpdateDisplay(count);
	}

	@Override
	public void close() throws IOException {

		super.close();

		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			if (closed) {
				throw new IOException("already closed");
			}

			closed = true;

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	private int incrementCounterAndUpdateDisplay(int count) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			if (count > 0)
				progress += count;
			lastUpdate = maybeUpdateDisplay(progress, lastUpdate);

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return count;
	}

	private long maybeUpdateDisplay(long progress, long lastUpdate) {
		// VARIÁVEIS
		// FIM VARIÁVEIS
		try {
			// AÇÕES

			if (progress - lastUpdate > TEN_KILOBYTES) {
				lastUpdate = progress;
				sendLong(PROGRESS_UPDATE, progress);
			}

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return lastUpdate;
	}

	public void sendLong(String key, long value) {
		// VARIÁVEIS

		Bundle data;
		Message message;

		// FIM VARIÁVEIS
		try {
			// AÇÕES

			data = new Bundle();
			data.putLong(key, value);

			message = Message.obtain();
			message.setData(data);
			handler.sendMessage(message);

			// FIM AÇÕES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	// FIM MÉTODOS

	// EVENTOS
	// FIM EVENTOS

}