package com.aman.recyclermenubottom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.aman.recyclermenubottom.databinding.ActivityMainBinding
import com.aman.recyclermenubottom.databinding.ItemStudentBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: Recycler
    var studentInfo: ArrayList<StudentInfo> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        studentInfo.add(StudentInfo("Aman",1, "BCA 1"))
        studentInfo.add(StudentInfo("Kajal",2, "BCA 1"))
        studentInfo.add(StudentInfo("Bobby",3, "BCA 1"))
        studentInfo.add(StudentInfo("Cherry",4, "BCA 1"))
        studentInfo.add(StudentInfo("Daman",5, "BCA 1"))

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = Recycler()
        adapter.addStudentList(studentInfo)
        binding.recyclerView.adapter = adapter

        adapter.setOnClickInterface(object: ClickInterface {
            override fun OnMoreClicked(view: View, studentInfo: StudentInfo, position: Int) {
                PopupMenu(this@MainActivity,view).apply {
                    setOnMenuItemClickListener(object: PopupMenu.OnMenuItemClickListener {
                        override fun onMenuItemClick(item: MenuItem?): Boolean {
                            return when (item?.itemId) {
                                R.id.openBottomSheet -> {

                                    var bottomSheetDialog = BottomSheetDialog(this@MainActivity)

                                    var dialogLayout = ItemStudentBinding.inflate(layoutInflater)

                                    bottomSheetDialog.setContentView(dialogLayout.root)

                                    dialogLayout.studentInfo = studentInfo

                                    dialogLayout.iv.visibility = View.GONE

                                    bottomSheetDialog.show()

                                    true
                                }
                                R.id.showToast -> {
                                    adapter.deleteMyEducations(position)
                                    Toast.makeText(this@MainActivity, " Showing Toast", Toast.LENGTH_LONG).show()
                                    true
                                }
                                else -> false
                            }
                        }

                    })
                    inflate(R.menu.menu)
                    show()
                }
            }
        })

    }
}