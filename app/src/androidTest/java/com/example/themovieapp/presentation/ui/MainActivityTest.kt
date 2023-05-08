package com.example.themovieapp.presentation.ui


import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.Test

@RunWith(AndroidJUnit4::class)

class MainActivityTest{

    @get:Rule var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testMainActivityLaunch(){
        activityScenarioRule.scenario.onActivity {

        }
    }

    /*@Rule
    @JvmField
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testMainActivityLaunch(){
        ActivityScenario.launch(MainActivity::class.java)
    }*/
}