package com.egco428.practice

/**
 * Created by WIN10 on 5/12/2560.
 */
class User (val id: String, val username: String, val password: String, val lat: Double, val lon: Double) {
    constructor(): this("","","",0.0,0.0)
}