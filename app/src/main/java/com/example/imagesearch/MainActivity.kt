package com.example.imagesearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.imagesearch.databinding.ActivityMainBinding
import com.example.imagesearch.favorite.FavoriteFragment
import com.example.imagesearch.search.SearchFragment
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    var likedItems = mutableListOf<SearchItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.bnvMainBottom.setOnItemSelectedListener(this)

        supportFragmentManager.beginTransaction()
            .add(R.id.fcv_main_frag, SearchFragment()).commit()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_search -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fcv_main_frag, SearchFragment()).commitAllowingStateLoss()
                true
            }

            R.id.action_favorite -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fcv_main_frag, FavoriteFragment()).commitAllowingStateLoss()
                true
            }

            else -> false
        }
    }

    fun addLikedItem(item: SearchItem) {
        if(!likedItems.contains(item)) {
            likedItems.add(item)
        }
    }

    fun removeLikedItem(item: SearchItem) {
        likedItems.remove(item)
    }

}