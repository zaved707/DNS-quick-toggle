package com.zavedahmad.dnstoggle.quickTile


import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import android.util.Log
import android.widget.Toast
import com.zavedahmad.dnstoggle.ui.utilities.hasWriteSecureSettingsPermission
import com.zavedahmad.dnstoggle.ui.utilities.setPrivateDNS
import com.zavedahmad.dnstoggle.ui.utilities.turnOffPrivateDns


class QSTileService: TileService() {
    var dnsActive=false
    override fun onClick() {
        super.onClick()
        if(hasWriteSecureSettingsPermission(this)) {
            if (!dnsActive) {

                setPrivateDNS(this, "dns.adguard.com")
                qsTile.state = Tile.STATE_ACTIVE


            } else {
                turnOffPrivateDns(this)
                qsTile.state = Tile.STATE_INACTIVE
            }
        }
        else {
            Log.e("DNS_Setting", "Failed  dnstoggle: ")
            Toast.makeText(this@QSTileService, "You Don't have permissions ", Toast.LENGTH_LONG).show()
        }

        dnsActive=!dnsActive

        qsTile.updateTile()
    }
}