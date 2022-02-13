package by.varyvoda.matvey

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import by.varyvoda.matvey.view.tabs.TabsAdapter


class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.viewPager)

        viewPager.adapter = TabsAdapter(supportFragmentManager, lifecycle)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.infoButton) {
            AlertDialog.Builder(this)
                .setTitle(R.string.info_title)
                .setMessage(R.string.info_message)
                .create()
                .show()
        }
        return super.onOptionsItemSelected(item)
    }
}