package kr.teamcadi.firebasekt


import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kr.teamcadi.firebasekt.databinding.ActivityItemdbBinding


class ItemdbActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemdbBinding
    private val db: FirebaseFirestore = Firebase.firestore
    private val itemsCollectionRef = db.collection("items")
    private var adapter: MyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemdbBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recylcerViewItems.layoutManager = LinearLayoutManager(this)
        adapter = MyAdapter(this, emptyList())
//        adapter?.setOnItemClickListener {
//            queryItem(it)
//        }
        binding.checkAutoID.setOnClickListener {
            binding.editID.isEnabled = !binding.checkAutoID.isChecked
            if (!binding.editID.isEnabled)
                binding.editID.setText("")
        }
        binding.btnAdd.setOnClickListener{
            add();
            }
        binding.btnCartIn.setOnClickListener{
            val itemID=binding.editID.text.toString()
            val cart = "True"
            itemsCollectionRef.document(itemID).update("cart",cart).addOnSuccessListener {updateList()}
        }
        binding.btnCartOut.setOnClickListener{
            val itemID=binding.editID.text.toString()
            val cart = "False"
            itemsCollectionRef.document(itemID).update("cart",cart).addOnSuccessListener {updateList()}

        }

        }



//    private fun queryItem(itemID: String) {
//        itemsCollectionRef.document(itemID).get()
//            .addOnSuccessListener {
//                binding.editID.setText(it.id)
//                binding.checkAutoID.isChecked = false
//                binding.editID.isEnabled = true
//                binding.editName.setText(it["name"].toString())
//                binding.editPrice.setText(it["price"].toString())
//            }.addOnFailureListener {
//            }
//    }
    private fun add(){
        val name = binding.editName.text.toString()
        val price =binding.editPrice.text.toString()
        val cart = binding.editCart.text.toString()
        val autoID=binding.checkAutoID.isChecked
        val itemID=binding.editID.text.toString()
        val itemMap=hashMapOf("name" to name,"price" to price,"cart" to cart)
        if(autoID){
            //Document의ID를자동으로생성
            itemsCollectionRef.add(itemMap).addOnSuccessListener{
                updateList()
            }.addOnFailureListener{} }
        else{//Document의ID를itemID의값으로지정
            itemsCollectionRef.document(itemID).set(itemMap).addOnSuccessListener{
                updateList()}.addOnFailureListener{}
        }
    }
    private fun updateList() {
        itemsCollectionRef.get().addOnSuccessListener { // it: QuerySnapshot
            val items = mutableListOf<Item>()
            for (doc in it) {
                items.add(Item(doc)) // Item의 생성자가 doc를 받아 처리
            }
            adapter?.updateList(items)
        }
    }

//    private fun cartOut() {
//        val itemID = binding.editID.text.toString()
//        val price = binding.editPrice.text.toString().toInt()
//
//        itemsCollectionRef.document(itemID).update("cart", false)
//            .addOnSuccessListener { queryItem(itemID) }
//    }

}
