package junjange.dev.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.browser.window
import kotlinx.coroutines.await
import org.jetbrains.compose.resources.decodeToImageBitmap
import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Int8Array
import org.khronos.webgl.get
import org.w3c.fetch.Response

@Composable
fun rememberRemoteImage(url: String): ImageBitmap? {
    var image by remember(url) { mutableStateOf<ImageBitmap?>(null) }
    LaunchedEffect(url) {
        image = runCatching { fetchImage(url) }.getOrNull()
    }
    return image
}

private suspend fun fetchImage(url: String): ImageBitmap {
    val response = window.fetch(url).await<Response>()
    check(response.ok)
    val buffer = Int8Array(response.arrayBuffer().await<ArrayBuffer>())
    return ByteArray(buffer.length) { buffer[it] }.decodeToImageBitmap()
}
