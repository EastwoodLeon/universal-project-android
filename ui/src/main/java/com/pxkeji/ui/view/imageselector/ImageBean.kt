package com.pxkeji.ui.view.imageselector

class ImageBean {

    companion object {
        enum class ImageType {
            LOCAL, REMOTE
        }
    }

    var imageType = ImageType.LOCAL

    /**
     * 本地路径
     */
    var path: String = ""

    /**
     * 网上路径
     */
    var remotePath: String = ""
}