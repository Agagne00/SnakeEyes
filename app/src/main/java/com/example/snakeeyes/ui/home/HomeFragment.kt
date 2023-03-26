package com.example.snakeeyes.ui.home

import android.graphics.Color
import android.graphics.Color.rgb
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
import com.example.snakeeyes.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), View.OnClickListener{

private var _binding: FragmentHomeBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!
private var rollbtn:Button? = null
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    val root: View = binding.root
    activity?.title = "Roll Dice"
      // going to try commenting out all the text stuff, see if it builds
    //val textView: TextView = binding.textHome
    //homeViewModel.text.observe(viewLifecycleOwner) {
    //  textView.text = it
    //}
      root.setBackgroundColor(rgb(255,130,0))
    return root
  }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rollbtn = view.findViewById<Button>(R.id.rollButton)
        rollbtn!!.setOnClickListener(this)
    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(p0: View?) {
        (activity as MainActivity).rollResults()
    }


}