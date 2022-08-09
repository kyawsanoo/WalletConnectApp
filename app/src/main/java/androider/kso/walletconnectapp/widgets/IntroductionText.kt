package androider.kso.walletconnectapp.widgets

import androider.kso.walletconnectapp.ui.theme.LightPink
import androider.kso.walletconnectapp.ui.theme.Purple
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


fun LazyListScope.introductionText() {
    item {
        Text(text = "Follow below steps to connect your MetaMask Wallet",
            style = MaterialTheme.typography.subtitle2.copy(color = Purple, lineHeight = 18.sp),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp))
                .border(2.dp, LightPink, RoundedCornerShape(15.dp))
                .background(
                    Color.White)
                .padding(15.dp))
    }
}