package com.example.baseproject.util.thermal

import com.common.thermalimage.TemperatureBitmapData
import com.common.thermalimage.TemperatureData

interface ThermalListener {
    fun onDone(
        error: String?,
        temperatureBitmapData: TemperatureBitmapData?,
        temperatureData: TemperatureData?
    )
}