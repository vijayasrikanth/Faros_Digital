package com.vijaysrikanth.fd

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sgs.citytax.util.IClickListener
import com.vijaysrikanth.fd.api.Response.GetLayoutList

class RecyclerAdapter(val listener: IClickListener) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    var mGetLayoutList : List<GetLayoutList> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_adapter,parent,false)
        view.isFocusable = true
        view.isFocusableInTouchMode = true
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mGetLayoutList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tvName.text = mGetLayoutList.get(position).layout_name
//        Glide.with(context).load(movieList.get(position).image)
//                .apply(RequestOptions().centerCrop())
//                .into(holder.image)
        holder.llContainer.setOnClickListener {
            listener.onClick(it, position, mGetLayoutList.get(position))
        }
        holder.itemView.setOnFocusChangeListener { view, isFocused ->
            if(isFocused) {
                Log.e("text true", ""+holder.tvName.text )
                holder.itemView.setBackgroundResource(R.color.search_opaque)
            } else {
                holder.itemView.setBackgroundResource(R.color.white)

            }
        }
    }

    fun setListItems(list: List<GetLayoutList>){
        this.mGetLayoutList = list;
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.title)
        val llContainer: LinearLayout = itemView.findViewById(R.id.llContainer)
    }
}