package com.minegocio.core.entities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Direccion {
    private String id;
    private String provincia;
    private String ciudad;
    private String direccion;

    public Direccion(String id, String provincia, String ciudad, String direccion) {
        if (inputValidation(id, provincia, ciudad, direccion)) {
            this.id = id;
            this.provincia = provincia;
            this.ciudad = ciudad;
            this.direccion = direccion;
        }
    }

    public String getId() {
        return id;
    }

    public boolean setId(String id) {
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(id);
        boolean matchFound = matcher.matches();
        if (matchFound)
            this.id = id;
        return matchFound;
    }

    public String getProvincia() {
        return provincia;
    }

    public boolean setProvincia(String provincia) {
        Pattern pattern = Pattern.compile("^[A-Za-zÁáÉéÍíÓóÚúÜüÑñ]{1,15}(\\.)?(\\s[A-Za-zÁáÉéÍíÓóÚúÜüÑñ]{1,15}){0,4}$",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(provincia);
        boolean matchFound = matcher.matches();
        if (matchFound)
            this.provincia = provincia;
        return matchFound;
    }

    public String getCiudad() {
        return ciudad;
    }

    public boolean setCiudad(String ciudad) {
        Pattern pattern = Pattern.compile("^[A-Za-zÁáÉéÍíÓóÚúÜüÑñ]{1,15}(\\.)?(\\s[A-Za-zÁáÉéÍíÓóÚúÜüÑñ]{1,15}){0,4}$",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(ciudad);
        boolean matchFound = matcher.matches();
        if (matchFound)
            this.ciudad = ciudad;
        return matchFound;
    }

    public String getDireccion() {
        return direccion;
    }

    public boolean setDireccion(String direccion) {
        Pattern pattern = Pattern.compile(
                "^[A-Za-zÁáÉéÍíÓóÚúÜüÑñ0-9]{1,15}((\\.|\\-|\\,)?\\s?[A-Za-zÁáÉéÍíÓóÚúÜüÑñ0-9]{1,15}){3,10}$",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(direccion);
        boolean matchFound = matcher.matches();
        if (matchFound)
            this.direccion = direccion;
        return matchFound;
    }

    private boolean inputValidation(String id, String provincia, String ciudad, String direccion) {
        Pattern patternId = Pattern.compile("^[0-9]*$");
        Pattern patternProvincia = Pattern.compile(
                "^[A-Za-zÁáÉéÍíÓóÚúÜüÑñ]{1,15}(\\.)?(\\s[A-Za-zÁáÉéÍíÓóÚúÜüÑñ]{1,15}){0,4}$",
                Pattern.CASE_INSENSITIVE);
        Pattern patternCiudad = Pattern.compile(
                "^[A-Za-zÁáÉéÍíÓóÚúÜüÑñ]{1,15}(\\.)?(\\s[A-Za-zÁáÉéÍíÓóÚúÜüÑñ]{1,15}){0,4}$",
                Pattern.CASE_INSENSITIVE);
        Pattern patternDireccion = Pattern.compile(
                "^[A-Za-zÁáÉéÍíÓóÚúÜüÑñ0-9]{1,15}((\\.|\\-|\\,)?\\s?[A-Za-zÁáÉéÍíÓóÚúÜüÑñ0-9]{1,15}){3,10}$",
                Pattern.CASE_INSENSITIVE);

        return patternId.matcher(id).matches() && patternProvincia.matcher(provincia).matches()
                && patternCiudad.matcher(ciudad).matches()
                && patternDireccion.matcher(direccion).matches();
    }

    public boolean isValid() {
        return this.provincia != null && this.ciudad != null && this.direccion != null;
    }

    @Override
    public String toString() {
        return "Direccion { id:" + this.id +
                ", provincia:" + this.provincia +
                ", ciudad:" + this.ciudad +
                ", direccion:" + this.direccion +
                " }";
    }

}
