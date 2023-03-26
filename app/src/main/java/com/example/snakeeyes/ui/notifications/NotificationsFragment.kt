package com.example.snakeeyes.ui.notifications

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.snakeeyes.MainActivity
import com.example.snakeeyes.R
import com.example.snakeeyes.databinding.FragmentNotificationsBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class NotificationsFragment : Fragment(), View.OnClickListener {

private var _binding: FragmentNotificationsBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private var alertDialog: AlertDialog? = null
  private val binding get() = _binding!!
    private var resetbtn: Button? = null
    private var barChart:BarChart? = null
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

    _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
    val root: View = binding.root
      activity?.title = "Results"
    //val textView: TextView = binding.textNotifications
    //notificationsViewModel.text.observe(viewLifecycleOwner) {
    //  textView.text = it
    //}
      root.setBackgroundColor(Color.rgb(255, 130, 0))




    return root
  }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resetbtn = view.findViewById<Button>(R.id.resetButton)
        resetbtn!!.setOnClickListener(this)
        barChart = view.findViewById<BarChart>(R.id.barChart)
        view.findViewById<TextView>(R.id.numValues).text = (activity as MainActivity).totalDice.toString()
        view.findViewById<TextView>(R.id.avgRecorded).text = "%.3f".format((activity as MainActivity).avgDice)
        setBarChart()
    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(p0: View?) {
        (activity as MainActivity).resetResults()
    }
    // TODO: render the entire graph
    // TODO: figure out how to change "Home/Dashboard/Notifications" to proper names
    private fun setBarChart() {
        val entries = ArrayList<BarEntry>()
        val res = (activity as MainActivity).results
        entries.add(BarEntry(res[0].toFloat(), 0))
        entries.add(BarEntry(res[1].toFloat(), 1))
        entries.add(BarEntry(res[2].toFloat(), 2))
        entries.add(BarEntry(res[3].toFloat(), 3))
        entries.add(BarEntry(res[4].toFloat(), 4))
        entries.add(BarEntry(res[5].toFloat(), 5))

        val barDataSet = BarDataSet(entries, "Cells")

        val labels = ArrayList<String>()
        labels.add("1")
        labels.add("2")
        labels.add("3")
        labels.add("4")
        labels.add("5")
        labels.add("6")
        val data = BarData(labels, barDataSet)
        barChart?.data = data

        //setDescription("Set Bar Chart Description")  // set the description

        barDataSet.color = Color.WHITE

        barChart?.animateY(5000)
    }
}