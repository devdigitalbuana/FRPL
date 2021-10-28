package com.example.baseproject.util.thermal

import android.content.Context
import com.common.thermalimage.TemperatureBitmapData
import com.common.thermalimage.HotImageCallback
import com.common.thermalimage.TemperatureData
import com.example.baseproject.util.thermal.devices.F10Device
import com.example.baseproject.util.thermal.devices.SentuhDevice
import org.json.JSONObject

class FRThermal(context: Context) {

    companion object {
        var context: Context? = null
        private var instance: FRThermal? = null

        fun getSingleton(): FRThermal {
            if (instance == null) {
                instance = context?.let { FRThermal(it) }
                instance?.initThermal()
            }
            return instance as FRThermal
        }
    }

    private var device: ThermalDevice? = null;
    private fun initThermal() {
        device = SentuhDevice();
        if ( device != null )
            context?.let { device?.init(it) }
    }


    fun getData(thermalListener: ThermalListener?) {
        if ( device != null )
            device?.getData(thermalListener);
    }

    fun destroy() {
        if ( device != null )
            device?.release();
    }

}