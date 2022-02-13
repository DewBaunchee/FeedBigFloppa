package by.varyvoda.matvey.view.tabs

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import by.varyvoda.matvey.view.fragment.About
import by.varyvoda.matvey.view.fragment.Floppa

class TabsAdapter(fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {

    val labels = listOf("Floppa", "About")

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Floppa()
            1 -> About()
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int {
        return labels.size
    }
}
