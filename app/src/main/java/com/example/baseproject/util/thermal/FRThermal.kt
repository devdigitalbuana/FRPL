package com.example.baseproject.util.thermal

import android.content.Context
import com.common.thermalimage.TemperatureBitmapData
import com.common.thermalimage.HotImageCallback
import com.common.thermalimage.TemperatureData
import com.example.baseproject.util.thermal.devices.F10Device
import com.example.baseproject.util.thermal.devices.SentuhDevice
import org.json.JSONObject

class FRThermal(context: Context) {
    enum class DeviceType {
        NONE,
        SENTUH,
        F10
    }

    companion object {
        var context: Context? = null
        private var instance: FRThermal? = null

        fun getSingleton(): FRThermal {
            if (instance == null) {
                instance = context?.let { FRThermal(it) }
            }
            return instance as FRThermal
        }
    }

    private var device: ThermalDevice? = null;
    private var deviceType: DeviceType = DeviceType.NONE;
    fun initThermal(deviceType:DeviceType) {
        when ( deviceType )
        {
            DeviceType.SENTUH -> device = SentuhDevice()
            DeviceType.F10 -> device = F10Device()
        }

        //if ( device != null )
        try {
            context?.let { device?.init(it) }
        }
        catch ( e:Exception )
        {
            System.out.println ("Error")
        }
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