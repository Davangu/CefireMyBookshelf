package com.davant.cefiremybookshelf.data.repository

import com.davant.cefiremybookshelf.domain.model.Book
import com.davant.cefiremybookshelf.domain.repository.BooksRepository

class LocalBooksRepository: BooksRepository {
    override fun getAllBooks(): List<Book> {
        return listOf(
                Book("Los Diablos", "Joe Abercrombie", 2024, "9788418037663", "los_diablos", true, true),
                Book("Los jardines de la luna", "Steven Erikson", 1999, "9788498890005", "los_jardines_de_la_luna", false, true),
                Book("El camino de los reyes", "Brandon Sanderson", 2010, "9788413143772", "el_camino_de_los_reyes", true, false),
                Book("Juego de tronos", "George R. R. Martin", 1996, "9788496208949", "juego_de_tronos", false, false),
                Book("El señor de los anillos", "J. R. R. Tolkien", 1954, "9788445000663", "el_senor_de_los_anillos", false, false),
                Book("El nombre del viento", "Patrick Rothfuss", 2007, "9788499082478", "el_nombre_del_viento", false, false),
                Book("Vencer al dragón", "Barbara Hambly", 1985, "9788498003887", "vencer_al_dragon", false, false),
                Book("La voluntad de muchos", "James Islington", 2014, "9788417347350", "la_voluntad_de_muchos", false, false),
                Book("Aprendiz de asesino", "Robin Hobb", 1995, "9788496208511", "aprendiz_de_asesino", true, false),
                Book("El ojo del mundo", "Robert Jordan", 1991, "9788498726143", "el_ojo_del_mundo", false, true),
                Book("El último deseo", "Andrzej Sapkowski", 1993, "9788498890005", "el_ultimo_deseo", false, false),
                Book("El color de la magia", "Terry Pratchett", 1983, "9788497596823", "el_color_de_la_magia", false, false),
                Book("La mano izquierda de la oscuridad", "Ursula K. Le Guin", 1969, "9788496208376", "la_mano_izquierda_de_la_oscuridad", false, false),
                Book("El trono de jade", "Naomi Novik", 2006, "9788498003887", "el_trono_de_jade", false, false),
                Book("La quinta estación", "N. K. Jemisin", 2015, "9788417347350", "la_quinta_estacion", false, false),
                Book("El trono de huesos de dragón", "Tad Williams", 1988, "9788498003887", "el_trono_de_huesos_de_dragon", false, false)
            )
    }
}