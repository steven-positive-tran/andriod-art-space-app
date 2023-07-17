package com.example.andriod_art_space_app.data
import com.example.andriod_art_space_app.R
import com.example.andriod_art_space_app.model.Painting

class DataSource() {

    fun loadPaintings(): List<Painting>{
        return listOf(
            Painting(R.string.title_1, R.string.artist_1, R.string.year_1, R.drawable.fruit__jug__and_a_glass_1943_7_4),
            Painting(R.string.title_2, R.string.artist_2, R.string.year_2, R.drawable.portrait_of_a_young_man_and_his_tutor_1961_9_26),
            Painting(R.string.title_3, R.string.artist_3, R.string.year_3, R.drawable.the_attentive_nurse_1952_5_37)
        )
    }
}