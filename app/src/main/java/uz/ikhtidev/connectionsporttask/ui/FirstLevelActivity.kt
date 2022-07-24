package uz.ikhtidev.connectionsporttask.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import uz.ikhtidev.connectionsporttask.R
import uz.ikhtidev.connectionsporttask.databinding.ActivityFirstLevelBinding
import uz.ikhtidev.connectionsporttask.model.MyButton

class FirstLevelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstLevelBinding

    private val myButtonsList: ArrayList<MyButton> = ArrayList()
    val newButtonList: ArrayList<Int> = ArrayList()
    private lateinit var firstButton: MyButton
    var attemptsNumber:Int = 0

    @SuppressLint("ClickableViewAccessibility", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstLevelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createButtons()

        binding.btnLevel.setOnClickListener { finish() }

    }

    @SuppressLint("ClickableViewAccessibility", "UseCompatLoadingForDrawables")
    override fun onResume() {
        super.onResume()
        binding.touchview.setOnTouchListener { _: View, event: MotionEvent ->

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {

                    val pointStart = Point(event.x.toInt(), event.y.toInt())
                    for (i in 0 until binding.touchview.childCount) {
                        val current: View = binding.touchview.getChildAt(i)
                        if (current is ImageView) {
                            val b: ImageView = current

                            if (isPointWithin(pointStart, b)) {

                                firstButton = myButtonsList[i]

                            }
                        }
                    }
                }
                MotionEvent.ACTION_UP -> {

                    val pointEnd = Point(event.x.toInt(), event.y.toInt())
                    for (i in 0 until binding.touchview.childCount) {
                        val current: View = binding.touchview.getChildAt(i)
                        if (current is ImageView) {
                            val b: ImageView = current

                            if (isPointWithin(pointEnd, b)) {
                                if (myButtonsList[i].image != firstButton.image) {
                                    for (number in newButtonList){
                                        myButtonsList[number].status = 0
                                        if(myButtonsList[number].image != 1 && myButtonsList[number].image != 2 && myButtonsList[number].image != 3)
                                            myButtonsList[number].image = 0
                                    }

                                } else {
                                    attemptsNumber+=1
                                    if (attemptsNumber == 3){
                                        var freeButtonCount = 0
                                        for (button in myButtonsList){
                                            if (button.image == 0) freeButtonCount+=1
                                        }
                                        if (freeButtonCount == 0) {
                                            startActivity(Intent(this, SuccessActivity::class.java))
                                            finish()
                                        }
                                        else startVibrator(600)
                                    }
                                }
                                newButtonList.clear()
                                setViews(myButtonsList)
                            }
                        }
                    }

                }
                MotionEvent.ACTION_MOVE -> {
                    val point = Point(event.x.toInt(), event.y.toInt())
                    for (i in 0 until binding.touchview.childCount) {
                        val current: View = binding.touchview.getChildAt(i)
                        if (current is ImageView) {
                            val button: ImageView = current
                            if (isPointWithin(point, button)) {
                                if (firstButton.image == 1 || firstButton.image == 2 || firstButton.image == 3) {
                                    if (myButtonsList[i].status == 0) {
                                        for (oneButton in myButtonsList) {
                                            if (oneButton.status == 1)
                                                oneButton.status = 2
                                        }
                                        myButtonsList[i].status = 1
                                        newButtonList.add(i)
                                    } else if (myButtonsList[i].status == 1) {
                                        // jarayonda


                                        if (myButtonsList[i].image == 0) {
                                            when (firstButton.image) {
                                                1 -> myButtonsList[i].image = 4
                                                2 -> myButtonsList[i].image = 5
                                                else -> myButtonsList[i].image = 6  // 6
                                            }

                                        }
                                        else {
                                            // break finger, start Vibrator
                                        }
                                    } else {
                                        // status = 2

                                        startVibrator(200)
                                    }
                                    setViews(myButtonsList)
                                }
                            }
                        }
                    }


                }
            }
            true
        }
    }

    private fun startVibrator(milliSeconds: Long) {
        val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(milliSeconds, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(milliSeconds)
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setViews(buttonList: ArrayList<MyButton>) {
        for (i in 0 until binding.touchview.childCount) {
            val current: View = binding.touchview.getChildAt(i)

            if (current is ImageView) {
                val button: ImageView = current
                val currentOfList: MyButton = buttonList[i]
                when (currentOfList.image) {
                    1 -> button.setImageDrawable(resources.getDrawable(R.drawable.img1))
                    2 -> button.setImageDrawable(resources.getDrawable(R.drawable.img2))
                    3 -> button.setImageDrawable(resources.getDrawable(R.drawable.img3))
                    4 -> button.setImageDrawable(resources.getDrawable(R.drawable.img4))
                    5 -> button.setImageDrawable(resources.getDrawable(R.drawable.img5))
                    6 -> button.setImageDrawable(resources.getDrawable(R.drawable.img6))
                    else -> button.setImageDrawable(resources.getDrawable(R.drawable.img0)) // 0
                }
            }
        }
    }

    private fun createButtons() {
        myButtonsList.add(MyButton(0, 2))   //0
        myButtonsList.add(MyButton(0, 0))    //1
        myButtonsList.add(MyButton(0, 0))    //2
        myButtonsList.add(MyButton(0, 0))   //3
        myButtonsList.add(MyButton(0, 0))   //4
        myButtonsList.add(MyButton(0, 0))   //5
        myButtonsList.add(MyButton(0, 0))   //6
        myButtonsList.add(MyButton(0, 0))   //7
        myButtonsList.add(MyButton(0, 0))   //8
        myButtonsList.add(MyButton(0, 2))   //9
        myButtonsList.add(MyButton(0, 0))   //10
        myButtonsList.add(MyButton(0, 0))   //11
        myButtonsList.add(MyButton(0, 0))   //12
        myButtonsList.add(MyButton(0, 0))   //13
        myButtonsList.add(MyButton(0, 3))   //14
        myButtonsList.add(MyButton(0, 0))   //15
        myButtonsList.add(MyButton(0, 0))   //16
        myButtonsList.add(MyButton(0, 0))   //17
        myButtonsList.add(MyButton(0, 0))   //18
        myButtonsList.add(MyButton(0, 3))   //19
        myButtonsList.add(MyButton(0, 1))   //20
        myButtonsList.add(MyButton(0, 0))   //21
        myButtonsList.add(MyButton(0, 0))   //22
        myButtonsList.add(MyButton(0, 0))   //23
        myButtonsList.add(MyButton(0, 1))   //24

        setViews(myButtonsList)
    }

    private fun isPointWithin(point: Point, button: ImageView): Boolean {
        val x = point.x
        val y = point.y
        val x1 = button.left
        val x2 = button.right
        val y1 = button.top
        val y2 = button.bottom
        return ((x in x1..x2) && (y in y1..y2))
    }

}