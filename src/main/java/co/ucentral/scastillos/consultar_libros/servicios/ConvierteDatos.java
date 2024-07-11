package co.ucentral.scastillos.consultar_libros.servicios;

import co.ucentral.scastillos.consultar_libros.dto.AutorDto;
import co.ucentral.scastillos.consultar_libros.dto.LibroDto;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ConvierteDatos {

    public AutorDto extraerAutor(JSONObject jsonObject1) {
        JSONArray jsonArrayAutores = new JSONArray(jsonObject1.get("authors").toString());
        JSONObject jsonObject2 = jsonArrayAutores.getJSONObject(0);
        String nombreAutor = jsonObject2.getString("name");
        int fechaNacimeiento = jsonObject2.getInt("birth_year");
        int fechaMuerte = jsonObject2.getInt("death_year");

        return AutorDto.builder()
                .nombre(nombreAutor)
                .fechaNacimiento(fechaNacimeiento)
                .fechaMuerte(fechaMuerte)
                .build();

    }

    public LibroDto extraerLibro(JSONObject jsonObject1, AutorDto autor) {
        String titulo = jsonObject1.getString("title");
        Long id_libro = (long) jsonObject1.getInt("id");
        int descargas = jsonObject1.getInt("download_count");

        JSONArray languagesArray = jsonObject1.getJSONArray("languages");
        List<String> idiomas = new ArrayList<>();
        for (int i = 0; i < languagesArray.length(); i++) {
            idiomas.add(languagesArray.getString(i));
        }


        return LibroDto.builder()
                .id_libro(id_libro)
                .titulo(titulo)
                .descargas(descargas)
                .idiomas(idiomas)
                .autor(autor)
                .build();
    }

    public JSONObject convertir(String datosJson) {
        JSONObject jsonObject = new JSONObject(datosJson);
        JSONArray jsonArray = new JSONArray(jsonObject.getJSONArray("results").toString());
        JSONObject jsonObject1 = jsonArray.getJSONObject(0);

        return  jsonObject1;
    }
}





