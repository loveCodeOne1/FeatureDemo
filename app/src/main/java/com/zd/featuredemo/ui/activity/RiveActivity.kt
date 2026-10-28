package com.zd.featuredemo.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.databinding.DataBindingUtil
import app.rive.Result
import app.rive.Rive
import app.rive.RiveFile
import app.rive.RiveFileSource
import app.rive.RiveLog
import app.rive.ViewModelInstance
import app.rive.ViewModelSource
import app.rive.core.ArtboardHandle
import app.rive.core.RiveWorker
import app.rive.rememberArtboard
import app.rive.rememberRiveFile
import app.rive.rememberRiveWorkerOrNull
import app.rive.rememberStateMachine
import app.rive.rememberViewModelInstance
import com.zd.featuredemo.R
import com.zd.featuredemo.bean.RiveBean
import com.zd.featuredemo.databinding.ActivityRiveBinding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.zd.featuredemo.viewmodel.RiveViewModel

class RiveActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityRiveBinding
    var mList = mutableListOf<RiveBean>()
    val mVM: RiveViewModel by viewModels()
    var mBound = false
    companion object{
        val TAG: String = "RiveActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate")
        enableEdgeToEdge()
//        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
//        windowInsetsController.hide(WindowInsetsCompat.Type.statusBars())
        supportActionBar?.hide()
        window.decorView.post {
            window.insetsController?.apply {
                hide(WindowInsets.Type.navigationBars())

                systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
        mBinding  = DataBindingUtil.setContentView(this,R.layout.activity_rive)
        RiveLog.logger = RiveLog.LogcatLogger()

        mBinding.composeView.setContent {
            var leftVmInstance by remember { mutableStateOf<ViewModelInstance?>(null) }
            val beans by mVM.beans.collectAsState()

            Column(modifier = Modifier
                .fillMaxSize()
                .padding(top = 300.dp, start = 20.dp, end = 20.dp)) {
                Box(modifier = Modifier.height(500.dp)){
                    SetupTestRive(
                        riveResId = R.raw.dsegment_sentry_dhd_base001,
                        viewModelName = "SentryVM",
                        onViewModelReady = { vm -> leftVmInstance = vm }
                    )
                }
                RiveList(
                    beans = beans,
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.LightGray)
                        .padding(start = 50.dp)
                ){ bean, value ->
                    // leftVmInstance 就在这个作用域里，可以直接访问
                    when (bean.type) {
                        "NUMBER" -> value.toFloatOrNull()?.let {
                            leftVmInstance?.setNumber(bean.property, it.toFloat())
                        }
                        "STRING" -> leftVmInstance?.setString(bean.property, value)
                        "BOOLEAN" -> value.toBooleanStrictOrNull()?.let {
                            leftVmInstance?.setBoolean(bean.property, it)
                        }
                        "COLOR" -> { /* 按 Rive 的 Color API 处理 */ }
                        else -> Log.w(TAG, "unsupported type: ${bean.type}")
                    }
//                    mVM.notify(property,value)
//                    leftVmInstance
                }
            }


            val hour:Int? = 12
            val minutes:Int? = 30
//            leftVmInstance?.setNumber("in_out",1F)
//            leftVmInstance?.setNumber("themes",2F)
            /*
                0 -> empty schedule|error
                1 -> RemainTime
                2 -> Complete
                3 -> N/A ini value
                4 -> battery progress
             */
//            leftVmInstance?.setNumber("right_message",4F)
//            leftVmInstance?.setString("AM-PM","AAA")
//            leftVmInstance?.setString("schedule_time","Schedule time")
//            leftVmInstance?.setString("schedule_charge_time_text","Charging will start at 4:05 PM")
//            leftVmInstance?.setNumber("timer_hour",hour?.toFloat() ?: 00f)
//            leftVmInstance?.setNumber("timer_minute",minutes?.toFloat() ?: 00f)
//            leftVmInstance?.setString("battery_timer_text","REMAINTIME")
//            leftVmInstance?.setString("complete_text","COMPLETE")
//            leftVmInstance?.setString("battery_capacity_text","Battery_capacity")
//            leftVmInstance?.setString("schedule_charge_time_text","Schedule_charge")

        }
    }
    private fun defaultInput(bean: RiveBean): String = when (bean.property) {
        "in_out" -> "1"          // 特定属性优先
        "themes" -> "2"
        else -> when (bean.type) {   // 其余按类型给默认值
            "NUMBER" -> "2"           // 数字默认 2F
            "BOOLEAN" -> "false"      // 布尔默认 false
            "STRING" -> ""
            "COLOR" -> ""
            else -> ""
        }
    }
    @Composable
    fun RiveList(beans:List<RiveBean>,
                 modifier: Modifier,
                 onConfirm: (bean: RiveBean, value: String) -> Unit){
        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(beans){ bean->
                Log.d(TAG,"RiveList bean ${bean.property}")
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically){
                    Text(
                        text = bean.property,
                        color = Color.Black,
                        fontSize = 30.sp,
                        modifier = Modifier
                            .background(Color.White.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                            .weight(1.5f)    ,
                    )
                    Text(
                        text = bean.type,
                        color = Color.Black,
                        fontSize = 30.sp,
                        modifier = Modifier
                            .background(Color.White.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                            .weight(1f),
                    )
//                    var input by remember { mutableStateOf("") }

                    var input by remember(bean.property) {
                        mutableStateOf(defaultInput(bean))
                    }
                    OutlinedTextField(
                        value = input,
                        onValueChange = { input = it },
                        modifier = Modifier.weight(4f),
                        singleLine = true,
                        textStyle = TextStyle(fontSize = 30.sp)
                    )
                    // 确认按钮：固定宽度
                    Button(
                        onClick = {
                            // 处理确认逻辑，比如把 input 写回 Rive
                            Log.d(TAG, "confirm ${bean.property} = $input")
//                            mVM.notify(bean.property,)
                            onConfirm(bean,input)
                        },
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(0.dp)   // 窄按钮，去掉默认大内边距
                    ) {
                        Text(text = "确认", fontSize = 30.sp)
                    }

                }
            }
        }
    }

    @Composable
    fun getArtboardFirstName(file: RiveFile): String? {
        val artboardNames by produceState(emptyList(), file) {
            value = file.getArtboardNames()
        }
        return artboardNames.firstOrNull().let {
            Log.d(TAG,"artboardFirstName = $it")
            it
        }
    }
    @Composable
    fun getStateMachineName(riveWorker: RiveWorker,ar: ArtboardHandle): String? {
        val stateMachineNames by produceState(emptyList(), riveWorker) {
            value = riveWorker.getStateMachineNames(ar)
        }
        return stateMachineNames.firstOrNull().let {
            Log.d(TAG,"getStateMachineFirstName = $it")
            it
        }
    }
    @Composable
    private fun OutsideCarChargingScreen(){
        var rightVmInstance by remember { mutableStateOf<ViewModelInstance?>(null) }
    }

    @Composable
    private fun SetupTestRive(
        riveResId : Int,
        viewModelName: String,
        onViewModelReady: (ViewModelInstance) -> Unit){
        val errorState = remember { mutableStateOf<Throwable?>(null) }
        val riveWorker = rememberRiveWorkerOrNull(errorState)
        if (riveWorker == null) {
            Log.d(TAG, "RiveWorker is null for $viewModelName")
            return
        }
        val riveFile = rememberRiveFile(
            source = RiveFileSource.RawRes.from(riveResId),
            riveWorker
        )
        when (riveFile) {
            is app.rive.Result.Loading -> {
                Log.e(TAG, "SetupLeftRive Rive loading: ")
            }
            is app.rive.Result.Error -> {
                Log.e(TAG, "SetupLeftRive Rive load error: ${riveFile.throwable}")
            }
            is Result.Success -> {
                Log.d(TAG,"SetupLeftRive Success")

                val file = riveFile.value

                val artboard = rememberArtboard(file, getArtboardFirstName(file))

                if (artboard == null) {
                    Log.e(TAG, "Artboard not found")
                    return
                }
                LaunchedEffect(Unit) {

                    Log.d(TAG,"viewmode NAme = ${file.getDefaultViewModelInfo(artboard).viewModelName}")
//                    file.getViewModelProperties(viewModelName).forEach {
//                        Log.d(TAG,"Rive parmeter name=${it.name}, type = ${it.type}")
//                        val title = "${it.name}--${it.type}"
//                        mVM.loadData()
//                    }
                    val params: MutableList<RiveBean> = file.getViewModelProperties(viewModelName)
                        .map { RiveBean(it.name,"${it.type}") }
                        .toMutableList()
                    Log.d(TAG,"load success")
                    mVM.loadData(params)
                }
                val stateMachine = rememberStateMachine(artboard, getStateMachineName(riveWorker,artboard.artboardHandle))

                val vmiSource = ViewModelSource.Named(viewModelName).defaultInstance()
                val leftVMI = rememberViewModelInstance(file, vmiSource)
                onViewModelReady(leftVMI)
                Rive(
                    file = file,
                    artboard = artboard,
                    stateMachine = stateMachine,
                    playing = true,
                    viewModelInstance = leftVMI,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}



