package com.surelabsid.suryono.sigit.spinnerselection

import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var brand = arrayListOf("Apple", "BenQ", "Xiaomi", "Samsung")
    private lateinit var adapter: ArrayAdapter<String>

    /**
     * Anggap saja yang dituliskan di editText itu data dari database
     *
     * */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, brand)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        spinner.adapter = adapter

        submit.setOnClickListener {
            if (textCari.text.toString().isEmpty()) {
                Toast.makeText(this@MainActivity, "Wajib diisi ya", Toast.LENGTH_SHORT).show()
            } else {
                setSpinnerSelection(textCari.text.toString())
            }
        }
    }

    private fun setSpinnerSelection(search: String) {
        /**
         * prosesnya disini
         * kata yang dicari tadi akan dicari berdasarkan posisi nya di adapter spinner
         * INGAT.. ini case sensitif. Besar Kecilnya huruf akan ngaruh
         * pencarian berdasarkan string, kalo ketemu nanti akan mengembalikan index dari arraynya
         * kalo nggak ketemu, kembaliannya -1
         */
        val selection = adapter.getPosition(search)
        if (selection > -1) {
            ketemu.text = "$search ketemu di index $selection"
            ketemu.setTextColor(Color.BLUE)
        } else {
            ketemu.text = "$search nggak ketemu (index: $selection)"
            ketemu.setTextColor(Color.RED)
        }
        spinner.setSelection(selection)
    }
}