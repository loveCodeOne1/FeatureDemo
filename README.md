# FeatureDemo
设计理念：
该项目希望以adb的形式发送指令仿车辆信号来进行各种调试功能，故需要设计成观察者模式

```
activity
adb shell am start -a <Action名>
adb shell am start -a android.intent.action.VIEW -d http://www.example.com

adb shell am start -n <包名>/<Activity全类名> -e <key> <value>
adb shell am start -n com.example.app/.MainActivity -e userId 123

service
adb shell am startservice -n com.example.app/.MyService
adb shell am startservice -n <包名>/<Service全类名> --es <key> <value>带参数
停止service
adb shell am stopservice -n <包名>/<Service全类名>

broadcastreceiver

adb shell am broadcast -a <Action名> --es <key> <value>
发送给指定 BroadcastReceiver 的广播。
adb shell am broadcast -n <包名>/<Receiver全类名> -a <Action名>
```

test commit2 2025.09.17
test commit3_a 2025.09.17
test commit4 2025.09.17