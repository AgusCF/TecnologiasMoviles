package com.example.manoslocales.utils

import com.example.manoslocales.R
import com.example.manoslocales.data.local.entities.Product
import com.example.manoslocales.data.local.entities.Seller
import com.example.manoslocales.data.local.entities.User

object ObjectsProvider {

    val cities = listOf(
        "Córdoba", "Villa María", "Río Cuarto", "Carlos Paz", "San Francisco",
        "Alta Gracia", "Bell Ville", "Jesús María", "La Falda", "Villa Dolores"
    )

    private val firstNames = listOf(
        "Juan", "María", "Carlos", "Lucía", "Pedro",
        "Sofía", "Martín", "Camila", "Joaquín", "Valentina"
    )

    private val lastNames = listOf(
        "González", "Pérez", "Fernández", "López", "Rodríguez",
        "Sánchez", "Romero", "Díaz", "Álvarez", "Torres"
    )

    val categories = listOf(
        "Higiene", "Decoración", "Accesorios", "Pan",
        "Textil", "Bebida", "Papelería", "Cerámica"
    )

    // Primero creamos los usuarios sin favoritos (los favoritos se agregan después)
    private val _users: MutableList<User> = mutableListOf()

    val usersList: List<User> by lazy {
        repeat(10) { i ->
            _users += User(
                id = i + 1,
                name = firstNames[i],
                surname = lastNames[i],
                dateOfBirth = "199${i}-01-01",
                username = "usuario${i + 1}",
                email = "usuario${i + 1}@mail.com",
                password = "password${i + 1}",
                city = cities[i],
                favoriteProducts = mutableListOf(),
                image = R.drawable.ic_profile
            )
        }
        _users
    }

    val sellerLists: List<Seller> by lazy {
        usersList.map { user ->
            Seller(
                user = user,
                businessName = "Emprendimiento ${user.id}",
                rating = (3..5).random() + (0..9).random() / 10.0
            )
        }
    }

    val productsList: List<Product> by lazy {
        List(100) { i ->
            val (name, description) = generateRandomProductData(i)
            val owner = sellerLists[i % sellerLists.size]

            Product(
                id = i + 1,
                name = name,
                price = (1000..10000).random() + (0..99).random() / 100.0,
                category = categories.random(),
                description = description,
                owner = owner,
                image = R.drawable.loki2
            )
        }.also { allProducts ->
            // Una vez generados los productos, asignamos favoritos a los usuarios
            _users.forEach { user ->
                user.favoriteProducts = allProducts.shuffled().take((3..5).random()) as MutableList<Product>?
            }
        }
    }

    private fun generateRandomProductData(index: Int): Pair<String, String> {
        val items = listOf("Vela", "Cuadro", "Taza", "Maceta", "Bolso", "Almohadón", "Espejo", "Lámpara", "Sahumador", "Caja decorativa")
        val styles = listOf("de cerámica", "de madera", "artesanal", "minimalista", "reciclado", "tejido", "bordado")
        val name = "${items.random()} ${styles.random()}"
        val description = "Producto artesanal #$index elaborado con dedicación."
        return name to description
    }
}