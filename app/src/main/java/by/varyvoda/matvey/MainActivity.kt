package by.varyvoda.matvey

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import by.varyvoda.matvey.view.tabs.TabsAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabs: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.viewPager)
        tabs = findViewById(R.id.tabs)

        val tabsAdapter = TabsAdapter()
        viewPager.adapter = tabsAdapter
        TabLayoutMediator(
            tabs,
            viewPager,
        ) { tab, position ->
            tab.text = tabsAdapter.labels[position]
        }.attach()
    }
}