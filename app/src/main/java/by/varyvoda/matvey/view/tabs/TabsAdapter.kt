package by.varyvoda.matvey.view.tabs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.varyvoda.matvey.R

class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView)

class TabsAdapter : RecyclerView.Adapter<PagerVH>() {

    public val labels = listOf("Floppa", "About")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =
        PagerVH(LayoutInflater.from(parent.context)
            .inflate(R.layout.floppa, parent, false))

    override fun getItemCount(): Int = labels.size

    override fun onBindViewHolder(holder: PagerVH, position: Int) = holder.itemView.run {
    }
}
