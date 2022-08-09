package androider.kso.walletconnectapp.viewmodels

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androider.kso.walletconnectapp.utils.BridgeServer
import androidx.lifecycle.ViewModel
import com.squareup.moshi.Moshi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.OkHttpClient
import org.komputing.khex.extensions.toHexString
import org.walletconnect.Session
import org.walletconnect.impls.MoshiPayloadAdapter
import org.walletconnect.impls.OkHttpTransport
import org.walletconnect.impls.WCSession
import org.walletconnect.impls.WCSessionStore
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val moshi: Moshi,
    private val client: OkHttpClient,
    private val storage: WCSessionStore,
) : ViewModel() {

    private val tag: String = "MainPageViewModel"

    var userWallet = MutableStateFlow("")
        private set
    private var config: Session.Config? = null
    private var session: Session? = null
    private var bridge: BridgeServer? = null
    private var activeCallback: Session.Callback? = null

    init {
        initBridge()
    }

    private fun initBridge() {
        bridge = BridgeServer(moshi).apply {
            onStart()
        }
    }

    private fun resetSession() {
        session?.clearCallbacks()
        val key = ByteArray(32).also { Random().nextBytes(it) }.toHexString(prefix = "")
        config =
            Session.Config(UUID.randomUUID().toString(), "https://bridge.walletconnect.org", key)
        session = WCSession(
            config ?: return,
            MoshiPayloadAdapter(moshi),
            storage,
            OkHttpTransport.Builder(client, moshi),
            Session.PeerMeta(name = "WalletConnectApp")
        )
        session?.offer()
    }


    fun connectWallet(context: Context) {
        resetSession()
        activeCallback = object : Session.Callback {
            override fun onMethodCall(call: Session.MethodCall) = Unit

            override fun onStatus(status: Session.Status) {
                status.handleStatus()
            }
        }
        session?.addCallback(activeCallback ?: return)
        context.startActivity(Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(config?.toWCUri() ?: return)
        })
    }

    fun Session.Status.handleStatus() {
        when (this) {
            Session.Status.Approved -> sessionApproved()
            Session.Status.Closed -> sessionClosed()
            Session.Status.Connected,
            Session.Status.Disconnected,
            is Session.Status.Error,
            -> Log.e(tag, "WC Session Status handleStatus: $this", )
        }
    }

    private fun sessionApproved() {
        val address = session?.approvedAccounts()?.firstOrNull() ?: return
        /* Provider name*/
        // val walletType = session?.peerMeta()?.name ?: ""
        userWallet.value = address
    }

    private fun sessionClosed() {

    }
}