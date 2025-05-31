package com.currencydemoapp.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.currencydemoapp.MainApplication
import com.currencydemoapp.R
import com.currencydemoapp.presentation.viewmodel.DemoViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DemoActivity : AppCompatActivity() {

    private lateinit var buttonSettings: FloatingActionButton
    private lateinit var buttonDisplayAll: FloatingActionButton
    private lateinit var buttonDisplayListB: FloatingActionButton
    private lateinit var buttonDisplayListA: FloatingActionButton
    private lateinit var buttonAdd: FloatingActionButton
    private lateinit var buttonClear: FloatingActionButton

    private lateinit var textDisplayAll: TextView
    private lateinit var textDisplayListB: TextView
    private lateinit var textDisplayListA: TextView
    private lateinit var textAdd: TextView
    private lateinit var textClear: TextView

    private var areOpenedMenu: Boolean = false

    private lateinit var viewModel: DemoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)

        initViews()
        initViewModel()
        handleMenuVisibility(false)

        setupClickListeners()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DemoFragment.newInstance())
                .commitNow()
        }
    }

    private fun initViewModel() {
        val factory =
            (application as MainApplication).providesComponents().providesDemoViewModelFactory()
        viewModel = ViewModelProvider(this, factory)[DemoViewModel::class.java]
    }

    private fun setupClickListeners() {
        buttonSettings.setOnClickListener {
            areOpenedMenu = !areOpenedMenu
            handleMenuVisibility(areOpenedMenu)
        }
        buttonAdd.setOnClickListener {
            viewModel.refresh()
        }
        buttonClear.setOnClickListener {
            viewModel.clearAll()
        }
        buttonDisplayListA.setOnClickListener {
            viewModel.useCryptoList()
        }
        buttonDisplayListB.setOnClickListener {
            viewModel.useFiatList()
        }
        buttonDisplayAll.setOnClickListener {
            viewModel.useAll()
        }
    }

    private fun handleMenuVisibility(visible: Boolean) {
        if (visible) {
            buttonDisplayAll.show()
            buttonDisplayListB.show()
            buttonDisplayListA.show()
            buttonAdd.show()
            buttonClear.show()

            textDisplayAll.visibility = View.VISIBLE
            textDisplayListB.visibility = View.VISIBLE
            textDisplayListA.visibility = View.VISIBLE
            textAdd.visibility = View.VISIBLE
            textClear.visibility = View.VISIBLE
        } else {
            buttonDisplayAll.hide()
            buttonDisplayListB.hide()
            buttonDisplayListA.hide()
            buttonAdd.hide()
            buttonClear.hide()

            textDisplayAll.visibility = View.GONE
            textDisplayListB.visibility = View.GONE
            textDisplayListA.visibility = View.GONE
            textAdd.visibility = View.GONE
            textClear.visibility = View.GONE

        }
    }

    private fun initViews() {
        buttonSettings = findViewById(R.id.button_settings)
        buttonDisplayAll = findViewById(R.id.button_display_all)
        buttonDisplayListB = findViewById(R.id.button_display_list_b)
        buttonDisplayListA = findViewById(R.id.button_display_list_a)
        buttonAdd = findViewById(R.id.button_add)
        buttonClear = findViewById(R.id.button_clear)

        textDisplayAll = findViewById(R.id.text_display_all)
        textDisplayListB = findViewById(R.id.text_display_list_b)
        textDisplayListA = findViewById(R.id.text_display_list_a)
        textAdd = findViewById(R.id.text_add)
        textClear = findViewById(R.id.text_clear)
    }
}
