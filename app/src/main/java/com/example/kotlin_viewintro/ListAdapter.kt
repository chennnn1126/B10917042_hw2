package com.example.kotlin_viewintro

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

@Suppress("UNREACHABLE_CODE")
class ListAdapter(val context: Context, var data: List<ViewIntro>) : BaseAdapter() {

    override fun getCount(): Int {
        return data.count()
    }

    override fun getItem(position: Int): ViewIntro {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holder: ViewHolder
        val view: View
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
            holder = ViewHolder()
            holder.img_item_picture = view.findViewById(R.id.img_item_picture)
            holder.txt_item_info = view.findViewById(R.id.txt_item_info)
            view.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
            view = convertView
        }


        var viewItem = getItem(position)
        if (viewItem != null){
            //var imgRid = context.resources.getIdentifier(viewItem.image, "drawable", context.packageName)
            //holder.txt_item_info?.text = viewItem.info
            holder.txt_item_info?.text = context.getString(viewItem.info)
            holder.img_item_picture?.setImageResource(viewItem.picture)
        }

        return view
    }

    private class ViewHolder{
        var img_item_picture: ImageView? = null
        var txt_item_info: TextView? = null
    }
}