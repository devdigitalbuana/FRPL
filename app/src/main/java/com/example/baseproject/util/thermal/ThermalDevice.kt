package com.example.baseproject.util.thermal

import android.content.Context
import com.example.baseproject.util.thermal.FRThermal

abstract class ThermalDevice {
    abstract fun getData(thermalListener: ThermalListener?);
    abstract fun release();
    abstract fun init(context: Context);
}