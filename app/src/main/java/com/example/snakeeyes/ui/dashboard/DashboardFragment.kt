package com.example.snakeeyes.ui.dashboard

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.snakeeyes.MainActivity
import com.example.snakeeyes.R
import com.example.snakeeyes.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment(), View.OnClickListener {

private var _binding: FragmentDashboardBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!
    private var recordbtn: Button? = null
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

    _binding = FragmentDashboardBinding.inflate(inflater, container, false)
    val root: View = binding.root
      activity?.title = "Record Die Rolls"
    //val textView: TextView = binding.textDashboard
    //dashboardViewModel.text.observe(viewLifecycleOwner) {
      //textView.text = it
    //}
      root.setBackgroundColor(Color.rgb(255, 130, 0))
    return root
  }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recordbtn = view.findViewById<Button>(R.id.recordButton)
        recordbtn!!.setOnClickListener(this)
    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(p0: View?) {
        (activity as MainActivity).addResults()
    }
}