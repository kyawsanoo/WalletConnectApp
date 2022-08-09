package androider.kso.walletconnectapp.utils

import androider.kso.walletconnectapp.ui.theme.Pink
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle


fun getCreateWalletText(
    index: Int,
) = when (index) {
    1 -> buildAnnotatedString {
        // First Step
        append("Click on the ")
        withStyle(SpanStyle(color = Pink)) {
            append("“Get Started”")
        }
        append(" button.")
    }
    2 -> buildAnnotatedString {
        // Second Step
        append("If you already have a account select ")
        withStyle(SpanStyle(color = Pink)) {
            append("“Import using Secret Recovery Phrase”")
        }
        append(" if you don't, select ")
        withStyle(SpanStyle(color = Pink)) {
            append("“Create a Wallet”")
        }
        append(" and after that create a ")
        withStyle(SpanStyle(color = Pink)) {
            append("“password”")
        }
    }
    3 -> buildAnnotatedString {
        append("Write down, store, or memorize your ")
        withStyle(SpanStyle(color = Pink)) {
            append("Secret Backup Phrase")
        }
    }
    4 -> buildAnnotatedString {
        withStyle(SpanStyle(color = Pink)) {
            append("Confirm your Secret Backup Phrase")
        }
        append(" to ensure you have it correct")
    }
    else -> buildAnnotatedString { }
}