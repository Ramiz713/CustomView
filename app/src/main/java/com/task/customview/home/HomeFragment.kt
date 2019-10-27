package com.task.customview.home


import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.task.customview.MockedRepository
import com.task.customview.R
import com.task.customview.home.adapter.PaymentAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as? AppCompatActivity)?.run {
            setSupportActionBar(tb_profile)
            supportActionBar?.run {
                setDisplayShowTitleEnabled(true)
                title = "Константин"
                setDisplayUseLogoEnabled(true)
                setLogo(R.drawable.ic_avatar)
            }
        }
        initRecycler()
    }

    private fun initRecycler() {
        rv_money_operations.adapter = PaymentAdapter()
        (rv_money_operations.adapter as PaymentAdapter).submitList(MockedRepository().getPaymentItems())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_home, menu)
    }

}
