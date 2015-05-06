package com.digosofter.digodroid.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;

import com.digosofter.digodroid.AppAndroid;
import com.digosofter.digodroid.erro.ErroAndroid;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.LocationSource.OnLocationChangedListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public abstract class ActMapaMain extends ActMain implements OnLocationChangedListener, OnMapReadyCallback, OnMarkerDragListener {

  private MapFragment _frgMap;
  private List<Marker> _lstObjMarker;
  private GoogleMap _objGoogleMap;
  private LocationManager _objLocationManager;

  @Override
  public void onMarkerDrag(Marker mrk) {

  }

  @Override
  public void onMarkerDragStart(Marker mrk) {

  }

  private MapFragment getFrgMap() {

    try {

      if (_frgMap != null) {

        return _frgMap;
      }

      _frgMap = new MapFragment();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _frgMap;
  }

  protected abstract int getIntMapContainerId();

  protected List<Marker> getLstObjMarker() {

    try {

      if (_lstObjMarker != null) {

        return _lstObjMarker;
      }

      _lstObjMarker = new ArrayList<Marker>();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _lstObjMarker;
  }

  protected GoogleMap getObjGoogleMap() {

    return _objGoogleMap;
  }

  protected void addMarca(MarkerOptions objMarkerOptions) {

    Marker mrk;

    try {

      if (objMarkerOptions == null) {

        return;
      }

      mrk = this.getObjGoogleMap().addMarker(objMarkerOptions);

      if (mrk == null) {

        return;
      }

      this.getLstObjMarker().add(mrk);

      if ((mrk.getPosition().latitude + mrk.getPosition().longitude) == 0) {

        AppAndroid.getI().mostrarNotificacao("Localização desconhecida.");
        return;
      }

      this.getObjGoogleMap().animateCamera(CameraUpdateFactory.newLatLngZoom(mrk.getPosition(), 15));
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  protected LocationManager getObjLocationManager() {

    try {

      if (_objLocationManager != null) {

        return _objLocationManager;
      }

      _objLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }

    return _objLocationManager;
  }

  @Override
  protected void montarLayout() {

    super.montarLayout();

    try {

      this.setTitle("Mapa");

      this.addFragmento(this.getIntMapContainerId(), this.getFrgMap());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  protected void montarLayoutMapa() {

    try {

      this.setEventosMapa();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  protected void setEventosMapa() {

    try {

      this.getObjGoogleMap().setOnMarkerDragListener(this);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public void onMarkerDragEnd(Marker mrk) {

    try {

      if (mrk == null) {

        return;
      }

      mrk.setPosition(mrk.getPosition());
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    try {

      this.getFrgMap().getMapAsync(this);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  @Override
  public void onMapReady(GoogleMap objGoogleMap) {

    try {

      if (objGoogleMap == null) {

        return;
      }

      this.setObjGoogleMap(objGoogleMap);

      this.montarLayoutMapa();
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }

  private void setObjGoogleMap(GoogleMap objGoogleMap) {

    _objGoogleMap = objGoogleMap;
  }

  protected void solicitarLocalizacao() {

    try {

      this.getObjLocationManager().requestSingleUpdate(LocationManager.GPS_PROVIDER, null);
    }
    catch (Exception ex) {

      new ErroAndroid("Erro inesperado.\n", ex);
    }
    finally {
    }
  }
}