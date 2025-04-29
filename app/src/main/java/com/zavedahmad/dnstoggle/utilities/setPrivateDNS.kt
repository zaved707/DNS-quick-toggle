package com.zavedahmad.dnstoggle.ui.utilities

import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat

fun setPrivateDNS(context: Context,dnsAddressDomain: String) {
    try {

            val resolver: ContentResolver = context.contentResolver
            Log.d("DNS_Setting", "Attempting to set private DNS")
            // Enable Private DNS (DoT)
            Settings.Global.putString(
                resolver,
                "private_dns_mode",
                "hostname"
            ) // 2 = strict hostname mode (DoT)
            Log.d("DNS_Setting", "private_dns_mode set to hostname")
            // Set AdGuard DNS hostname
            Settings.Global.putString(resolver, "private_dns_specifier", dnsAddressDomain)
            Log.d("DNS_Setting", "DNS set to $dnsAddressDomain")
            // Verify settings
            val currentMode = Settings.Global.getInt(resolver, "private_dns_mode", 0)
            val currentSpecifier = Settings.Global.getString(resolver, "private_dns_specifier")
            Log.d("DNS_Setting", "Current DNS mode: $currentMode, Specifier: $currentSpecifier")
            Toast.makeText(context, "DNS set to $dnsAddressDomain", Toast.LENGTH_SHORT).show()

    } catch (e: SecurityException) {
        Log.e("DNS_Setting", "Failed to set private DNS: ${e.message}")
        Toast.makeText(context, "Failed to set private DNS: ${e.message}", Toast.LENGTH_LONG).show()


    }
}

fun  turnOffPrivateDns(context: Context){
    try {
        val resolver: ContentResolver = context.contentResolver
        Log.d("DNS_Setting", "Attempting to set private DNS")
        // Enable Private DNS (DoT)
        Settings.Global.putString(
            resolver,
            "private_dns_mode",
            "off"
        ) // 2 = strict hostname mode (DoT)
        Log.d("DNS_Setting", "private_dns_mode set to 0")
        // Set AdGuard DNS hostname
        Toast.makeText(context, "DNS is turned off", Toast.LENGTH_SHORT).show()
        // Verify settings

    }catch (e: SecurityException) {
        Log.e("DNS_Setting", "Failed to set private DNS: ${e.message}")
        Toast.makeText(context, "Failed to set private DNS: ${e.message}", Toast.LENGTH_LONG).show()


    }

}
fun hasWriteSecureSettingsPermission(context: Context): Boolean {
    val granted = ContextCompat.checkSelfPermission(
        context,
        "android.permission.WRITE_SECURE_SETTINGS"
    ) == PackageManager.PERMISSION_GRANTED
    Log.d("DNS_Setting", "WRITE_SECURE_SETTINGS granted: $granted")
    // Toast.makeText(context, "permission granted"+granted.toString() ,Toast.LENGTH_SHORT).show()
    return granted
}