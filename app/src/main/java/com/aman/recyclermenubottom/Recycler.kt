package com.aman.recyclermenubottom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aman.recyclermenubottom.databinding.ItemStudentBinding

class Recycler : RecyclerView.Adapter<Recycler.ViewHolder>() {
    var studentInfo: ArrayList<StudentInfo> = ArrayList()
    lateinit var clickInterface: ClickInterface
    class ViewHolder(var binding: ItemStudentBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(studentInfo: StudentInfo, clickInterface: ClickInterface) {
            binding.studentInfo = studentInfo
            binding.iv.setOnClickListener {
                clickInterface.OnMoreClicked(binding.iv, studentInfo, adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =  ItemStudentBinding.inflate( LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(studentInfo[position], clickInterface)
    }

    override fun getItemCount(): Int {
        return studentInfo.size
    }

    fun addStudentList(arrayList: ArrayList<StudentInfo>){
        this.studentInfo.clear()
        this.studentInfo.addAll(arrayList)

    }

     fun deleteMyEducations(position: Int) {
        studentInfo.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, studentInfo.size)
        notifyDataSetChanged()
    }

    fun setOnClickInterface(clickInterface: ClickInterface){
        this.clickInterface = clickInterface
    }
}