package com.example.samplerecyclearview.interfaces

import com.example.samplerecyclearview.models.Butterfly

interface MyInterface {
    fun onButterClicked(position: Int, ArrayList: MutableList<Butterfly>)
    fun onForwardPressed(position: Int, ArrayList: MutableList<Butterfly>)
}