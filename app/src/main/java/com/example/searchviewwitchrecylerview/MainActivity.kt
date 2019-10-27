package com.example.searchviewwitchrecylerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Context.SEARCH_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.widget.SearchView


class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val view = findViewById<View>(R.id.am)
        var sharedPreference=SharedPreference(this)
        sharedPreference.clearSharedPreference()
        val wilaya= arrayOf("Adrar", "Chlef", "Laghouat", "Oum El Bouaghi", "Batna", "Béjaïa", "Biskra", "Béchar", "Blida", "Bouira",
            "Tamanrasset", "Tébessa", "Tlemcen", "Tiaret", "Tizi Ouzou", "Alger", "Djelfa", "Jijel", "Sétif", "Saïda", "Skikda", "Sidi Bel Abbès",
            "Annaba", "Guelma", "Constantine", "Médéa", "Mostaganem", "M'Sila", "Mascara", "Ouargla", "Oran", "El Bayadh", "Illizi",
            "Bordj Bou Arreridj", "Boumerdès", "El Tarf", "Tindouf", "Tissemsilt", "El Oued", "Khenchela", "Souk Ahras", "Tipaza",
            "Mila", "Aïn Defla", "Naama", "Aïn Témouchent", "Ghardaïa", "Relizane")
       var wilayas:ArrayList<String> = ArrayList()
        wilayas.addAll(wilaya)
        var Adapter=RecyclerViewAdapter(this,wilayas,wilayas,view)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter=Adapter
        recyclerview.visibility= View.INVISIBLE
        textView.setOnClickListener {
            recyclerview.visibility= View.VISIBLE
            searchView.visibility= View.VISIBLE
            textView.visibility= View.INVISIBLE
        }
        // adding inbuilt divider line
        recyclerview.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        // listening to search query text change
        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.equals("")){
                    recyclerview.visibility= View.INVISIBLE
                }
                else {
                    // filter recycler view when query submitted
                    Adapter.getFilter().filter(query)
                    recyclerview.visibility= View.VISIBLE
                }
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (query.equals("")){
                    recyclerview.visibility= View.INVISIBLE
                }
                else {
                    // filter recycler view when query submitted
                    Adapter.getFilter().filter(query)
                    recyclerview.visibility= View.VISIBLE
                }
                return false
            }
        })

    }

}

