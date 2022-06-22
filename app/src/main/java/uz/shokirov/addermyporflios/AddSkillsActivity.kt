package uz.shokirov.addermyporflios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import uz.shokirov.addermyporflios.model.Skills

class AddSkillsActivity : AppCompatActivity() {
    lateinit var firebaseFirestore: FirebaseFirestore
    lateinit var firebaseStorage: FirebaseStorage
    lateinit var reference: StorageReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_skills)

        var skill = findViewById<EditText>(R.id.edtSkill)
        var percent = findViewById<EditText>(R.id.edtPercent)
        var save = findViewById<Button>(R.id.btnSave)

        firebaseFirestore = FirebaseFirestore.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        reference = firebaseStorage.getReference("images")

        save.setOnClickListener {
            var skill = skill.text.toString()
            var percent = percent.text.toString().toInt()
            if (skill.isNotEmpty()) {
                var skills = Skills(skill, percent)
                firebaseFirestore.collection("skills")
                    .add(skills)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Saqlandi", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Ma'lumot chala", Toast.LENGTH_SHORT).show()
            }
        }

    }
}