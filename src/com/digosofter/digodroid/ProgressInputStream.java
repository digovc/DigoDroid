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
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			this.inputStream = inputStream;
			this.handler = handler;

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	// FIM CONSTRUTORES

	// M�TODOS

	@Override
	public int read() throws IOException {
		// VARI�VEIS

		int count = 0;

		// FIM VARI�VEIS
		try {
			// A��ES

			count = inputStream.read();

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return incrementCounterAndUpdateDisplay(count);
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		// VARI�VEIS

		int count = 0;

		// FIM VARI�VEIS
		try {
			// A��ES

			count = inputStream.read(b, off, len);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return incrementCounterAndUpdateDisplay(count);
	}

	@Override
	public void close() throws IOException {

		super.close();

		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			if (closed) {
				throw new IOException("already closed");
			}

			closed = true;

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	private int incrementCounterAndUpdateDisplay(int count) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			if (count > 0)
				progress += count;
			lastUpdate = maybeUpdateDisplay(progress, lastUpdate);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return count;
	}

	private long maybeUpdateDisplay(long progress, long lastUpdate) {
		// VARI�VEIS
		// FIM VARI�VEIS
		try {
			// A��ES

			if (progress - lastUpdate > TEN_KILOBYTES) {
				lastUpdate = progress;
				sendLong(PROGRESS_UPDATE, progress);
			}

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}

		return lastUpdate;
	}

	public void sendLong(String key, long value) {
		// VARI�VEIS

		Bundle data;
		Message message;

		// FIM VARI�VEIS
		try {
			// A��ES

			data = new Bundle();
			data.putLong(key, value);

			message = Message.obtain();
			message.setData(data);
			handler.sendMessage(message);

			// FIM A��ES
		} catch (Exception ex) {

			new Erro(App.getI().getStrTextoPadrao(0), ex.getMessage());

		} finally {
		}
	}

	// FIM M�TODOS

	// EVENTOS
	// FIM EVENTOS

}