package by.varyvoda.matvey.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import by.varyvoda.matvey.R
import by.varyvoda.matvey.domain.BringCounter
import by.varyvoda.matvey.domain.Record
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

const val RESULT_CHILD = "result"

class Floppa : Fragment() {

    private lateinit var currentRecord: Record
    private lateinit var currentUser: FirebaseUser
    private lateinit var db: DatabaseReference
    private lateinit var feedBtn: Button
    private lateinit var feedCountView: TextView
    private lateinit var floppaView: ImageView

    private val counter: BringCounter = BringCounter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.floppa, container, false)

        feedBtn = view.findViewById(R.id.feedBtn)
        feedCountView = view.findViewById(R.id.feedCount)
        floppaView = view.findViewById(R.id.floppa)
        db = Firebase.database(getString(R.string.dbUrl)).reference
        currentUser = Firebase.auth.currentUser!!

        feedBtn.setOnClickListener {
            counter.count()
            if (counter.count > currentRecord.record) {
                saveRecord(counter.count)
            }
        }

        db.child(RESULT_CHILD).child(currentUser.uid)
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.value == null) {
                        saveRecord(0)
                        return
                    }
                    currentRecord = snapshot.getValue(Record::class.java)!!
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })

        updateText()
        counter.setBringListener {
            updateText()
            when (it % 15) {
                0L -> floppaView.setImageResource(R.mipmap.big_floppa)
                1L -> floppaView.setImageResource(R.mipmap.floppa_foreground)
            }
        }

        return view
    }

    private fun saveRecord(record: Long) {
        db
            .child(RESULT_CHILD)
            .child(currentUser.uid)
            .setValue(Record(currentUser.displayName!!, record))
    }

    private fun updateText() {
        feedCountView.text = getString(R.string.count, counter.count)
    }
}