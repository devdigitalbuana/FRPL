package com.example.baseproject.util.thermal.devices

import android.content.Context
import com.common.thermalimage.HotImageCallback
import com.common.thermalimage.TemperatureBitmapData
import com.common.thermalimage.TemperatureData
import com.example.baseproject.util.thermal.ThermalDevice
import com.example.baseproject.util.thermal.ThermalListener
import org.json.JSONObject

class F10Device : ThermalDevice() {

    private var temperatureUtil: com.common.thermalimage.ThermalImageUtil? = null
    override fun getData(thermalListener: ThermalListener?) {
        if (this.temperatureUtil?.usingModule == null || this.temperatureUtil?.usingModule?.size == 0) {
            thermalListener?.onDone("Start Get Data Error", null, null)
            return
        }
        val distance = 50.0f
        Thread {
            var text = ""
            var temperatureBitmapData: TemperatureBitmapData? = null
            var temperatureData: TemperatureData? = null
            try {
                temperatureData = temperatureUtil?.getDataAndBitmap(distance,
                    true,
                    object : HotImageCallback.Stub() {
                        override fun onTemperatureFail(e: String) {
                            try {
                                val jsonErrorInfo = JSONObject(e)
                                text += "[" + jsonErrorInfo.get("errCode") + "] " +
                                        jsonErrorInfo.get("err")
                            } catch (e: Exception) {
                                text = "ERROR"
                            }
                        }

                        override fun getTemperatureBimapData(data: TemperatureBitmapData) {
                            temperatureBitmapData = data
                        }
                    }
                )
            } catch (e: Exception) {
                text = "ERROR"
            }
            thermalListener?.onDone(text, temperatureBitmapData, temperatureData)
        }.start()
    }

    override fun release() {
        temperatureUtil?.release()
    }

    override fun init(context: Context) {
        temperatureUtil = com.common.thermalimage.ThermalImageUtil(context)
        this.getData(null)
    }


}