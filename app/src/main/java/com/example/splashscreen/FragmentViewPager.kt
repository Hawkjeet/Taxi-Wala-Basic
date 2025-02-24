package com.example.splashscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.splashscreen.Screens.KnowMorePg1
import com.example.splashscreen.Screens.KnowMorePg2
import com.example.splashscreen.Screens.ViewPagerAdapter
import com.example.splashscreen.databinding.FragmentKnowMorePg1Binding
import com.example.splashscreen.databinding.FragmentViewPagerBinding


class FragmentViewPager : Fragment() {

    lateinit var binding:FragmentViewPagerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentViewPagerBinding.inflate(inflater, container, false)
        val v=binding.root
        val fragList= arrayListOf<Fragment>(
            KnowMorePg1(),
            KnowMorePg2()
        )
        val adapter= ViewPagerAdapter(
            fragList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        binding.viewPager.adapter=adapter
        return binding.root
    }

}