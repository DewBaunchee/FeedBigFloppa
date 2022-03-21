package by.varyvoda.matvey.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import by.varyvoda.matvey.R
import by.varyvoda.matvey.domain.Record
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Records : Fragment() {

    private lateinit var table: TableLayout
    private lateinit var context: AppCompatActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        context = (activity as AppCompatActivity)
        context.supportActionBar?.title = getString(R.string.records_title)

        val view = inflater.inflate(R.layout.fragment_records, container, false)
        table = view.findViewById(R.id.records)


        Firebase.database(getString(R.string.dbUrl)).reference
            .child(RESULT_CHILD)
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    processData(snapshot.getValue(
                        object :
                        GenericTypeIndicator<Map<String, Record>>() {}
                    )!!)
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })

        return view
    }

    private fun processData(records: Map<String, Record>) {
        table.removeAllViews()
        records.entries.forEach {
            val row = TextView(context)
            row.text = getString(R.string.record, it.value.name, it.value.record)
            table.addView(row)
        }
    }
}