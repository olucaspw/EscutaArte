package com.example.escutaarte.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Handler
import android.view.MotionEvent
import android.view.View

// MultiClickActions is a class that allows you to set multiple actions for a single view
class MultiClickActions(
    private val onSingleClick: () -> Unit,
    private val onDoubleClick: () -> Unit,
    private val onTripleClick: () -> Unit,
    private val onHold: () -> Unit
) : View.OnTouchListener {

    companion object {

        const val clickDelay: Long = 500 // How long in a click event (500ms)
        const val holdDelay: Long = 1000 // How long to hold to trigger the hold action (1s)
    }

    private var clickCount: Int = 0
    private var isHolding: Boolean = false
    private var isLongClick: Boolean = false

    private val handler = Handler()

    // This is the function that will be called when the user interacts with the view
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // If the user is holding the view, the onHold function will be called
                isHolding = true
                handler.postDelayed({
                    if (isHolding) {
                        // If the user is holding the view for holdDelay [1s], the onHold function will be called
                        onHold()
                        isLongClick = true
                    }
                }, holdDelay)
            }
            MotionEvent.ACTION_UP -> {
                // When the user releases the view
                isHolding = false
                if (isLongClick){
                    // Solves the problem of calling the onSingleClick function after the onHold function
                    isLongClick = false
                    return true
                }
                clickCount++
                handler.postDelayed({
                    when(clickCount) {
                        1 -> {
                            onSingleClick()
                            clickCount=0 // Reset the click count
                        }
                        2 -> {
                            onDoubleClick()
                            clickCount=0 // Reset the click count
                        }
                        3 -> {
                            onTripleClick()
                            clickCount=0 // Reset the click count
                        }
                        else -> {
                            //Toast.makeText(v.context, v.getActivity().toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }, clickDelay)


            }
        }
        return true // Return true to keep listening to the touch event
    }
}

inline fun View.setOnMultiClickActions(
    noinline onSingleClick: () -> Unit,
    noinline onDoubleClick: () -> Unit,
    noinline onTripleClick: () -> Unit,
    noinline onHold: () -> Unit
) {
    val listener = MultiClickActions(onSingleClick, onDoubleClick, onTripleClick, onHold)
    setOnTouchListener(listener)
}

fun View.getActivity(): Activity? {
    var context: Context = this.context
    while (context is ContextWrapper) {
        if (context is Activity) {
            return context
        }
        context = context.baseContext
    }
    return null
}