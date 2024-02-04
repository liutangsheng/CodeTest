package com.lts.codetest

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.lts.codetest.databinding.ActivityMainBinding
import com.lts.codetest.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModels()
    private val musicListAdapter by lazy {
        MusicListAdapter()
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getMusic()
        initView()
        observeMusic()
    }

    private fun initView() {
        binding.recyclerview.adapter = musicListAdapter
        binding.etSearch.addTextChangedListener(afterTextChanged = {
            val text = it.toString()
            musicListAdapter.filter.filter(text)
        })
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.rb_off -> {
                    musicListAdapter.offSort()
                }

                R.id.rb_sort_by_price -> {
                    musicListAdapter.sortByPrice()
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeMusic() {
        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this@MainActivity,it,Toast.LENGTH_SHORT).show()
        })

        viewModel.music.observe(this, Observer {
            musicListAdapter.setSourceList(it.toMutableList())
            musicListAdapter.notifyDataSetChanged()
        })
    }
}