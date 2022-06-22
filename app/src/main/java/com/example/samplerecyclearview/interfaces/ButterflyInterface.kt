package com.example.samplerecyclearview.interfaces

import com.example.samplerecyclearview.models.Butterfly

interface ButterflyInterface {
    fun onImageClicked(butterfly: Butterfly)
    fun onForwardIconPressed(butterfly: Butterfly)
}