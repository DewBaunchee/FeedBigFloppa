package by.varyvoda.matvey.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import by.varyvoda.matvey.view.fragment.Floppa
import by.varyvoda.matvey.view.fragment.Records

class ViewAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {

    private val fragmentFactories: List<() -> Fragment> = listOf(
        { return@listOf Floppa() },
        { return@listOf Records() }
    )

    override fun createFragment(position: Int): Fragment {
        return fragmentFactories[position]()
    }

    override fun getItemCount(): Int {
        return fragmentFactories.size
    }
}
