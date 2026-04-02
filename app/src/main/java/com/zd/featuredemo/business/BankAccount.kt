package com.zd.featuredemo.business


class BankAccount {


    private var money = 1000f

    @Synchronized
    fun cun (m:Float){
        println("开始存款 m:$m")
            Thread.sleep(10000)
            money += m
        println("存款完成 余额$money")

    }
    @Synchronized
    fun qu (m:Float){
        println("开始取款 m:$m")
        Thread.sleep(2000)
        money -= m
        println("取款完成 余额$money")
    }
    fun getMoney():String{
        return synchronized(this){
          "0f"
        }
    }



}