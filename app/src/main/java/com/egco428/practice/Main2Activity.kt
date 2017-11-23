package com.egco428.practice

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.row_main.view.*
import android.widget.Toast
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
//import javax.swing.text.StyleConstants.setIcon



class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        main_listview.adapter = MyCustomAdapter(this)

        // show normal
        main_listview.setOnItemClickListener { adapterView, view, position, id ->
            val item = adapterView.getItemAtPosition(position) as String
            Log.d("Result","Item: $item at position $position")

            val intent = Intent(this, MapsActivity::class.java)
            intent.putExtra("lat",10.015)
            intent.putExtra("long",105.222)

            startActivity(intent)
        }
    }

    private class MyCustomAdapter(context: Context): BaseAdapter(){
        private val mContext: Context
        private val names = arrayListOf<String>("Tay", "May", "Dream", "Kao-oat","Noey","Tay")

        init {
            //,าจาก this ด้านบน
            mContext = context
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return names[position]
        }

        override fun getCount(): Int {
            return names.size
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            /*val textView = TextView(mContext)
            textView.text = "Show the message"
            return textView*/
            val greyColor = Color.parseColor("#D7D5D2")
            val rowMain: View
            if(convertView == null){
                val layoutInflator = LayoutInflater.from(viewGroup!!.context)
                rowMain = layoutInflator.inflate(R.layout.row_main1,viewGroup,false) //input sub layout
                val viewHolder = ViewHolder(rowMain.name_textview)
                rowMain.tag = viewHolder
            }else{
                rowMain = convertView
            }

            val viewHolder = rowMain.tag as ViewHolder
            if(position%2 == 0){
                rowMain.setBackgroundColor(greyColor)

                //viewHolder.rowImageView.setImageResource(R.drawable.woman)

            }

            Log.d("Result","Load name_textView")
            viewHolder.nameTextView.text = names.get(position)
            //Log.d("Result","Load position_textView")
            //viewHolder.positionTextView.text = "Row Number: $position"

            //remove row
/*
            rowMain.setOnClickListener {
                rowMain.animate().setDuration(1500).alpha(0F).withEndAction({
                    names.removeAt(position)
                    notifyDataSetChanged()
                    rowMain.setAlpha(1.0F)
                })
            }
*/




            return rowMain

        }
        private class ViewHolder(val nameTextView: TextView)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when (item.itemId) {
            android.R.id.home -> {

                val alert = AlertDialog.Builder(this@Main2Activity)
                alert.setTitle("Log out")
                alert.setMessage("Are you sure you want to logout?")
                alert.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                    finish()

                })
                alert.setNegativeButton("No", DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
                alert.show()
                return true
            }

        }

        return super.onOptionsItemSelected(item)
    }
}
