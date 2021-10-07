package com.example.randomfacts.model

import android.accounts.AuthenticatorDescription
import java.util.prefs.PreferencesFactory


data class DataModel(
    var description: String? = null,
    var fact: String? = null,
    var id: String? = null,
    var isPremium: Int? = 0,
    var last_update: String? = null,
    var source: String? = null,
    var title: String? = null,
    var topic: String? = null,
    var topic_id: String? = null,
    )