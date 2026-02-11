package com.zd.featuredemo

object Test {

    @JvmStatic
    fun main(args: Array<String>) {
        val requestString = "requestId|true|0|1"
        val requestString2 = "requestId|true|0|1||"

        val split = requestString.split("|")
        val split2 = requestString2.split("|")
        val result = split.drop(1).dropLastWhile { it.isEmpty() }
        val result2 = split2.drop(1).dropLastWhile { it.isEmpty() }


    }
}