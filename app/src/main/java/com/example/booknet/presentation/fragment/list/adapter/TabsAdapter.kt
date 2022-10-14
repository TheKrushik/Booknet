package com.example.booknet.presentation.fragment.list.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.booknet.R
import com.example.booknet.presentation.fragment.list.archive.PageArchiveFragment
import com.example.booknet.presentation.fragment.list.favorites.PageFavoritesFragment
import com.example.booknet.presentation.fragment.list.read.PageReadFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TabsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = TypeLib.values().size

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            TypeLib.Read.value -> PageReadFragment.newInstance()
            TypeLib.Archive.value -> PageArchiveFragment.newInstance()
            TypeLib.Favorites.value -> PageFavoritesFragment.newInstance()
            else -> throw IllegalArgumentException("Unknown position $position")
        }
    }
}

class TabConfiguration : TabLayoutMediator.TabConfigurationStrategy {

    override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
        val titleId = TypeLib.getResStatus(position)
        tab.setText(titleId)
        tab.tag = titleId
    }
}

enum class TypeLib(val value: Int, val resId: Int) {
    Read(0, R.string.tab_lib_read),
    Archive(1, R.string.tab_lib_archive),
    Favorites(2, R.string.tab_lib_favorites);

    companion object{
        fun getResStatus(status: Int) = when (status) {
            Read.value -> Read.resId
            Archive.value -> Archive.resId
            Favorites.value -> Favorites.resId
            else -> 0
        }
    }
}