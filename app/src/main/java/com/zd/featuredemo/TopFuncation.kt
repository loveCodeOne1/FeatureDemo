import android.app.Activity
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun <T : ViewDataBinding> Activity.bindView(@LayoutRes id:Int):T {
    return DataBindingUtil.setContentView<T>(this,id)
}