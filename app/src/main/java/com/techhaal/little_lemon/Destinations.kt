package com.techhaal.little_lemon

interface Destinations{
    val home: String
    val profile: String
    val onBoarding: String
}

object MyDestinations : Destinations {
    override val home = "home"
    override val profile = "profile"
    override val onBoarding = "onBoarding"
}

