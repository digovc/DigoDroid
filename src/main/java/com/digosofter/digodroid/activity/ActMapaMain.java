package com.digosofter.digodroid.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public abstract class ActMapaMain extends ActMain implements OnMapReadyCallback
{
  private MapFragment _frgMap;
  private List<Marker> _lstObjMarker;
  private GoogleMap _objGoogleMap;
  private LocationManager _objLocationManager;

  protected void addMarca(MarkerOptions objMarkerOptions)
  {
    if (this.getObjGoogleMap() == null)
    {
      return;
    }

    if (objMarkerOptions == null)
    {
      return;
    }

    Marker mrk = this.getObjGoogleMap().addMarker(objMarkerOptions);

    if (mrk == null)
    {
      return;
    }

    this.getLstObjMarker().add(mrk);

    this.getObjGoogleMap().animateCamera(CameraUpdateFactory.newLatLngZoom(mrk.getPosition(), 15));
  }

  protected void atualizarObjGoogleMap()
  {
    if (this.getObjGoogleMap() == null)
    {
      return;
    }

    this.inicializar(this.getObjGoogleMap());
    this.setEventos(this.getObjGoogleMap());
    this.finalizar(this.getObjGoogleMap());
  }

  protected void finalizar(final GoogleMap objGoogleMap)
  {
  }

  private MapFragment getFrgMap()
  {
    if (_frgMap != null)
    {
      return _frgMap;
    }
    _frgMap = new MapFragment();

    return _frgMap;
  }

  protected abstract int getIntMapaContainerId();

  protected List<Marker> getLstObjMarker()
  {
    if (_lstObjMarker != null)
    {
      return _lstObjMarker;
    }

    _lstObjMarker = new ArrayList<Marker>();

    return _lstObjMarker;
  }

  protected GoogleMap getObjGoogleMap()
  {
    return _objGoogleMap;
  }

  protected LocationManager getObjLocationManager()
  {
    if (_objLocationManager != null)
    {
      return _objLocationManager;
    }

    _objLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

    return _objLocationManager;
  }

  protected void inicializar(final GoogleMap objGoogleMap)
  {

  }

  @Override
  protected void montarLayout()
  {
    super.montarLayout();

    this.setTitle("Mapa");

    this.montarLayoutMapa();
  }

  private void montarLayoutMapa()
  {
    if (this.getIntMapaContainerId() < 0)
    {
      return;
    }

    this.addFragmento(this.getIntMapaContainerId(), this.getFrgMap());
  }

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    this.getFrgMap().getMapAsync(this);
  }

  @Override
  public void onMapReady(GoogleMap objGoogleMap)
  {
    if (objGoogleMap == null)
    {
      return;
    }

    this.setObjGoogleMap(objGoogleMap);
  }

  protected void setEventos(final GoogleMap objGoogleMap)
  {

  }

  private void setObjGoogleMap(GoogleMap objGoogleMap)
  {
    if (_objGoogleMap == objGoogleMap)
    {
      return;
    }

    _objGoogleMap = objGoogleMap;

    this.atualizarObjGoogleMap();
  }

  @TargetApi(Build.VERSION_CODES.M)
  protected void solicitarLocalizacao()
  {
    // TODO: Checar permissão antes de solicitar a localização.
    if (this.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
    {
      return;
    }

    if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
    {
      return;
    }

    this.getObjLocationManager().requestSingleUpdate(LocationManager.GPS_PROVIDER, null);
  }

  protected void zoom(final LatLngBounds.Builder objLatLngBoundsBuilder)
  {
    if (objLatLngBoundsBuilder == null)
    {
      return;
    }

    if (this.getObjGoogleMap() == null)
    {
      return;
    }

    CameraUpdate objCameraUpdate = CameraUpdateFactory.newLatLngBounds(objLatLngBoundsBuilder.build(), 250);

    this.getObjGoogleMap().animateCamera(objCameraUpdate);
  }
}