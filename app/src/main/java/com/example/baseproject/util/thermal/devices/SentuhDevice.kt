package com.example.baseproject.util.thermal.devices

import android.content.Context
import com.example.baseproject.util.thermal.ThermalDevice
import com.example.baseproject.util.thermal.ThermalListener
import java.io.FileInputStream

class SentuhDevice : ThermalDevice() {
    private var temperatureBitmapData : com.common.thermalimage.TemperatureBitmapData? = null
    private var temperatureData : com.common.thermalimage.TemperatureData? = null
    override fun getData(thermalListener: ThermalListener?) {
        var buff = ByteArray(10 )
        var mInputStream = FileInputStream("dev/ttyS1")
        mInputStream.read(buff)
        var tempHex = "%02x".format(buff[4])+"%02x".format(buff[5])
        var tempDec = Integer.parseInt(tempHex, 16)
        var temp = tempDec/100
        temperatureData?.temperature = temp.toFloat();
        thermalListener?.onDone("", temperatureBitmapData, temperatureData)
    }

    override fun release() {

    }

    override fun init(context: Context) {
        temperatureData = com.common.thermalimage.TemperatureData()
    }

}