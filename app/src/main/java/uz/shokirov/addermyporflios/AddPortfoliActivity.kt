package uz.shokirov.addermyporflios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import uz.shokirov.addermyporflios.databinding.ActivityAddPortfoliBinding
import uz.shokirov.addermyporflios.model.Portfolios

class AddPortfoliActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddPortfoliBinding
    lateinit var firebaseFirestore: FirebaseFirestore
    lateinit var list: ArrayList<Portfolios>
    lateinit var firebaseStorage: FirebaseStorage
    lateinit var reference: StorageReference
    var iconImage = " "
    var screenshotImage = " "
    var apkUrl = " "
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPortfoliBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firebaseFirestore = FirebaseFirestore.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        reference = firebaseStorage.getReference("images")

        binding.imageIcon.setOnClickListener {
            getImageContent.launch("image/*")
        }
        binding.imageScreenShot.setOnClickListener {
            getImageContentScreenShot.launch("image/*")
        }
        binding.apkLInk.setOnClickListener {
            getApk.launch("application/*")
        }

        binding.btnSave.setOnClickListener {
            var name = binding.name.text.toString()
            var description = binding.description.text.toString()
            var size = binding.size.text.toString()
            var portfolios = Portfolios(name, iconImage, size, description, screenshotImage, apkUrl)
            firebaseFirestore.collection("portfolio")
                .add(portfolios).addOnSuccessListener {
                    Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
                }
        }

    }

    private var getApk =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            val m = System.currentTimeMillis()
            val uploadTask = reference.child(m.toString()).putFile(uri)
            uploadTask.addOnSuccessListener {
                if (it.task.isSuccessful) {
                    val downloadUrl = it.metadata?.reference?.downloadUrl
                    downloadUrl?.addOnSuccessListener { apkUri ->
                        apkUrl = apkUri.toString()
                        binding.apkLInk.text = apkUri.toString()
                    }
                }
            }.addOnFailureListener {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        }


    private var getImageContentScreenShot =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            val m = System.currentTimeMillis()
            val uploadTask = reference.child(m.toString()).putFile(uri)
            uploadTask.addOnSuccessListener {
                if (it.task.isSuccessful) {
                    val downloadUrl = it.metadata?.reference?.downloadUrl
                    downloadUrl?.addOnSuccessListener { imageUri ->
                        screenshotImage = imageUri.toString()
                        binding.imageScreenShot.setImageURI(uri)
                    }
                }
            }.addOnFailureListener {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        }


    private var getImageContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            val m = System.currentTimeMillis()
            val uploadTask = reference.child(m.toString()).putFile(uri)
            uploadTask.addOnSuccessListener {
                if (it.task.isSuccessful) {
                    val downloadUrl = it.metadata?.reference?.downloadUrl
                    downloadUrl?.addOnSuccessListener { imageUri ->
                        iconImage = imageUri.toString()
                        binding.imageIcon.setImageURI(uri)
                    }
                }
            }.addOnFailureListener {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        }
}