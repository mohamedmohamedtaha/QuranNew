package com.mohamedtaha.imagine.mvp.model

class ImageModel {
    constructor()
    var nameSora: String? = null
    var urlImage = 0
    var typeSora: String? = null
        private set
    var soraLink: String? = null
    var nameShekh: String? = null
    var position = 0
    var isSelected = false

    constructor(nameShekh: String?, url_image: Int, type_sora: String?, position: Int) {
        this.nameShekh = nameShekh
        this.urlImage = url_image
        this.typeSora = type_sora
        this.position = position
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }


}