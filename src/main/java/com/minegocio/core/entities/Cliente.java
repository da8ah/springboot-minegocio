package com.minegocio.core.entities;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {
    private String id;
    private String tipoIdentificacion;
    private String numIdentificacion;
    private String nombres;
    private String correo;
    private String movil;
    private List<Direccion> direcciones;

    public Cliente() {
    }

    public Cliente(String id, int tipoIdentificacion, String numIdentificacion, String nombres, String correo,
            String movil) {
        if (inputValidation(id, numIdentificacion, nombres, correo, movil)) {
            this.id = id;
            this.setTipoIdentificacion(tipoIdentificacion);
            this.numIdentificacion = numIdentificacion;
            this.nombres = nombres;
            this.correo = correo;
            this.movil = movil;
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

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public boolean setTipoIdentificacion(int tipoIdentificacion) {
        switch (tipoIdentificacion) {
            case 0:
                this.tipoIdentificacion = "DNI";
                break;
            case 1:
                this.tipoIdentificacion = "RUC";
                break;
            default:
                return false;
        }
        return true;
    }

    public String getNumIdentificacion() {
        return numIdentificacion;
    }

    public boolean setNumIdentificacion(String numIdentificacion) {
        Pattern pattern;
        Matcher matcher;
        boolean matchFound = false;
        switch (this.tipoIdentificacion) {
            case "DNI":
                pattern = Pattern.compile("^[0-9]{10}$");
                matcher = pattern.matcher(numIdentificacion);
                matchFound = matcher.matches();
                if (matchFound)
                    this.numIdentificacion = numIdentificacion;
                break;
            case "RUC":
                pattern = Pattern.compile("^[0-9]{13}$");
                matcher = pattern.matcher(numIdentificacion);
                matchFound = matcher.matches();
                if (matchFound)
                    this.numIdentificacion = numIdentificacion;
                break;
        }
        return matchFound;
    }

    public String getNombres() {
        return nombres;
    }

    public boolean setNombres(String nombres) {
        Pattern pattern = Pattern.compile("^[A-Za-zÁáÉéÍíÓóÚúÜüÑñ]{1,15}(\s[A-Za-zÁáÉéÍíÓóÚúÜüÑñ]{1,15}){1,4}$",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(nombres);
        boolean matchFound = matcher.matches();
        if (matchFound)
            this.nombres = nombres;
        return matchFound;
    }

    public String getCorreo() {
        return correo;
    }

    public boolean setCorreo(String correo) {
        Pattern pattern = Pattern.compile("^([\\w\\.\\-]+){1,3}@([\\w\\-]+)((\\.(\\w){2,3})+)$",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(correo);
        boolean matchFound = matcher.matches();
        if (matchFound)
            this.correo = correo;
        return matchFound;
    }

    public String getMovil() {
        return movil;
    }

    public boolean setMovil(String movil) {
        Pattern pattern = Pattern.compile("^(\\+593)?\\s?(\\d{10}|\\d{9})$",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(movil);
        boolean matchFound = matcher.matches();
        if (matchFound)
            this.movil = movil;
        return matchFound;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    private boolean inputValidation(String id, String numIdentificacion, String nombres, String correo, String movil) {
        Pattern patternId = Pattern.compile("^[0-9]*$");
        Pattern patternNumIdentificacion = Pattern.compile("^([0-9]{10}|[0-9]{13})$");
        Pattern patternNombres = Pattern.compile("^[A-Za-zÁáÉéÍíÓóÚúÜüÑñ]{1,15}(\s[A-Za-zÁáÉéÍíÓóÚúÜüÑñ]{1,15}){1,4}$",
                Pattern.CASE_INSENSITIVE);
        Pattern patternCorreo = Pattern.compile("^([\\w\\.\\-]+){1,3}@([\\w\\-]+)((\\.(\\w){2,3})+)$",
                Pattern.CASE_INSENSITIVE);
        Pattern patternMovil = Pattern.compile("^(\\+593)?\\s?(\\d{10}|\\d{9})$",
                Pattern.CASE_INSENSITIVE);

        return patternId.matcher(id).matches() && patternNumIdentificacion.matcher(numIdentificacion).matches()
                && patternNombres.matcher(nombres).matches() && patternCorreo.matcher(correo).matches()
                && patternMovil.matcher(movil).matches();
    }

    public boolean isValid() {
        return this.tipoIdentificacion != null && this.numIdentificacion != null
                && this.nombres != null && this.correo != null && this.movil != null;
    }

    @Override
    public String toString() {
        return "{ \"id\":\"" + this.id +
                "\", \"tipoIdentificacion\":\"" + this.tipoIdentificacion +
                "\", \"numIdentificacion\":\"" + this.numIdentificacion +
                "\", \"nombres\":\"" + this.nombres +
                "\", \"correo\":\"" + this.correo +
                "\", \"movil\":\"" + this.movil +
                "\", \"direcciones\":" + this.direcciones.toString() +
                " }";
    }

}
