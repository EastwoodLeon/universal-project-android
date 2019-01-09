package com.pxkeji.ui.view.imageselector

import java.io.File

class ImageBean {

    companion object {
        enum class ImageType {
            LOCAL, REMOTE
        }
    }

    var imageType = ImageType.LOCAL
    var path: String = ""
}