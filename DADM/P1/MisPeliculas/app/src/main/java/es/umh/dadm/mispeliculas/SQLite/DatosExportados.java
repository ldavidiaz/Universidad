package es.umh.dadm.mispeliculas ;

import java.util.List;

import es.umh.dadm.mispeliculas ;
import es.umh.dadm.mispeliculas ;
import es.umh.dadm.mispeliculas ;

public class DatosExportados {
    private List<Usuario> usuarios;
    private List<Pelicula> peliculas;
    private List<Plataforma> plataformas;

    public DatosExportados(List<Usuario> usuarios, List<Pelicula> peliculas, List<Plataforma> plataformas) {
        this.usuarios = usuarios;
        this.peliculas = peliculas;
        this.plataformas = plataformas;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public List<Plataforma> getPlataformas() {
        return plataformas;
    }
}






