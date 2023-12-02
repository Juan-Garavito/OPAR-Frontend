package ViewModel;

import androidx.lifecycle.ViewModel;

import Modelos.Ciudadano;

public class CiudadanoViewModel extends ViewModel {
    Ciudadano ciudadano;

    public void setCiudadano(Ciudadano ciudadano) {
        this.ciudadano = ciudadano;
    }

    public Ciudadano getCiudadano() {
        return ciudadano;
    }
}
