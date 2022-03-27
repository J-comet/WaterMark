package hs.project.watermark

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hs.project.watermark.customview.WaterMarksBg
import hs.project.watermark.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var textList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       textList = arrayListOf()
        val labels: MutableList<String> = ArrayList()
        labels.add("워터마크")

        binding.bgTv1.background = WaterMarksBg(this@MainActivity, labels, -40, 13)
        binding.bgTv2.background = WaterMarksBg(this@MainActivity, labels, -40, 13)
        binding.bgTv3.background = WaterMarksBg(this@MainActivity, labels, -40, 13)
    }
}