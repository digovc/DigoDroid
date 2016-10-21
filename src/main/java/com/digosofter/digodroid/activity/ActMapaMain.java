package com.digosofter.digodroid.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.LocationSource.OnLocationChangedListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public abstract class ActMapaMain extends ActMain implements OnLocationChangedListener, OnMapReadyCallback, OnMarkerDragListener
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

    this.getLstObjMarker().add(mrk);

    this.getObjGoogleMap().animateCamera(CameraUpdateFactory.newLatLngZoom(mrk.getPosition(), 15));
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

  protected abstract int getIntMapContainerId();

  protected List<Marker> getLstObjMarker()
  {
    if (_lstObjMarker != null)
    {
      return _lstObjMarker;
    }

    _lstObjMarker = new ArrayList<>();

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

  protected void inicializarMapa()
  {
    this.montarLayoutMapa();
    this.setEventosMapa();
  }

  @Override
  protected void montarLayout()
  {
    super.montarLayout();

    this.setTitle("Mapa");

    this.addFragmento(this.getIntMapContainerId(), this.getFrgMap());
  }

  protected void montarLayoutMapa()
  {
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

    this.inicializarMapa();
  }

  @Override
  public void onMarkerDrag(Marker mrk)
  {
  }

  @Override
  public void onMarkerDragEnd(Marker mrk)
  {
    if (mrk == null)
    {
      return;
    }

    mrk.setPosition(mrk.getPosition());
  }

  @Override
  public void onMarkerDragStart(Marker mrk)
  {
  }

  protected void setEventosMapa()
  {
    this.getObjGoogleMap().setOnMarkerDragListener(this);
  }

  private void setObjGoogleMap(GoogleMap objGoogleMap)
  {
    _objGoogleMap = objGoogleMap;
  }

  @TargetApi(Build.VERSION_CODES.M)
  protected void solicitarLocalizacao()
  {
    if (this.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
    {
      return;
    }

    this.getObjLocationManager().requestSingleUpdate(LocationManager.GPS_PROVIDER, null);
  }
}